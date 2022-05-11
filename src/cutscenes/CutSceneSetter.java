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

    }
}
