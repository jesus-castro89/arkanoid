---
title: Gráficos
description: Definición Inicial de los tipos gráficos del proyecto
editLink: false
---

# Gráficos

El paquete `graphics` es donde colocaremos nuestros elementos visuales, es decir, el jugador, la pelota, etc., así que,
veamos cada una de las clases iniciales de este paquete.

## Ball

La primera clase del paquete `graphics`, será nuestra pelota de juego que contendrá 4 atributos:

- **xam**
    - Representa el movimiento sobre el eje X, y es el acrónimo para 'X Axis Movement'.
- **yam**
    - Representa el movimiento sobre el eje Y, y es el acrónimo para 'Y Axis Movement'.
- **stop**
    - Es un elemento booleano que permite saber si la pelota se puede o no mover libremente.
- **xsm**
    - Representa el movimiento sobre el eje X, pero únicamente cuando la pelota está pegada al jugador, y es el acrónimo
      para 'X Stop Movement'.

Deberá además definir las siguientes funciones:

- Un constructor que requiera por ahora de una posición inicial sobre los ejes 'Y' y 'X'.
- Una función para reiniciar el estado base de la pelota.

Y por último heredar de `Sprite` e implementar `Moveable`, por lo que la clase quedaría de la siguiente manera:

```java
package graphics;

import util.Moveable;
import util.Globals;

public class Ball extends Sprite implements Moveable {

    private int xam;
    private int yam;
    private boolean stop;
    private int xsm;

    public Ball(int x, int y) {

        super(Globals.BALL_SPRITE, x, y);
        this.xam = 1;
        this.yam = -1;
        this.stop = true;
        this.xsm = 0;
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

## Paddle

Seguidamente, crearemos a nuestra clase que representará al jugador que se encuentre en el paquete `graphics.paddle`, y
que contará con los siguientes atributos:

- **xam**
    - Representa el movimiento sobre el eje X, y es el acrónimo para 'X Axis Movement'.
- **minX**
    - Coordenada X del menor punto de dibujo del Sprite.
- **minY**
    - Coordenada Y del menor punto de dibujo del Sprite.
- **maxX**
    - Coordenada X del mayor punto de dibujo del Sprite.
- **maxY**
    - Coordenada Y del menor punto de dibujo del Sprite.
- **border**
    - Ancho del borde exterior del Sprite de juego. Se usa para algunas fórmulas estándar.
- **normalZone**
    - Ancho desde el borde de la barra hasta la zona central. Se usa para algunas fórmulas estándar.
- **centerZone**
    - Ancho de la zona central. Se usa para algunas fórmulas estándar.
- **endFirstBorder**
    - Donde acaba el borde y es igual a: X + border.
- **startCenter**
    - Donde comienza la zona central de la tabla es igual a: endFirstBorder + normalZone.
- **endCenter**
    - Donde acaba la zona centra de la tabla y es igual a: startCenter + centerZone.
- **startSecondBorder**
    - Donde empieza el segundo borde del jugador y es igual a: X + imageWidth - border.
- **endSecondBorder**
    - Donde empieza el segundo borde del jugador y es igual a: X + imageWidth.
- **lowPart**
    - Es la parte de abajo de la barra y es igual a: Y + imageHeight.
- **shootMode**
    - Booleano que permite determinar si el jugador esta o no en modo de disparo.

De igual manera, deberá definir las siguientes funciones:

- Constructor que recibe un tipo de `PaddleType` o jugador y las coordenadas de inicio.
- La implementación de la interface.
- Una función que permita reiniciar la posición y tipo del jugador.
- Una función que permita cambiar el tipo de Sprite en función de varios elementos como los `Bonus`.

De igual manera extenderá de `Sprite` e implementará `Moveable`, tal manera que la clase nos quedará de la siguiente
forma:

```java
package graphics.paddle;

import graphics.Sprite;
import util.Globals;
import util.Moveable;

public class Paddle extends Sprite implements Moveable {

    private int xam;
    private int minX;
    private int minY;
    private int maxX;
    private int maxY;
    private int border; //Ancho del borde de la barra
    private int normalZone; //Ancho desde el borde de la barra hasta la zona central
    private int centerZone; //Ancho de la zona central
    private int endFirstBorder; //2
    private int startCenter; //3
    private int endCenter; //4
    private int startSecondBorder; //5
    private int endSecondBorder; //6
    private int lowPart; //7
    private boolean shootMode;

    public Paddle(PaddleType type, int x, int y) {

        super("", x, y);
        this.xam = 0;
        this.shootMode = false;
        this.border = 16; //Ancho del borde de la barra
        this.normalZone = 10; //Ancho desde el borde de la barra hasta la zona central
        this.centerZone = 12; //Ancho de la zona central
        this.endFirstBorder = this.x + this.border; //2
        this.startCenter = this.endFirstBorder + this.normalZone; //3
        this.endCenter = this.startCenter + this.centerZone; //4
        this.startSecondBorder = this.x + this.getImageWidth() - this.border; //5
        this.endSecondBorder = this.x + this.getImageWidth(); //6
        this.lowPart = this.y + this.getImageHeight(); //7
    }

    @Override
    public void move() {

    }

    public void changeType(PaddleType type) {

    }

    public void resetState() {

        this.x = Globals.INIT_PADDLE_X;
        this.y = Globals.INET_PADDLE_Y;
    }
}
```

## Bonus

La clase Bonus del paquete `graphics.bonus`, nos permitirá controlar los Bonus en pantalla más adelante dentro del
juego.
En el caso de esta clase cuenta por ahora con los siguientes atributos:

- **yam**
    - Representa el movimiento sobre el eje Y, y es el acrónimo para 'Y Axis Movement'.
- **taken**
    - Booleano que determina si el Bonus ha sido o no tomado por el jugador.

Junto a sus atributos, deberá de heredar de `Sprite` e implementar `Moveable` quedando la clase de la siguiente manera:

```java
package graphics.bonus;

import graphics.Sprite;
import util.Moveable;

public class Bonus extends Sprite implements Moveable {

    private int yam;
    private boolean taken;

    public Bonus(String image, int x, int y) {

        super(image, x, y);
        this.yam = 1;
        this.taken = false;
    }

    @Override
    public void move() {

    }
}

```

## Brick

La clase Brick es nuestra última parada en este primer paso para la preparación de nuestro proyecto, esta clase del
paquete `graphics.brick`, cuenta con algunos atributos similares a Paddle, ya que al igual que esa clase, necesitamos de
los puntos máximos y mínimos de dibujo, pero mejor analicemos con detalle todos sus atributos:

- **minX**
    - Coordenada X del menor punto de dibujo del Sprite.
- **minY**
    - Coordenada Y del menor punto de dibujo del Sprite.
- **maxX**
    - Coordenada X del mayor punto de dibujo del Sprite.
- **maxY**
    - Coordenada Y del menor punto de dibujo del Sprite.
- **life**
    - Representa la resistencia o número de impactos que puede recibir el ladrillo antes de considerarse destruido.
- **points**
    - Representa a la cantidad de puntos a obtener cuando el ladrillo se considere destruido.
- **destroy**
    - Booleano que permite determinar si un ladrillo esta o no destruido para el juego.

Junto a sus atributos, la clase requiere de las siguientes funciones:

- Una función que permita disminuir su resistencia o vida.
- Un constructor que coincida con la clase Sprite.

Por lo que la clase quedaría de la siguiente manera:
```java
package graphics.brick;

import graphics.Sprite;

public class Brick extends Sprite {

    private int minX;
    private int minY;
    private int maxX;
    private int maxY;
    private int life;
    private int points;
    private boolean destroy;

    public Brick(String image, int x, int y) {
        super(image, x, y);
        this.life = 1;
        this.points = 100;
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
