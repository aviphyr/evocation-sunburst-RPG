package save_load;

import java.io.File;
import java.util.Scanner;

public class Load extends Master
{
    public void load()
    {
        try
        {
            Scanner scan = new Scanner(new File("sunburst_save.txt"));
            System.out.println(scan.nextLine());
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
