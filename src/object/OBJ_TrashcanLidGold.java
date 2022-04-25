package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_TrashcanLidGold extends Entity
{
    GamePanel gp;
    public OBJ_TrashcanLidGold(GamePanel gp)
    {

        super(gp);
        type = type_shield;
        defenseValue = 2;
        name = "Gold Lid";
        down1 = setup("/objects/trashcanlid_gold", gp.tileSize, gp.tileSize);
        description = "[" + name + "]" + "\nSomeone took a\nlid and painted\n it gold.";

    }
}
