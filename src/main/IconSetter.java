package main;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public class IconSetter extends JFrame
{
    //Game Icon
    public static void setIconImage(JFrame window)
    {
        try
        {
            URL resource = window.getClass().getResource("/icons/GameIcon.png");
            BufferedImage image = ImageIO.read(resource);
            window.setIconImage(image);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

    }

}
