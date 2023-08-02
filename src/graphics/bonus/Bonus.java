package graphics.bonus;

import graphics.Sprite;
import util.Moveable;

public class Bonus extends Sprite implements Moveable {

    private int yam;
    private boolean taken;
    private BonusType type;

    public Bonus(BonusType type, int x, int y) {

        super(type.getImageName(), x, y);
        this.yam = 1;
        this.taken = false;
        this.type = type;
    }

    @Override
    public void move() {

        y += yam;
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
