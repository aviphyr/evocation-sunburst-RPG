package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_SwordBasic extends Entity {

    public OBJ_SwordBasic(GamePanel gp) {
        super(gp);

        type = type_sword;
        name = "Normal Sword";
        down1 = setup("/objects/sword_normal", gp.tileSize, gp.tileSize);
        attackValue = 2;
        description = "[" + name + "]" + "\nAn old sword.";
        attackHitbox.width = attackHitbox.height = 36;
    }
}
