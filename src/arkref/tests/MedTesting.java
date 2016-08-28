package arkref.tests;

import gov.nih.nlm.nls.metamap.*;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by jason on 8/13/16.
 */
public class MedTesting {

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




}
