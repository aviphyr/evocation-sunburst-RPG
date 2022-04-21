package entity;

import main.GamePanel;

import java.awt.*;
import java.util.Random;

public class NPC_Froog extends Entity
{


    public NPC_Froog(GamePanel gp)
    {
        super(gp);

        direction = "down";
        speed = 1;

        entityColor = new Color(11, 218, 12);
        //entityColor = new Color(255, 255, 255);

        getImage();
        setDialogue();
    }

    public void getImage()
    {

        up1 = setup("/npc/froog/FroogUp1", gp.tileSize, gp.tileSize);
        up2 = setup("/npc/froog/FroogUp2", gp.tileSize, gp.tileSize);
        down1 = setup("/npc/froog/FroogDown1", gp.tileSize, gp.tileSize);
        down2 = setup("/npc/froog/FroogDown2", gp.tileSize, gp.tileSize);
        left1 = setup("/npc/froog/FroogLeft1", gp.tileSize, gp.tileSize);
        left2 = setup("/npc/froog/FroogLeft2", gp.tileSize, gp.tileSize);
        right1 = setup("/npc/froog/FroogRight1", gp.tileSize, gp.tileSize);
        right2 = setup("/npc/froog/FroogRight2", gp.tileSize, gp.tileSize);

    }

    public void setDialogue()
    {
        dialogues[0] = "Hello Racc! Me is Froog.";
        dialogues[1] = "How did-... C-can you see me!?";
        dialogues[2] = "They had to put transparent\npixels in my top right corner\nfor my sprites to load correctly";
        dialogues[3] = "I'm a full Frog now.";
    }

    public void setAction()
    {
        actionLockCounter++;

        if(actionLockCounter == 120)
        {
            Random random = new Random();
            int i = random.nextInt(100); //Pick up a Number from 1 to 100

            if(i < 25)
            {
                direction = "up";
            }
            if(i >= 25 && i < 50)
            {
                direction = "down";
            }
            if(i >= 50 && i < 75)
            {
                direction = "left";
            }
            if(i > 70)
            {
                direction = "right";
            }

            actionLockCounter = 0;
        }
    }

    public void speak()
    {
        //Do Character Specific Stuff

        super.speak();
    }
}
