package main;

import entity.Entity;
import entity.Player;
import object.OBJ_Heart;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Array;
import java.util.ArrayList;

public class UI
{
    GamePanel gp;
    Graphics2D g2;
    Font VCR;
    BufferedImage heart_full, heart_half, heart_blank, keyImage;
    public boolean messageOn = false;
    ArrayList<String> message = new ArrayList<>();
    ArrayList<Integer> messageCounter = new ArrayList<>();
    public boolean gameFinished = false;
    public String currentDialogue = "";
    public int commandNum = 0;


    public UI(GamePanel gp)
    {
        this.gp = gp;

        try {

            InputStream is = getClass().getResourceAsStream("/font/VCR.ttf");
            VCR = Font.createFont(Font.TRUETYPE_FONT, is);
        } catch (FontFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //OBJ_Key key = new OBJ_Key(gp);
        //keyImage = key.image;

        // HUD objects
        Entity heart = new OBJ_Heart(gp);
        heart_full = heart.image;
        heart_half = heart.image2;
        heart_blank = heart.image3;
    }

    public void addMessage(String text)
    {
        message.add(text);
        messageCounter.add(0);
    }

    public void draw(Graphics2D g2)
    {
        this.g2 = g2;

        g2.setFont(VCR);
        g2.setColor(Color.white);

        //Title State
        if(gp.gameState == gp.titleState)
        {
            drawTitleScreen();
        }
        //Play State
        if(gp.gameState == gp.playState)
        {
            drawPlayerLife();
            drawMessage();
        }
        //Pause State
        if(gp.gameState == gp.pauseState)
        {
            drawPlayerLife();
            drawPauseScreen();
        }
        //Dialogue State
        if(gp.gameState == gp.dialogueState)
        {
            drawPlayerLife();
            if(Player.getNPC() != 999)
                drawDialogueScreen(gp.npc[Player.getNPC()]);
            else
                drawDialogueScreen(gp.player);
        }

        // Character status
        if(gp.gameState == gp.characterState){
            drawCharacterScreen();
        }
    }

    public void drawPlayerLife(){

        int x = gp.tileSize/2;
        int y = gp.tileSize/2;
        int i = 0;

        // max hearts
        while(i < gp.player.maxLife/2){
            g2.drawImage(heart_blank, x, y, null);
            i++;
            x += gp.tileSize;
        }

        // reset
        x = gp.tileSize/2;
        y = gp.tileSize/2;
        i = 0;

        // current life
        while (i < gp.player.life){
            g2.drawImage(heart_half, x, y, null);
            i++;
            if (i < gp.player.life){
                g2.drawImage(heart_full, x, y, null);
            }
            i++;
            x += gp.tileSize;
        }

    }

    public void drawMessage(){
        int messageX = gp.tileSize;
        int messageY = gp.tileSize * 2;
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 16F));

        for (int i = 0; i < message.size(); i++){
            if(message.get(i) != null){

                g2.setColor(Color.black);
                g2.drawString(message.get(i), messageX+2, messageY+2);
                g2.setColor(Color.white);
                g2.drawString(message.get(i), messageX, messageY);

                int counter = messageCounter.get(i) + 1;
                messageCounter.set(i, counter); // set counter to array
                messageY += 20;

                if (messageCounter.get(i) > 180){
                    message.remove(i);
                    messageCounter.remove(i);
                }
            }
        }
    }

    public void drawTitleScreen()
    {
        int x, y;
        String text;

        //Bottom Background Color
        g2.setColor(new Color(35, 35, 35, 255));
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

        //Top Background Color
        g2.setColor(new Color(89, 50, 126));
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight/3 + 20);

        //Title Box
        g2.setColor(new Color(81, 22, 171));
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(gp.screenWidth/4, 30, (2 * gp.screenWidth)/4, gp.tileSize * 3, 35, 35);
        g2.setColor(new Color(40, 40, 40));
        g2.fillRoundRect(gp.screenWidth/4, 30, (2 * gp.screenWidth)/4, gp.tileSize * 3, 35, 35);

        //Shadow Title
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 48F));
        String evocation = "Evocation:";
        x = getXforCenteredText(evocation);
        y = gp.tileSize * 2;
        g2.setColor(Color.BLACK);
        g2.drawString(evocation, x+3, y+3);

        String sunburst = "Sunburst";
        x = getXforCenteredText(sunburst);
        y = gp.tileSize * 3;
        g2.setColor(Color.BLACK);
        g2.drawString(sunburst, x+3, y+3);

        //Main Title
        x = getXforCenteredText(evocation);
        y = gp.tileSize * 2;
        g2.setColor(Color.GREEN);
        g2.drawString(evocation, x, y);

        x = getXforCenteredText(sunburst);
        y = gp.tileSize * 3;
        g2.setColor(Color.ORANGE);
        g2.drawString(sunburst, x, y);

        //Raccoon Image
        x = 20;
        y += gp.tileSize * 2;
        g2.drawImage(gp.player.idle, x, y, gp.tileSize * 6, gp.tileSize * 6, null);

        //Menu
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 36F));
        g2.setColor(Color.WHITE);

        text = "NEW GAME";
        x = (9 * gp.screenWidth)/16;
        y = gp.tileSize * 7;
        g2.drawString(text, x, y);
        if(commandNum == 0)
        {
            g2.drawString(">", x - gp.tileSize, y);
        }

        text = "LOAD GAME";
        x = (9 * gp.screenWidth)/16;
        y += gp.tileSize;
        g2.drawString(text, x, y);
        if(commandNum == 1)
        {
            g2.drawString(">", x - gp.tileSize, y);
        }

        text = "EXIT";
        x = (9 * gp.screenWidth)/16;
        y += gp.tileSize;
        g2.drawString(text, x, y);
        if(commandNum == 2)
        {
            g2.drawString(">", x - gp.tileSize, y);
        }

    }

    public void drawPauseScreen()
    {
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 32F));

        String text = "PAUSED";

        int x = getXforCenteredText(text);
        int y = gp.screenHeight/2;

        g2.drawString(text, x, y);
    }

    public void drawDialogueScreen(Entity e)
    {
        //Window
        int x = gp.tileSize * 2;
        int y = gp.tileSize/2;
        int width = gp.screenWidth - (gp.tileSize * 4);
        int height = gp.tileSize * 4;
        drawSubWindow(x, y, width, height, e.entityColor);

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 28F));
        x += gp.tileSize;
        y += gp.tileSize;

        for(String line : currentDialogue.split("\n"))
        {
            g2.drawString(line, x, y);
            y += 40;
        }
    }
    public void drawCharacterScreen(){
        // create a frame
        final int frameX = gp.tileSize;
        final int frameY = gp.tileSize;
        final int frameWidth = gp.tileSize * 5;
        final int frameHeight = gp.tileSize * 10;
        drawSubWindow(frameX, frameY, frameWidth, frameHeight, Color.WHITE);

        // Text
        g2.setColor(Color.WHITE);
        g2.setFont(g2.getFont().deriveFont(25F));
        int textX = frameX + 20;
        int textY = frameY + gp.tileSize;
        final int lineHeight = 37;

        // Names
        g2.drawString("Level", textX, textY); textY += lineHeight;
        g2.drawString("Life", textX, textY); textY += lineHeight;
        g2.drawString("Strength", textX, textY); textY += lineHeight;
        g2.drawString("Dexterity", textX, textY); textY += lineHeight;
        g2.drawString("Attack", textX, textY); textY += lineHeight;
        g2.drawString("Defense", textX, textY); textY += lineHeight;
        g2.drawString("EXP", textX, textY); textY += lineHeight;
        g2.drawString("Next Level", textX, textY); textY += lineHeight;
        g2.drawString("Coin", textX, textY); textY += lineHeight + 20;
        g2.drawString("Weapon", textX, textY); textY += lineHeight + 15;
        g2.drawString("Shield", textX, textY); textY += lineHeight;

        // Values
        int tailX = (frameX + frameWidth) - 30;
        // reset text
        textY = frameY + gp.tileSize;
        String value;

        value = String.valueOf(gp.player.level);
        textX = getXforAlignRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;
        value = String.valueOf(gp.player.life + "/" + gp.player.maxLife);
        textX = getXforAlignRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;
        value = String.valueOf(gp.player.strength);
        textX = getXforAlignRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;
        value = String.valueOf(gp.player.dexterity);
        textX = getXforAlignRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;
        value = String.valueOf(gp.player.attack);
        textX = getXforAlignRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;
        value = String.valueOf(gp.player.defense);
        textX = getXforAlignRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;
        value = String.valueOf(gp.player.exp);
        textX = getXforAlignRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;
        value = String.valueOf(gp.player.nextLevelExp);
        textX = getXforAlignRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;
        value = String.valueOf(gp.player.coin);
        textX = getXforAlignRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        g2.drawImage(gp.player.currentPrimary.down1, tailX - gp.tileSize, textY - 14, null);
        textY += gp.tileSize;
        g2.drawImage(gp.player.currentSecondary.down1, tailX - gp.tileSize, textY - 14, null);
    }

    public void drawSubWindow(int x, int y, int width, int height, Color entity)
    {
        Color c = new Color(0, 0, 0, 150);
        g2.setColor(c);
        g2.fillRoundRect(x, y, width, height, 35, 35);

        g2.setColor(entity);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x + 5, y + 5, width - 10, height - 10, 25, 25);
    }

    public int getXforCenteredText(String text)
    {
        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        return gp.screenWidth/2 - length/2;
    }

    public int getXforAlignRightText(String text, int tailX)
    {
        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = tailX - length;
        return x;
    }


}
