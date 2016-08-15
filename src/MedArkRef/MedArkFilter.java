package MedArkRef;


import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by jason on 8/15/16.
 */
public class MedArkFilter {
    public static HashMap<String,String> RNPCUIs = new HashMap<String,String>(); //replacable NP CUIS
    public static ArrayList<String> repSems = new ArrayList<>();
    public static void initialize(){
        try {
            BufferedReader br = new BufferedReader(new java.io.FileReader("config/MedArkConfig.config"));
            String line = br.readLine();
            while (line != null) {
                String[] rows = line.split(" ");
                String semType = rows[2];
                String CUI = rows[1];
                if(repSems.indexOf(semType)== -1){
                    repSems.add(semType);
                }
                RNPCUIs.put(CUI,semType);
                //System.out.println(rows[1]+":"+rows[2]);
                line = br.readLine();
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
