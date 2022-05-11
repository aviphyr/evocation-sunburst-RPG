package entity;

import entity.Entity;
import main.GamePanel;

import java.awt.*;
import java.util.Random;

public class BOSS_Frung extends Entity
{


    public BOSS_Frung(GamePanel gp)
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

        up1 = setup("/npc/frung/FrungChargeBlank", gp.tileSize*2, gp.tileSize*2);
        up2 = setup("/npc/frung/FrungThrowBlank", gp.tileSize*2, gp.tileSize*2);
        down1 = setup("/npc/frung/FrungChargeBlank", gp.tileSize*2, gp.tileSize*2);
        down2 = setup("/npc/frung/FrungThrowBlank", gp.tileSize*2, gp.tileSize*2);
        left1 = setup("/npc/frung/FrungAttacksLightning1", gp.tileSize*2, gp.tileSize*2);
        left2 = setup("/npc/frung/FrungAttacksLightning2", gp.tileSize*2, gp.tileSize*2);
        right1 = setup("/npc/frung/FrungAttacksLightning1", gp.tileSize*2, gp.tileSize*2);
        right2 = setup("/npc/frung/FrungAttacksLightning2", gp.tileSize*2, gp.tileSize*2);

    }

    public void setDialogue()
    {
        dialogues[0] = "Foolish Creature!";
        dialogues[1] = "You dare to toy with MY STORY!!";
        dialogues[2] = "You don't even cast any Spells!";
        dialogues[3] = "GET!! RATIOED!!";
        dialogues[4] = "Take me out it you have to.\nJust promise me you'll protect froog.";
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
