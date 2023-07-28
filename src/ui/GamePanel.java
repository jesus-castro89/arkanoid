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
}
