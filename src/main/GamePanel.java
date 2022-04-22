package main;

import entity.Entity;
import entity.Player;
import tile.TileManager;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class GamePanel extends JPanel implements Runnable {

    // Screen settings
    final int originalTileSize = 16;
    final int scale = 3;

    public final int tileSize = originalTileSize * scale; // scaling for monitor
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol;
    public final int screenHeight = tileSize * maxScreenRow;

    // the world...settings
    public final int maxWorldCol = 100;
    public final int maxWorldRow = 100;

    public final int worldWidth = tileSize * maxWorldCol;
    public final int worldHeight = tileSize * maxWorldRow;

    int FPS = 60; // so that the game doesn't update a million times a second

    //Audio
    boolean isMusicPlaying = false;

    //System
    TileManager tileM = new TileManager(this);
    public KeyHandler keyH = new KeyHandler(this);
    Sound music = new Sound();
    Sound se = new Sound();
    public CollisionChecker cChecker = new CollisionChecker(this);
    public AssetSetter aSetter = new AssetSetter(this);
    public UI ui = new UI(this);
    public EventHandler eHandler = new EventHandler(this);
    Thread gameThread;

    //Entity and Object
    public Player player = new Player(this, keyH);
    public Entity obj[] = new Entity[30];
    public Entity npc[] = new Entity[10];
    public Entity monster[] = new Entity[20];
    public ArrayList<Entity> projectileList = new ArrayList<>();
    ArrayList<Entity> entityList = new ArrayList<>();

    //Game State
    public int gameState;
    public final int titleState = 0;
    public final int playState = 1;
    public final int pauseState = 2;
    public final int dialogueState = 3;
    public final int characterState = 4;

    public GamePanel(){

        this.setPreferredSize((new Dimension(screenWidth, screenHeight)));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void setupGame()
    {
        aSetter.setObject();
        aSetter.setNPC();
        aSetter.setMonster();
        playMusic(0);
        gameState = titleState;
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

    public void update()
    {
        if(gameState == playState)
        {
            //Player
            player.update();

            //NPC
            for(int i = 0; i < npc.length; i++)
            {
                if(npc[i] != null)
                {
                    npc[i].update();
                }
            }
            for(int i = 0; i < monster.length; i++){
                if(monster[i] != null){
                    if(monster[i].alive && !monster[i].dying){
                        monster[i].update();
                    }
                    if (!monster[i].alive){
                        monster[i].checkDrop();
                        monster[i] = null;
                    }

                }
            }
            for(int i = 0; i < projectileList.size(); i++){
                if(projectileList.get(i) != null){
                    if(projectileList.get(i).alive){
                        projectileList.get(i).update();
                    }
                    if (!projectileList.get(i).alive){ projectileList.remove(i);}

                }
            }

        }
        if(gameState == pauseState)
        {
            //nothing

        }

    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;

        //Debug
        long drawStart = 0;
        if(keyH.showDebug)
        {
            drawStart = System.nanoTime();
        }

        //Title Screen
        if(gameState == titleState)
        {
            ui.draw(g2);
        }
        //Others
        else
        {
            //Tile
            tileM.draw(g2);

            //entities
            entityList.add(player);
            for(int i = 0; i < npc.length; i++){
                if(npc[i] != null){
                    entityList.add(npc[i]);
                }
            }

            for(int i = 0; i < obj.length; i++){
                if(obj[i] != null){
                    entityList.add(obj[i]);
                }
            }

            for(int i = 0; i < monster.length; i++){
                if(monster[i] != null){
                    entityList.add(monster[i]);
                }
            }

            for(int i = 0; i < projectileList.size(); i++){
                if(projectileList.get(i) != null){
                    entityList.add(projectileList.get(i));
                }
            }


            // sort
            Collections.sort(entityList, new Comparator<Entity>() {
                @Override
                public int compare(Entity e1, Entity e2) {
                    int result = Integer.compare(e1.worldY, e2.worldY);
                    return result;
                }
            });

            // draw entities
            for(int i = 0; i < entityList.size(); i++){
                entityList.get(i).draw(g2);
            }
            //empty
            entityList.clear();

            //UI
            ui.draw(g2);

            //Debug
            if(keyH.showDebug)
            {
                long drawEnd = System.nanoTime();
                long passed = drawEnd - drawStart;

                g2.setFont(new Font("Arial", Font.PLAIN, 20));
                g2.setColor(Color.white);
                int x = 10;
                int y = 400;
                int lineHeight = 20;

                g2.drawString("WorldX: " + player.worldX, x, y); y += lineHeight;
                g2.drawString("WorldX: " + player.worldY, x, y); y += lineHeight;
                g2.drawString("Col: " + (player.worldX + player.hitbox.x)/tileSize, x, y); y += lineHeight;
                g2.drawString("Row: " + (player.worldY + player.hitbox.y)/tileSize, x, y); y += lineHeight;

                g2.drawString("Draw Time: " + passed, x, y);
                //System.out.println("Draw Time: " + passed);
            }
        }


        g2.dispose();
    }

    public void playMusic(int i)
    {
        if(isMusicPlaying)
            stopMusic();
        music.setFile(i);
        music.play();
        music.loop();
    }

    public void stopMusic()
    {
        music.stop();
    }

    public void playSE(int i)
    {
        se.setFile(i);
        se.play();
    }
}
