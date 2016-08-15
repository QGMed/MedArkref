package MetamapStuff;

import gov.nih.nlm.nls.metamap.*;

import java.util.List;

/**
 * Created by davidsilin on 4/7/16.
 */
public class ShowMetamap {
    public static void main(String[] args) throws Exception{
       // Servers.initializeAPIs();
       // MetaMapWrapper mmw = Servers.getMMW(0);
        String in = "the chest radiograph";
        //List<Result> resultList = mmw.parseString(in);
        //System.out.println(resultList.size());
        //Result result = resultList.get(0);
        //List<AcronymsAbbrevs> aaList = result.getAcronymsAbbrevs();
//        if (aaList.size() > 0) {
//            System.out.println("Acronyms and Abbreviations:");
//            for (AcronymsAbbrevs e : aaList) {
//                System.out.println("Acronym: " + e.getAcronym());
//                System.out.println("Expansion: " + e.getExpansion());
//                System.out.println("Count list: " + e.getCountList());
//                System.out.println("CUI list: " + e.getCUIList());
//            }
//        } else {
//            System.out.println(" None.");
//        }
//
//        for (Utterance utterance : result.getUtteranceList()) {
//            System.out.println("Utterance:");
//            System.out.println(" Id: " + utterance.getId());
//            System.out.println(" Utterance text: " + utterance.getString());
//            System.out.println(" Position: " + utterance.getPosition());
//            for (PCM pcm: utterance.getPCMList()) {
//                System.out.println("Phrase:");
//                System.out.println(" text: " + pcm.getPhrase().getPhraseText());
//                System.out.println("Mappings:");
//                for (Mapping map: pcm.getMappingList()) {
//                    System.out.println(" Map Score: " + map.getScore());
//                    for (Ev ev: map.getEvList()) {
//                        System.out.println(" Candidate:");
//                        System.out.println("  Score: " + ev.getScore());
//                        System.out.println("  Concept Id: " + ev.getConceptId());
//                        System.out.println("  Concept Name: " + ev.getConceptName());
//                        System.out.println("  Preferred Name: " + ev.getPreferredName());
//                        System.out.println("  Matched Words: " + ev.getMatchedWords());
//                        System.out.println("  Semantic Types: " + ev.getSemanticTypes());
//                        System.out.println("  MatchMap: " + ev.getMatchMap());
//                        System.out.println("  MatchMap alt. repr.: " + ev.getMatchMapList());
//                        System.out.println("  is Head?: " + ev.isHead());
//                        System.out.println("  is Overmatch?: " + ev.isOvermatch());
//                        System.out.println("  Sources: " + ev.getSources());
//                        System.out.println("  Positional Info: " + ev.getPositionalInfo());
//
//                    }
//                }
//            }
//        }
    }
}
