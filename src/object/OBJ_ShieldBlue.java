package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_ShieldBlue extends Entity
{
    GamePanel gp;
    public OBJ_ShieldBlue(GamePanel gp)
    {

        super(gp);
        type = type_shield;
        defenseValue = 2;
        name = "Blue Shield";
        down1 = setup("/objects/shield_blue", gp.tileSize, gp.tileSize);
        description = "[" + name + "]" + "\nSomeone took a\nshield and painted\n it blue.";

    }
}
