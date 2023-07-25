package graphics.paddle;

public enum PaddleType {
    NORMAL("paddle.png"), LARGE("paddle-large.png"),
    LASER("paddle-laser.png");

    private final String imageName;

    PaddleType(String imageName) {
        this.imageName = imageName;
    }

    public String getImageName() {
        return imageName;
    }
}
