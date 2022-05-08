package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_BasicStaff extends Entity {

    public OBJ_BasicStaff(GamePanel gp) {
        super(gp);

        type = type_bStaff;
        name = "Staff";
        down1 = setup("/objects/staff", gp.tileSize, gp.tileSize);
        attackValue = 2;
        description = "[" + name + "]" + "\nA classic magic\nstaff.\nDoubles well as a\nwalking stick.";
        attackHitbox.width = attackHitbox.height = 36;
    }
}
