package object;

import entity.Entity;
import main.GamePanel;


public class OBJ_GarbageBag extends Entity
{
    GamePanel gp;
    public OBJ_GarbageBag(GamePanel gp)
    {
        super(gp);
        name = "Garbage";
        down1 = setup("/objects/trashbag", gp.tileSize, gp.tileSize);
        description = "[" + name + "]" + "\nWhy... did\n you pick this\n up?";

    }
}
