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
            gp.scenes.moveY(gp.player, "up", 0.5, 2,1);
            gp.scenes.moveY(gp.player, "up", 1.90, 2,2);
            System.out.println("Yo");
            gp.scenes.moveY(gp.player, "down", 0.75, 2,3);
            gp.scenes.moveBit(4);
            gp.scenes.leaveScene(5);
        }
    }
}
