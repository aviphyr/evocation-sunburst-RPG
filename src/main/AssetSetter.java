package main;

import entity.NPC_Froog;
import entity.NPC_Foz;
import entity.NPC_Frung;
import monster.MON_Slime;
import object.*;

public class AssetSetter
{
    GamePanel gp;

    public AssetSetter(GamePanel gp)
    {
        this.gp = gp;
    }

    public void setObject()
    {
        int i = 0;

        gp.obj[i] = new OBJ_Heart(gp);
        gp.obj[i].worldX = gp.tileSize * 31;
        gp.obj[i].worldY = gp.tileSize * 48;

        i++;

        gp.obj[i] = new OBJ_ManaCrystal(gp);
        gp.obj[i].worldX = gp.tileSize * 34;
        gp.obj[i].worldY = gp.tileSize * 57;

        i++;

        gp.obj[i] = new OBJ_CrumpledPaper(gp);
        gp.obj[i].worldX = gp.tileSize * 35;
        gp.obj[i].worldY = gp.tileSize * 60;

        i++;

        gp.obj[i] = new OBJ_Axe(gp);
        gp.obj[i].worldX = gp.tileSize * 38;
        gp.obj[i].worldY = gp.tileSize * 51;

        i++;

        gp.obj[i] = new OBJ_TrashcanLidGold(gp);
        gp.obj[i].worldX = gp.tileSize * 41;
        gp.obj[i].worldY = gp.tileSize * 51;

        i++;

        gp.obj[i] = new OBJ_PotionRed(gp);
        gp.obj[i].worldX = gp.tileSize * 35;
        gp.obj[i].worldY = gp.tileSize * 48;

    }

    public void setNPC()
    {
        int i = 0;

        gp.npc[i] = new NPC_Froog(gp);
        gp.npc[i].worldX = gp.tileSize * 31;
        gp.npc[i].worldY = gp.tileSize * 48;

        i++;

        gp.npc[i] = new NPC_Foz(gp);
        gp.npc[i].worldX = gp.tileSize * 34;
        gp.npc[i].worldY = gp.tileSize * 49;


        i++;

        gp.npc[i] = new NPC_Frung(gp);
        gp.npc[i].worldX = gp.tileSize * 52;
        gp.npc[i].worldY = gp.tileSize * 57;
    }

    public void setMonster(){
        int i = 0;

        gp.monster[i] = new MON_Slime(gp);
        gp.monster[i].worldX = gp.tileSize * 41;
        gp.monster[i].worldY = gp.tileSize * 25;

        i++;

        gp.monster[i] = new MON_Slime(gp);
        gp.monster[i].worldX = gp.tileSize * 36;
        gp.monster[i].worldY = gp.tileSize * 27;

        i++;

        gp.monster[i] = new MON_Slime(gp);
        gp.monster[i].worldX = gp.tileSize * 40;
        gp.monster[i].worldY = gp.tileSize * 18;
    }

}
