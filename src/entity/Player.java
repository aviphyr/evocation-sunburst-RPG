package entity;

import main.GamePanel;
import main.KeyHandler;
import main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity {
    GamePanel gp;
    KeyHandler keyH;

    public final int screenX;
    public final int screenY;

    public int hasKey = 0;

    public Player(GamePanel gp, KeyHandler keyH){
        this.gp = gp;
        this.keyH = keyH;

        screenX = gp.screenWidth/2 - (gp.tileSize/2);
        screenY = gp.screenHeight/2- (gp.tileSize/2);

        hitbox = new Rectangle();
        hitbox.x = 16;
        hitbox.y = 16;
        hitboxDefaultX = hitbox.x;
        hitboxDefaultY = hitbox.y;
        hitbox.width = 16;
        hitbox.height = 32;


        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues(){
        worldX = gp.tileSize * 40;
        worldY = gp.tileSize * 50;
        speed = 4;
        direction = "down";
    }

    //Walking Sprites

    public void getPlayerImage()
    {
        up1 = setup("RaccoonWalkBack1");
        up2 = setup("RaccoonWalkBack2");
        down1 = setup("RaccoonWalkForward1");
        down2 = setup("RaccoonWalkForward2");
        left1 = setup("RaccoonWalkLeft1");
        left2 = setup("RaccoonWalkLeft2");
        right1 = setup("RaccoonWalkRight1");
        right2 = setup("RaccoonWalkRight2");
    }

    public BufferedImage setup(String image)
    {
        UtilityTool uTool = new UtilityTool();
        BufferedImage scaledImage = null;

        try
        {
            scaledImage = ImageIO.read(getClass().getResourceAsStream("/player/" + image + ".png"));
            scaledImage = uTool.scaleImage(scaledImage, gp.tileSize, gp.tileSize);

        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return scaledImage;
    }

    public void update(){

        //

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

            //check tile collision
            collisionOn = false;
            gp.cChecker.checkTile(this);

            //check object collision
            int objIndex = gp.cChecker.checkObject(this, true);
            pickUpObject(objIndex);

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
    }

    public void pickUpObject(int index)
    {
        if(index != 999)
        {
            String objectName = gp.obj[index].name;

            switch(objectName)
            {
                case "Key":
                    gp.playSE(1);
                    hasKey++;
                    gp.obj[index] = null;
                    System.out.println("Key: " + hasKey);
                    gp.ui.showMessage("You got a key!");
                    break;
                case "Door":
                    if(hasKey > 0)
                    {
                        gp.playSE(3);
                        hasKey--;
                        gp.obj[index] = null;
                        gp.ui.showMessage("You opened the door!");
                    }
                    else
                    {
                        gp.ui.showMessage("You need a key!");
                    }
                    break;
                case "Boots":
                    gp.playSE(2);
                    speed += 1;
                    gp.obj[index] = null;
                    gp.ui.showMessage("Speed up!");
                    break;
                case "Chest":
                    gp.ui.gameFinished = true;
                    gp.stopMusic();
                    gp.playSE(4);
                    break;
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

        g2.drawImage(image, screenX, screenY, null);

    }
}
