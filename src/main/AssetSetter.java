package main;

import object.OBJ_Boots;
import object.OBJ_Chest;
import object.OBJ_Door;
import object.OBJ_Key;

public class AssetSetter
{
    GamePanel gp;

    public AssetSetter(GamePanel gp)
    {
        this.gp = gp;
    }

    public void setObject()
    {
        gp.obj[0] = new OBJ_Key(gp);
        gp.obj[0].worldX = 18 * gp.tileSize;
        gp.obj[0].worldY = 17 * gp.tileSize;

        gp.obj[1] = new OBJ_Key(gp);
        gp.obj[1].worldX = 94 * gp.tileSize;
        gp.obj[1].worldY = 22 * gp.tileSize;

        gp.obj[2] = new OBJ_Key(gp);
        gp.obj[2].worldX = 86 * gp.tileSize;
        gp.obj[2].worldY = 72 * gp.tileSize;

        gp.obj[3] = new OBJ_Door(gp);
        gp.obj[3].worldX = 55 * gp.tileSize;
        gp.obj[3].worldY = 50 * gp.tileSize;

        gp.obj[4] = new OBJ_Door(gp);
        gp.obj[4].worldX = 60 * gp.tileSize;
        gp.obj[4].worldY = 50 * gp.tileSize;

        gp.obj[5] = new OBJ_Door(gp);
        gp.obj[5].worldX = 65 * gp.tileSize;
        gp.obj[5].worldY = 50 * gp.tileSize;

        gp.obj[6] = new OBJ_Chest(gp);
        gp.obj[6].worldX = 88 * gp.tileSize;
        gp.obj[6].worldY = 50 * gp.tileSize;

        gp.obj[7] = new OBJ_Boots(gp);
        gp.obj[7].worldX = 9 * gp.tileSize;
        gp.obj[7].worldY = 48 * gp.tileSize;
    }
}
