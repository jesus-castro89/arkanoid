---
title: Actualizando Laser
description: Agregando el panel al laser y modificando su comportamiento.
editLink: false
---

# Actualizando Laser <Badge type="warning" text="Modificada" vertical="middle" />

La clase Laser recibirá una pequeña lavadita de cara para poder agregar el GamePanel y también definir algunas cosas
sobre su movimiento.

:::: code-group
::: code-group-item Anterior

```java
package graphics;

import util.Globals;
import util.Moveable;

public class Laser extends Sprite implements Moveable {
    private boolean destroy = false;

    /**
     * Constructor de los objetos que recibe como parámetros, las coordenadas (x, y)
     * de origen de dibujo y el nombre del archivo a dibujar.
     *
     * @param x El valor de la coordenada x
     * @param y El valor de la coordenada y
     */
    public Laser(int x, int y) {

        super(Globals.LASER_SPRITE, x, y);
    }

    @Override
    public void move() {

        this.setY(this.getY() - 5);
    }
}
```

:::
::: code-group-item ACTUALIZADO

```java{3,9,18,21,27}
package graphics;

import ui.GamePanel;
import util.Globals;
import util.Moveable;

public class Laser extends Sprite implements Moveable {
    private boolean destroy = false;
    private GamePanel gamePanel;

    /**
     * Constructor de los objetos que recibe como parámetros, las coordenadas (x, y)
     * de origen de dibujo y el nombre del archivo a dibujar.
     *
     * @param x El valor de la coordenada x
     * @param y El valor de la coordenada y
     */
    public Laser(int x, int y, GamePanel gamePanel) {

        super(Globals.LASER_SPRITE, x, y);
        this.gamePanel = gamePanel;
    }

    @Override
    public void move() {

        y -= 5;
    }
}
```

:::
::::
