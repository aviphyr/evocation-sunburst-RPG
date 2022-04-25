package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_TrashcanLid extends Entity {
    public OBJ_TrashcanLid(GamePanel gp) {
        super(gp);
        type = type_shield;
        name = "Trashcan Shield";
        down1 = setup("/objects/trashcanlid", gp.tileSize, gp.tileSize);
        defenseValue = 1;
        description = "[" + name + "]" + "\nTrash can lid. \nIt's a shield.";
    }
}
