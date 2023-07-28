package graphics.brick;

import graphics.Sprite;
import ui.GamePanel;

import java.io.Serializable;

public class Brick extends Sprite implements Serializable {

    private int minX;
    private int minY;
    private int maxX;
    private int maxY;
    private int life;
    private int points;
    private boolean destroy;
    private GamePanel gamePanel;

    public Brick(BrickType type, int x, int y) {
        super(type.getImageName(), x, y);
        this.life = type.getLife();//⇽ Esto permite asociar al bloque la vida de su tipo
        this.points = type.getPoints();//⇽ Esto asocia el puntaje del bloque con su tipo
        this.destroy = false;//⇽ Esto permite indicar que el bloque en un principio es visible
        this.minX = (int) this.getRect().getMinX();
        this.minY = (int) this.getRect().getMinY();
        this.maxX = (int) this.getRect().getMaxX();
        this.maxY = (int) this.getRect().getMaxY();
    }

    public void updateRect() {

        this.minX = (int) this.getRect().getMinX();
        this.minY = (int) this.getRect().getMinY();
        this.maxX = (int) this.getRect().getMaxX();
        this.maxY = (int) this.getRect().getMaxY();
    }

    public void minusLife() {

        if (this.getLife() > 1) {

            this.setLife(this.getLife() - 1);
        } else if (this.getLife() > 0) {
            this.setLife(0);
            this.setDestroy(true);
        }
    }

    public GamePanel getGamePanel() {
        return gamePanel;
    }

    public void setGamePanel(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public int getMinX() {
        return minX;
    }

    public void setMinX(int minX) {
        this.minX = minX;
    }

    public int getMinY() {
        return minY;
    }

    public void setMinY(int minY) {
        this.minY = minY;
    }

    public int getMaxX() {
        return maxX;
    }

    public void setMaxX(int maxX) {
        this.maxX = maxX;
    }

    public int getMaxY() {
        return maxY;
    }

    public void setMaxY(int maxY) {
        this.maxY = maxY;
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public boolean isDestroy() {
        return destroy;
    }

    public void setDestroy(boolean destroy) {
        this.destroy = destroy;
    }
}
