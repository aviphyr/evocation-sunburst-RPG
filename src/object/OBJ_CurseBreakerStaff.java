package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_CurseBreakerStaff extends Entity {

    public OBJ_CurseBreakerStaff(GamePanel gp) {
        super(gp);

        type = type_cbStaff;
        name = "Curse Breaker";
        down1 = setup("/objects/curseBreakerStaff", gp.tileSize, gp.tileSize);
        attackValue = 3;
        description = "[" + name + "]" + "\nAn ancient magic\nstaff. Said to be\ncapable of\nbreaking any\ncurse.";
        attackHitbox.width = attackHitbox.height = 36;
    }
}
