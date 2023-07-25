package graphics.border;

public enum BorderType {
    LEFT("leftbar.png"), TOP("topbar.png"), RIGHT("rightbar.png");
    private final String imageName;

    BorderType(String imageName) {
        this.imageName = imageName;
    }

    public String getImageName() {
        return imageName;
    }
}
