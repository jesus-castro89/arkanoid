package graphics.paddle;

import graphics.Sprite;
import util.Globals;
import util.Moveable;

import javax.swing.*;

public class Paddle extends Sprite implements Moveable {

    private int xam;
    private int minX;
    private int minY;
    private int maxX;
    private int maxY;
    private int border; //Ancho del borde de la barra
    private int normalZone; //Ancho desde el borde de la barra hasta la zona central
    private int centerZone; //Ancho de la zona central
    private int endFirstBorder; //2
    private int startCenter; //3
    private int endCenter; //4
    private int startSecondBorder; //5
    private int endSecondBorder; //6
    private int lowPart; //7
    private boolean shootMode;

    public Paddle(PaddleType type, int x, int y) {

        super(type.getImageName(), x, y);
        this.xam = 0;
        changeType(type);
    }

    @Override
    public void move() {

    }

    public void changeType(PaddleType type) {
        try {

            this.imageName = type.getImageName();
            this.getImageDimensions();
            this.border = this.getImageWidth() / 4;
            this.normalZone = (int) (this.getImageWidth() / 6.4);
            this.centerZone = (int) (this.getImageWidth() / 5.3);
            this.endFirstBorder = this.x + this.border;
            this.startCenter = this.endFirstBorder + this.normalZone;
            this.endCenter = this.startCenter + this.centerZone;
            this.startSecondBorder = this.x + this.getImageWidth() - this.border;
            this.endSecondBorder = this.x + this.getImageWidth();
            this.lowPart = this.y + this.getImageHeight();
            this.shootMode = switch (type) {
                case LARGE, NORMAL -> false;
                case LASER -> true;
            };
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "¡Error al cargar Imagen de Juego!");
        }
    }

    public void resetState() {

        this.x = Globals.INIT_PADDLE_X;
        this.y = Globals.INET_PADDLE_Y;
    }

    public int getXam() {
        return xam;
    }

    public void setXam(int xam) {
        this.xam = xam;
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

    public int getBorder() {
        return border;
    }

    public void setBorder(int border) {
        this.border = border;
    }

    public int getNormalZone() {
        return normalZone;
    }

    public void setNormalZone(int normalZone) {
        this.normalZone = normalZone;
    }

    public int getCenterZone() {
        return centerZone;
    }

    public void setCenterZone(int centerZone) {
        this.centerZone = centerZone;
    }

    public int getEndFirstBorder() {
        return endFirstBorder;
    }

    public void setEndFirstBorder(int endFirstBorder) {
        this.endFirstBorder = endFirstBorder;
    }

    public int getStartCenter() {
        return startCenter;
    }

    public void setStartCenter(int startCenter) {
        this.startCenter = startCenter;
    }

    public int getEndCenter() {
        return endCenter;
    }

    public void setEndCenter(int endCenter) {
        this.endCenter = endCenter;
    }

    public int getStartSecondBorder() {
        return startSecondBorder;
    }

    public void setStartSecondBorder(int startSecondBorder) {
        this.startSecondBorder = startSecondBorder;
    }

    public int getEndSecondBorder() {
        return endSecondBorder;
    }

    public void setEndSecondBorder(int endSecondBorder) {
        this.endSecondBorder = endSecondBorder;
    }

    public int getLowPart() {
        return lowPart;
    }

    public void setLowPart(int lowPart) {
        this.lowPart = lowPart;
    }

    public boolean isShootMode() {
        return shootMode;
    }

    public void setShootMode(boolean shootMode) {
        this.shootMode = shootMode;
    }
}
