package entity;

import main.GamePanel;
import main.KeyHandler;
import main.UtilityTool;
import object.*;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.sql.Array;
import java.util.ArrayList;

@SuppressWarnings("ALL")
public class Player extends Entity {

    KeyHandler keyH;

    public final int screenX;
    public final int screenY;

    public BufferedImage idle;

    static int whichNPC = 0;
    public ArrayList<Entity> inventory = new ArrayList<>();
    public final int inventorySize = 20;

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
        getPlayerAttackImage();
        setItems();
    }

    public void setDefaultValues(){
        worldX = gp.tileSize * 40;
        worldY = gp.tileSize * 50;
        speed = 4;
        direction = "down";

        // Player status
        maxLife = 6;
        life = maxLife;
        maxMana = 4;
        mana = maxMana;
        ammo = 10;
        level = strength = dexterity = 1;
        exp = coin = 0;
        nextLevelExp = 5;
        currentPrimary = new OBJ_SwordBasic(gp);
        currentSecondary = new OBJ_ShieldWood(gp);
        projectile = new OBJ_Fireball(gp);
        //projectile = new OBJ_Rock(gp);
        attack = getAttack(); // Attack value is decided by strength and weapon damage.
        defense = getDefense(); // Defense value is decided by dexterity and shield.
    }

    public void setItems(){
        inventory.add(currentPrimary);
        inventory.add(currentSecondary);
        inventory.add(new OBJ_Key(gp));
    }

    public int getAttack(){attackHitbox = currentPrimary.attackHitbox;return strength * currentPrimary.attackValue;}
    public int getDefense(){
        return dexterity * currentSecondary.defenseValue;
    }

    //Walking Sprites
    public void getPlayerImage()
    {
        up1 = setup("/player/RaccoonWalkBack1", gp.tileSize, gp.tileSize);
        up2 = setup("/player/RaccoonWalkBack2", gp.tileSize, gp.tileSize);
        down1 = setup("/player/RaccoonWalkForward1", gp.tileSize, gp.tileSize);
        down2 = setup("/player/RaccoonWalkForward2", gp.tileSize, gp.tileSize);
        left1 = setup("/player/RaccoonWalkLeft1", gp.tileSize, gp.tileSize);
        left2 = setup("/player/RaccoonWalkLeft2", gp.tileSize, gp.tileSize);
        right1 = setup("/player/RaccoonWalkRight1", gp.tileSize, gp.tileSize);
        right2 = setup("/player/RaccoonWalkRight2", gp.tileSize, gp.tileSize);
        idle = setup("/player/RaccoonIdleFront", gp.tileSize, gp.tileSize);
    }
    public void getPlayerAttackImage(){
        if(currentPrimary.type == type_sword){
            attackUp1 = setup("/player/RaccAttackUp1", gp.tileSize, gp.tileSize*2);
            attackUp2 = setup("/player/RaccAttackUp2", gp.tileSize, gp.tileSize*2);
            attackDown1 = setup("/player/RaccAttackDown1", gp.tileSize, gp.tileSize*2);
            attackDown2 = setup("/player/RaccAttackDown2", gp.tileSize, gp.tileSize*2);
            attackLeft1 = setup("/player/RaccAttackLeft1", gp.tileSize*2, gp.tileSize);
            attackLeft2 = setup("/player/RaccAttackLeft2", gp.tileSize*2, gp.tileSize);
            attackRight1 = setup("/player/RaccAttackRight1", gp.tileSize*2, gp.tileSize);
            attackRight2 = setup("/player/RaccAttackRight2", gp.tileSize*2, gp.tileSize);
        }
        if(currentPrimary.type == type_axe){
            attackUp1 = setup("/player/boy_axe_up_1", gp.tileSize, gp.tileSize*2);
            attackUp2 = setup("/player/boy_axe_up_2", gp.tileSize, gp.tileSize*2);
            attackDown1 = setup("/player/boy_axe_down_1", gp.tileSize, gp.tileSize*2);
            attackDown2 = setup("/player/boy_axe_down_2", gp.tileSize, gp.tileSize*2);
            attackLeft1 = setup("/player/boy_axe_left_1", gp.tileSize*2, gp.tileSize);
            attackLeft2 = setup("/player/boy_axe_left_2", gp.tileSize*2, gp.tileSize);
            attackRight1 = setup("/player/boy_axe_right_1", gp.tileSize*2, gp.tileSize);
            attackRight2 = setup("/player/boy_axe_right_2", gp.tileSize*2, gp.tileSize);
        }

    }

    public void update(){

        // if attacking then attacking, yes. don't ask me why it's like this.
        if (keyH.interact){
            attacking = true;
        }
        if (attacking){
            //gp.playSE(6); //sound effect for swinging
            attacking();
        }

        //animation updates.
        else if (keyH.upPressed || keyH.downPressed|| keyH.leftPressed|| keyH.rightPressed){
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

        // set default coordinates, direction and user
        if (gp.keyH.shotKeyPressed && !projectile.alive && shotAvailableCounter == 30 && projectile.haveResource(this)){
            projectile.set(worldX, worldY, direction, true, this);
            projectile.subtractResource(this);
            gp.projectileList.add(projectile);
            shotAvailableCounter = 0;
            gp.playSE(10);
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
        if(life > maxLife){
            life = maxLife;
        }

        if(mana > maxMana){
            mana = maxMana;
        }
    }

    public void attacking(){

        spriteCounter++;

        if (spriteCounter <= 5){
            spriteNum = 1;
        }
        if (spriteCounter > 5 && spriteCounter <= 25){
            spriteNum = 2;

            // world location
            int currentWorldX = worldX;
            int currentWorldY = worldY;
            int hitboxWidth = hitbox.width;
            int hitboxHeight = hitbox.height;

            //adjust
            switch(direction){
                case "up": worldY -= attackHitbox.height; break;
                case "down": worldY += attackHitbox.height; break;
                case "left": worldX -= attackHitbox.width; break;
                case "right": worldX += attackHitbox.width; break;
            }
            // attackHitbox becomes hitbox
            hitbox.width = attackHitbox.width;
            hitbox.height = attackHitbox.height;
            //check monster collision with the update
            int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
            damageMonster(monsterIndex, attack);

            // reset
            worldX = currentWorldX;
            worldY = currentWorldY;
            hitbox.height = hitboxHeight;
            hitbox.width = hitboxWidth;
        }
        if (spriteCounter > 25){
            spriteNum = 1;
            spriteCounter = 0;
            attacking = false;
        }
    }

    public void pickUpObject(int index)
    {
        if(index != 999)
        {
            //pickup items
            if(gp.obj[index].type ==type_pickupOnly){
                gp.obj[index].use(this);
                gp.obj[index] = null;
            }
            //inventory
            else {
                String text;

                if(inventory.size() != inventorySize)
                {
                    inventory.add(gp.obj[index]);
                    text = "Picked up " + gp.obj[index].name + ".";
                    gp.playSE(9);
                } else {
                    text = "Not enough space in inventory.";
                }
                gp.ui.addMessage(text);
                gp.obj[index] = null;
            }
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
            if (!invincible && !gp.monster[i].dying) {
                gp.playSE(7);
                int damage = gp.monster[i].attack - defense;
                if(damage < 0){
                    damage = 0;
                }
                life -= damage;
                invincible = true;
            }
        }

    }

    public void damageMonster(int i, int attack){
        if (i != 999) {
            if (!gp.monster[i].invincible) {
                gp.playSE(6);

                int damage = attack - gp.monster[i].defense;
                if(damage < 0){
                    damage = 0;
                }

                gp.monster[i].life -= damage;
                gp.ui.addMessage(damage + " damage!");
                gp.monster[i].invincible = true;
                gp.monster[i].damageReaction();

                if (gp.monster[i].life <= 0) {
                    //gp.monster[i] = null;
                    gp.monster[i].dying = true;
                    gp.ui.addMessage("Killed the " + gp.monster[i].name + "!");
                    gp.ui.addMessage("Exp + " + gp.monster[i].exp);
                    exp += gp.monster[i].exp;
                    checkLevelUp();
                }
            }
        }

    }

    public void checkLevelUp(){
        if (exp >= nextLevelExp){
            level++;
            nextLevelExp = nextLevelExp *2;
            maxLife += 2;
            strength++;
            dexterity++;
            attack = getAttack();
            defense = getDefense();
            gp.playSE(8);
            gp.gameState = gp.dialogueState;
            gp.ui.currentDialogue = "You are level " + level + " now!\n";
            mana = maxMana;
        }
    }

    public void selectItem(){
        int itemIndex = gp.ui.getItemIndexOnSlot();
        if (itemIndex < inventory.size()){
            Entity selectedItem = inventory.get(itemIndex);
            if(selectedItem.type == type_sword || selectedItem.type == type_axe){
                currentPrimary = selectedItem;
                attack = getAttack();
                getPlayerAttackImage();
            }
            if(selectedItem.type == type_shield){
                currentSecondary = selectedItem;
                defense = getDefense();
            }
            if(selectedItem.type == type_consumable){
                selectedItem.use(this);
                inventory.remove(itemIndex);
            }
        }
    }

    public void draw(Graphics2D g2){
        //g2.setColor(Color.white);
        //g2.fillRect(x, y, gp.tileSize, gp.tileSize);

        BufferedImage image = null;
        int tempScreenX = screenX;
        int tempScreenY = screenY;

        switch (direction) {
            case "up":
                if (!attacking){
                    if (spriteNum == 1) image = up1;
                    if (spriteNum == 2) image = up2;
                }
                if (attacking){
                    tempScreenY = screenY - gp.tileSize;
                    if (spriteNum == 1) image = attackUp1;
                    if (spriteNum == 2) image = attackUp2;
                }
                break;
            case "down":
                if (!attacking){
                    if (spriteNum == 1) image = down1;
                    if (spriteNum == 2) image = down2;
                }
                if (attacking){
                    if (spriteNum == 1) image = attackDown1;
                    if (spriteNum == 2) image = attackDown2;
                }
                break;
            case "left":
                if (!attacking){
                    if (spriteNum == 1) image = left1;
                    if (spriteNum == 2) image = left2;
                }
                if (attacking){
                    tempScreenX = screenX - gp.tileSize;
                    if (spriteNum == 1) image = attackLeft1;
                    if (spriteNum == 2) image = attackLeft2;
                }
                break;
            case "right":
                if (!attacking){
                    if(spriteNum == 1) image = right1;
                    if(spriteNum == 2) image = right2;
                }
                if (attacking){
                    if(spriteNum == 1) image = attackRight1;
                    if(spriteNum == 2) image = attackRight2;
                }
                break;
        }

        // hit!
        if (invincible){
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
        }
        g2.drawImage(image, tempScreenX, tempScreenY , null);
        // reset alpha
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));

    }
}
