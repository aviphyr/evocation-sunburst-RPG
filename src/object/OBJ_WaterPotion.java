package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_WaterPotion extends Entity
{
    GamePanel gp;

    public OBJ_WaterPotion(GamePanel gp)
    {
        super(gp);
        this.gp = gp;
        type = type_consumable;
        name = "Water Bottle";
        value = 5;
        down1 = setup("/objects/waterpotion", gp.tileSize, gp.tileSize);
        description = "[" + name + "]" + "\nHeals you.\nHydration\n+5 hp";
    }
    public void use(Entity entity){
        gp.gameState = gp.dialogueState;
        entity.life += value;
        gp.ui.currentDialogue = "you drink the " + name + "!\nYou feel fresh. +5 hp.";
    }
}
