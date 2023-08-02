---
title: Actualizando Brick
description: Actualización de la clase Brick de la primera parte
editLink: false
---

# Actualizando Brick

Ante lo que hemos hecho, es momento de agregar un tipo enumerado al paquete `graphics.brick` y modificar la clase Brick.

## BrickType

Este enum funciona de la misma manera que `BorderType`, pero ahora contendrá además algunas indicaciones en cuanto a la
resistencia y puntos que otorgará al ser destruido, por consiguiente el Enum, quedaría de la siguiente forma:

```java
package graphics.brick;

import java.io.Serializable;

public enum BrickType {

    BLUE("brick-blue.png", 1, 5),
    RED("brick-red.png", 2, 10);

    private final String imageName;
    private final int life;
    private final int points;

    BrickType(String imageName, int life, int points) {
        this.imageName = imageName;
        this.life = life;
        this.points = points;
    }

    public String getImageName() {
        return imageName;
    }

    public int getLife() {
        return life;
    }

    public int getPoints() {
        return points;
    }
}
```

::: warning MÁS BLOQUES PARA LA ENTREGA FINAL
En este ejemplo solo se agregaron dos tipos de bloques, pero tú deberás de agregar 5 o más tipos a tu gusto.
:::

## Brick

Con este nuevo Enum, nuestra clase Brick deberá de actualizarse en concordancia, así que agregaremos una función y
modificaremos el constructor de la siguiente manera:

```java
package graphics.brick;

import graphics.Sprite;

public class Brick extends Sprite {

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
}
```

Como puedes ver, ahora no pedimos el nombre del Bloque sino su tipo y con base a ello es que trabajamos, así mismo la
función updateRect se crea por seguridad para el uso de interfaces.
