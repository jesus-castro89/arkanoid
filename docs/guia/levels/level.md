---
title: La clase Level
description: Definición y aplicación de los niveles de Juego
editLink: false
---

# Level

Esta clase del paquete util nos permitirá definir en un primer instante los niveles de juego para poder observar la
creación dinámica de las cosas y luego poder almacenar, mediante otras clases, su contenido en archivos que pueda leer
nuestro Juego.

Esta clase cuenta con tres atributos:
- **brickPad**
  - Matriz de bloques representan el área de juego.
- **background**
  - El fondo de pantalla de juego.
- **audio**
  - El audio de fondo para el nivel.

Por lo que nuestra clase se ve así:
```java
package util;

import graphics.brick.Brick;
import graphics.brick.BrickType;

public class Level {

  private Brick[][] brickPad;
  private String background;
  private String audio;

  public Level(String background, String audio) {

    this.background = background;
    this.audio = audio;
    //Esto lo que hace es tomar las constantes de Globals para determinar el tamaño de la matriz
    this.brickPad = new Brick[Globals.BRICKS_ROWS][Globals.BRICK_COLUMNS];
    //Esta variable permitirá guardar un número que usaremos para cada tipo de bloque
    int numero;
    int x;
    int y;
    //El primer for es para recorrer las filas
    for (int row = 0; row < Globals.BRICKS_ROWS; row++) {
      //El segundo for es para recorrer las columnas
      for (int column = 0; column < Globals.BRICK_COLUMNS; column++) {
        numero = Globals.random(1, 2);
        x = (row * (Globals.BRICK_WIDTH)) + Globals.BRICK_MARGIN;
        y = (column * (Globals.BRICK_HEIGHT)) + Globals.BORDER_HEIGHT;
        switch (numero) {
          case 1 -> brickPad[row][column] = new Brick(BrickType.BLUE, x, y);
          case 2 -> brickPad[row][column] = new Brick(BrickType.RED, x, y);
        }
      }
    }
  }
}
```
