package entity;

import main.GamePanel;

import java.awt.*;
import java.util.HashMap;

public class EVN_Event extends Entity {
    GamePanel gp;
    public boolean canContact = true;
    int previousEventX, previousEventY, x, y;; // for distance calculation
    int eventType;
    boolean doesRepeat;
    HashMap<String, String> dialogues = new HashMap<String, String>();

    private EVN_Event(String name, GamePanel gp, int eventType, boolean doesRepeat){ // default constructor
        super(gp);
        this.gp = gp;

        type = type_event;
        this.name = name;
        collision = false;
        this.doesRepeat = doesRepeat;
        this.eventType = eventType;
    }

    public EVN_Event(GamePanel gp, int eventType, boolean doesRepeat) { // simple event
        this("Simple Event", gp, eventType, doesRepeat);
    }

    public EVN_Event(GamePanel gp, int eventType, boolean doesRepeat, int x, int y) { // coordinate based
        this("Coordinate Event", gp, eventType, doesRepeat);
        this.x = x;
        this.y = y;
    }

    public EVN_Event(GamePanel gp, int eventType, boolean doesRepeat, HashMap<String, String> dialogues) { // dialogue
        this("Dialogue Event", gp, eventType, doesRepeat);
        this.dialogues = dialogues;
    }

    public void use(Entity player){
        // This method looks at the distance between the player and the event and resets the
        // canContact variable to true if the player is more than 1 tile away from the event.
        if (doesRepeat){
            int xDistance = Math.abs(gp.player.worldX - previousEventX);
            int yDistance = Math.abs(gp.player.worldY - previousEventY);
            int distance = Math.max(xDistance, yDistance);
            if (distance > gp.tileSize){canContact = true;}
        }

        // Setup for the switch-case that depends on the event type
        if (canContact){
            canContact = false;
            previousEventX = gp.player.worldX;
            previousEventY = gp.player.worldY;

            // Event types
            switch (eventType){
                case 1: // damage
                    gp.gameState = gp.dialogueState;
                    gp.ui.currentDialogue = "You fell into a pit.";
                    gp.player.life -= 1;
                    break;
                case 2: // healing
                    gp.gameState = gp.dialogueState;
                    gp.ui.currentDialogue = "You get healed.";
                    gp.player.life = gp.player.maxLife;
                    gp.player.mana = gp.player.maxMana;
                    break;
                case 3: // tp
                    gp.gameState = gp.dialogueState;
                    gp.ui.currentDialogue = "OH that's a cool ha-\n\nWHA! WHERE AM I!!?";
                    gp.player.worldX = gp.tileSize*x;
                    gp.player.worldY = gp.tileSize*y;
                    break;
                case 4: // dialogue
                    // This case creates a complex dialogue state. (It's just an array of dialogues)

                    // Debug
                    System.out.println("Dialogues:");
                    for (String x : dialogues.keySet()) {
                        System.out.println(x + ": \"" + dialogues.get(x) + "\"");
                    }
                    System.out.println();

                    // Dialogue list
                    for (String x : dialogues.keySet()) {
                    //    entityColor = new Color(119, 192, 119);
                        gp.gameState = gp.dialogueState;
                        gp.ui.currentDialogue = dialogues.get(x);
                        System.out.println("Current dialogue: " + x);
                    }


                    break;
            }
        }
    }
}
