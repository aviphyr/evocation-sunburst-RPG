package main;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {

    // Screen settings
    final int originalTileSize = 16;
    final int scale = 3;

    final int tileSize = originalTileSize * scale; // scaling for monitor
    final int maxScreenCol = 16;
    final int maxScreenRow = 12;
    final int screenWidth = tileSize * maxScreenCol;
    final int screenHeight = tileSize * maxScreenRow;

    int FPS = 60; // so that the game doesn't update a million times a second

    KeyHandler keyH = new KeyHandler();

    Thread gameThread;

    int playerX = 100;
    int playerY = 100;
    int playerSpeed = 4;


    public GamePanel(){

        this.setPreferredSize((new Dimension(screenWidth, screenHeight)));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();

    }

    @Override
    public void run() {

        double drawInterval = 1000000000/FPS;
        double delta = 0;
        long lastTime = System.nanoTime(); //so futuristic....
        long currentTime;

        while(gameThread != null){

            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;

            lastTime = currentTime;

            if (delta >= 1) {
                // refresh the screen
                update();
                // "paint" the screen
                repaint();
                // update delta to 0
                delta--;
            }

        }

    }

    public void update(){
        if(keyH.upPressed){
            playerY -= playerSpeed;
        }
        else if (keyH.downPressed){
            playerY += playerSpeed;
        }
        else if (keyH.leftPressed){
            playerX -= playerSpeed;
        }
        else if (keyH.rightPressed){
            playerX += playerSpeed;
        }

    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;

        g2.setColor(Color.white);
        g2.fillRect(playerX, playerY, tileSize, tileSize);
        g2.dispose();
    }
}
