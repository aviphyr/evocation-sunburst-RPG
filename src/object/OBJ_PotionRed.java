package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_PotionRed extends Entity
{
    GamePanel gp;
    int value = 5;

    public OBJ_PotionRed (GamePanel gp)
    {
        super(gp);
        this.gp = gp;
        type = type_consumable;
        name = "Red Potion";
        down1 = setup("/objects/potion_red", gp.tileSize, gp.tileSize);
        description = "[" + name + "]" + "\nHeals you. +5 hp";
    }
    public void use(Entity entity){
        gp.gameState = gp.dialogueState;
        gp.ui.currentDialogue = "you drink the " + name + "!\nYou feel fresh. +5 hp.";
        entity.life += value;
        if(gp.player.life > gp.player.maxLife){
            gp.player.life = gp.player.maxLife;
        }
    }
}
