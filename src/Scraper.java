import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.InputStream;
import java.net.URL;
import java.io.FileNotFoundException;

class Scraper {

    static String[] prefixes = new String[] 
    {"MC", "WJ", "SB", "BG", "BS", "WS", "AKHD", "LP", "QA", "GO", "PJ", "SWSB", "CH", "MP",
     "TP", "MO", "JP", "LLP", "P-W", "P-S", "MG", "SH", "SPS", "HP", "WO", "CDO", "MCS", "NEHW",
      "NEPP", "NESF", "OJW", "SG", "PPJ", "EOSG", "EOSJ", "EOSP", "SONH", "GCPH", "ACPH", "EBG", "EBS",
       "EBW", "EBE", "HI-G", "HI-S", "HI-W", "HI-F"};

    public static void main(String args[]) {
        try {
            new Scraper();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    public Scraper() throws Exception {
        String url_base = "https://depts.washington.edu/nwfire/dps/images/exp/";
        String dest_dir = "C:/Users/cohens/Documents/Java Workspace/CFC Scraper/photos/";
        for(String pre : prefixes) {
            int i = 1;
            while(true) {
                String file_name = pre + "_" + (i < 10 ? "0" : "") + Integer.toString(i) + ".jpg";
                String url = url_base + file_name;
                System.out.println("Trying to access page: " + url);
                InputStream in = null;
                try {
                    in = new URL(url).openStream();
                } catch(FileNotFoundException e) {
                    System.out.println("Using next file prefix.");
                    break;
                }
                if(in == null) {
                    System.out.println("InputStream was null, using next file prefix.");
                    break;
                }
                Files.copy(in, Paths.get(dest_dir + file_name));
                System.out.println("Saved file: " + file_name);
                i++;
                if(i > 25) break;
            }
        }
    }
}