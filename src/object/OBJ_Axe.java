package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Axe extends Entity
{
    GamePanel gp;
    public OBJ_Axe(GamePanel gp)
    {
        super(gp);
        type = type_axe;
        name = "Axe";
        attackValue = 1;
        down1 = setup("/objects/axe", gp.tileSize, gp.tileSize);
        description = "[" + name + "]" + "\nSharp. Ouch.";
        attackHitbox.width = attackHitbox.height = 30;
    }
}
