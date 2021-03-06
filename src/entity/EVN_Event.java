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
    //HashMap<String, String> dialogues = new HashMap<String, String>();
    String dialogue;
    int sceneNum;
    int teleportDialouge;

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

    public EVN_Event(GamePanel gp, int eventType, boolean doesRepeat, int x, int y, int tpDialouge) { // coordinate based
        this("Coordinate Event", gp, eventType, doesRepeat);
        this.x = x;
        this.y = y;
        this.teleportDialouge = tpDialouge;
    }

    public EVN_Event(GamePanel gp, int eventType, boolean doesRepeat, String dialogue) { // dialogue
        this("Dialogue Event", gp, eventType, doesRepeat);
        this.dialogue = dialogue;
    }
    public EVN_Event(GamePanel gp, int eventType, boolean doesRepeat, int sceneNum)
    {
        this("CutScene Event", gp, eventType, doesRepeat);
        this.sceneNum = sceneNum;
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
                    gp.player.setHat(true);
                    gp.gameState = gp.dialogueState;
                    switch(teleportDialouge){
                        case 1:
                            gp.ui.currentDialogue = "OH that's a cool ha-WHA!\n\nWHERE AM I!!?";
                            break;
                        case 2:
                            gp.ui.currentDialogue = "Ouw, my eyes.";
                            break;
                        case 3:
                            gp.ui.currentDialogue = "Oh...\nmy...\ngod. What have you done";
                            break;
                        default:
                            gp.ui.currentDialogue = "Whooosh!";
                    }
                    gp.player.worldX = gp.tileSize*x;
                    gp.player.worldY = gp.tileSize*y;
                    break;
                case 4: // dialogue
                    // This case creates a complex dialogue state. (It's just an array of dialogues)

                    // Debug
                    /*System.out.println("Dialogues:");
                    for (String x : dialogues.keySet()) {
                        System.out.println(x + ": \"" + dialogues.get(x) + "\"");
                    }
                    System.out.println();*/
                    gp.gameState = gp.dialogueState;
                    gp.ui.currentDialogue = dialogue;

                    // Dialogue list
                    /*for (String x : dialogues.keySet()) {
                        // entityColor = new Color(119, 192, 119);
                        gp.gameState = gp.dialogueState;
                        gp.ui.currentDialogue = dialogues.get(x);
                        System.out.println("Current dialogue: " + x);
                    }*/
                    break;
                case 5: //Cutscenes

                    gp.gameState = gp.cutSceneState;
                    gp.scenePacks.runScene(sceneNum);
            }
        }
    }
}
