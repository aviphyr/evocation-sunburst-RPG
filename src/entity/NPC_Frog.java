package entity;

import main.GamePanel;

import java.util.Random;

public class NPC_Frog extends Entity
{
    public NPC_Frog(GamePanel gp)
    {
        super(gp);

        direction = "down";
        speed = 1;

        getImage();

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

}
