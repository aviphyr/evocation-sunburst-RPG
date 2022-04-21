package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_ShieldWood extends Entity {
    public OBJ_ShieldWood(GamePanel gp) {
        super(gp);

        name = "Wood Shield";
        down1 = setup("/objects/shield_wood", gp.tileSize, gp.tileSize);
        defenseValue = 1;
    }
}
