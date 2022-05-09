package monster;

import entity.Entity;
import main.GamePanel;
import object.*;

import java.util.Random;

public class MON_Void extends Entity {
    GamePanel gp;
    private boolean hit;
    public MON_Void(GamePanel gp){

        super(gp);
        this.gp = gp;

        type = type_monster;
        name = "Void";
        speed = 2;
        maxLife = 10 * gp.player.level;
        life = maxLife;
        attack = 6 * gp.player.level;
        defense = 1 * gp.player.level;
        exp = 5;

        hitbox.x = 3;
        hitbox.y = 18;
        hitbox.width = 42*2;
        hitbox.height = 30*2;
        hitboxDefaultX = hitbox.x;
        hitboxDefaultY = hitbox.y;

        hit = false;

        getImage();
    }

    public void getImage(){
        up1 = setup("/monster/void", gp.tileSize*2, gp.tileSize*2);
        up2 = setup("/monster/void1", gp.tileSize*2, gp.tileSize*2);
        down1 = setup("/monster/void", gp.tileSize*2, gp.tileSize*2);
        down2 = setup("/monster/void1", gp.tileSize*2, gp.tileSize*2);
        left1 = setup("/monster/void", gp.tileSize*2, gp.tileSize*2);
        left2 = setup("/monster/void1", gp.tileSize*2, gp.tileSize*2);
        right1 = setup("/monster/void", gp.tileSize*2, gp.tileSize*2);
        right2 = setup("/monster/void1", gp.tileSize*2, gp.tileSize*2);
    }

    public void setAction()
    {
        actionLockCounter++;

        if(!hit) {
            if (actionLockCounter == 120) {
                Random random = new Random();
                int i = random.nextInt(100); //Pick up a Number from 1 to 100

                if (i < 25) {
                    direction = "up";
                }
                if (i >= 25 && i < 50) {
                    direction = "down";
                }
                if (i >= 50 && i < 75) {
                    direction = "left";
                }
                if (i > 70) {
                    direction = "right";
                }

                actionLockCounter = 0;
            }
        }
        else{
            if(actionLockCounter <= 30){
                if(gp.player.worldY + 16> this.worldY) {
                    direction = "down";
                }
                else if(gp.player.worldY - 16 < this.worldY){
                    direction = "up";
                }
            }

            else if(actionLockCounter <= 60){
                if(gp.player.worldX - 16 < this.worldX){
                    direction = "left";
                }
                if(gp.player.worldX + 16 > this.worldX){
                    direction = "right";
                }
            }
            else {
                actionLockCounter = 0;
            }


        }

        /*int i = new Random().nextInt(100)+1;
        if(i > 99 && !projectile.alive && shotAvailableCounter == 30){
            projectile.set(worldX, worldY, direction, true, this);
            gp.projectileList.add(projectile);
            shotAvailableCounter = 0;
        }*/
    }

    public void damageReaction(){
        hit = true;
        speed = 5;

//        switch (gp.player.direction){
//            case "up": this.direction = "down";
//            case "down": this.direction = "up";
//            case "left": this.direction = "right";
//            case "right": this.direction = "left";
//        }
    }

    public void checkDrop(){
        dropItem(new OBJ_Heart(gp));
        dropItem(new OBJ_Heart(gp));

        int hasIt = 0;
        Entity test = new OBJ_EchoingVoidStaff(gp);
        for(Entity x: gp.player.inventory){
            if(x.name.equals(test.name)){
                dropItem(new OBJ_Coin(gp));
                hasIt++;
            }
        }
        if (hasIt == 0) {
            dropItem(new OBJ_EchoingVoidStaff(gp));
        }
    }

}