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
        dialogues[0] = "Names Tod.";
        dialogues[1] = "So what brings you to\nthis town?";
        dialogues[2] = "Me? I went to the old hag to\nget my family back, but he had\ngotten a lot more powerful\nthan the last time we met.";
        dialogues[3] = "You best not hurt young\nMs. Froog. Cause I make no\npromises of what will happen\nto you if you do.";
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
