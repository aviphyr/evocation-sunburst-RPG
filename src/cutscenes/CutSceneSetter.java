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
            gp.scenes.moveBit(3);
            gp.scenes.leaveScene(3);
        }

        else if (sceneNum == 1) {
            gp.scenes.moveY(gp.player, "up", 1, 3, 1);
            gp.scenes.moveX(gp.player, "right", 1, 3, 2);
            gp.scenes.moveY(gp.player, "down", 1, 3, 3);
            gp.scenes.moveX(gp.player, "left", 1, 3, 4);
            gp.scenes.leaveScene(5);
        }
    }
}
