package main;

import java.awt.*;

public class EventHandler {
    GamePanel gp;
    Rectangle eventRect;
    int eventRectDefaultX, eventRectDefaultY;

    public EventHandler(GamePanel gp) {
        this.gp = gp;

        eventRect = new Rectangle();
        eventRect.x = 48;
        eventRect.y = 48;
        eventRect.width = 2;
        eventRect.height = 2;
        eventRectDefaultX = eventRect.x;
        eventRectDefaultY = eventRect.y;
    }

    public void checkEvent(){
        if (hit(34, 43, "up")) {damagePit(gp.dialogueState);}
        if (hit(50, 52, "right")) {healthRestore(gp.dialogueState);}
        if (hit(24, 32, "up")) {teleport(gp.dialogueState, 44, 86);}

    }

    public boolean hit(int eventCol, int eventRow, String reqDirection){
        boolean hit = false;
        gp.player.hitbox.x = gp.player.worldX + gp.player.hitbox.x;
        gp.player.hitbox.y = gp.player.worldY + gp.player.hitbox.y;
        eventRect.x = eventCol * gp.tileSize + eventRect.x;
        eventRect.y = eventRow * gp.tileSize + eventRect.y;

        if (gp.player.hitbox.intersects(eventRect)){
            if (gp.player.direction.contentEquals(reqDirection) || reqDirection.contentEquals("any")){
                hit = true;
            }
        }

        gp.player.hitbox.x = gp.player.hitboxDefaultX;
        gp.player.hitbox.y = gp.player.hitboxDefaultY;
        eventRect.x = eventRectDefaultX;
        eventRect.y = eventRectDefaultY;

        return hit;

    }
    public void damagePit(int gameState){
        gp.gameState = gameState;
        gp.ui.currentDialogue = "You fell into a pit";
        gp.player.life -= 1;

    }

    public void teleport(int gameState, int x, int y){
        gp.gameState = gameState;
        gp.ui.currentDialogue = "Wooosh!";
        gp.player.worldX = gp.tileSize*x;
        gp.player.worldY = gp.tileSize*y;

    }

    public void healthRestore(int gameState){
        if(gp.keyH.enterPressed){
            gp.gameState = gameState;
            gp.ui.currentDialogue = "You magically become healed...";
            gp.player.life = gp.player.maxLife;
        }
        gp.keyH.enterPressed = false;
    }



}
