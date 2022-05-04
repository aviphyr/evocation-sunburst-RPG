package cutscenes;

import entity.Entity;
import main.GamePanel;

import java.util.Timer;

public class CutScene
{
    GamePanel gp;

    public boolean active = false;

    public int actionNum = 0;

    public CutScene(GamePanel gp)
    {
        this.gp = gp;
    }

    public void moveX(Entity e, String direction, double duration, int actionNum)
    {
        if(!active && actionNum == this.actionNum + 1)
        {
            active = true;
            this.actionNum = actionNum;
            if (direction.equals("left")) {
                e.keyH.leftPressed = true;
            }
            if (direction.equals("right")) {
                e.keyH.rightPressed = true;
            }

            Timer t = new java.util.Timer();
            t.schedule(
                    new java.util.TimerTask() {
                        @Override
                        public void run() {
                            e.keyH.leftPressed = false;
                            e.keyH.rightPressed = false;
                            active = false;
                            t.cancel();
                        }
                    },
                    Math.round(duration * 1000));
            //System.out.println(duration);
        }
        else
        {
            Timer t = new java.util.Timer();
            t.schedule(
                    new java.util.TimerTask() {
                        @Override
                        public void run() {
                            moveX(e, direction, duration, actionNum);
                            t.cancel();
                        }
                    },
                    Math.round(20));
        }
    }

    public void moveY(Entity e, String direction, double duration, int actionNum)
    {
        if(!active && actionNum == this.actionNum + 1)
        {
            active = true;
            this.actionNum = actionNum;
            if (direction == "up")
                e.keyH.upPressed = true;
            if (direction == "down")
                e.keyH.downPressed = true;

            Timer t = new java.util.Timer();
            t.schedule(
                    new java.util.TimerTask() {
                        @Override
                        public void run() {
                            e.keyH.upPressed = false;
                            e.keyH.downPressed = false;
                            active = false;
                            t.cancel();
                        }
                    },
                    Math.round(duration * 1000));
            //System.out.println(duration);
        }
        else
        {
            Timer t = new java.util.Timer();
            t.schedule(
                    new java.util.TimerTask() {
                        @Override
                        public void run() {
                            moveY(e, direction, duration, actionNum);
                            t.cancel();
                        }
                    },
                    Math.round(20));
        }

    }
}
