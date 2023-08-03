---
title: Actualizando Paddle
description: Agregación de funciones de movimiento y eventos de teclado par ala clase Paddle.
editLink: false
---

# Actualizando Paddle <Badge type="warning" text="Modificada" vertical="middle" />

En la clase Paddle, haremos algo muy similar que en Ball, así que veamos qué hay de nuevo.

:::: code-group
::: code-group-item Anterior

```java
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
        this.gamePanel = gamePanel;
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
::: code-group-item ACTUALIZADO

```java{3,10,11,43-51,54-60,62-73,75-80}
package graphics.paddle;

import graphics.Laser;
import graphics.Sprite;
import ui.GamePanel;
import util.Globals;
import util.Moveable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

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
        this.gamePanel = gamePanel;
    }

    @Override
    public void move() {

        //Desplazamiento esperado
        x += xam;
        //Dimensiones del Panel de juego
        Dimension gameDimension = Globals.PANEL_DIMENSION;
        //El punto máximo hacia la derecha para jugador
        int maximum = (int) (gameDimension.getWidth() - 28) - imageWidth;
        //Globals.BRICK_MARGIN es el punto mínimo para el jugador
        if (x <= Globals.BRICK_MARGIN) x = Globals.BRICK_MARGIN;
        if (x >= maximum) x = maximum;
    }

    public void keyPressed(KeyEvent e) {

        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT -> xam = -1;
            case KeyEvent.VK_RIGHT -> xam = 1;
        }
    }

    public void keyReleased(KeyEvent e) {

        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT -> xam = 0;
            case KeyEvent.VK_SPACE -> {
                if (/*isShootMode() && */gamePanel.getLasers().size() < 5) {

                    makeLaser();
                }
            }
        }
    }

    private void makeLaser() {

        int x = gamePanel.getPaddle().getX() + ((Globals.PADDLE_WIDTH / 2) - (Globals.LASER_WIDTH / 2));
        int y = gamePanel.getPaddle().getY() - gamePanel.getPaddle().getImageHeight();
        gamePanel.getLasers().add(new Laser(x, y, gamePanel));
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

En este caso también contamos con las funciones key, pero además tenemos una función llamada makeLaser, que como su
nombre indica, se encargará de crear los lasers en cuanto el jugador tome el Bonus adecuado.
