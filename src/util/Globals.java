package util;

import java.awt.*;

public interface Globals {

    String BALL_SPRITE = "ball.gif";
    Dimension PANEL_DIMENSION = new Dimension(466, 640);
    Dimension MENU_DIMENSION = new Dimension(200, 630);
    int BALL_MARGIN = ((Globals.PADDLE_WIDTH / 2) + Globals.BRICK_MARGIN) - (Globals.BALL_WIDTH / 2);
    int PADDLE_WIDTH = 64;
    int BALL_WIDTH = 20;
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
}
