package arkref.tests;

import MetamapStuff.MetaMapWrapper;
import gov.nih.nlm.nls.metamap.*;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by jason on 8/13/16.
 */
public class MedTesting {
    static MetaMapWrapper mmw = new MetaMapWrapper("104.197.31.127");
    public static void main(String[] args) throws Exception{
        String test1 = "(Tuberculosis)/dsyn is terrible";
        String test2 = " A (disease-and-words)/dsyn blah blah blah";
        //System.out.println(getMMSense(input));

        snatchSubject(test1);
        snatchSubject(test2);

    }

    public static void snatchSubject(String line){
        Pattern p = Pattern.compile("(\\S+/\\S+)\\s");
        Matcher m = p.matcher(line+" ");
        while(m.find()){
            System.out.println("group 1:"+m.group(1));
        }
    }

    public static String getMMSense(String input) throws Exception {
        input = input.replaceAll("\\.","");
        String outputString = input;
        String copyString = input;
        List<Result> results = mmw.parseString(input);
        for(Result result: results) {
            for (Utterance utterance : result.getUtteranceList()) {
                for (PCM pcm : utterance.getPCMList()) {
                    String subject = pcm.getPhrase().getPhraseText();
                    for (Mapping map : pcm.getMappingList()) {
                        for (Ev ev : map.getEvList()) {
                            if(copyString.indexOf(subject)>-1) {
                                outputString = outputString.replaceAll(subject, "(" + subject + ")/" + ev.getSemanticTypes().get(0));
                                copyString = copyString.replaceAll(subject, "");
                            }
                        }
                    }
                }
            }
        }
        return outputString;
    }


}
