---
title: Actualizando Brick
description: Actualización de Brick para registro de puntos
editLink: false
---

# Brick

Para nuestra clase Brick, unicamente agregaremos el registro de puntos en la etiqueta de MainWindow, por lo que la clase
se actualizará de la siguiente forma:

:::: code-group
::: code-group-item Anterior

```java
package graphics.brick;

import graphics.Sprite;
import graphics.bonus.Bonus;
import graphics.bonus.BonusType;
import ui.GamePanel;
import util.Globals;

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
            //Agregamos el Bonus a la Pantalla
            if (Globals.random(1, 10) >= 7) {

                BonusType type = switch (Globals.random(1, 4)) {
                    case 2 -> BonusType.ENLARGE;
                    case 3 -> BonusType.SMALL;
                    case 4 -> BonusType.PLAYER;
                    default -> BonusType.LASER;
                };
                this.getGamePanel().getBonuses().add(new Bonus(type, getMinX(), getMaxY(), this.getGamePanel()));
            }
        }
    }
}
```

:::
::: code-group-item ACTUALIZADO

```java{49-51}
package graphics.brick;

import graphics.Sprite;
import graphics.bonus.Bonus;
import graphics.bonus.BonusType;
import ui.GamePanel;
import util.Globals;

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
            //Añadimos el puntaje al tablero
            int score = Integer.parseInt(gamePanel.getMainWindow().getScoreCountLabel().getText());
            gamePanel.getMainWindow().getScoreCountLabel().setText("%d".formatted((score + getPoints())));
            //Agregamos el Bonus a la Pantalla
            if (Globals.random(1, 10) >= 7) {

                BonusType type = switch (Globals.random(1, 4)) {
                    case 2 -> BonusType.ENLARGE;
                    case 3 -> BonusType.SMALL;
                    case 4 -> BonusType.PLAYER;
                    default -> BonusType.LASER;
                };
                this.getGamePanel().getBonuses().add(new Bonus(type, getMinX(), getMaxY(), this.getGamePanel()));
            }
        }
    }
}
```

:::
::::
