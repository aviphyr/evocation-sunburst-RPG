package main;

import entity.NPC_Froog;
import entity.NPC_Foz;

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
        gp.npc[0] = new NPC_Froog(gp);
        gp.npc[0].worldX = gp.tileSize * 31;
        gp.npc[0].worldY = gp.tileSize * 48;
        gp.npc[1] = new NPC_Foz(gp);
        gp.npc[1].worldX = gp.tileSize * 34;
        gp.npc[1].worldY = gp.tileSize * 49;
    }
}
