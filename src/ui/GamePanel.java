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
import util.*;

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
    private AudioPlayer audioPlayer;

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
        this.audioPlayer = new AudioPlayer("audio/level1.wav");
        this.loadLevel();
        timer = new Timer(Globals.PERIOD, new GameCycle(this));
        timer.start();
        this.addKeyListener(new KeyboardAction(this));
        this.setFocusable(true);
        this.requestFocus();
    }

    private void loadLevel() {

        FileManager fileManager = new FileManager();
        currentLevel = fileManager.readLevel(level);
        currentLevel.setGamePanel(this);
        audioPlayer.stop();
        audioPlayer.start("audio/" + currentLevel.getAudio());
    }

    //Esta función permite detener el juego
    public void stopGame() {

        this.inGame = false;
        timer.stop();
        audioPlayer.stop();
    }

    //Esta función permite reanudar el juego
    public void playGame() {

        this.inGame = true;
        timer.start();
        audioPlayer.resume();
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
                    new File("fonts\\game.ttf")).deriveFont(50f);
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
                    case ENLARGE -> getPaddle().changeType(PaddleType.LARGE);
                    case SMALL -> getPaddle().changeType(PaddleType.NORMAL);
                    case PLAYER -> {
                        setLives(getLives() + 1);
                        int lives = Integer.parseInt(getMainWindow().getLivesCountLabel().getText());
                        getMainWindow().getLivesCountLabel().setText("%d".formatted(++lives));
                    }
                    case LASER -> {
                        getPaddle().changeType(PaddleType.LASER);
                        getPaddle().setShootMode(true);
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
                int lives = Integer.parseInt(getMainWindow().getLivesCountLabel().getText());
                getMainWindow().getLivesCountLabel().setText("%d".formatted(--lives));
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
        int ballWidth = actualBall.getX() + actualBall.getImageWidth();
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

    public MainWindow getMainWindow() {
        return mainWindow;
    }

    public void setMainWindow(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
    }

    public Border getLeftBorder() {
        return leftBorder;
    }

    public void setLeftBorder(Border leftBorder) {
        this.leftBorder = leftBorder;
    }

    public Border getTopBorder() {
        return topBorder;
    }

    public void setTopBorder(Border topBorder) {
        this.topBorder = topBorder;
    }

    public Border getRightBorder() {
        return rightBorder;
    }

    public void setRightBorder(Border rightBorder) {
        this.rightBorder = rightBorder;
    }

    public Vector<Ball> getBalls() {
        return balls;
    }

    public void setBalls(Vector<Ball> balls) {
        this.balls = balls;
    }

    public Level getCurrentLevel() {
        return currentLevel;
    }

    public void setCurrentLevel(Level currentLevel) {
        this.currentLevel = currentLevel;
    }

    public Paddle getPaddle() {
        return paddle;
    }

    public void setPaddle(Paddle paddle) {
        this.paddle = paddle;
    }

    public static Timer getTimer() {
        return timer;
    }

    public static void setTimer(Timer timer) {
        GamePanel.timer = timer;
    }

    public boolean isInGame() {
        return inGame;
    }

    public void setInGame(boolean inGame) {
        this.inGame = inGame;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getLives() {
        return lives;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Vector<Laser> getLasers() {
        return lasers;
    }

    public void setLasers(Vector<Laser> lasers) {
        this.lasers = lasers;
    }

    public Vector<Bonus> getBonuses() {
        return bonuses;
    }

    public void setBonuses(Vector<Bonus> bonuses) {
        this.bonuses = bonuses;
    }

    public AudioPlayer getAudioPlayer() {
        return audioPlayer;
    }

    public void setAudioPlayer(AudioPlayer audioPlayer) {
        this.audioPlayer = audioPlayer;
    }
}
