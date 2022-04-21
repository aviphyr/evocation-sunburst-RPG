package main;

import save_load.Master;
import save_load.Save;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    GamePanel gp;
    public boolean upPressed, downPressed, leftPressed, rightPressed, enterPressed, pausing;
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

        pausing = false;

        //Title State
        if(gp.gameState == gp.titleState)
        {
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
                //NEW GAME
                if(gp.ui.commandNum == 0)
                {
                    gp.gameState = gp.playState;
                    gp.stopMusic();
                    gp.playMusic(5);
                }
                //LOAD GAME
                if(gp.ui.commandNum == 1)
                {
                    Main.loading.load();
                }
                //EXIT
                if(gp.ui.commandNum == 2)
                {
                    System.exit(0);
                }
            }
        }

        //Play State
        if(gp.gameState == gp.playState)
        {

            if(code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN){
                downPressed = true;
            }
            if(code == KeyEvent.VK_W || code == KeyEvent.VK_UP){
                upPressed = true;
            }
            if(code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT){
                leftPressed = true;
            }
            if(code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT){
                rightPressed = true;
            }
            if(code == KeyEvent.VK_ENTER){
                enterPressed = true;
            }

            if(code == KeyEvent.VK_ESCAPE)
            {
                pausing = true;
            }
            else
            {
                pausing = false;
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
            if(code == KeyEvent.VK_S && e.isControlDown())
            {
                System.out.println("Saving...");
                Main.saving.save();
            }
            if(code == KeyEvent.VK_ESCAPE)
            {
                pausing = false;
                gp.gameState = gp.playState;
            }


            //Menu Selections
            if(code == KeyEvent.VK_UP)
            {
                gp.ui.pauseNum--;
                if(gp.ui.pauseNum < 0)
                {
                    gp.ui.pauseNum = 2;
                }
            }
            if(code == KeyEvent.VK_DOWN)
            {
                gp.ui.pauseNum++;
                if(gp.ui.pauseNum > 2)
                {
                    gp.ui.pauseNum = 0;
                }
            }
            if(code == KeyEvent.VK_ENTER)
            {
                //Resume
                if(gp.ui.pauseNum == 0)
                {
                    gp.gameState = gp.playState;
                    //gp.stopMusic();
                    //gp.playMusic(5);
                }
                //Save
                if(gp.ui.pauseNum == 1)
                {
                    Main.saving.save();
                }
                //Save and Quit
                if(gp.ui.pauseNum == 2)
                {
                    Main.saving.save();
                    gp.stopMusic();
                    gp.gameState = gp.titleState;
                }
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

        if(pausing)
        {
            pausing = false;
            gp.gameState = gp.pauseState;
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

        if(code == KeyEvent.VK_ESCAPE)
        {
            code = 0;
        }
    }
}
