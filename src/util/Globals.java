package util;

import graphics.Ball;

import java.awt.*;

public interface Globals {

    SpriteCache SPRITE_CACHE = new SpriteCache();
    String BALL_SPRITE = "ball.gif";
    String LASER_SPRITE = "laser.png";
    Dimension PANEL_DIMENSION = new Dimension(466, 640);
    Dimension MENU_DIMENSION = new Dimension(200, 630);
    int BALL_MARGIN = ((Globals.PADDLE_WIDTH / 2) + Globals.BRICK_MARGIN) - (Globals.BALL_WIDTH / 2);
    int PADDLE_WIDTH = 64;
    int LASER_WIDTH = 16;
    int BALL_WIDTH = 20;
    int BRICK_WIDTH = 42;
    int BRICK_HEIGHT = 20;
    int BORDER_HEIGHT = 19;
    int BRICK_MARGIN = 18;
    int INIT_PADDLE_X = 202;
    int INET_PADDLE_Y = 604;
    int INIT_BALL_X = 224;
    int INIT_BALL_Y = 585;
    int BRICKS_ROWS = 10;
    int BRICK_COLUMNS = 10;
    int PERIOD = 10;
    int START_LIVES = 3;
    int BOTTOM_EDGE = 621;

    static int random(int min, int max) {

        int range = max - min + 1;
        return (int) (Math.random() * range) + min;
    }

    static double distancia(Ball ball, int AX, int AY, int BX, int BY) {

        double distancia = 0;
        double centroX = ball.getRect().getCenterX();
        double centroY = ball.getRect().getCenterY();
        double v = Math.pow((BX - AX), 2) + Math.pow((BY - AY), 2);
        double u = (((centroX - AX) * (BX - AX)) + ((centroY - AY) * (BY - AY))) / v;
        if (u >= 0 && u <= 1) {

            distancia = (((BX - AX) * (centroY - AY)) - ((BY - AY) * (centroX - AX))) / (Math.sqrt(v));
        } else if (u > 1) {

            distancia = Math.sqrt((Math.pow(centroX - BX, 2)) + (Math.pow(centroY - BY, 2)));
        } else if (u < 0) {

            distancia = Math.sqrt((Math.pow(centroX - AX, 2)) + (Math.pow(centroY - AY, 2)));
        }
        return Math.abs(distancia);
    }

    static void bounce(Ball ball, int section) {

        switch (section) {
            case 1 -> {
                if (ball.getXam() != 1) {

                    ball.setXam(-1);
                }
                ball.setYam(-1);
            }
            case 2 -> {
                ball.setXam(-1);
                ball.setYam(-1);
            }
            case 3 -> {
                if (ball.getXam() <= 0) {

                    ball.setXam(-1);
                } else {
                    ball.setXam(1);
                }
                ball.setYam(-1);
            }
            case 4 -> {
                ball.setXam(1);
                ball.setYam(-1);
            }
            case 5 -> {
                ball.setXam(ball.getXam() * -1);
                ball.setYam(-1);
            }
            case 6 -> {
                if (ball.getYam() == 1) {

                    ball.setYam(-1);
                }
            }
            case 7 -> {
                if (ball.getYam() == -1) {

                    ball.setYam(1);
                }
            }
            case 8 -> {
                if (ball.getXam() == 0 || ball.getXam() == 1) {

                    ball.setXam(-1);
                }
            }
            case 9 -> {
                if (ball.getXam() == 0 || ball.getXam() == -1) {

                    ball.setXam(1);
                }
            }
        }
    }
}
