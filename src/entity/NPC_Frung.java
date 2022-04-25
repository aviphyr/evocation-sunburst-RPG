package entity;

import main.GamePanel;

import java.awt.*;
import java.util.Random;

public class NPC_Frung extends Entity
{


    public NPC_Frung(GamePanel gp)
    {
        super(gp);

        name = "Frung";
        direction = "down";
        speed = 1;
        hitbox.height = hitbox.height*2;
        hitbox.width = hitbox.width*2;

        entityColor = new Color(119, 192, 119);
        //entityColor = new Color(255, 255, 255);

        getImage();
        setDialogue();
    }

    public void getImage()
    {

        up1 = setup("/npc/frung/Frung-Attacks", gp.tileSize*2, gp.tileSize*2);
        up2 = setup("/npc/frung/Frung-Attacks", gp.tileSize*2, gp.tileSize*2);
        down1 = setup("/npc/frung/Frung-Attacks", gp.tileSize*2, gp.tileSize*2);
        down2 = setup("/npc/frung/Frung-Attacks", gp.tileSize*2, gp.tileSize*2);
        left1 = setup("/npc/frung/Frung-Attacks", gp.tileSize*2, gp.tileSize*2);
        left2 = setup("/npc/frung/Frung-Attacks", gp.tileSize*2, gp.tileSize*2);
        right1 = setup("/npc/frung/Frung-Attacks", gp.tileSize*2, gp.tileSize*2);
        right2 = setup("/npc/frung/Frung-Attacks", gp.tileSize*2, gp.tileSize*2);

    }

    public void setDialogue()
    {
        dialogues[0] = "Frung.";
        dialogues[1] = "I'm 32x32. Unlike you.";
        dialogues[2] = "Fruuung.";
        dialogues[3] = "No, I'm not related to Froog.";
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
