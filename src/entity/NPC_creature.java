package entity;

import main.GamePanel;

import java.awt.*;
import java.util.Random;

public class NPC_creature extends Entity
{


    public NPC_creature(GamePanel gp)
    {
        super(gp);

        name = "Tod";
        direction = "down";
        speed = 0;

        entityColor = new Color(24, 159, 192);
        //entityColor = new Color(255, 255, 255);

        getImage();
        setDialogue();
    }

    public void getImage()
    {
        up1 = setup("/npc/creature/creature", gp.tileSize, gp.tileSize);
        up2 = setup("/npc/creature/creature", gp.tileSize, gp.tileSize);
        down1 = setup("/npc/creature/creature", gp.tileSize, gp.tileSize);
        down2 = setup("/npc/creature/creature", gp.tileSize, gp.tileSize);
        right1 = setup("/npc/creature/creature", gp.tileSize, gp.tileSize);
        right2 = setup("/npc/creature/creature", gp.tileSize, gp.tileSize);
        left1 = setup("/npc/creature/creature", gp.tileSize, gp.tileSize);
        left2 = setup("/npc/creature/creature1", gp.tileSize, gp.tileSize);

    }

    public void setDialogue()
    {
        dialogues[0] = "...";
        dialogues[1] = "...\n\n\nKeys";
        dialogues[2] = "... *unintelligible*\n... keeheHEES.";
        dialogues[3] = "Weeeeeeeeee\neeheHeEHeHE..\n\n Covered IN StriIIiiIings.";
        dialogues[4] = "I've got strings to hold me down.\nThey make me fret, make Me frown\nI found no keys, so I'm not free\nThey got their strings on me.";
        dialogues[5] = "Sometimes. I don't know weather\nhe's still in control or I can\nactually speak freely now.";
        dialogues[6] = null;
    }

    public void setAction()
    {
    }

    public void speak()
    {
        //Do Character Specific Stuff

        super.speak();
    }
}
