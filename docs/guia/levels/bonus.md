---
title: Actualizando Bonus
description: Actualización de la clase Bonus de la primera parte
editLink: false
---

# Actualizando Bonus

Ante lo que hemos hecho, es momento de agregar un tipo enumerado al paquete `graphics.bonus` y modificar la clase Bonus.

## BonusType

Este enum funciona de la misma manera que `BorderType`, por consiguiente el Enum, quedaría de la siguiente forma:

```java
package graphics.bonus;

public enum BonusType {

    LASER("Laser.gif"), ENLARGE("Enlarge.gif"), SMALL("Small.gif"),
    PLAYER("Player.gif");
    private final String imageName;

    BonusType(String imageName) {
        this.imageName = imageName;
    }

    public String getImageName() {
        return imageName;
    }
}
```

En este ejemplo se agregaron solo 4 tipos, pero tú puedes agregar más si así lo deseas.

## Bonus

En este caso agregaremos un tipo a los Bonus y modificaremos el constructor para que quede de la siguiente forma:

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
}
```

Como puedes ver, ahora no pedimos el nombre del Bonus sino su tipo y con base a ello es que trabajamos.
