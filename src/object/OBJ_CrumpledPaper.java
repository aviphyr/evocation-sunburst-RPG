package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_CrumpledPaper extends Entity
{
    GamePanel gp;
    public OBJ_CrumpledPaper(GamePanel gp)
    {
        super(gp);
        name = "Crumpled Paper";
        down1 = setup("/objects/crumpled", gp.tileSize, gp.tileSize);
        description = "[" + name + "]" + "\nIt used to\n be a paper\n airplane once.\n Probably.";

    }
}
