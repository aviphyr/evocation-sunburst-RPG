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

    public void moveX(Entity e, String direction, double duration, int speed, int actionNum)
    {
        if(!active && actionNum == this.actionNum + 1)
        {
            e.speed = speed;
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
                            moveX(e, direction, duration, speed, actionNum);
                            t.cancel();
                        }
                    },
                    Math.round(20));
        }
    }

    public void moveY(Entity e, String direction, double duration, int speed, int actionNum)
    {
        if(!active && actionNum == this.actionNum + 1)
        {
            e.speed = speed;
            active = true;
            this.actionNum = actionNum;
            if (direction.equals("down"))
                e.keyH.downPressed = true;
            if (direction.equals("up"))
                e.keyH.upPressed = true;


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
                            moveY(e, direction, duration, speed, actionNum);
                            t.cancel();
                        }
                    },
                    Math.round(20));
        }

    }

    public void leaveScene(int actionNum)
    {
        if(!active && actionNum == this.actionNum + 1)
        {
            gp.gameState = gp.playState;
        }
        else
        {
            Timer t = new java.util.Timer();
            t.schedule(
                    new java.util.TimerTask() {
                        @Override
                        public void run() {
                            leaveScene(actionNum);
                            t.cancel();
                        }
                    },
                    Math.round(20));
        }

    }

    public void moveBit(String direction, int actionNum)
    {
        if(!active && actionNum == this.actionNum + 1)
        {
            gp.gameState = gp.playState;
            Timer t;
            switch(direction)
            {
                case "up":
                    moveY(gp.player, "up", 0.5, 4, actionNum);
                    break;
                case "down":
                    moveY(gp.player, "down", 0.5, 4, actionNum);
                    break;
                case "left":
                    moveX(gp.player, "left", 0.5, 4, actionNum);
                    break;
                case "right":
                    moveX(gp.player, "right", 0.5, 4, actionNum);
                    break;
            }
        }
        else
        {
            Timer t = new java.util.Timer();
            t.schedule(
                    new java.util.TimerTask() {
                        @Override
                        public void run() {
                            moveBit(direction, actionNum);
                            t.cancel();
                        }
                    },
                    Math.round(0));
        }
    }

    public void speech(String dialouge, int actionNum){

        if(!active && actionNum == this.actionNum + 1)
        {
            gp.gameState = gp.dialogueState;
            gp.ui.currentDialogue = dialouge;

            Timer t = new java.util.Timer();
            t.schedule(
                    new java.util.TimerTask() {
                        @Override
                        public void run() {
                            gp.gameState = gp.cutSceneState;
                            active = false;
                            t.cancel();
                        }
                    },
                    Math.round(5000));
        }
        else
        {
            Timer t = new java.util.Timer();
            t.schedule(
                    new java.util.TimerTask() {
                        @Override
                        public void run() {
                            speech(dialouge, actionNum);
                            t.cancel();
                        }
                    },
                    Math.round(20));
        }
    }
}
