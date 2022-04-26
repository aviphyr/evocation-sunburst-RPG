package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Scythe extends Entity
{
    GamePanel gp;
    public OBJ_Scythe(GamePanel gp)
    {
        super(gp);
        type = type_axe;
        name = "Scythe";
        attackValue = 1;
        down1 = setup("/objects/scythe", gp.tileSize, gp.tileSize);
        description = "[" + name + "]" + "\nSharp. Ouch.";
        attackHitbox.width = attackHitbox.height = 30;
    }
}
