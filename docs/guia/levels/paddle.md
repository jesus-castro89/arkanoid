---
title: Actualizando Paddle
description: Actualización de la clase Paddle de la primera parte
editLink: false
---

# Actualizando Paddle

Ante lo que hemos hecho, es momento de agregar un tipo enumerado al paquete `graphics.paddle` y modificar la clase
Paddle.

## PaddleType

Este enum funciona de la misma manera que `BorderType`, por consiguiente el Enum, quedaría de la siguiente forma:

```java
package graphics.paddle;

public enum PaddleType {
    NORMAL("paddle.png"), LARGE("paddle-large.png"),
    LASER("paddle-laser.png");

    private final String imageName;

    PaddleType(String imageName) {
        this.imageName = imageName;
    }

    public String getImageName() {
        return imageName;
    }
}
```

En este ejemplo se agregaron solo 3 tipos, pero tú puedes agregar más si así lo deseas.

## Paddle

En este caso lo que haremos será agregar una función y modificar el constructor para que quede de la siguiente manera:

```java
package graphics.paddle;

public class Paddle extends Sprite implements Moveable {

    public Paddle(PaddleType type, int x, int y) {

        super(type.getImageName(), x, y);
        this.xam = 0;
        changeType(type);
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
}
```

Como puedes ver, ahora no pedimos el nombre del Bonus sino su tipo y con base a ello es que trabajamos.
