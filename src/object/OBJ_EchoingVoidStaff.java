package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_EchoingVoidStaff extends Entity {

    public OBJ_EchoingVoidStaff(GamePanel gp) {
        super(gp);

        type = type_vStaff;
        name = "Echoing Void\nStaff";
        down1 = setup("/objects/cursedStaff", gp.tileSize, gp.tileSize);
        attackValue = 3;
        description = "[" + name + "]" + "\nWhen holding it\nyou can hear the\nechoing torment\nof countless\nsouls...\nCool.";
        attackHitbox.width = attackHitbox.height = 36;
    }
}
