package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Lighter extends Entity
{
    GamePanel gp;
    public OBJ_Lighter(GamePanel gp)
    {
        super(gp);
        name = "Old Lighter";
        down1 = setup("/objects/lighter", gp.tileSize, gp.tileSize);
        description = "[" + name + "]" + "\nWho needs magic\nwhen you can\nlight trash on\nfire and throw it";


    }
}
