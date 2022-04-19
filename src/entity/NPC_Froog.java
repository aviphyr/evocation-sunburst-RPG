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

        up1 = setup("/npc/froog/FroogUp1");
        up2 = setup("/npc/froog/FroogUp2");
        down1 = setup("/npc/froog/FroogDown1");
        down2 = setup("/npc/froog/FroogDown2");
        left1 = setup("/npc/froog/FroogLeft1");
        left2 = setup("/npc/froog/FroogLeft2");
        right1 = setup("/npc/froog/FroogRight1");
        right2 = setup("/npc/froog/FroogRight2");

    }

    public void setDialogue()
    {
        dialogues[0] = "Hello Racc! Me is Froog.";
        dialogues[1] = "How did-... Can you see me!?";
        dialogues[2] = "I use old player textures.\nThey're temporary, I promise.";
        dialogues[3] = "I am to become a Frog.";
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
            if(i > 70 && i < 100)
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
