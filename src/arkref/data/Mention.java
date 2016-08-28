package arkref.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import MedArkRef.MedArkFilter;
import arkref.ace.AceDocument;
import arkref.analysis.Types;
import arkref.parsestuff.AnalysisUtilities;
import arkref.parsestuff.TregexPatternFactory;

import edu.stanford.nlp.trees.Tree;
import edu.stanford.nlp.trees.tregex.TregexPattern;
import edu.stanford.nlp.trees.tregex.tsurgeon.Tsurgeon;
import edu.stanford.nlp.trees.tregex.tsurgeon.TsurgeonPattern;
import edu.stanford.nlp.util.Pair;
import gov.nih.nlm.nls.metamap.*;

public class Mention implements Serializable{
	private static final long serialVersionUID = 3218834840031746390L;
	private Tree node;
	private Sentence sentence;
	private int id;
	public AceDocument.Mention aceMention;  // for convenience
	public boolean medRepNP = false;
	public String medNPSem = "null";
	public String nodeText = "null";
	
	public Mention(int id, Sentence sentence, Tree node, MetaMapApi api,String ip) {
		this.id=id;
		this.sentence=sentence;
		this.node=node;

		nodeText = node.yield().toString();
		getMMSense(api,nodeText, ip);
	}


	public void getMMSense(MetaMapApi api, String input,String ip) {
		String options = api.getOptions();
		input = input.replaceAll("\\.", "");
		try {
			List<Result> results = api.processCitationsFromString(input);
			for (Result result : results) {
				for (Utterance utterance : result.getUtteranceList()) {
					for (PCM pcm : utterance.getPCMList()) {
						for (Mapping map : pcm.getMappingList()) {
							for (Ev ev : map.getEvList()) {
								for(String semType: ev.getSemanticTypes()) {
									String subject = pcm.getPhrase().getPhraseText();
									String CUI = ev.getConceptId();
									if(MedArkFilter.repSems.indexOf(semType)>-1){
										medNPSem = semType;
										//System.out.println(subject+":"+CUI);
										if(MedArkFilter.RNPCUIs.containsKey(CUI)){
											medRepNP = true;
											//System.out.println("replacable: "+subject);
										}
									}
								}
							}
						}
					}
				}
			}
		}catch(Exception e){
			System.out.println("MM Rejected in correferencer");
			api.disconnect();
			api = new MetaMapApiImpl(ip);
			api.setOptions(options);
			//return input;
		}
		//return outputString;
	}

	public String neType() {
		// using head word strongly outperforms using right-most
		//List<Tree> leaves = node.getLeaves();
		//Tree rightmost = leaves.get(leaves.size()-1);
		//return sentence.neType(rightmost);
		if (node==null) {
			// TODO wrong!!!  can get from Word alignment
			return "O";
		}
		Tree head = node.headTerminal(AnalysisUtilities.getInstance().getHeadFinder()); 
		return sentence.neType(head);
	}
	
	public boolean isName() { return !neType().equals("O"); }
	
	public String toString() { 
		String g = safeToString(Types.gender(this));
		String n = safeToString(Types.number(this));
		String p = safeToString(Types.personhood(this));
		return String.format("M%-2d | S%-2d | %3s %2s %4s | %-12s | %s", id, sentence.ID(), 
				g, n, p, neType(), node); 
	}
	public String safeToString(Object o) {
		if (o==null) return "";
		return o.toString();
	}
	
	public int ID() {
		return id;
	}
	
	public Tree node() {
		return node;
	}
	
	public String getHeadWord(){
		Tree headTerminalNode = getHeadNode();
		if (headTerminalNode==null) {
			// TODO tricky: use the token span alignments and do guesswork if length>1.
			// for now, bailing...
			return "NO_HEAD_WORD";
		}
		return headTerminalNode.yield().toString();
		
	}
	
	
	public Tree getHeadNode(){
		if (node==null) {
			// TODO tricky: use the token span alignments and do guesswork if length>1.
			// for now, bailing...
			return null;
		}
		Tree res = node.headTerminal(AnalysisUtilities.getInstance().getHeadFinder());
		String yield = res.yield().toString();
		
		if(yield.equals("'s")){
			Tree copy = node.deeperCopy();
			List<Pair<TregexPattern, TsurgeonPattern>> ops = new ArrayList<Pair<TregexPattern, TsurgeonPattern>>();
			List<TsurgeonPattern> ps = new ArrayList<TsurgeonPattern>();
			TregexPattern matchPattern = TregexPatternFactory.getPattern("POS=pos");
			ps.add(Tsurgeon.parseOperation("prune pos"));
			TsurgeonPattern p = Tsurgeon.collectOperations(ps);
			ops.add(new Pair<TregexPattern,TsurgeonPattern>(matchPattern,p));
			Tsurgeon.processPatternsOnTree(ops, copy);

			res = copy.headTerminal(AnalysisUtilities.getInstance().getHeadFinder());
		}
		return res;
	}
	
	public Sentence getSentence() {
		return sentence;
	}

	public boolean hasSameHeadWord(Mention cand) {
		String head = getHeadWord();
		String candHead = cand.getHeadWord();
		
		return head.equalsIgnoreCase(candHead);
	}
	
}