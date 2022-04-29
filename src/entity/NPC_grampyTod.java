package entity;

import main.GamePanel;

import java.awt.*;
import java.util.Random;

public class NPC_grampyTod extends Entity
{


    public NPC_grampyTod(GamePanel gp)
    {
        super(gp);

        name = "Tod";
        direction = "down";
        speed = 0;

        entityColor = new Color(27, 187, 105);
        //entityColor = new Color(255, 255, 255);

        getImage();
        setDialogue();
    }

    public void getImage()
    {
        up1 = setup("/npc/grampyTod/ToadFront", gp.tileSize, gp.tileSize);
        up2 = setup("/npc/grampyTod/ToadFront", gp.tileSize, gp.tileSize);
        down1 = setup("/npc/grampyTod/ToadFront", gp.tileSize, gp.tileSize);
        down2 = setup("/npc/grampyTod/ToadFront", gp.tileSize, gp.tileSize);
        right1 = setup("/npc/grampyTod/ToadFront", gp.tileSize, gp.tileSize);
        right2 = setup("/npc/grampyTod/ToadFront", gp.tileSize, gp.tileSize);
        left1 = setup("/npc/grampyTod/ToadFront", gp.tileSize, gp.tileSize);
        left2 = setup("/npc/grampyTod/ToadFront", gp.tileSize, gp.tileSize);

    }

    public void setDialogue()
    {
        dialogues[0] = "I'm Grampy Tod.";
        dialogues[1] = "So what brings you to\nthis town?";
        dialogues[2] = "NullPointerException.\nHaha, got you.";
        dialogues[3] = "Aaaaaah.";
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
