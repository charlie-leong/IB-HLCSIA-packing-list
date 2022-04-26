import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class propertiesFile {

    public Object accessProperty(String name){
        //create a new input stream to access the file containing the base packing list
        try (InputStream input = new FileInputStream(getDirectory("packing list contents"))) {
            Properties prop = new Properties();
            prop.load(input);
            return prop.getProperty(name);
        }catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
    }

  public String getDirectory(String fileName){
        //access the directory of a file name so the file path doesn't need to be hard coded
        //connecting the system directory to the file name to create the full file path
        String directory = System.getProperty("user.dir") + "/" + fileName;
    return directory;
  }
}

