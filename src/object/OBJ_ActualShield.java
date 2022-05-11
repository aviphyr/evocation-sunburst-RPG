package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_ActualShield extends Entity
{
    GamePanel gp;
    public OBJ_ActualShield(GamePanel gp)
    {

        super(gp);
        type = type_shield;
        defenseValue = 3;
        name = "Actual Shield";
        down1 = setup("/objects/trashcanlid_gold", gp.tileSize, gp.tileSize);
        description = "[" + name + "]" + "\nIt's your first\nactual shield\n\nCool.";

    }
}
