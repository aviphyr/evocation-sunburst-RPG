package entity;

import main.GamePanel;

import java.awt.*;
import java.util.Random;

public class NPC_Frog extends Entity
{


    public NPC_Frog(GamePanel gp)
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

        up1 = setup("/npc/FrogUp1");
        up2 = setup("/npc/FrogUp2");
        down1 = setup("/npc/FrogDown1");
        down2 = setup("/npc/FrogDown2");
        left1 = setup("/npc/FrogLeft1");
        left2 = setup("/npc/FrogLeft2");
        right1 = setup("/npc/FrogRight1");
        right2 = setup("/npc/FrogRight2");

    }

    public void setDialogue()
    {
        dialogues[0] = "Hello Rack.";
        dialogues[1] = "How did can you see me?dgrdrgrdfrdrg\ndrdgf";
        dialogues[2] = "I have no texture.";
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
