package entity;

import main.GamePanel;

import java.awt.*;
import java.util.Random;

public class NPC_Foz extends Entity
{


    public NPC_Foz(GamePanel gp)
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

        up1 = setup("/npc/foz/FozUp1");
        up2 = setup("/npc/foz/FozUp2");
        down1 = setup("/npc/foz/FozDown1");
        down2 = setup("/npc/foz/FozDown2");
        left1 = setup("/npc/foz/FozLeft1");
        left2 = setup("/npc/foz/FozLeft2");
        right1 = setup("/npc/foz/FozRight1");
        right2 = setup("/npc/foz/FozRight2");

    }

    public void setDialogue()
    {
        dialogues[0] = "Hello Racc. You may know me as Foz";
        dialogues[1] = "How did-... Can you see me!?";
        dialogues[2] = "I use old player textures.\nThey're temporary.";
        dialogues[3] = "I am to become a FULL FOX.";
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
