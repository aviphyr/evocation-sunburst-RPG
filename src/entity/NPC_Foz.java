package entity;

import main.GamePanel;

import java.awt.*;
import java.util.Random;

public class NPC_Foz extends Entity
{


    public NPC_Foz(GamePanel gp)
    {
        super(gp);

        name = "Foz";
        direction = "down";
        speed = 1;

        entityColor = new Color(223, 130, 0);
        //entityColor = new Color(255, 255, 255);

        getImage();
        setDialogue();
    }

    public void getImage()
    {

        up1 = setup("/npc/foz/FozUp1", gp.tileSize, gp.tileSize);
        up2 = setup("/npc/foz/FozUp2", gp.tileSize, gp.tileSize);
        down1 = setup("/npc/foz/FozDown1", gp.tileSize, gp.tileSize);
        down2 = setup("/npc/foz/FozDown2", gp.tileSize, gp.tileSize);
        left1 = setup("/npc/foz/FozLeft1", gp.tileSize, gp.tileSize);
        left2 = setup("/npc/foz/FozLeft2", gp.tileSize, gp.tileSize);
        right1 = setup("/npc/foz/FozRight1", gp.tileSize, gp.tileSize);
        right2 = setup("/npc/foz/FozRight2", gp.tileSize, gp.tileSize);

    }

    public void setDialogue()
    {
        dialogues[0] = "Oh? You name is Racc?\nIt's wonderful to meet you.\nYou may refer to me as Foz";
        dialogues[1] = "It's good to see that there is\nanother wizard in town.\nThe other one is getting a bit\ntoo moody if you ask me.";
        dialogues[2] = "I used to were a monocle.\nI don't know where it could\nhave went.";
        dialogues[3] = "I wonder where my house is?";
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
