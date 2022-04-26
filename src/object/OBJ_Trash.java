package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Trash extends Entity {
    GamePanel gp;
    public OBJ_Trash(GamePanel gp) {
        super(gp);
        this.gp = gp;

        type = type_pickupOnly;
        name = "Trash";
        value = 1;
        description = "[" + name + "]" + "\nWhy... did\n you pick this\n up?";
        down1 = setup("/objects/trash_full", gp.tileSize, gp.tileSize);
        image = setup("/objects/trash_full", gp.tileSize, gp.tileSize);
        image2 = setup("/objects/trash_empty", gp.tileSize, gp.tileSize);
    }
    public void use(Entity entity){
        gp.playSE(6);
        gp.ui.addMessage("Trash +" + value);
        entity.mana += value;
    }

}
