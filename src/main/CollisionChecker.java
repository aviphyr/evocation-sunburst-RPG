package main;

import entity.Entity;
import object.OBJ_Key;

public class CollisionChecker {
    GamePanel gp;

    public CollisionChecker(GamePanel gp){
        this.gp = gp;
    }

    public void checkTile(Entity entity){

        int entityLeftWorldX = entity.worldX + entity.hitbox.x;
        int entityRightWorldX = entity.worldX + entity.hitbox.x + entity.hitbox.width;
        int entityTopWorldY = entity.worldY + entity.hitbox.y;
        int entityBottomWorldY = entity.worldY + entity.hitbox.y + entity.hitbox.height;

        int entityLeftCol = entityLeftWorldX/gp.tileSize;
        int entityRightCol = entityRightWorldX/gp.tileSize;
        int entityTopRow = entityTopWorldY/gp.tileSize;
        int entityBottomRow = entityBottomWorldY/gp.tileSize;

        int tileNum1, tileNum2;

        switch(entity.direction){
            case "up":
                entityTopRow = (entityTopWorldY - entity.speed)/gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
                if(gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision){
                    entity.collisionOn = true;
                }
                break;
            case "down":
                entityBottomRow = (entityBottomWorldY + entity.speed)/gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
                tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
                if(gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision){
                    entity.collisionOn = true;
                }
                break;
            case "left":
                entityLeftCol = (entityLeftWorldX - entity.speed)/gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
                if(gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision){
                    entity.collisionOn = true;
                }
                break;
            case "right":
                entityRightCol = (entityRightWorldX + entity.speed)/gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
                if(gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision){
                    entity.collisionOn = true;
                }
                break;
        }
    }

    public int checkObject(Entity entity, boolean player)
    {
        int index = 999;

        for(int i = 0; i < gp.obj.length; i++)
        {
            if(gp.obj[i] != null)
            {
                //Get entity's hitbox position
                entity.hitbox.x = entity.worldX + entity.hitbox.x;
                entity.hitbox.y = entity.worldY + entity.hitbox.y;

                //Get the object's hitbox position
                gp.obj[i].hitbox.x = gp.obj[i].worldX + gp.obj[i].hitbox.x;
                gp.obj[i].hitbox.y = gp.obj[i].worldY + gp.obj[i].hitbox.y;

                switch(entity.direction)
                {
                    case "up":  entity.hitbox.y -= entity.speed; break;
                    case "down": entity.hitbox.y += entity.speed; break;
                    case "left": entity.hitbox.x -= entity.speed; break;
                    case "right": entity.hitbox.x += entity.speed; break;
                }

                if(entity.hitbox.intersects(gp.obj[i].hitbox))
                {
                    if(gp.obj[i].collision)
                    {
                        entity.collisionOn = true;
                    }
                    if(player)
                    {
                        index = i;
                    }
                }

                entity.hitbox.x = entity.hitboxDefaultX;
                entity.hitbox.y = entity.hitboxDefaultY;
                gp.obj[i].hitbox.x = gp.obj[i].hitboxDefaultX;
                gp.obj[i].hitbox.y = gp.obj[i].hitboxDefaultY;
            }
        }

        return index;
    }

    //NPC or Monster
    public int checkEntity(Entity entity, Entity[] target)
    {
        int index = 999;

        for(int i = 0; i < target.length; i++)
        {
            if(target[i] != null)
            {
                //Get entity's hitbox position
                entity.hitbox.x = entity.worldX + entity.hitbox.x;
                entity.hitbox.y = entity.worldY + entity.hitbox.y;

                //Get the object's hitbox position
                target[i].hitbox.x = target[i].worldX + target[i].hitbox.x;
                target[i].hitbox.y = target[i].worldY + target[i].hitbox.y;

                switch(entity.direction)
                {
                    case "up": entity.hitbox.y -= entity.speed; break;
                    case "down": entity.hitbox.y += entity.speed; break;
                    case "left": entity.hitbox.x -= entity.speed; break;
                    case "right": entity.hitbox.x += entity.speed; break;
                }

                if(entity.hitbox.intersects(target[i].hitbox))
                {
                    if(target[i] != entity){
                        entity.collisionOn = true;
                        index = i;
                    }
                }

                entity.hitbox.x = entity.hitboxDefaultX;
                entity.hitbox.y = entity.hitboxDefaultY;
                target[i].hitbox.x = target[i].hitboxDefaultX;
                target[i].hitbox.y = target[i].hitboxDefaultY;
            }
        }

        return index;
    }

    public boolean checkPlayer(Entity entity)
    {
        int index = 999;
        boolean contactPlayer = false;

        //Get entity's hitbox position
        entity.hitbox.x = entity.worldX + entity.hitbox.x;
        entity.hitbox.y = entity.worldY + entity.hitbox.y;

        //Get the object's hitbox position
        gp.player.hitbox.x = gp.player.worldX + gp.player.hitbox.x;
        gp.player.hitbox.y = gp.player.worldY + gp.player.hitbox.y;

        switch(entity.direction)
        {
            case "up": entity.hitbox.y -= entity.speed; break;
            case "down": entity.hitbox.y += entity.speed; break;
            case "left": entity.hitbox.x -= entity.speed; break;
            case "right": entity.hitbox.x += entity.speed; break;
        }

        if(entity.hitbox.intersects(gp.player.hitbox))
        {
            entity.collisionOn = true;
            contactPlayer = true;
        }

        entity.hitbox.x = entity.hitboxDefaultX;
        entity.hitbox.y = entity.hitboxDefaultY;
        gp.player.hitbox.x = gp.player.hitboxDefaultX;
        gp.player.hitbox.y = gp.player.hitboxDefaultY;

        return contactPlayer;

    }
}
