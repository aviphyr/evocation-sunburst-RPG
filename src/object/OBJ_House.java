package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_House extends Entity {
    //GamePanel gp;
    public OBJ_House(GamePanel gp, int worldX, int worldY, int type, int style) {
        super(gp);

        this.worldX = worldX * gp.tileSize;
        this.worldY = worldY * gp.tileSize;
        this.type = type_noPickup;

        name = "House";
        description = "How did you\n pick this up?";
        hitbox.width = 0;
        hitbox.height = 0;

        if (type == 1){
            switch (style){
                case 1: down1 = setup("/objects/housing/HouseBottom", gp.tileSize, gp.tileSize); break;
                case 2: down1 = setup("/objects/housing/HouseBottom", gp.tileSize, gp.tileSize); break;
            }
        }
        else if (type == 2){
            switch (style){
                case 1: down1 = setup("/objects/housing/RoofYellow", gp.tileSize, gp.tileSize); break;
                case 2: down1 = setup("/objects/housing/RoofYellow", gp.tileSize, gp.tileSize); break;
            }
        }
    }
}
