---
title: Definición de Bordes, Tipos Enumerados y Niveles.
description: En este segunda parte nos dedicaremos a trabajar sobre los tipos enumerados del juego y la creación de los niveles del mismo.
editLink: false
---

# Definición de Bordes, Tipos Enumerados y Niveles

Para esta segunda parte del proyecto seguiremos con la estructura de carpetas mencionada en el punto previo.

En esta ocasión trabajaremos tanto con tipos enumerados como con el manejo de archivos para gestionar correctamente los
niveles a ser cargados dentro del juego una vez que empecemos a trabajar ya con la GUI (Graphic User Interface), así que
pongamos manos a la obra.

::: warning Importante
Recuerda que para estas clases y el resto que hemos hecho y para todas las que creemos, deberás
de crear por tu cuenta las funciones get y set.
:::

## SpriteCache

Para trabajar de una forma más adecuada con los Sprites, es momento de crear una caché de imágenes, por lo tanto,
crearemos una clase en el paquete `util` llamada `SpriteCache` que contendrá un único atributo:

- sprites
    - Objeto de tipo HashMap, o lo que es lo mismo una tabla de relaciones de tipo, llave <=> valor, en donde la llave
      es un nombre y el valor es un objeto asociado, esto nos permitirá que una vez que demos de alta una imagen en la
      tabla, ese nombre no pueda ser usado nuevamente y en su lugar recuperaremos lo que esté almacenado o asociado a
      dicho nombre.

De igual manera, contendrá tanto su constructor como dos funciones, una para obtener una imagen con base a una llave y
la otra para recuperar la imagen directamente de disco y crear con ello una entrada en la tabla de control, por
consiguiente nuestra clase se verá de la siguiente manera:

```java
package util;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public final class SpriteCache {

    private HashMap<String, Image> sprites;

    public SpriteCache() {

        sprites = new HashMap<>();
    }

    public Image getSprite(String nombre) {

        Image img = sprites.get(nombre);
        if (img == null) {

            img = loadImage("img/" + nombre);
            sprites.put(nombre, img);

        }
        return img;
    }

    private Image loadImage(String nombre) {

        try {

            return new ImageIcon(nombre).getImage();
        } catch (Exception e) {
            System.out.println("No se pudo cargar la imagen " + nombre);
            System.out.println("El error fue : " + e.getClass().getName() + " " + e.getMessage());
            //La siguiente línea hace que el programa termine, esto por seguridad al no contar con una caché.
            System.exit(0);
            return null;
        }
    }

    public HashMap<String, Image> getSprites() {
        return sprites;
    }

    public void setSprites(HashMap<String, Image> sprites) {
        this.sprites = sprites;
    }
}
```

Como podrás notar, la clase usar la notación public final class, esto se debe a que en este caso la clase será
inmutable, es decir, solo existirá una en el sistema y será usada por todos mediante la interfaz Globals.

## Globals

Como mencionamos antes, una vez creada la caché, es hora de hacer que nuestra interfaz de elementos globales contenga
esta caché, además de agregar el nombre del Sprite de los Lasers, así como el ancho y alto de los ladrillos, el alto del
margen y también crearemos la función random que nos permitirá crear números aleatorios con varios fine, para ello
agregaremos algunas líneas a la interfaz:

```java
package util;

import java.awt.*;

public interface Globals {

    SpriteCache SPRITE_CACHE = new SpriteCache();
    String LASER_SPRITE = "laser.png";
    int BRICK_WIDTH = 42;
    int BRICK_HEIGHT = 20;
    int BORDER_HEIGHT = 19;

    static int random(int min, int max) {

        int range = max - min + 1;
        return (int) (Math.random() * range) + min;
    }
}
```

## Sprite

Hasta ahora la clase Sprite solo contenía información básica, pero ahora que empezaremos a usar más las cualidades de un
Sprite, es hora agregar tres funciones a nuestra clase:

- Una función para obtener la caja de choque o Collision Box de cada Sprite.
- Una función para obtener las dimensiones de una imagen.
- Una función para obtener imágenes desde la caché.

```java
package graphics;

public abstract class Sprite {

    public Sprite(String image, int x, int y) {

        this.imageName = image;
        this.x = x;
        this.y = y;
        //En el constructor agregaremos la siguiente línea para obtener inicializar las dimensiones de cada imagen.
        getImageDimensions();
    }

    public Rectangle getRect() {

        return new Rectangle(this.getX(), this.getY(), this.getImageWidth(), this.getImageHeight());
    }

    public void getImageDimensions() {

        this.setImageWidth(this.getImage().getWidth(null));
        this.setImageHeight(this.getImage().getHeight(null));
    }

    public Image getImage() {
        return new ImageIcon(Globals.SPRITE_CACHE.getSprite(this.imageName)).getImage();
    }
}
```

## Laser

Ahora que ya tenemos todo esto, podemos agregar la clase `Laser` ya que nuestro juego usará el modo de disparo y nuestra
clase se verá así:

```java
package graphics;

import util.Globals;
import util.Moveable;

public class Laser extends Sprite implements Moveable {

    private boolean destroy = false;

    public Laser(int x, int y) {

        super(Globals.LASER_SPRITE, x, y);
    }

    @Override
    public void move() {

        this.setY(this.getY() - 5);
    }

    public boolean isDestroy() {
        return destroy;
    }

    public void setDestroy(boolean destroy) {
        this.destroy = destroy;
    }
}
```

Como podrás notar, solo tiene un atributo que nos sirve para saber si el laser choco con algo o no y se mueve a razón de
5px por segundo.
