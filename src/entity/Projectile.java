package entity;

import main.GamePanel;
import sun.nio.ch.SelectorProviderImpl;

public class Projectile extends Entity {

    Entity user;

    public Projectile(GamePanel gp) {
        super(gp);
    }

    public void set(int worldX, int worldY, String direction, boolean alive, Entity user){
        this.worldY = worldY;
        this.worldX = worldX;
        this.direction = direction;
        this.alive = alive;
        this.user = user;
        this.life = this.maxLife;
    }

    public void update(){

        if(user == gp.player){
            int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
            if(monsterIndex != 999){
                gp.player.damageMonster(monsterIndex, attack);
                alive = false;
            }
        }

        if(user != gp.player){
            boolean contactPlayer = gp.cChecker.checkPlayer(this);
            if(!gp.player.invincible && contactPlayer){
                damagePlayer(attack);
                alive = false;
            }
        }


        switch(direction){
            case "up": worldY -= speed; break;
            case "down": worldY += speed; break;
            case "left": worldX -= speed; break;
            case "right": worldX += speed; break;
        }

        life--;
        if(life <= 0){
            alive = false;
        }

        spriteCounter++;
        if(spriteCounter > 12){
            if(spriteNum == 1){
                spriteNum = 2;
            }
        }

    }
}
