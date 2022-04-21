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
        down1 = setup("object/key", gp.tileSize, gp.tileSize);

    }
}
