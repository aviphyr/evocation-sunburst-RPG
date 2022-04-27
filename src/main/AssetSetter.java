package main;

import entity.NPC_Froog;
import entity.NPC_Foz;
import entity.NPC_Frung;
import monster.MON_Slime;
import monster.MON_Void;
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

        /*gp.obj[i] = new OBJ_Heart(gp);
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
        */

        gp.obj[i] = new OBJ_Scythe(gp);
        gp.obj[i].worldX = gp.tileSize * 38;
        gp.obj[i].worldY = gp.tileSize * 51;

        i++;

        gp.obj[i] = new OBJ_TrashcanLidGold(gp);
        gp.obj[i].worldX = gp.tileSize * 41;
        gp.obj[i].worldY = gp.tileSize * 51;

        i++;

        gp.obj[i] = new OBJ_WaterPotion(gp);
        gp.obj[i].worldX = gp.tileSize * 35;
        gp.obj[i].worldY = gp.tileSize * 48;

        i++;

        // Houses have coordinates built-in parameters
        // Type = color
        gp.obj[i] = new OBJ_House(gp,36, 48, 1);
        gp.obj[i+1] = new OBJ_House(gp,36, 43, 2);
        gp.obj[i+2] = new OBJ_House(gp,38, 50, 1);
        gp.obj[i+3] = new OBJ_House(gp,41, 50, 5);
        gp.obj[i+4] = new OBJ_House(gp,35, 63, 3);
        gp.obj[i+5] = new OBJ_House(gp,35, 61, 4);
        gp.obj[i+6] = new OBJ_House(gp,32, 60, 4);
        gp.obj[i+7] = new OBJ_House(gp,34, 59, 6);
        gp.obj[i+8] = new OBJ_House(gp,28, 56, 1);
        gp.obj[i+9] = new OBJ_House(gp,32, 55, 6);
        gp.obj[i+10] = new OBJ_House(gp,28, 52,1);
        gp.obj[i+11] = new OBJ_House(gp,25, 49, 4);


    }

    public void setNPC()
    {
        int i = 0;

        gp.npc[i] = new NPC_Froog(gp);
        gp.npc[i].worldX = gp.tileSize * 39;
        gp.npc[i].worldY = gp.tileSize * 49;
        i++;

        gp.npc[i] = new NPC_Foz(gp);
        gp.npc[i].worldX = gp.tileSize * 29;
        gp.npc[i].worldY = gp.tileSize * 47;
        i++;

        gp.npc[i] = new NPC_Frung(gp);
        gp.npc[i].worldX = gp.tileSize * 39;
        gp.npc[i].worldY = gp.tileSize * 91;
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

        i++;

        gp.monster[i] = new MON_Void(gp);
        gp.monster[i].worldX = gp.tileSize * 71;
        gp.monster[i].worldY = gp.tileSize * 84;
    }

}
