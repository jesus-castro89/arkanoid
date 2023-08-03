package graphics.bonus;

import graphics.Sprite;
import ui.GamePanel;
import util.Moveable;

public class Bonus extends Sprite implements Moveable {

    private int yam;
    private boolean taken;
    private BonusType type;
    private GamePanel gamePanel;

    public Bonus(BonusType type, int x, int y, GamePanel gamePanel) {

        super(type.getImageName(), x, y);
        this.yam = 1;
        this.taken = false;
        this.type = type;
        this.gamePanel=gamePanel;
    }

    @Override
    public void move() {

        y += yam;
    }

    public GamePanel getGamePanel() {
        return gamePanel;
    }

    public void setGamePanel(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public BonusType getType() {
        return type;
    }

    public void setType(BonusType type) {
        this.type = type;
    }

    public int getYam() {
        return yam;
    }

    public void setYam(int yam) {
        this.yam = yam;
    }

    public boolean isTaken() {
        return taken;
    }

    public void setTaken(boolean taken) {
        this.taken = taken;
    }
}
