package entity;

import main.GamePanel;

import java.awt.*;
import java.util.Random;

public class NPC_Froog extends Entity
{

    private int turn; //movement control

    public NPC_Froog(GamePanel gp)
    {
        super(gp);

        name = "Froog";
        direction = "down";
        speed = 1;
        turn = 1;

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
        dialogues[1] = "Hello again!";
        dialogues[2] = "Hehehehehe.";
        dialogues[3] = "They had to put transparent\npixels in my top right corner\nfor my sprites to load correctly";
    }

    public void setAction()
    {
        if(gp.gameState == gp.playState)
        {
            actionLockCounter++;

            if (actionLockCounter == 45)
            {

                switch (turn){
                    case 1:
                        direction = "right";
                        turn++;
                        break;
                    case 2:
                        direction = "down";
                        turn++;
                        break;
                    case 3:
                        direction = "left";
                        turn++;
                        break;
                    case 4:
                        direction = "up";
                        turn = 1;
                        break;
                    default:
                        turn = 1;
                }

                actionLockCounter = 0;

            }
        }

    }

    public void speak()
    {
        //Do Character Specific Stuff

        super.speak();
    }
}
