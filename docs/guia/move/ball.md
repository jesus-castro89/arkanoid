---
title: Actualizando Ball
description: Agregación de acciones de movimiento para la clase Ball
editLink: false
---

# Actualizando Ball <Badge type="warning" text="Modificada" vertical="middle" />

Nuestra primera parada será la clase `Ball` del paquete `graphics`, en esta clase lo que haremos es agregar un par de
funciones para recibir las acciones del teclado y actuar en consecuencia sobre lo que haga nuestro usuario.
Por otro lado, agregaremos un par de funcionalidades a la función `move` de la clase, así que veamos esas diferencias.

:::: code-group
::: code-group-item Anterior

```java
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
::: code-group-item ACTUALIZADO

```java{7,40-57,60-72,74-82,84-86,88-91}
package graphics;

import ui.GamePanel;
import util.Moveable;
import util.Globals;

import java.awt.event.KeyEvent;

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

        if (!this.isStop()) {//Esto es si la pelota no es detenida al jugador
            //Para movernos hacia la derecha
            if ((x + xam) < 0) xam = 1;
            //Para movernos hacia la izquierda
            if ((x + xam) > (this.gamePanel.getWidth() - this.getImageWidth())) xam = -1;
            //Para movernos hacia abajo
            if ((y + yam) < 0) yam = 1;
            //Para movernos hacia arriba
            if ((y + yam) > (this.gamePanel.getHeight() - this.getImageHeight())) yam = -1;
            //Una vez determinamos hacia donde nos movemos, agregamos eso a nuestras coordenadas
            x += xam;
            y += yam;
        } else {// Esto es cuando si está detenida
            x = x + xsm;
            int maximum = (int) ((Globals.PANEL_DIMENSION.getWidth() - Globals.BALL_MARGIN) - (imageWidth + 10));
            if (x <= Globals.BALL_MARGIN) x = Globals.BALL_MARGIN;
            if (x >= maximum) x = maximum;
        }
    }

    public void keyPressed(KeyEvent e) {

        if (this.isStop()) {//Esto solo aplica si esta detenida
            switch (e.getKeyCode()) {//Reviso la tecla
                case KeyEvent.VK_SPACE -> {//Si es la barra espaciadora
                    xsm = 0;
                    stop = false;
                }
                case KeyEvent.VK_LEFT -> xsm = -1;//Si es la tecla <-
                case KeyEvent.VK_RIGHT -> xsm = 1;//Si es la tecla ->
            }
        }
    }

    public void keyReleased(KeyEvent e) {

        if (this.isStop()) {//Esto solo aplica si esta detenida
            switch (e.getKeyCode()) {//Reviso la tecla
                //En caso de que se suelte alguna de las flechas
                case KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT -> xsm = 0;
            }
        }
    }

    public int getCenter() {
        return x + getMiddle();
    }

    public int getMiddle() {

        return (imageWidth / 2);
    }
}
```

:::
::::

Como puedes ver, se agregaron las funciones keyReleased y keyPressed, junto a getCenter y getMiddle.

Las funciones key lo que hacen es trabajar sobre las acciones de las teclas cuando estas están presionadas o cuando de
hecho se dejan de presionar, mientras getCenter y getMiddle, nos permiten determinar en donde está la pelota y donde
está su coordenada central para la distancia entre las cajas de colisión.
