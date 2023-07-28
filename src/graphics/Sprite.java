package graphics;

import util.Globals;

import javax.swing.*;
import java.awt.*;
import java.io.Serializable;

public class Sprite implements Serializable {

    protected int x;
    protected int y;
    protected int imageWidth;
    protected int imageHeight;
    protected String imageName;

    public Sprite(String image, int x, int y) {

        this.imageName = image;
        this.x = x;
        this.y = y;
        getImageDimensions();
    }

    public void paint(Graphics2D g2d) {

        g2d.drawImage(this.getImage(), this.getX(), this.getY(), null);
        //Debug Collision Box
        g2d.setColor(Color.RED);
        g2d.drawRect((int) getRect().getX(),
                (int) getRect().getY(),
                (int) getRect().getWidth(),
                (int) getRect().getHeight());
    }

    public Rectangle getRect() {

        return new Rectangle(this.getX(), this.getY(), this.getImageWidth(), this.getImageHeight());
    }

    public void getImageDimensions() {

        this.setImageWidth(this.getImage().getWidth(null));
        this.setImageHeight(this.getImage().getHeight(null));
    }

    public Image getImage() {
        return new ImageIcon(Globals.SPRITE_CACHE.getSprite(this.imageName)).getImage();
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getImageWidth() {
        return imageWidth;
    }

    public void setImageWidth(int imageWidth) {
        this.imageWidth = imageWidth;
    }

    public int getImageHeight() {
        return imageHeight;
    }

    public void setImageHeight(int imageHeight) {
        this.imageHeight = imageHeight;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }
}
