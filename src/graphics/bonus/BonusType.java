package graphics.bonus;

public enum BonusType {

    LASER("Laser.gif"), ENLARGE("Enlarge.gif"), SMALL("Small.gif"),
    PLAYER("Player.gif");
    private final String imageName;

    BonusType(String imageName) {
        this.imageName = imageName;
    }

    public String getImageName() {
        return imageName;
    }
}
