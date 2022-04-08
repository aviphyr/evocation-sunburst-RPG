package main;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public class Main extends JFrame {
    public static void main(String [] args){

        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("2D RPG");

        //Game Icon
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

        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);

        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);

        gamePanel.setupGame();
        gamePanel.startGameThread();
    }
}
