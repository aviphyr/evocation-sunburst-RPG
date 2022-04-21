package entity;

import main.GamePanel;
import main.KeyHandler;
import main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity {

    KeyHandler keyH;

    public final int screenX;
    public final int screenY;

    public BufferedImage idle;

    static int whichNPC = 0;

    public Player(GamePanel gp, KeyHandler keyH)
    {
        super(gp);

        this.keyH = keyH;

        entityColor = new Color(255, 255, 255);

        screenX = gp.screenWidth/2 - (gp.tileSize/2);
        screenY = gp.screenHeight/2- (gp.tileSize/2);

        hitbox = new Rectangle();
        hitbox.x = 16;
        hitbox.y = 16;
        hitboxDefaultX = hitbox.x;
        hitboxDefaultY = hitbox.y;
        hitbox.width = 22;
        hitbox.height = 22;

        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues(){
        worldX = gp.tileSize * 40;
        worldY = gp.tileSize * 50;
        speed = 4;
        direction = "down";

        maxLife = 6;
        life = maxLife;
    }

    //Walking Sprites

    public void getPlayerImage()
    {
        up1 = setup("/player/RaccoonWalkBack1");
        up2 = setup("/player/RaccoonWalkBack2");
        down1 = setup("/player/RaccoonWalkForward1");
        down2 = setup("/player/RaccoonWalkForward2");
        left1 = setup("/player/RaccoonWalkLeft1");
        left2 = setup("/player/RaccoonWalkLeft2");
        right1 = setup("/player/RaccoonWalkRight1");
        right2 = setup("/player/RaccoonWalkRight2");
        idle = setup("/player/RaccoonIdleFront");
    }

    public void update(){

        //update

        if (keyH.upPressed || keyH.downPressed|| keyH.leftPressed|| keyH.rightPressed){
            // movement
            if (keyH.upPressed)
            {
                direction = "up";
            }
            else if (keyH.downPressed)
            {
                direction = "down";
            }
            else if (keyH.leftPressed)
            {
                direction = "left";
            }
            else if (keyH.rightPressed)
            {
                direction = "right";
            }

            //Check Tile Collision
            collisionOn = false;
            gp.cChecker.checkTile(this);

            //Check Object Collision
            int objIndex = gp.cChecker.checkObject(this, true);
            pickUpObject(objIndex);

            //Check NPC Collision
            int npcIndex = gp.cChecker.checkEntity(this, gp.npc);
            interactNPC(npcIndex);

            // check monster collision
            int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
            contactMonster(monsterIndex);


            //  check event
            gp.eHandler.checkEvent();

            //If Collision is False, Player Can Move
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
        }

        if (invincible){
            invincibleCounter++;
            if(invincibleCounter > 60){
                invincible = false;
                invincibleCounter = 0;
            }

        }
    }

    public void pickUpObject(int index)
    {
        if(index != 999)
        {

        }
    }

    public void interactNPC(int index)
    {
        if(index != 999)
        {
            whichNPC = index;
            gp.gameState = gp.dialogueState;
            gp.npc[index].speak();
        }
        else
            whichNPC = index;
    }

    public static int getNPC()
    {
        //System.out.println(whichNPC);
        return whichNPC;
    }

    public void contactMonster(int i){
        if (i != 999){
            if (!invincible) {
                life -= 1;
                invincible = true;
            }
        }

    }


    public void draw(Graphics2D g2){
        //g2.setColor(Color.white);
        //g2.fillRect(x, y, gp.tileSize, gp.tileSize);

        BufferedImage image = null;

        switch (direction) {
            case "up":
                if(spriteNum == 1)
                    image = up1;
                if(spriteNum == 2)
                    image = up2;
                break;
            case "down":
                if(spriteNum == 1)
                    image = down1;
                if(spriteNum == 2)
                    image = down2;
                break;
            case "left":
                if(spriteNum == 1)
                    image = left1;
                if(spriteNum == 2)
                    image = left2;
                break;
            case "right":
                if(spriteNum == 1)
                    image = right1;
                if(spriteNum == 2)
                    image = right2;
                break;
            default:
                image = null;
        }

        // hit!
        if (invincible){
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
        }
        g2.drawImage(image,screenX, screenY, null);
        // reset alpha
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));


        int x = screenX;
        int y = screenY;

        if(screenX > worldX){
            x = worldX;
        }
        if(screenY > worldY){
            y = worldY;
        }

        int rightOffset = gp.screenWidth - screenX;
        if(rightOffset > gp.worldWidth - worldX){
            x = gp.screenWidth - (gp.worldWidth - worldX);
        }
        int bottomOffset = gp.screenHeight - screenY;
        if(bottomOffset > gp.worldHeight - worldY){
            y = gp.screenHeight - (gp.worldHeight - worldY);
        }


        g2.drawImage(image, x, y, null);

        // debug
        //g2.setFont(new Font("Arial", Font.PLAIN, 26));
        //g2.setColor(Color.white);
        //g2.drawString("Invincible: " +invincibleCounter, 10, 400);

    }
}
