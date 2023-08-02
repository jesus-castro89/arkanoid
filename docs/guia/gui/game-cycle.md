---
title: GameCycle
description: Clase que permite controlar el tiempo de juego.
editLink: false
---

# GameCycle <Badge type="tip" text="Nuevo" vertical="middle" />

Esta pequeña clase del paquete `events`, es usada para definir el evento del juego y es de suma importancia, ya que sin ella las imágenes
animadas y el concepto de movimiento dentro del juego no tienen sentido.

Como tal, la clase solamente define la manera en la cual se realizarán ciertas tareas de forma repetitiva, siendo estas tareas las de move y repaint de `GamePanel`.

Por lo que la clase se verá de la siguiente forma:

```java
package events;

import ui.GamePanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameCycle implements ActionListener {

    private final GamePanel panel;

    public GameCycle(GamePanel panel) {

        this.panel = panel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        doGameCycle();
    }

    private void doGameCycle() {

        panel.move();
        panel.repaint();
    }
}
```
