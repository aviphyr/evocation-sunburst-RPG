package save_load;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

public class Save extends Master
{
    public void save()
    {
        try
        {
            File saveFile = new File("sunburst_save.txt");

            FileOutputStream fos = new FileOutputStream(saveFile);
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));

            //Writing contents in the file.
            bw.write("SAVE PLACEHOLDER");

            //Closes connection with file.
            bw.close();

            System.out.println("Save Created Successfully!");
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
