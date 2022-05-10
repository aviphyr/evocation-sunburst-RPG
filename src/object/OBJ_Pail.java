package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Pail extends Entity
{
    GamePanel gp;
    public OBJ_Pail(GamePanel gp)
    {
        super(gp);
        type = type_pail;
        name = "Pail of Water";
        attackValue = 0;
        down1 = setup("/objects/pail", gp.tileSize, gp.tileSize);
        description = "[" + name + "]" + "\nIt's the pail of\ninfinitely clean\nwater I bought\nfrom the shady\nguy that gave me\nsentience.";
        attackHitbox.width = attackHitbox.height = 30;
    }
}
