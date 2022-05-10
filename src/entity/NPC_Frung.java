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

        up1 = setup("/npc/frung/FrungUp1", gp.tileSize, gp.tileSize);
        up2 = setup("/npc/frung/FrungUp2", gp.tileSize, gp.tileSize);
        down1 = setup("/npc/frung/FrungDown1", gp.tileSize, gp.tileSize);
        down2 = setup("/npc/frung/FrungDown2", gp.tileSize, gp.tileSize);
        left1 = setup("/npc/frung/FrungLeft1", gp.tileSize, gp.tileSize);
        left2 = setup("/npc/frung/FrungLeft2", gp.tileSize, gp.tileSize);
        right1 = setup("/npc/frung/FrungRight1", gp.tileSize, gp.tileSize);
        right2 = setup("/npc/frung/FrungRight2", gp.tileSize, gp.tileSize);

    }

    public void setDialogue()
    {
        dialogues[0] = "Really, You're the new wizard";
        dialogues[1] = "Yes, Froog is my sister.\nDon't touch her.";
        dialogues[2] = "You don't even cast any spells.";
        dialogues[3] = "Could you just leave me alone.";
        dialogues[4] = "I don't really hate you.\n\nHe just wants me too.";
        dialogues[5] = null;
        dialogues[6] = null;
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
