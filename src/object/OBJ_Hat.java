package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Hat extends Entity
{
    GamePanel gp;
    public OBJ_Hat(GamePanel gp)
    {
        super(gp);
        name = "Hat";
        down1 = setup("/objects/hat", gp.tileSize, gp.tileSize);
        description = "[" + name + "]" + "Made me\nget into all this\nmess.";


    }
}
