package object;

import entity.Entity;
import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Door extends Entity
{
    GamePanel gp;
    public OBJ_Door(GamePanel gp)
    {
        super(gp);
        name = "Door";
        down1 = setup("objects/door.png");

        collision = true;
    }
}
