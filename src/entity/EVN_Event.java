package entity;

import main.GamePanel;

public class EVN_Event extends Entity {
    GamePanel gp;
    public boolean canContact = true;
    int previousEventX, previousEventY;
    int eventType;
    int x, y;
    public EVN_Event(GamePanel gp, int eventType, int x, int y) {
        super(gp);
        this.gp = gp;

        type = type_event;
        name = "Default Event";
        collision = false;

        this.eventType = eventType;
        this.x = x;
        this.y = y;

    }
    public void use(Entity player){

        int xDistance = Math.abs(gp.player.worldX - previousEventX);
        int yDistance = Math.abs(gp.player.worldY - previousEventY);

        int distance = Math.max(xDistance, yDistance);
        if (distance > gp.tileSize){
            canContact = true;
        }

        if (canContact){
            gp.gameState = gp.dialogueState;
            canContact = false;
            previousEventX = gp.player.worldX;
            previousEventY = gp.player.worldY;
            switch (eventType){
                case 1:
                    gp.ui.currentDialogue = "You fell into a pit.";
                    gp.player.life -= 1;
                    break;
                case 2:
                    gp.ui.currentDialogue = "You get healed.";
                    gp.player.life = gp.player.maxLife;
                    gp.player.mana = gp.player.maxMana;
                    break;
                case 3:
                    gp.ui.currentDialogue = "Whooosh!";
                    gp.player.worldX = gp.tileSize*x;
                    gp.player.worldY = gp.tileSize*y;
                    break;
            }
        }
    }
}
