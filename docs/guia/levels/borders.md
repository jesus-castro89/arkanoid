---
title: La clases Border y BorderType.
description: Definición y aplicación de los bordes del juego.
editLink: false
---

# El paquete Border

Dentro del paquete `graphics.border`, nos encontramos con un espacio que hasta ahora no hemos tocado, pero que es
necesario definir para el correcto trabajo del juego, así que veamos que clases, atributos y funciones contiene este
pequeño paquete.

## BorderType

En primer lugar, hablemos del tipo enumerado BorderType, este nos permitirá definir solo uno de tres bordes en el juego,
siendo estos:

- **LEFT**
    - El borde del extremo izquierdo del tablero de juego.
- **RIGHT**
    - El borde del extremo derecho del tablero de juego.
- **TOP**
    - El borde del extremo superior del juego.

El objetivo entonces de este tipo enumerado no es otro sino contener el nombre de la imagen asociada a cada uno de los
bordes mencionados, por lo tanto, solo contendrá un atributo llamado imageName que usará la clase Border que veremos en
breve, entonces nuestro `Enum`, quedaría de la siguiente forma:

```java
package graphics.border;

public enum BorderType {
    LEFT("leftbar.png"), TOP("topbar.png"), RIGHT("rightbar.png");
    private final String imageName;

    BorderType(String imageName) {
        this.imageName = imageName;
    }

    public String getImageName() {
        return imageName;
    }
}

```

## Border

Una vez que hemos definido el `Enum` previo, ahora podemos trabajar con el Border como tal, el cual define cuatro puntos
como los ladrillos o el jugador para saber los elementos de colisión que usaremos más adelante. Recuerda que de igual
manera, la clase heredará de `Sprite`, por consiguiente nuestra clase quedará de la siguiente manera:

```java Border.java
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

  public int getMinX() {
    return minX;
  }

  public void setMinX(int minX) {
    this.minX = minX;
  }

  public int getMinY() {
    return minY;
  }

  public void setMinY(int minY) {
    this.minY = minY;
  }

  public int getMaxX() {
    return maxX;
  }

  public void setMaxX(int maxX) {
    this.maxX = maxX;
  }

  public int getMaxY() {
    return maxY;
  }

  public void setMaxY(int maxY) {
    this.maxY = maxY;
  }
}
```

Cómo podrás notar, adaptamos el constructor para que reciba las coordenadas de dibujo inicial, pero al mismo tiempo le
pedimos el sistema que para crear el objeto, necesitaremos un tipo de borde.
