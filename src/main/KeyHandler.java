package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    GamePanel gp;
    public boolean upPressed, downPressed, leftPressed, rightPressed, interact;
    //Debug
    boolean showDebug;

    public KeyHandler(GamePanel gp){
        this.gp = gp;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        int code = e.getKeyCode();

        //Title State
        if(gp.gameState == gp.titleState)
        {
            System.out.println("Yo");
            if(code == KeyEvent.VK_UP)
            {
                gp.ui.commandNum--;
                if(gp.ui.commandNum < 0)
                {
                    gp.ui.commandNum = 2;
                }
            }
            if(code == KeyEvent.VK_DOWN)
            {
                gp.ui.commandNum++;
                if(gp.ui.commandNum > 2)
                {
                    gp.ui.commandNum = 0;
                }
            }
            if(code == KeyEvent.VK_ENTER)
            {
                if(gp.ui.commandNum == 0)
                {
                    gp.gameState = gp.playState;
                    gp.stopMusic();
                    gp.playMusic(5);
                }
                if(gp.ui.commandNum == 1)
                {
                    //Doesn't work yet
                }
                if(gp.ui.commandNum == 2)
                {
                    System.exit(0);
                }
            }
        }

        //Play State
        if(gp.gameState == gp.playState)
        {
            if(code == KeyEvent.VK_W || code == KeyEvent.VK_UP){
                upPressed = true;
            }
            if(code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN){
                downPressed = true;
            }
            if(code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT){
                leftPressed = true;
            }
            if(code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT){
                rightPressed = true;
            }

            if(code == KeyEvent.VK_ESCAPE)
            {
                gp.gameState = gp.pauseState;
            }

            //Debug
            if(code == KeyEvent.VK_F3){
                if(showDebug)
                    showDebug = false;
                else
                    showDebug = true;
            }

            if(code == KeyEvent.VK_F2){
                gp.tileM.loadMap("/maps/world2.txt");
            }
        }

        //Pause State
        if(gp.gameState == gp.pauseState)
        {
            if(code == KeyEvent.VK_ESCAPE)
            {
                gp.gameState = gp.playState;
            }
        }

        //Dialogue State
        if(gp.gameState == gp.dialogueState)
        {
            if(code == KeyEvent.VK_ESCAPE)
            {
                gp.gameState = gp.playState;
            }
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

        int code = e.getKeyCode();

        if(code == KeyEvent.VK_W || code == KeyEvent.VK_UP){
            upPressed = false;
        }
        if(code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN){
            downPressed = false;
        }
        if(code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT){
            leftPressed = false;
        }
        if(code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT){
            rightPressed = false;
        }
    }
}
