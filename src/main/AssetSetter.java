package main;

import entity.NPC_Frog;
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

    }

    public void setNPC()
    {
        gp.npc[0] = new NPC_Frog(gp);
        gp.npc[0].worldX = gp.tileSize * 31;
        gp.npc[0].worldY = gp.tileSize * 48;
    }
}
