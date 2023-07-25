package graphics;

import util.Globals;
import util.Moveable;

public class Laser extends Sprite implements Moveable {
    private boolean destroy = false;

    /**
     * Constructor de los objetos que recibe como parámetros, las coordenadas (x, y)
     * de origen de dibujo y el nombre del archivo a dibujar.
     *
     * @param x El valor de la coordenada x
     * @param y El valor de la coordenada y
     */
    public Laser(int x, int y) {

        super(Globals.LASER_SPRITE, x, y);
    }

    @Override
    public void move() {

        this.setY(this.getY() - 5);
    }

    public boolean isDestroy() {
        return destroy;
    }

    public void setDestroy(boolean destroy) {
        this.destroy = destroy;
    }
}
