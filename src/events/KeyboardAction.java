package events;

import graphics.Ball;
import ui.GamePanel;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyboardAction extends KeyAdapter {

    private final GamePanel gamePanel;

    public KeyboardAction(GamePanel gamePanel) {

        this.gamePanel = gamePanel;
    }

    @Override
    public void keyPressed(KeyEvent e) {

        for (Ball actualBall : this.gamePanel.getBalls()) {

            if (this.gamePanel.getBalls().size() == 1) {

                actualBall.keyPressed(e);
            }
        }
        this.gamePanel.getPaddle().keyPressed(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {

        for (Ball actualBall : this.gamePanel.getBalls()) {

            if (this.gamePanel.getBalls().size() == 1) {

                actualBall.keyReleased(e);
            }
        }
        this.gamePanel.getPaddle().keyReleased(e);
    }
}
