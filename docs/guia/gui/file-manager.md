---
title: FileManager
description: Definición de la clase encargada de gestionar los niveles
editLink: false
---

# FileManager <Badge type="tip" text="Nuevo" vertical="middle" />

Esta clase se encuentra en el paquete ui y tendrá la siguiente estructura:

```java
package util;

import java.io.*;

public class FileManager {

    public static void main(String[] args) {

        Level level = new Level("background1.png", "level1.wav");
        FileManager fileManager = new FileManager();
        fileManager.writeLevel(level, 1);
        System.out.println("---NIVEL GUARDADO CON ÉXITO---");
        /*FileManager fileManager = new FileManager();
        Level level = fileManager.readLevel(1);
        System.out.println(level);*/
    }

    public Level readLevel(int currentLevel) {

        Level level = null;
        InputStream file = null;
        InputStream buffer = null;
        ObjectInput input = null;
        try {

            file = new FileInputStream("levels/level" + currentLevel + ".lvl");
            buffer = new BufferedInputStream(file);
            input = new ObjectInputStream(buffer);
            level = (Level) input.readObject();
            input.close();
            buffer.close();
            file.close();
        } catch (Exception e) {
            e.printStackTrace();
            if (input != null) {
                try {
                    input.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
            if (buffer != null) {
                try {
                    buffer.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
            if (file != null) {
                try {
                    file.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
        return level;
    }

    public void writeLevel(Level level, int curentLevel) {

        OutputStream file = null;
        OutputStream buffer = null;
        ObjectOutputStream output = null;
        try {

            file = new FileOutputStream("levels/level" + curentLevel + ".lvl");
            buffer = new BufferedOutputStream(file);
            output = new ObjectOutputStream(buffer);
            output.writeObject(level);
            output.close();
            buffer.close();
            file.close();
        } catch (Exception e1) {

            e1.printStackTrace();
            if (output != null) {
                try {
                    output.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (buffer != null) {
                try {
                    buffer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (file != null) {
                try {
                    file.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
```

Como te darás cuenta, esta clase es muy similar a algo que ya hemos hecho en clase, recuerda que para que esto funcione
deberás de modificar el nivel y todas las clases afectadas por este para que pueda ser serializado, es decir hacer que
las siguientes clases Implementen Serializable:

- Level
- Sprite
- BrickType
- Brick

::: tip PROBAR LA VENTANA
Si has seguido todos los pasos previos, te habrás percatado que ya modificamos todas esas clases, así que para probar la
funcionalidad de la ventana, lo que debes hacer es ejecutar esta clase para que se genere de forma aleatoria la matriz
de bloques de al menos un nivel.
    
Pero para que funcione correctamente también deberás de descargar el paquete de imágenes que puedes encontrar en BB en
el apartado dedicado al proyecto.
:::
