package entity;

import main.GamePanel;
import main.KeyHandler;
import main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ShortLookupTable;
import java.io.IOException;
import java.nio.Buffer;

public class Entity
{
    // Basic
    GamePanel gp;
    String[] dialogues = new String[20];
    public boolean collision = false;

    public KeyHandler keyH;

    public int screenX;
    public int screenY;

    // Image loading
    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
    public BufferedImage attackUp1, attackUp2, attackDown1, attackDown2, attackLeft1, attackLeft2, attackRight1, attackRight2;
    public BufferedImage image, image2, image3;

    // Hitbox
    public Rectangle hitbox = new Rectangle(0, 0, 48, 48);
    public Rectangle attackHitbox = new Rectangle(0,0,0,0);
    public int hitboxDefaultX, hitboxDefaultY;

    // State
    public int worldX, worldY;
    public String direction = "down";
    public int spriteNum = 1;
    int dialogueIndex = 0;
    public boolean collisionOn = false;
    boolean attacking = false;
    boolean hpBarOn = false;
    public boolean invincible = false;
    public boolean alive = true;
    public boolean dying = false;
    public boolean canContact = false;


    // Counter
    public int spriteCounter = 0;
    public int actionLockCounter = 0;
    public int invincibleCounter = 0;
    public int shotAvailableCounter = 0;
    int hpBarCounter = 0;
    int dyingCounter = 0;

    // Entity attributes
    public Color entityColor;
    public String name = "???";
    public int speed, maxLife, life;
    public int ammo, level, strength, dexterity, attack, defense, exp, nextLevelExp, coin;
    public int maxMana, mana;
    public Entity currentPrimary, currentSecondary;
    public Projectile projectile;

    // Item attributes
    public int value;
    public int attackValue, defenseValue;
    public String description = "N/A";
    public int useCost;

    // Type
    public int type;
    public final int type_player = 0;
    public final int type_npc = 1;
    public final int type_monster = 2;
    public final int type_sword= 3;
    public final int type_axe = 4;
    public final int type_shield= 5;
    public final int type_consumable = 6;
    public final int type_pickupOnly = 7;
    public final int type_noPickup = 8;
    public final int type_event = 9;
    public final int type_keyReq = 10;


    public Entity(GamePanel gp)
    {
        this.gp = gp;
    }

    public void setAction(){}
    public void damageReaction(){}
    public void use(Entity entity){}
    public void checkDrop(){}
    public void dropItem(Entity droppedItem){
        for(int i = 0; i < gp.obj.length; i++){
            if(gp.obj[i] == null){
                gp.obj[i] = droppedItem;
                gp.obj[i].worldX = worldX;
                gp.obj[i].worldY = worldY;
                break;
            }
        }
    }

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

        if(this.type == type_monster && contactPlayer){
            damagePlayer(attack);
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

        if(shotAvailableCounter < 30){
            shotAvailableCounter++;
        }


    }

    public void damagePlayer(int attack){
        if(!gp.player.invincible){
            gp.playSE(7);
            int damage = attack - gp.player.defense;
            if(damage < 0){
                damage = 0;
            }
            gp.player.life -= damage;
            gp.player.invincible = true;
        }
    }

    public void draw(Graphics2D g2)
    {
        BufferedImage image = null;

        screenX = worldX - gp.player.worldX + gp.player.screenX;
        screenY = worldY - gp.player.worldY + gp.player.screenY;

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
                double oneScale = (double) hitbox.width/maxLife;
                double hpBarValue = oneScale*life;

                g2.setColor(new Color(66, 58, 59));
                g2.fillRect(screenX - 1, screenY - 16, hitbox.width+2, 12);

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

            g2.drawImage(image, screenX, screenY,null);

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
        if (dyingCounter > i*8) {alive = false;}
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
