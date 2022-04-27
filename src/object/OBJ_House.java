package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_House extends Entity {
    //GamePanel gp;
    public OBJ_House(GamePanel gp, int worldX, int worldY, int type) {
        super(gp);

        this.worldX = worldX * gp.tileSize;
        this.worldY = worldY * gp.tileSize;
        this.type = type_noPickup;

        name = "House";
        description = "How did you\n pick this up?";
        hitbox.width = 0;
        hitbox.height = 0;

        switch (type){
            case 1: down1 = setup("/objects/housing/HouseBrown", gp.tileSize, gp.tileSize*2); break;
            case 2: down1 = setup("/objects/housing/HousePurple", gp.tileSize, gp.tileSize*2); break;
            case 3: down1 = setup("/objects/housing/HouseRed", gp.tileSize, gp.tileSize*2); break;
            case 4: down1 = setup("/objects/housing/HouseBlue", gp.tileSize, gp.tileSize*2); break;
            case 5: down1 = setup("/objects/housing/HouseTeal", gp.tileSize, gp.tileSize*2); break;
            case 6: down1 = setup("/objects/housing/HouseYellow", gp.tileSize, gp.tileSize*2); break;
        }
        /*else if (type == 2){
            switch (style){
                case 1: down1 = setup("/objects/housing/RoofYellow", gp.tileSize, gp.tileSize); break;
                case 2: down1 = setup("/objects/housing/RoofYellow", gp.tileSize, gp.tileSize); break;
            }
        }*/
    }
}
