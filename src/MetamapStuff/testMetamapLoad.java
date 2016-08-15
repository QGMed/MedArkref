package MetamapStuff;

import gov.nih.nlm.nls.metamap.MetaMapApiImpl;
import gov.nih.nlm.nls.metamap.Result;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.List;
import java.util.Random;
import java.util.regex.Pattern;

/**
 * Created by davidsilin on 7/30/16.
 */
public class testMetamapLoad {
    static Random r;
    public static void main(String[] args) throws Exception{
        MetaMapApiImpl mm = new MetaMapApiImpl("104.236.212.169");
        mm.setOptions("-y");
        int counter = 0;
        String[] in = new String[0];
        Random r = new Random();
        FileReader fileReader = new FileReader("src/large_input");
        BufferedReader reader = new BufferedReader(fileReader);
        in = reader.readLine().split(Pattern.quote("."));
        while(true) {
            for (int i = 0; i < in.length; i++) {
                if (in[i].length() == 0) {
                    continue;
                }
                List<Result> resList = mm.processCitationsFromString(in[i] + ".");
                counter++;
                if (counter % 10 == 0) {
                    System.out.println("Counter: " + counter);
                    System.out.println(resList.get(0).getUtteranceList().get(0).getString());
                }
            }
        }
    }

    public static String generateRandomString(Random r){
        String a = "The Heart";

        for(int i = 0; i<8; i++){
            a+=" ";
            for (int j=0; j<5;j++){
                a = a+(char)('a' + r.nextInt(26));
            }
        }
        return a +".";
    }
}
