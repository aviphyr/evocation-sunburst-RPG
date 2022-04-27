package monster;

import entity.Entity;
import main.GamePanel;
import object.OBJ_Coin;
import object.OBJ_Heart;
import object.OBJ_Trash;
import object.OBJ_Rock;

import java.util.Random;

public class MON_Void extends Entity {
    GamePanel gp;
    public MON_Void(GamePanel gp){

        super(gp);
        this.gp = gp;

        type = type_monster;
        name = "Void";
        speed = 3;
        maxLife = 6;
        life = maxLife;
        attack = 6;
        defense = 0;
        exp = 5;

        hitbox.x = 3;
        hitbox.y = 18;
        hitbox.width = 42;
        hitbox.height = 30;
        hitboxDefaultX = hitbox.x;
        hitboxDefaultY = hitbox.y;

        getImage();
    }

    public void getImage(){
        up1 = setup("/monster/void", gp.tileSize, gp.tileSize);
        up2 = setup("/monster/void1", gp.tileSize, gp.tileSize);
        down1 = setup("/monster/void", gp.tileSize, gp.tileSize);
        down2 = setup("/monster/void1", gp.tileSize, gp.tileSize);
        left1 = setup("/monster/void", gp.tileSize, gp.tileSize);
        left2 = setup("/monster/void1", gp.tileSize, gp.tileSize);
        right1 = setup("/monster/void", gp.tileSize, gp.tileSize);
        right2 = setup("/monster/void1", gp.tileSize, gp.tileSize);
    }

    public void setAction()
    {
        actionLockCounter++;

        if(actionLockCounter == 120)
        {
            Random random = new Random();
            int i = random.nextInt(100); //Pick up a Number from 1 to 100

            if(i < 25)
            {
                direction = "up";
            }
            if(i >= 25 && i < 50)
            {
                direction = "down";
            }
            if(i >= 50 && i < 75)
            {
                direction = "left";
            }
            if(i > 70)
            {
                direction = "right";
            }

            actionLockCounter = 0;
        }

        /*int i = new Random().nextInt(100)+1;
        if(i > 99 && !projectile.alive && shotAvailableCounter == 30){
            projectile.set(worldX, worldY, direction, true, this);
            gp.projectileList.add(projectile);
            shotAvailableCounter = 0;
        }*/
    }

    public void damageReaction(){
        actionLockCounter = 0;
        direction = gp.player.direction;
    }

    public void checkDrop(){
        int i = new Random().nextInt(100)+1;
        if(i < 50){
            dropItem(new OBJ_Heart(gp));
        }
        if(i >= 50 && i < 75){
            dropItem(new OBJ_Heart(gp));
        }
        if(i >= 75 && i < 100){
            dropItem(new OBJ_Trash(gp));
        }
    }

}