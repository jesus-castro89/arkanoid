---
title: Agregando los Bonus a la Pantalla
description: Modificación de Brick y Bonus para la aparición aleatoria de Bonus en juego.
editLink: false
---

# Agregando los Bonus a la Pantalla

Para este pequeño apartado, trabajaremos sobre dos clases Brick y Bonus, esto con el objetivo de que de forma aleatoria
se agreguen los bonus al juego.

## Bonus <Badge type="warning" text="Modificada" vertical="middle" />

En la clase Bonus lo que haremos será agregar el GamePanel para que podamos agregar el bonus.

:::: code-group
::: code-group-item Anterior

```java
package graphics.bonus;

import graphics.Sprite;
import util.Moveable;

public class Bonus extends Sprite implements Moveable {

    private int yam;
    private boolean taken;
    private BonusType type;

    public Bonus(BonusType type, int x, int y) {

        super(type.getImageName(), x, y);
        this.yam = 1;
        this.taken = false;
        this.type = type;
    }

    @Override
    public void move() {

        y += yam;
    }
}
```

:::
::: code-group-item ACTUALIZADO

```java{4,12,14,20}
package graphics.bonus;

import graphics.Sprite;
import ui.GamePanel;
import util.Moveable;

public class Bonus extends Sprite implements Moveable {

    private int yam;
    private boolean taken;
    private BonusType type;
    private GamePanel gamePanel;

    public Bonus(BonusType type, int x, int y, GamePanel gamePanel) {

        super(type.getImageName(), x, y);
        this.yam = 1;
        this.taken = false;
        this.type = type;
        this.gamePanel=gamePanel;
    }

    @Override
    public void move() {

        y += yam;
    }
}
```

:::
::::

## Brick <Badge type="warning" text="Modificada" vertical="middle" />

Por su parte, la clase Brick agrega la manera de crear los bonus de forma aleatoria de la siguiente forma:

:::: code-group
::: code-group-item Anterior

```java
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
::: code-group-item ACTUALIZADO

```java{4,5,7,49-59}
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
::::
