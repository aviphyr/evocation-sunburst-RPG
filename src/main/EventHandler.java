package main;

import java.awt.*;

public class EventHandler {
    GamePanel gp;
    EventRect eventRect[][];
    int previousEventX, previousEventY;
    boolean canTouchEvent = true;

    public EventHandler(GamePanel gp) {
        this.gp = gp;

        eventRect = new EventRect[gp.maxWorldCol][gp.maxWorldRow];

        int col = 0;
        int row = 0;
        while (col < gp.maxWorldCol && row < gp.maxWorldRow){
            eventRect[col][row] = new EventRect();
            eventRect[col][row].y = 48;
            eventRect[col][row].width = 16;
            eventRect[col][row].height = 16;
            eventRect[col][row].eventRectDefaultX = eventRect[col][row].x;
            eventRect[col][row].eventRectDefaultY = eventRect[col][row].y;
            col++;
            if (col == gp.maxWorldCol){
                col = 0;
                row++;
            }
        }

    }

    public void checkEvent(){
        int xDistance = Math.abs(gp.player.worldX - previousEventX);
        int yDistance = Math.abs(gp.player.worldY - previousEventY);

        int distance = Math.max(xDistance, yDistance);
        if (distance > gp.tileSize){
            canTouchEvent = true;
        }
        if (canTouchEvent){
            //if (hit(34, 43, "any")) {damagePit(34, 43, gp.dialogueState);}
            //if (hit(50, 52, "any")) {healthRestore(50, 52, gp.dialogueState);}
            //if (hit(24, 32, "any")) {teleport(24, 32, gp.dialogueState, 44, 86);}
        }

    }

    public boolean hit(int col, int row, String reqDirection){
        boolean hit = false;
        gp.player.hitbox.x = gp.player.worldX + gp.player.hitbox.x;
        gp.player.hitbox.y = gp.player.worldY + gp.player.hitbox.y;
        eventRect[col][row].x = col * gp.tileSize + eventRect[col][row].x;
        eventRect[col][row].y = row * gp.tileSize + eventRect[col][row].y;

        if (gp.player.hitbox.intersects(eventRect[col][row]) && !eventRect[col][row].eventDone){
            if (gp.player.direction.contentEquals(reqDirection) || reqDirection.contentEquals("any")){
                hit = true;
                previousEventX = gp.player.worldX;
                previousEventY = gp.player.worldY;
            }
        }

        gp.player.hitbox.x = gp.player.hitboxDefaultX;
        gp.player.hitbox.y = gp.player.hitboxDefaultY;
        eventRect[col][row].x = eventRect[col][row].eventRectDefaultX;
        eventRect[col][row].y = eventRect[col][row].eventRectDefaultY;

        return hit;

    }
    public void damagePit(int col, int row, int gameState){
        gp.gameState = gameState;
        gp.ui.currentDialogue = "You fell into a pit";
        gp.player.life -= 1;
        //eventRect[col][row].eventDone = true;
        canTouchEvent = false;

    }

    public void teleport(int col, int row, int gameState, int x, int y){
        gp.gameState = gameState;
        gp.ui.currentDialogue = "OH that's a cool ha-, WHA! WHERE AM I!!?";
        gp.player.worldX = gp.tileSize*130;
        gp.player.worldY = gp.tileSize*18;
        //entity.Player.getHat();

    }

    public void healthRestore(int col, int row, int gameState){
        if(gp.keyH.enterPressed){
            gp.gameState = gameState;
            gp.ui.currentDialogue = "You magically become healed...";
            gp.player.life = gp.player.maxLife;
            gp.player.mana = gp.player.maxMana;
            gp.aSetter.setMonster();
        }
        gp.keyH.enterPressed = false;
    }



}
