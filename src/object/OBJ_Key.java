package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Key extends Entity
{
    GamePanel gp;
    public OBJ_Key(GamePanel gp)
    {
        super(gp);
        name = "Key";
        down1 = setup("/objects/key", gp.tileSize, gp.tileSize);
        description = "[" + name + "]" + "\nWho knows what \nthis unlocks?";

    }
}
