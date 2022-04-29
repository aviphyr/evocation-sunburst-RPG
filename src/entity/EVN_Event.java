package entity;

import main.GamePanel;

public class EVN_Event extends Entity {
    GamePanel gp;
    public boolean canContact = true;
    int previousEventX, previousEventY;
    public EVN_Event(GamePanel gp, int eventType) {
        super(gp);
        this.gp = gp;
        type = type_event;
        name = "Default Event";
        collision = false;
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
            gp.ui.currentDialogue = "You fell into a pit";
            System.out.println("Event touched");
            canContact = false;
            previousEventX = gp.player.worldX;
            previousEventY = gp.player.worldY;
        }
    }
}
