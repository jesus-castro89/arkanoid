package graphics;

import ui.GamePanel;
import util.Moveable;
import util.Globals;

public class Ball extends Sprite implements Moveable {

    private int xam;
    private int yam;
    private boolean stop;
    private int xsm;
    private GamePanel gamePanel;

    public Ball(int x, int y, GamePanel gamePanel) {

        super(Globals.BALL_SPRITE, x, y);
        this.xam = 1;
        this.yam = -1;
        this.stop = true;
        this.xsm = 0;
        this.gamePanel = gamePanel;
    }

    public void resetState() {

        this.x = Globals.INIT_BALL_X;
        this.y = Globals.INIT_BALL_Y;
        this.xam = 1;
        this.yam = -1;
        this.stop = true;
        this.xsm = 0;
    }

    @Override
    public void move() {

    }

    public GamePanel getGamePanel() {
        return gamePanel;
    }

    public void setGamePanel(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public int getXam() {
        return xam;
    }

    public void setXam(int xam) {
        this.xam = xam;
    }

    public int getYam() {
        return yam;
    }

    public void setYam(int yam) {
        this.yam = yam;
    }

    public boolean isStop() {
        return stop;
    }

    public void setStop(boolean stop) {
        this.stop = stop;
    }

    public int getXsm() {
        return xsm;
    }

    public void setXsm(int xsm) {
        this.xsm = xsm;
    }
}
