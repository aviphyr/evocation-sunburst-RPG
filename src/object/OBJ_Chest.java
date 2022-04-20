package object;

import entity.Entity;
import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Chest extends Entity
{
    GamePanel gp;
    public OBJ_Chest(GamePanel gp)
    {
        super(gp);
        name = "Chest";
        down1 = setup("objects/chest.png");
    }
}
