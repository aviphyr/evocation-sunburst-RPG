package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_CoinBronze extends Entity {
    GamePanel gp;
    public OBJ_CoinBronze(GamePanel gp) {
        super(gp);
        this.gp = gp;

        type = type_pickupOnly;
        name = "Bronze Coin";
        value = 1;
        down1 = setup("/objects/coin_bronze", gp.tileSize, gp.tileSize);
    }
    public void use(Entity entity){
        gp.playSE(6);
        gp.ui.addMessage("Coin +" + value);
        gp.player.coin += value;
    }
}
