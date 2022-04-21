package entity;

import main.GamePanel;
import main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.Buffer;

public class Entity
{
    GamePanel gp;
    public int worldX, worldY;
    public int speed;

    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
    public BufferedImage attackUp1, attackUp2, attackDown1, attackDown2, attackLeft1, attackLeft2, attackRight1, attackRight2;
    public String direction = "down";

    public int spriteCounter = 0;
    public int spriteNum = 1;

    public Rectangle hitbox = new Rectangle(0, 0, 48, 48);
    public Rectangle attackHitbox = new Rectangle(0,0,0,0);
    public int hitboxDefaultX, hitboxDefaultY;
    public boolean collisionOn = false;

    public BufferedImage image, image2, image3;
    public String name;
    public boolean collision = false;

    public int actionLockCounter = 0;
    public boolean invincible = false;
    public int invincibleCounter = 0;

    boolean attacking = false;
    boolean hpBarOn = false;
    int hpBarCounter = 0;
    public boolean alive = true;
    public boolean dying = false;

    int dyingCounter = 0;

    String dialogues[] = new String[20];
    int dialogueIndex = 0;
    public Color entityColor;
    public int type; // 0 = player, 1 = npc, 2 = monster

    // Character Status
    public int maxLife;
    public int life;


    public Entity(GamePanel gp)
    {
        this.gp = gp;
    }

    public void setAction(){}
    public void damageReaction(){}

    public void speak()
    {
        if(dialogues[dialogueIndex] == null)
        {
            dialogueIndex = 0;
        }
        gp.ui.currentDialogue = dialogues[dialogueIndex];
        dialogueIndex++;

        switch(gp.player.direction)
        {
            case "up":
                direction = "down";
                break;
            case "down":
                direction = "up";
                break;
            case "left":
                direction = "right";
                break;
            case "right":
                direction = "left";
                break;
        }
    }

    public void update()
    {
        setAction();

        collisionOn = false;
        gp.cChecker.checkTile(this);
        gp.cChecker.checkObject(this, false);
        gp.cChecker.checkEntity(this, gp.npc);
        gp.cChecker.checkEntity(this, gp.monster);
        boolean contactPlayer = gp.cChecker.checkPlayer(this);

        if(this.type == 2 && contactPlayer){
            if(!gp.player.invincible){
                gp.playSE(7);
                gp.player.life -=1;
                gp.player.invincible = true;
            }
        }


        if(!collisionOn){
            switch(direction){
                case "up": worldY -= speed; break;
                case "down": worldY += speed; break;
                case "left": worldX -= speed; break;
                case "right": worldX += speed; break;
            }
        }

        // animation (walk cycle)
        spriteCounter++;
        if(spriteCounter > 10)
        {
            if(spriteNum == 1) {
                spriteNum = 2;
            }
            else if(spriteNum == 2) {
                spriteNum = 1;
            }
            spriteCounter = 0;
        }

        if(invincible){
            invincibleCounter++;
            if(invincibleCounter > 60){
                invincible = false;
                invincibleCounter = 0;
            }

        }
    }

    public void draw(Graphics2D g2)
    {
        BufferedImage image = null;

        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;

        if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
                worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
                worldY - gp.tileSize < gp.player.worldY + gp.player.screenY){


            switch (direction) {
                case "up":
                    if(spriteNum == 1) image = up1;
                    if(spriteNum == 2) image = up2;
                    break;
                case "down":
                    if(spriteNum == 1) image = down1;
                    if(spriteNum == 2) image = down2;
                    break;
                case "left":
                    if(spriteNum == 1) image = left1;
                    if(spriteNum == 2) image = left2;
                    break;
                case "right":
                    if(spriteNum == 1) image = right1;
                    if(spriteNum == 2) image = right2;
                    break;
            }
            //monster hp bar
            if (type == 2 && hpBarOn){
                double oneScale = (double) gp.tileSize/maxLife;
                double hpBarValue = oneScale*life;

                g2.setColor(new Color(66, 58, 59));
                g2.fillRect(screenX - 1, screenY - 16, gp.tileSize+2, 12);

                g2.setColor(new Color(179, 32, 53));
                g2.fillRect(screenX, screenY - 15, (int)hpBarValue, 10);

                hpBarCounter++;
                if (hpBarCounter > 600){
                    hpBarCounter = 0;
                    hpBarOn = false;
                }
            }


            if (invincible){
                hpBarOn = true;
                hpBarCounter = 0;
                changeAlpha(g2, 0.4f);
            }
            if (dying){
                dyingAnimation(g2);
            }

            g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);

            changeAlpha(g2, 1f);
        }
    }

    public void dyingAnimation(Graphics2D g2){

        dyingCounter++;

        int i = 5;

        if (dyingCounter <= i) {changeAlpha(g2, 0f);}
        if (dyingCounter > i && dyingCounter <= i*2) {changeAlpha(g2, 1f);}
        if (dyingCounter > i*2 && dyingCounter <= i*3) {changeAlpha(g2, 0f);}
        if (dyingCounter > i*3 && dyingCounter <= i*4) {changeAlpha(g2, 1f);}
        if (dyingCounter > i*4 && dyingCounter <= i*5) {changeAlpha(g2, 0f);}
        if (dyingCounter > i*5 && dyingCounter <= i*6) {changeAlpha(g2, 0f);}
        if (dyingCounter > i*6 && dyingCounter <= i*7) {changeAlpha(g2, 1f);}
        if (dyingCounter > i*7 && dyingCounter <= i*8) {changeAlpha(g2, 0f);}
        if (dyingCounter > i*8) {
            dying = false;
            alive = false;
        }
    }

    public void changeAlpha(Graphics2D g2, float alphaValue){
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alphaValue));
    }

    public BufferedImage setup(String imagePath, int width, int height)
    {
        UtilityTool uTool = new UtilityTool();
        BufferedImage scaledImage = null;

        try
        {
            scaledImage = ImageIO.read(getClass().getResourceAsStream(imagePath + ".png"));
            scaledImage = uTool.scaleImage(scaledImage, width, height);

        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return scaledImage;
    }



}
