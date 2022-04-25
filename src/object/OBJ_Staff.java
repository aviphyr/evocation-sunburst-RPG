package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Staff extends Entity {

    public OBJ_Staff(GamePanel gp) {
        super(gp);

        type = type_sword;
        name = "Staff";
        down1 = setup("/objects/staff", gp.tileSize, gp.tileSize);
        attackValue = 2;
        description = "[" + name + "]" + "\nAn ancient magic\nstaff.\nDoubles well as a\nwalking stick.";
        attackHitbox.width = attackHitbox.height = 36;
    }
}
