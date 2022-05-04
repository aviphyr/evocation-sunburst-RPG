package main;

import com.sun.applet2.AppletParameters;
import entity.*;
import monster.MON_Slime;
import monster.MON_Void;
import object.*;

import java.util.HashMap;

public class AssetSetter
{
    GamePanel gp;

    public AssetSetter(GamePanel gp) {this.gp = gp;}

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
        gp.obj[i].worldX = gp.tileSize * 41;
        gp.obj[i].worldY = gp.tileSize * 54;
        i++;

        gp.obj[i] = new OBJ_TrashcanLidGold(gp);
        gp.obj[i].worldX = gp.tileSize * 44;
        gp.obj[i].worldY = gp.tileSize * 54;
        i++;

        gp.obj[i] = new OBJ_WaterPotion(gp);
        gp.obj[i].worldX = gp.tileSize * 38;
        gp.obj[i].worldY = gp.tileSize * 51;
        i++;

        // Events

        gp.obj[i] = new EVN_Event(gp, 1, false);
        gp.obj[i].worldX = gp.tileSize * 36;
        gp.obj[i].worldY = gp.tileSize * 50;
        i++;

        gp.obj[i] = new EVN_Event(gp, 2, true);
        gp.obj[i].worldX = gp.tileSize * 37;
        gp.obj[i].worldY = gp.tileSize * 50;
        i++;

        gp.obj[i] = new EVN_Event(gp, 3,true, 50, 50);
        gp.obj[i].worldX = gp.tileSize * 38;
        gp.obj[i].worldY = gp.tileSize * 50;

        i++;

        HashMap<String, String> dialogues = new HashMap<String, String>();
        dialogues.put("England", "London");
        dialogues.put("Germany", "Berlin");
        dialogues.put("Norway", "Oslo");
        dialogues.put("USA", "Washington DC");

        gp.obj[i] = new EVN_Event(gp, 4, false, dialogues);
        gp.obj[i].worldX = gp.tileSize * 27;
        gp.obj[i].worldY = gp.tileSize * 56;
        i++;

        // Houses have coordinates built-in parameters
        // Type = color
        gp.obj[i] = new OBJ_House(gp,39, 51, 1);
        gp.obj[i+1] = new OBJ_House(gp,39, 46, 2);
        gp.obj[i+2] = new OBJ_House(gp,41, 53, 1);
        gp.obj[i+3] = new OBJ_House(gp,44, 53, 5);
        gp.obj[i+4] = new OBJ_House(gp,38, 66, 3);
        gp.obj[i+5] = new OBJ_House(gp,38, 64, 4);
        gp.obj[i+6] = new OBJ_House(gp,35, 63, 4);
        gp.obj[i+7] = new OBJ_House(gp,37, 52, 6);
        gp.obj[i+8] = new OBJ_House(gp,31, 59, 1);
        gp.obj[i+9] = new OBJ_House(gp,35, 58, 6);
        gp.obj[i+10] = new OBJ_House(gp,31, 55,1);
        gp.obj[i+11] = new OBJ_House(gp,28, 52, 4);


    }

    public void setNPC()
    {
        int i = 0;

        gp.npc[i] = new NPC_Froog(gp);
        gp.npc[i].worldX = gp.tileSize * 42;
        gp.npc[i].worldY = gp.tileSize * 52;
        i++;

        gp.npc[i] = new NPC_Foz(gp);
        gp.npc[i].worldX = gp.tileSize * 42;
        gp.npc[i].worldY = gp.tileSize * 50;
        i++;

        gp.npc[i] = new NPC_Frung(gp);
        gp.npc[i].worldX = gp.tileSize * 42;
        gp.npc[i].worldY = gp.tileSize * 94;
        i++;

        gp.npc[i] = new NPC_grampyTod(gp);
        gp.npc[i].worldX = gp.tileSize * 32;
        gp.npc[i].worldY = gp.tileSize * 49;
        i++;

        gp.npc[i] = new NPC_creature(gp);
        gp.npc[i].worldX = gp.tileSize * 17;
        gp.npc[i].worldY = gp.tileSize * 33;
    }

    public void setMonster(){
        int i = 0;

        gp.monster[i] = new MON_Slime(gp);
        gp.monster[i].worldX = gp.tileSize * 44;
        gp.monster[i].worldY = gp.tileSize * 28;

        i++;

        gp.monster[i] = new MON_Slime(gp);
        gp.monster[i].worldX = gp.tileSize * 39;
        gp.monster[i].worldY = gp.tileSize * 30;

        i++;

        gp.monster[i] = new MON_Slime(gp);
        gp.monster[i].worldX = gp.tileSize * 43;
        gp.monster[i].worldY = gp.tileSize * 21;

        i++;

        gp.monster[i] = new MON_Void(gp);
        gp.monster[i].worldX = gp.tileSize * 74;
        gp.monster[i].worldY = gp.tileSize * 87;
    }

}
