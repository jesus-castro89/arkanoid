---
title: La clase Sprite
description: Configuración de la clase Sprite del proyecto
editLink: false
---

# Sprite

Nuestra primera parada será la clase `Sprite` del paquete `graphics`, en esta primera clase, deberemos de crear la
siguiente lista de atributos:

- x
    - Coordenada sobre el eje X de posicionamiento del elemento.
- y
    - Coordenada sobre el eje y de posicionamiento del elemento.
- imageWidth
    - Ancho de la imagen que representa al Sprite en el juego.
- imageHeight
    - Alto de la imagen que represente al Sprite en el juego.
- imageName
    - Nombre del archivo a representar el Sprite en cuestión.

Nuestra clase de igual forma deberá de contar con un constructor que recibe como parámetros, el nombre y las posiciones
x, y del objeto.

De tal manera que nuestra clase quede de esta manera:

```java
public abstract class Sprite {
    protected int x;
    protected int y;
    protected int imageWidth;
    protected int imageHeight;
    protected String imageName;

    public Sprite(String image, int x, int y) {

        this.imageName = image;
        this.x = x;
        this.y = y;
    }
}
```

Recuerda que además de la estructura anterior, deberá de crear lass funciones get y set correspondientes para los
atributos de la clase, así mismo, deberás de realizar los bloques de código de tipo JavaDoc a la clase, y a cada
atributo o función.
