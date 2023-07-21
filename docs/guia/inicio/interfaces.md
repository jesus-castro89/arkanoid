---
title: Interfaces
description: Descripción de las Interfaces a usar en el proyecto
editLink: false
---

# Interfaces

En este caso deberemos crear dos interfaces que nos permitirán manejar algunos aspectos generales del juego o bien
definir una función específica para el movimiento de los componentes, ambas se encontrarán dentro del paquete `util`.

Así que analicemos en este espacio a las dos interfaces iniciales con las que tendremos que trabajar.

## Moveable

Esta interfaz deberá de ser creada en el paquete util y solo contendrá la definición de la función move de la siguiente
manera:

```java
public interface Moveable {

    void move();
}
```

En este caso debes de tener en cuenta que esta interfaz es la que implementarán algunas de las clases que hereden de
Sprite, esto debido a que no todos los elementos en pantalla serán susceptibles a moverse.

## Globals

Esta es quizás la interfaz más compleja de todas, ya que trabajaremos tanto con funciones como con constantes, debido a
que esta interfaz es quien albergará toda la información de configuración de nuestro juego, así que primero analicemos
las constantes:

- **BALL_SPRITE**
    - Nombre del Sprite a usar para la pelota por defecto.
- **PANEL_DIMENSION**
    - Dimension del panel de juego principal.
- **MENU_DIMENSION**
    - Dimensión del panel del menú de acciones del juego
- **BALL_MARGIN**
    - Margen de la pelota de juego respecto al jugador, esto solo aplicable cuando el juego inicia o el jugador pierde
      una vida.
- **PADDLE_WIDTH**
    - El margen del jugador respecto a los bordes del área de juego.
- **BRICK_MARGIN**
    - El margen de los ladrillos de juego respecto a los bordes del área de juego.
- **INIT_PADDLE_X**
    - Posición inicial sobre el eje X del jugador.
- **INIT_PADDLE_Y**
    - Posición inicial sobre el eje Y del jugador.
- **INIT_BALL_X**
    - Posición inicial sobre el eje X de la pelota de juego.
- **INIT_BALL_Y**
    - Posición inicial sobre el eje Y de la pelota de juego.
- **BRICK_ROWS**
    - Número de filas de ladrillos.
- **BRICK_COLUMNS**
    - Número de columnas de ladrillos.
- **PERIOD**
    - Taza de refresco del juego.
- **STAR_LIVES**
    - Vidas iniciales.
- **BOTTOM_EDGE**
    - Posición sobre el eje X desde donde se pierde una vida.

Cómo podrás darte cuenta, estamos definiendo una cantidad considerable de variables debido a que serán usadas por
diversas clases en el transcurso de la ejecución del juego.

Ahora bien, una vez visto lo anterior, nuestra interface deberá quedar de la siguiente manera:

```java
import java.awt.*;

interface Globals {

    String BALL_SPRITE = "ball.gif";
    Dimension PANEL_DIMENSION = new Dimension(466, 640);
    Dimension MENU_DIMENSION = new Dimension(200, 630);
    int BALL_MARGIN = ((Globals.PADDLE_WIDTH / 2) + Globals.BRICK_MARGIN) - (Globals.BALL_WIDTH / 2);
    int PADDLE_WIDTH = 64;
    int BALL_WIDTH = 20;
    int BRICK_MARGIN = 18;
    int INIT_PADDLE_X = 202;
    int INET_PADDLE_Y = 604;
    int INIT_BALL_X = 224;
    int INIT_BALL_Y = 585;
    int BRICKS_ROWS = 10;
    int BRICK_COLUMNS = 10;
    int PERIOD = 10;
    int START_LIVES = 3;
    int BOTTOM_EDGE = 621;
}
```

En esta ocasión omitimos las palabras static y final de las interfaces de acuerdo a las actualizaciones en las
disposiciones de Java.
