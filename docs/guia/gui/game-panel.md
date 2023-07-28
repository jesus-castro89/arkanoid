---
title: GamePanel
description: Definición del panel de juego y en donde se mueven los componentes del juego
editLink: false
---

# GamePanel <Badge type="tip" text="Nuevo" vertical="middle" />

Esta clase se encuentra igual en el paquete `ui` y contendrá todo lo referente a la lógica de juego y los componentes
visuales, por lo que se contiene los siguientes atributos:

- **mainWindow**
    - Referencia a la ventana principal
- **leftBorder**
    - Borde izquierdo de juego
- **topBorder**
    - Borde superior de juego
- **rightBorder**
    - Borde derecho de juego
- **balls**
    - Vector de pelotas en juego
- **currentLevel**
    - Objeto de tipo nivel que representa el nivel actual
- **paddle**
    - El jugador
- **timer**
    - Reloj interno de juego
- **inGame**
    - Booleano que permite saber si el juego está en curso o en pausa
- **level**
    - Número de nivel actual, se usa para cargar los niveles siguientes
- **lives**
    - Vidas del jugador
- **score**
    - Puntaje del jugador
- **lasers**
    - Vector de lasers en juego
- **bonuses**
    - Vector de bonus en juego

De igual manera, deberá definir las siguientes funciones:

- Carga de niveles
- Detener y Reanudar el juego
- Pintar los componentes de juego
    - Pintar los bordes
    - Pinter el fondo de juego
    - Pintar los ladrillos
- Definir la pantalla de game over
- Función de control de movimiento

```java
package ui;

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
        this.balls = new Vector<Ball>(1, 1);
        this.balls.add(new Ball(Globals.INIT_BALL_X, Globals.INIT_BALL_Y, this));
        this.paddle = new Paddle(PaddleType.NORMAL, Globals.INIT_PADDLE_X, Globals.INET_PADDLE_Y, this);
        this.inGame = true;
        this.level = 1;
        this.lives = Globals.START_LIVES;
        this.lasers = new Vector<>(5, 1);
        this.bonuses = new Vector<>(1, 1);
        this.loadLevel();
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

Si te marca algún error, descuida, sigue leyendo y veras como resolver estos detalles.

## Sprite <Badge type="warning" text="Modificado" vertical="middle" />

Lo primero que haremos es modificar nuestros sprites para agregar la función paint de la siguiente manera:

:::: code-group
::: code-group-item ANTERIOR

```java
package graphics;

import util.Globals;

import javax.swing.*;
import java.awt.*;
import java.io.Serializable;

public class Sprite {

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
}
```

:::
::: code-group-item NUEVO

```java{9,25-34}
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
}
```

:::
::::

## Agregando GamePanel a los Sprites

Una vez que hemos agregado la función de dibujo, nos queda agregar la clase GamePanel a algunos Sprites de la siguiente
forma:

### Border <Badge type="warning" text="Modificado" vertical="middle" />

:::: code-group
::: code-group-item ANTERIOR

```java
package graphics.border;

import graphics.Sprite;

public class Border extends Sprite {
    private int minX;
    private int minY;
    private int maxX;
    private int maxY;

    public Border(int x, int y, BorderType type) {

        super(type.getImageName(), x, y);
        //Las siguientes líneas permiten obtener las coordenadas con base a la imagen de acuerdo al tipo de borde.
        this.minX = (int) this.getRect().getMinX();
        this.minY = (int) this.getRect().getMinY();
        this.maxX = (int) this.getRect().getMaxX();
        this.maxY = (int) this.getRect().getMaxY();
    }
}
```

:::
::: code-group-item NUEVO

```java{4,12,22}
package graphics.border;

import graphics.Sprite;
import ui.GamePanel;

public class Border extends Sprite {
    
    private int minX;
    private int minY;
    private int maxX;
    private int maxY;
    private GamePanel gamePanel;

    public Border(int x, int y, BorderType type, GamePanel gamePanel) {

        super(type.getImageName(), x, y);
        //Las siguientes líneas permiten obtener las coordenadas con base a la imagen de acuerdo al tipo de borde.
        this.minX = (int) this.getRect().getMinX();
        this.minY = (int) this.getRect().getMinY();
        this.maxX = (int) this.getRect().getMaxX();
        this.maxY = (int) this.getRect().getMaxY();
        this.gamePanel = gamePanel;
    }
}
```

:::
::::

### Ball <Badge type="warning" text="Modificado" vertical="middle" />

:::: code-group
::: code-group-item ANTERIOR

```java
package graphics;

import util.Moveable;
import util.Globals;

public class Ball extends Sprite implements Moveable {

  private int xam;
  private int yam;
  private boolean stop;
  private int xsm;

  public Ball(int x, int y) {

    super(Globals.BALL_SPRITE, x, y);
    this.xam = 1;
    this.yam = -1;
    this.stop = true;
    this.xsm = 0;
  }

  public void resetState() {

    this.x = Globals.INIT_BALL_X;
    this.y = Globals.INIT_BALL_Y;
    this.xam = 1;
    this.yam = -1;
    this.stop = true;
    this.xsm = 0;
  }

  @Override
  public void move() {

  }
}
```

:::
::: code-group-item NUEVO

```java{3,13,22}
package graphics;

import ui.GamePanel;
import util.Moveable;
import util.Globals;

public class Ball extends Sprite implements Moveable {

    private int xam;
    private int yam;
    private boolean stop;
    private int xsm;
    private GamePanel gamePanel;

    public Ball(int x, int y, GamePanel gamePanel) {

        super(Globals.BALL_SPRITE, x, y);
        this.xam = 1;
        this.yam = -1;
        this.stop = true;
        this.xsm = 0;
        this.gamePanel = gamePanel;
    }

    public void resetState() {

        this.x = Globals.INIT_BALL_X;
        this.y = Globals.INIT_BALL_Y;
        this.xam = 1;
        this.yam = -1;
        this.stop = true;
        this.xsm = 0;
    }

    @Override
    public void move() {

    }
}
```

:::
::::

### Brick <Badge type="warning" text="Modificado" vertical="middle" />

:::: code-group
::: code-group-item ANTERIOR

```java
package graphics.brick;

import graphics.Sprite;

import java.io.Serializable;

public class Brick extends Sprite implements Serializable {

  private int minX;
  private int minY;
  private int maxX;
  private int maxY;
  private int life;
  private int points;
  private boolean destroy;

  public Brick(BrickType type, int x, int y) {
    super(type.getImageName(), x, y);
    this.life = type.getLife();//⇽ Esto permite asociar al bloque la vida de su tipo
    this.points = type.getPoints();//⇽ Esto asocia el puntaje del bloque con su tipo
    this.destroy = false;//⇽ Esto permite indicar que el bloque en un principio es visible
    this.minX = (int) this.getRect().getMinX();
    this.minY = (int) this.getRect().getMinY();
    this.maxX = (int) this.getRect().getMaxX();
    this.maxY = (int) this.getRect().getMaxY();
  }

  public void updateRect() {

    this.minX = (int) this.getRect().getMinX();
    this.minY = (int) this.getRect().getMinY();
    this.maxX = (int) this.getRect().getMaxX();
    this.maxY = (int) this.getRect().getMaxY();
  }

  public void minusLife() {

    if (this.getLife() > 1) {

      this.setLife(this.getLife() - 1);
    } else if (this.getLife() > 0) {
      this.setLife(0);
      this.setDestroy(true);
    }
  }
}
```

:::
::: code-group-item NUEVO

```java{4,17}
package graphics.brick;

import graphics.Sprite;
import ui.GamePanel;

import java.io.Serializable;

public class Brick extends Sprite implements Serializable {

  private int minX;
  private int minY;
  private int maxX;
  private int maxY;
  private int life;
  private int points;
  private boolean destroy;
  private GamePanel gamePanel;

  public Brick(BrickType type, int x, int y) {
    super(type.getImageName(), x, y);
    this.life = type.getLife();//⇽ Esto permite asociar al bloque la vida de su tipo
    this.points = type.getPoints();//⇽ Esto asocia el puntaje del bloque con su tipo
    this.destroy = false;//⇽ Esto permite indicar que el bloque en un principio es visible
    this.minX = (int) this.getRect().getMinX();
    this.minY = (int) this.getRect().getMinY();
    this.maxX = (int) this.getRect().getMaxX();
    this.maxY = (int) this.getRect().getMaxY();
  }

  public void updateRect() {

    this.minX = (int) this.getRect().getMinX();
    this.minY = (int) this.getRect().getMinY();
    this.maxX = (int) this.getRect().getMaxX();
    this.maxY = (int) this.getRect().getMaxY();
  }

  public void minusLife() {

    if (this.getLife() > 1) {

      this.setLife(this.getLife() - 1);
    } else if (this.getLife() > 0) {
      this.setLife(0);
      this.setDestroy(true);
    }
  }
}
```

:::
::::

### Paddle <Badge type="warning" text="Modificado" vertical="middle" />

:::: code-group
::: code-group-item ANTERIOR

```java
package graphics.paddle;

import graphics.Sprite;
import util.Globals;
import util.Moveable;

import javax.swing.*;

public class Paddle extends Sprite implements Moveable {

  private int xam;
  private int minX;
  private int minY;
  private int maxX;
  private int maxY;
  private int border; //Ancho del borde de la barra
  private int normalZone; //Ancho desde el borde de la barra hasta la zona central
  private int centerZone; //Ancho de la zona central
  private int endFirstBorder; //2
  private int startCenter; //3
  private int endCenter; //4
  private int startSecondBorder; //5
  private int endSecondBorder; //6
  private int lowPart; //7
  private boolean shootMode;

  public Paddle(PaddleType type, int x, int y) {

    super(type.getImageName(), x, y);
    this.xam = 0;
    changeType(type);
  }

  @Override
  public void move() {

  }

  public void changeType(PaddleType type) {
    try {

      this.imageName = type.getImageName();
      this.getImageDimensions();
      this.border = this.getImageWidth() / 4;
      this.normalZone = (int) (this.getImageWidth() / 6.4);
      this.centerZone = (int) (this.getImageWidth() / 5.3);
      this.endFirstBorder = this.x + this.border;
      this.startCenter = this.endFirstBorder + this.normalZone;
      this.endCenter = this.startCenter + this.centerZone;
      this.startSecondBorder = this.x + this.getImageWidth() - this.border;
      this.endSecondBorder = this.x + this.getImageWidth();
      this.lowPart = this.y + this.getImageHeight();
      this.shootMode = switch (type) {
        case LARGE, NORMAL -> false;
        case LASER -> true;
      };
    } catch (Exception e) {
      JOptionPane.showMessageDialog(null, "¡Error al cargar Imagen de Juego!");
    }
  }

  public void resetState() {

    this.x = Globals.INIT_PADDLE_X;
    this.y = Globals.INET_PADDLE_Y;
  }
}
```

:::
::: code-group-item NUEVO

```java{4,27,34}
package graphics.paddle;

import graphics.Sprite;
import ui.GamePanel;
import util.Globals;
import util.Moveable;

import javax.swing.*;

public class Paddle extends Sprite implements Moveable {

    private int xam;
    private int minX;
    private int minY;
    private int maxX;
    private int maxY;
    private int border; //Ancho del borde de la barra
    private int normalZone; //Ancho desde el borde de la barra hasta la zona central
    private int centerZone; //Ancho de la zona central
    private int endFirstBorder; //2
    private int startCenter; //3
    private int endCenter; //4
    private int startSecondBorder; //5
    private int endSecondBorder; //6
    private int lowPart; //7
    private boolean shootMode;
    private GamePanel gamePanel;
    
    public Paddle(PaddleType type, int x, int y, GamePanel gamePanel) {

        super(type.getImageName(), x, y);
        this.xam = 0;
        changeType(type);
        this.gamePanel=gamePanel;
    }

    @Override
    public void move() {

    }

    public void changeType(PaddleType type) {
        try {

            this.imageName = type.getImageName();
            this.getImageDimensions();
            this.border = this.getImageWidth() / 4;
            this.normalZone = (int) (this.getImageWidth() / 6.4);
            this.centerZone = (int) (this.getImageWidth() / 5.3);
            this.endFirstBorder = this.x + this.border;
            this.startCenter = this.endFirstBorder + this.normalZone;
            this.endCenter = this.startCenter + this.centerZone;
            this.startSecondBorder = this.x + this.getImageWidth() - this.border;
            this.endSecondBorder = this.x + this.getImageWidth();
            this.lowPart = this.y + this.getImageHeight();
            this.shootMode = switch (type) {
                case LARGE, NORMAL -> false;
                case LASER -> true;
            };
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "¡Error al cargar Imagen de Juego!");
        }
    }

    public void resetState() {

        this.x = Globals.INIT_PADDLE_X;
        this.y = Globals.INET_PADDLE_Y;
    }
}
```

:::
::::
