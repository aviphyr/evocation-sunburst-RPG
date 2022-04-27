package monster;

import entity.Entity;
import main.GamePanel;
import object.*;

import java.util.Random;

public class MON_Void extends Entity {
    GamePanel gp;
    public MON_Void(GamePanel gp){

        super(gp);
        this.gp = gp;

        type = type_monster;
        name = "Void";
        speed = 3;
        maxLife = 10;
        life = maxLife;
        attack = 6;
        defense = 0;
        exp = 5;

        hitbox.x = 3;
        hitbox.y = 18;
        hitbox.width = 42*2;
        hitbox.height = 30*2;
        hitboxDefaultX = hitbox.x;
        hitboxDefaultY = hitbox.y;

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
        switch (gp.player.direction){
            case "up": this.direction = "down";
            case "down": this.direction = "up";
            case "left": this.direction = "right";
            case "right": this.direction = "left";
        }
    }

    public void checkDrop(){
        dropItem(new OBJ_Heart(gp));
        dropItem(new OBJ_Heart(gp));
        dropItem(new OBJ_Key(gp));
    }

}