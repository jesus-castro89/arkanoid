package graphics.border;

import graphics.Sprite;
import ui.GamePanel;

public class Border extends Sprite {
    private int minX;
    private int minY;
    private int maxX;
    private int maxY;
    private GamePanel gamePanel;

    public Border(int x, int y, BorderType type, GamePanel gamePanel) {

        super(type.getImageName(), x, y);
        //Las siguientes líneas permiten obtener las coordenadas con base a la imagen de acuerdo al tipo de borde.
        this.minX = (int) this.getRect().getMinX();
        this.minY = (int) this.getRect().getMinY();
        this.maxX = (int) this.getRect().getMaxX();
        this.maxY = (int) this.getRect().getMaxY();
        this.gamePanel = gamePanel;
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
}
