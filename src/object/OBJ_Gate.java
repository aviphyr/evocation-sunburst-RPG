package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Gate extends Entity
{
    GamePanel gp;
    public OBJ_Gate(GamePanel gp)
    {
        super(gp);
        name = "Gate";
        down1 = setup("/objects/gate", gp.tileSize, gp.tileSize);

        collision = true;
    }
}
