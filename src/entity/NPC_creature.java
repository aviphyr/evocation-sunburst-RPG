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
        dialogues[1] = "Fishe";
        dialogues[2] = "... *unintelligible*\n... fishe.";
        dialogues[3] = "Weeeeeeeeee\neeeeeee..";
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
