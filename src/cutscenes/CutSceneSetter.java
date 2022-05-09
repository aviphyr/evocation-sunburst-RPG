package cutscenes;

import main.GamePanel;

public class CutSceneSetter
{
    GamePanel gp;

    public CutSceneSetter(GamePanel gp)
    {
        this.gp = gp;
    }

    public void runScene(int sceneNum)
    {
        //Beginning Scene
        if(sceneNum == 0)
        {
            //gp.scenes.moveY(gp.player, "up", 0.5, 2,1);
            //gp.scenes.moveBit(1);
            //gp.scenes.moveY(gp.player, "up", 0.5, 2,1);
            gp.scenes.moveY(gp.player, "up", 2, 2,1);
            gp.scenes.moveY(gp.player, "down", 0.75, 2,2);
            gp.scenes.moveBit("down", 3);
            gp.scenes.leaveScene(3);
        }

        else if (sceneNum == 1) {
            gp.scenes.moveY(gp.player, "up", 0.5, 3, 1);
            //gp.scenes.speech("Ah, what a lovely new home.\nRoomy, spacious, a constant flow\nof pollution through the\nroadside drains.", 2);
            gp.scenes.moveBit("left",2);
            gp.scenes.leaveScene(3);
        }

        else if (sceneNum == 2) {
            gp.scenes.moveX(gp.player, "right", 0.5, 3,1);
            //gp.scenes.speech("Hopefully more food will come\ntrough there soon.", 2);
            gp.scenes.moveBit("up",1);
            gp.scenes.leaveScene(2);
        }

        else if (sceneNum == 3) {
            gp.scenes.moveY(gp.player, "down", 0.5, 3,1);
            //gp.scenes.speech("Hopefully more food will come\ntrough there soon.", 2);
            //gp.scenes.moveBit("left", 2);
            gp.scenes.leaveScene(2);
        }

    }
}
