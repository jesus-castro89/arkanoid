---
title: Procesando los movimientos
description: Creación de la clase KeyboardAction y de su implementación en la ventana.
editLink: false
---

# Procesando los movimientos

En este apartado trabajaremos tres cosas, en primer lugar la creación de la clase KeyboardAction y en segundo plano, como
recibir los movimientos desde el teclado y una breve actualización de la interfaz Global.

## KeyboardAction <Badge type="tip" text="Nuevo" vertical="middle" />

La clase `KeyboardAction`, nos permitirá procesar los eventos del teclado y delegar las acciones a las clases que hemos
trabajado anteriormente, así que veamos que tenemos por aquí.

```java
package events;

import graphics.Ball;
import ui.GamePanel;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyboardAction extends KeyAdapter {

    private final GamePanel gamePanel;

    public KeyboardAction(GamePanel gamePanel) {

        this.gamePanel = gamePanel;
    }

    @Override
    public void keyPressed(KeyEvent e) {

        for (Ball actualBall : this.gamePanel.getBalls()) {

            if (this.gamePanel.getBalls().size() == 1) {

                actualBall.keyPressed(e);
            }
        }
        this.gamePanel.getPaddle().keyPressed(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {

        for (Ball actualBall : this.gamePanel.getBalls()) {

            if (this.gamePanel.getBalls().size() == 1) {

                actualBall.keyReleased(e);
            }
        }
        this.gamePanel.getPaddle().keyReleased(e);
    }
}
```

Como podrás notar, lo único que hacemos aquí es recorrer el arreglo de pelotas en ambos casos y llamar a la función
homónima en cada clase, tanto de las pelotitas (`Ball`), como del jugador (`Paddle`).

## Globals <Badge type="warning" text="Modificada" vertical="middle" />

En la interfaz Globals agregamos algunas constantes y dos funciones que usaremos más adelante.

:::: code-group
::: code-group-item Anterior

```java
package util;

import java.awt.*;

public interface Globals {

    SpriteCache SPRITE_CACHE = new SpriteCache();
    String BALL_SPRITE = "ball.gif";
    String LASER_SPRITE = "laser.png";
    Dimension PANEL_DIMENSION = new Dimension(466, 640);
    Dimension MENU_DIMENSION = new Dimension(200, 630);
    int BALL_MARGIN = ((Globals.PADDLE_WIDTH / 2) + Globals.BRICK_MARGIN) - (Globals.BALL_WIDTH / 2);
    int PADDLE_WIDTH = 64;
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
}
```

:::
::: code-group-item ACTUALIZADO

```java{3,16,38-50,52-89}
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

        double centroX = ball.getRect().getCenterX();
        double centroY = ball.getRect().getCenterY();
        double v = Math.pow((BX - AX), 2) + Math.pow((BY - AY), 2);
        double u = (((centroX - AX) * (BX - AX)) + ((centroY - AY) * (BY - AY))) / v;
        double baseDistance = (((BX - AX) * (centroY - AY)) - ((BY - AY) * (centroX - AX)));
        double distancia = 0;
        if (u >= 0 && u <= 1) distancia = baseDistance / (Math.sqrt(v));
        else if (u > 1) distancia = Math.sqrt((Math.pow(centroX - BX, 2)) + (Math.pow(centroY - BY, 2)));
        else if (u < 0) distancia = Math.sqrt((Math.pow(centroX - AX, 2)) + (Math.pow(centroY - AY, 2)));
        return Math.abs(distancia);
    }

    static void bounce(Ball ball, int section) {

        switch (section) {
            case 1 -> {
                if (ball.getXam() != 1) ball.setXam(-1);
                ball.setYam(-1);
            }
            case 2 -> {
                ball.setXam(-1);
                ball.setYam(-1);
            }
            case 3 -> {
                if (ball.getXam() <= 0) ball.setXam(-1);
                else ball.setXam(1);
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
                if (ball.getYam() == 1) ball.setYam(-1);
            }
            case 7 -> {
                if (ball.getYam() == -1) ball.setYam(1);
            }
            case 8 -> {
                if ((ball.getXam() == 0) || (ball.getXam() == 1)) ball.setXam(-1);
            }
            case 9 -> {
                if ((ball.getXam() == 0) || (ball.getXam() == -1)) ball.setXam(1);
            }
        }
    }
}
```

:::
::::

## GamePanel <Badge type="warning" text="Modificada" vertical="middle" />

En este caso, para que todo funcione correctamente, deberemos de agregar un par de funciones al GamePanel, para que
pueda reaccionar a los cambios en el entorno de juego.

:::: code-group
::: code-group-item Anterior

```java
package ui;

import events.GameCycle;
import graphics.Ball;
import graphics.Laser;
import graphics.bonus.Bonus;
import graphics.border.Border;
import graphics.border.BorderType;
import graphics.brick.Brick;
import graphics.paddle.Paddle;
import graphics.paddle.PaddleType;
import util.FileManager;
import util.Globals;
import util.Level;
import util.Moveable;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Vector;

public class GamePanel extends JPanel implements Moveable {
    private MainWindow mainWindow;
    private Border leftBorder;
    private Border topBorder;
    private Border rightBorder;
    private Vector<Ball> balls;
    private Level currentLevel;
    private Paddle paddle;
    /**
     * Timer es un elemento que sirve para tener un reloj interno en el sistema que podemos
     * detener, reiniciar, destruir, etc. Es lo que permite detener el juego.
     */
    public static Timer timer;
    private boolean inGame;
    private int level;
    private int lives;
    private int score;
    private Vector<Laser> lasers;
    private Vector<Bonus> bonuses;

    public GamePanel(MainWindow mainWindow) {

        this.mainWindow = mainWindow;
        this.setPreferredSize(Globals.PANEL_DIMENSION);
        this.setMinimumSize(Globals.PANEL_DIMENSION);
        this.setMaximumSize(Globals.PANEL_DIMENSION);
        this.leftBorder = new Border(0, 0, BorderType.LEFT, this);
        this.topBorder = new Border(18, 0, BorderType.TOP, this);
        this.rightBorder = new Border(438, 0, BorderType.RIGHT, this);
        this.balls = new Vector<>(1, 1);
        this.balls.add(new Ball(Globals.INIT_BALL_X, Globals.INIT_BALL_Y, this));
        this.paddle = new Paddle(PaddleType.NORMAL, Globals.INIT_PADDLE_X, Globals.INET_PADDLE_Y, this);
        this.inGame = true;
        this.level = 1;
        this.lives = Globals.START_LIVES;
        this.lasers = new Vector<>(5, 1);
        this.bonuses = new Vector<>(1, 1);
        this.loadLevel();
        timer = new Timer(Globals.PERIOD, new GameCycle(this));
        timer.start();
        this.setFocusable(true);
        this.requestFocus();
    }

    private void loadLevel() {

        FileManager fileManager = new FileManager();
        this.currentLevel = fileManager.readLevel(this.level);
        this.currentLevel.setGamePanel(this);
    }

    //Esta función permite detener el juego
    public void stopGame() {

        this.inGame = false;
        timer.stop();
    }

    //Esta función permite reanudar el juego
    public void playGame() {

        this.inGame = true;
        timer.start();
        this.requestFocus();
    }

    @Override
    public void paint(Graphics g) {

        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        if (this.isInGame()) {

            this.drawLevelBackground(g2d);
            this.drawBorders(g2d);
            this.paintBricks(g2d);
            this.paddle.paint(g2d);
            for (Ball ball : this.balls) {

                ball.paint(g2d);
            }
            for (Laser laser : lasers) {

                laser.paint(g2d);
            }
            for (Bonus bonus : bonuses) {

                bonus.paint(g2d);
            }
        } else {

            this.gameFinished(g2d);
        }
        Toolkit.getDefaultToolkit().sync();
    }

    private void drawBorders(Graphics2D g2d) {

        this.leftBorder.paint(g2d);
        this.topBorder.paint(g2d);
        this.rightBorder.paint(g2d);
    }

    private void drawLevelBackground(Graphics2D g2d) {

        Image background = new ImageIcon(Globals.SPRITE_CACHE.getSprite(this.getCurrentLevel().getBackground())).getImage();
        g2d.drawImage(background, 18, 18, null);
    }

    private void paintBricks(Graphics2D g2d) {

        boolean clear = true;
        Brick actualBrick;
        for (int row = 0; row < Globals.BRICKS_ROWS; row++) {

            for (int column = 0; column < Globals.BRICK_COLUMNS; column++) {

                actualBrick = currentLevel.getBrickPad()[row][column];
                if (!actualBrick.isDestroy()) {

                    actualBrick.paint(g2d);
                    clear = false;
                }
            }
        }
        if (clear) {

            if (this.getLevel() < 5) {

                this.level++;
                this.loadLevel();
                this.lasers.clear();
                this.bonuses.clear();
                this.balls = new Vector<Ball>(1, 1);
                this.balls.add(new Ball(Globals.INIT_BALL_X, Globals.INIT_BALL_Y, this));
                this.paddle.resetState();
            } else {
                setInGame(false);
                gameFinished(g2d);
            }
        }
    }

    private void gameFinished(Graphics2D g2d) {
        Font font;
        try {
            font = Font.createFont(Font.TRUETYPE_FONT,
                    new File("fonts\\game_over.ttf")).deriveFont(50f);
        } catch (FontFormatException | IOException e) {
            throw new RuntimeException(e);
        }
        FontMetrics fontMetrics = this.getFontMetrics(font);
        String message = "¡GAME OVER!";
        g2d.setColor(Color.BLACK);
        g2d.setFont(font);
        g2d.drawString(message, (int) ((Globals.PANEL_DIMENSION.getWidth() - fontMetrics.stringWidth(message)) / 2),
                Globals.PANEL_DIMENSION.width / 2);
    }

    @Override
    public void move() {
    }
}
```

:::
::: code-group-item ACTUALIZADO

```java{4,64,159,187-204,207-213,215-249,251-272,274-290,292-313,315-332,334-354,356-389}
package ui;

import events.GameCycle;
import events.KeyboardAction;
import graphics.Ball;
import graphics.Laser;
import graphics.bonus.Bonus;
import graphics.border.Border;
import graphics.border.BorderType;
import graphics.brick.Brick;
import graphics.paddle.Paddle;
import graphics.paddle.PaddleType;
import util.FileManager;
import util.Globals;
import util.Level;
import util.Moveable;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Vector;

public class GamePanel extends JPanel implements Moveable {
    private MainWindow mainWindow;
    private Border leftBorder;
    private Border topBorder;
    private Border rightBorder;
    private Vector<Ball> balls;
    private Level currentLevel;
    private Paddle paddle;
    /**
     * Timer es un elemento que sirve para tener un reloj interno en el sistema que podemos
     * detener, reiniciar, destruir, etc. Es lo que permite detener el juego.
     */
    public static Timer timer;
    private boolean inGame;
    private int level;
    private int lives;
    private int score;
    private Vector<Laser> lasers;
    private Vector<Bonus> bonuses;

    public GamePanel(MainWindow mainWindow) {

        this.mainWindow = mainWindow;
        this.setPreferredSize(Globals.PANEL_DIMENSION);
        this.setMinimumSize(Globals.PANEL_DIMENSION);
        this.setMaximumSize(Globals.PANEL_DIMENSION);
        this.leftBorder = new Border(0, 0, BorderType.LEFT, this);
        this.topBorder = new Border(18, 0, BorderType.TOP, this);
        this.rightBorder = new Border(438, 0, BorderType.RIGHT, this);
        this.balls = new Vector<>(1, 1);
        this.balls.add(new Ball(Globals.INIT_BALL_X, Globals.INIT_BALL_Y, this));
        this.paddle = new Paddle(PaddleType.NORMAL, Globals.INIT_PADDLE_X, Globals.INET_PADDLE_Y, this);
        this.inGame = true;
        this.level = 1;
        this.lives = Globals.START_LIVES;
        this.lasers = new Vector<>(5, 1);
        this.bonuses = new Vector<>(1, 1);
        this.loadLevel();
        timer = new Timer(Globals.PERIOD, new GameCycle(this));
        timer.start();
        this.addKeyListener(new KeyboardAction(this));
        this.setFocusable(true);
        this.requestFocus();
    }

    private void loadLevel() {

        FileManager fileManager = new FileManager();
        this.currentLevel = fileManager.readLevel(this.level);
        this.currentLevel.setGamePanel(this);
    }

    //Esta función permite detener el juego
    public void stopGame() {

        this.inGame = false;
        timer.stop();
    }

    //Esta función permite reanudar el juego
    public void playGame() {

        this.inGame = true;
        timer.start();
        this.requestFocus();
    }

    @Override
    public void paint(Graphics g) {

        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        if (this.isInGame()) {

            this.drawLevelBackground(g2d);
            this.drawBorders(g2d);
            this.paintBricks(g2d);
            this.paddle.paint(g2d);
            for (Ball ball : this.balls) {

                ball.paint(g2d);
            }
            for (Laser laser : lasers) {

                laser.paint(g2d);
            }
            for (Bonus bonus : bonuses) {

                bonus.paint(g2d);
            }
        } else {

            this.gameFinished(g2d);
        }
        Toolkit.getDefaultToolkit().sync();
    }

    private void drawBorders(Graphics2D g2d) {

        this.leftBorder.paint(g2d);
        this.topBorder.paint(g2d);
        this.rightBorder.paint(g2d);
    }

    private void drawLevelBackground(Graphics2D g2d) {

        Image background = new ImageIcon(Globals.SPRITE_CACHE.getSprite(this.getCurrentLevel().getBackground())).getImage();
        g2d.drawImage(background, 18, 18, null);
    }

    private void paintBricks(Graphics2D g2d) {

        boolean clear = true;
        Brick actualBrick;
        for (int row = 0; row < Globals.BRICKS_ROWS; row++) {

            for (int column = 0; column < Globals.BRICK_COLUMNS; column++) {

                actualBrick = currentLevel.getBrickPad()[row][column];
                if (!actualBrick.isDestroy()) {

                    actualBrick.paint(g2d);
                    clear = false;
                }
            }
        }
        if (clear) {

            if (this.getLevel() < 5) {

                this.level++;
                this.loadLevel();
                this.lasers.clear();
                this.bonuses.clear();
                this.balls = new Vector<>(1, 1);
                this.balls.add(new Ball(Globals.INIT_BALL_X, Globals.INIT_BALL_Y, this));
                this.paddle.resetState();
            } else {
                setInGame(false);
                gameFinished(g2d);
            }
        }
    }

    private void gameFinished(Graphics2D g2d) {
        Font font;
        try {
            font = Font.createFont(Font.TRUETYPE_FONT,
                    new File("fonts\\game_over.ttf")).deriveFont(50f);
        } catch (FontFormatException | IOException e) {
            throw new RuntimeException(e);
        }
        FontMetrics fontMetrics = this.getFontMetrics(font);
        String message = "¡GAME OVER!";
        g2d.setColor(Color.BLACK);
        g2d.setFont(font);
        g2d.drawString(message, (int) ((Globals.PANEL_DIMENSION.getWidth() - fontMetrics.stringWidth(message)) / 2),
                Globals.PANEL_DIMENSION.width / 2);
    }

    @Override
    public void move() {

        for (Ball ball : balls) {

            ball.move();
        }
        for (Laser laser : lasers) {

            laser.move();
        }
        for (Bonus bonus : bonuses) {

            bonus.move();
        }
        lasers.removeIf(b -> b.getY() <= 24);
        lasers.removeIf(Laser::isDestroy);
        bonuses.removeIf(b -> b.getY() >= Globals.BOTTOM_EDGE);
        bonuses.removeIf(Bonus::isTaken);
        paddle.move();
    }

    public void checkCollisions() {

        Brick[][] bricks = this.currentLevel.getBrickPad();
        checkBallCollisions(bricks);
        checkBonusesCollision();
        checkLaserCollisions(bricks);
    }

    private void checkBallCollisions(Brick[][] bricks) {

        //Verificamos si la pelota golpea algún elemento
        for (Ball actualBall : balls) {

            if (actualBall.getRect().intersects(paddle.getRect()) && actualBall.getYam() != -1) {

                paddleCollision(actualBall, paddle);
            }
            if ((actualBall.getRect()).intersects(leftBorder.getRect())) {

                borderCollision(actualBall, leftBorder);
            }
            if ((actualBall.getRect()).intersects(topBorder.getRect())) {

                borderCollision(actualBall, topBorder);
            }
            if ((actualBall.getRect()).intersects(rightBorder.getRect())) {

                borderCollision(actualBall, this.rightBorder);
            }
            for (int row = 0; row < Globals.BRICKS_ROWS; row++) {
                for (int column = 0; column < Globals.BRICK_COLUMNS; column++) {

                    Brick actualBrick = bricks[row][column];
                    if (!actualBrick.isDestroy()
                            && (actualBall.getRect()).intersects(actualBrick.getRect())) {

                        brickCollision(actualBall, actualBrick);
                    }
                }
            }
            checkBottomCollision(actualBall);
        }
    }

    private void checkBonusesCollision() {

        //Verificamos sin el jugador toma un bonus
        for (Bonus bonus : bonuses) {

            if (getPaddle().getRect().intersects(bonus.getRect())) {

                switch (bonus.getType()) {
                    case ENLARGE -> this.getPaddle().changeType(PaddleType.LARGE);
                    case SMALL -> this.getPaddle().changeType(PaddleType.NORMAL);
                    case PLAYER -> {
                        this.setLives(this.getLives() + 1);
                    }
                    case LASER -> {
                        this.getPaddle().changeType(PaddleType.LASER);
                        this.getPaddle().setShootMode(true);
                    }
                }
                bonus.setTaken(true);
            }
        }
    }

    private void checkLaserCollisions(Brick[][] bricks) {

        //Verificamos que si un laser colisiona con algún ladrillo
        for (Laser laser : lasers) {
            for (int row = 0; row < Globals.BRICKS_ROWS; row++) {
                for (int column = 0; column < Globals.BRICK_COLUMNS; column++) {

                    Brick actualBrick = bricks[row][column];
                    if (!actualBrick.isDestroy() && laser.getRect().intersects(actualBrick.getRect())) {

                        actualBrick.minusLife();
                        laser.setDestroy(true);
                    }
                }
            }
        }
    }

    private void checkBottomCollision(Ball actualBall) {

        if (actualBall.getRect().getMaxY() > Globals.BOTTOM_EDGE) {

            if (balls.size() > 1) {

                balls.remove(actualBall);
            } else {

                if (this.getLives() > 1) {

                    this.setLives(this.getLives() - 1);
                    actualBall.resetState();
                    this.paddle.resetState();
                    this.resetKeyboardActions();
                } else {
                    this.setLives(this.getLives() - 1);
                    this.stopGame();
                }
            }
        }
    }

    private void paddleCollision(Ball actualBall, Paddle paddle) {

        int ballCenter = actualBall.getCenter();
        int ballHeight = actualBall.getY() + actualBall.getImageHeight();
        int ballWidth= actualBall.getX() + actualBall.getImageWidth();
        int paddleHeight = paddle.getY() + paddle.getImageHeight();
        if ((ballHeight >= paddle.getY()) && (actualBall.getY() <= paddleHeight))
            if ((ballWidth >= paddle.getX()) && (ballCenter <= paddle.getEndFirstBorder()))
                Globals.bounce(actualBall, 1);
            else if ((ballCenter >= paddle.getEndFirstBorder()) && (ballCenter <= paddle.getStartCenter()))
                Globals.bounce(actualBall, 2);
            else if ((ballCenter >= paddle.getStartCenter()) && (ballCenter <= paddle.getEndCenter()))
                Globals.bounce(actualBall, 3);
            else if ((ballCenter >= paddle.getEndCenter()) && (ballCenter <= paddle.getStartSecondBorder()))
                Globals.bounce(actualBall, 4);
            else if ((ballCenter >= paddle.getStartSecondBorder()) && (actualBall.getX() <= paddle.getEndSecondBorder()))
                Globals.bounce(actualBall, 5);
    }
    
    private void borderCollision(Ball actualBall, Border border) {

        double lowerEdge = Globals.distancia(actualBall, border.getMinX(), border.getMaxY(),
                border.getMaxX(), border.getMaxY());
        double leftEdge = Globals.distancia(actualBall, border.getMinX(), border.getMinY(),
                border.getMinX(), border.getMaxY());
        double rightEdge = Globals.distancia(actualBall, border.getMaxX(), border.getMinY(),
                border.getMaxX(), border.getMaxY());
        if (lowerEdge <= actualBall.getMiddle()) {

            Globals.bounce(actualBall, 7);
        }
        if (leftEdge <= actualBall.getMiddle()) {

            Globals.bounce(actualBall, 8);
        }
        if (rightEdge <= actualBall.getMiddle()) {

            Globals.bounce(actualBall, 9);
        }
    }

    private void brickCollision(Ball actualBall, Brick brick) {

        double topEdge = Globals.distancia(actualBall, brick.getMinX(), brick.getMinY(),
                brick.getMaxX(), brick.getMinY());
        double lowerEdge = Globals.distancia(actualBall, brick.getMinX(), brick.getMaxY(),
                brick.getMaxX(), brick.getMaxY());
        double leftEdge = Globals.distancia(actualBall, brick.getMinX(), brick.getMinY(),
                brick.getMinX(), brick.getMaxY());
        double rightEdge = Globals.distancia(actualBall, brick.getMaxX(), brick.getMinY(),
                brick.getMaxX(), brick.getMaxY());
        boolean minus = false;
        if (topEdge <= actualBall.getMiddle()) {

            Globals.bounce(actualBall, 6);
            minus = true;
        }
        if (lowerEdge <= actualBall.getMiddle()) {

            Globals.bounce(actualBall, 7);
            minus = true;
        }
        if (leftEdge <= actualBall.getMiddle()) {

            Globals.bounce(actualBall, 8);
            minus = true;
        }
        if (rightEdge <= actualBall.getMiddle()) {

            Globals.bounce(actualBall, 9);
            minus = true;
        }
        if (minus)
            brick.minusLife();
    }
}
```

:::
::::
