package MetamapStuff;


import gov.nih.nlm.nls.metamap.*;

import java.util.List;

/**
 * Created by davidsilin on 5/17/16.
 */
public class MetaMapWrapper{
    private MetaMapApi api;
    //String ip = "104.236.212.169"; //Dave's Server
    String ip = "104.196.124.169";  //GCloud Server
    String options = "-y";

    public MetaMapWrapper(){
      newMetaMapAPI();
    }

    public MetaMapWrapper(String ip){
        this.ip = ip;
        newMetaMapAPI();
    }

    private void newMetaMapAPI(){
        api = new MetaMapApiImpl(ip);
        api.setOptions(options);
    }

    /**
     * Sends string to String to metamap server to be parsed.
     * @param in
     * @return
     */
    public List<Result> parseString(String in){
        List<Result> ret = null;
        try {
            if (api == null) {
                System.out.println("Metamap not initialized");
                newMetaMapAPI();
            }
            ret = api.processCitationsFromString(in);
        }catch(Exception e){
            System.out.println("[ "+ip+" ] Crashed on: "+in);
            try {
                System.out.println("SLEEPING...");
                Thread.sleep(5000);
                api.disconnect();
                newMetaMapAPI();
                //Notes.makeTally("called new mmAPI");

                System.out.println("AWAKE.");
            } catch (InterruptedException t) {
                t.printStackTrace();
            }
        }
        return ret;
    }

    public void disconnect(){
        api.disconnect();
    }

}
