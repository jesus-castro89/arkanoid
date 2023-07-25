package util;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public final class SpriteCache {
    /**
     * La clase HashMap permite definir una matriz bajo el estándar: llave <=> valor, es decir, a cada String en este caso
     * le corresponderá un objeto de la clase Image dentro del arreglo
     */
    private HashMap<String, Image> sprites;

    public SpriteCache() {

        sprites = new HashMap<>();
    }

    /**
     * Carga la imagen de la lista, en caso de que no este dentro, la carga desde el archivo y la inserta
     * en el hashmap para no tener que volver a leer desde archivo.
     *
     * @param nombre Nombre de la imagen a recuperar
     * @return Objeto de tipo Image que permite dibujar dicho elemento dentro de un panel.
     */
    public Image getSprite(String nombre) {

        Image img = sprites.get(nombre);
        if (img == null) {

            img = loadImage("img/" + nombre);
            sprites.put(nombre, img);

        }
        return img;
    }


    /**
     * Intenta cargar la imagen de la dirección, en caso de que no la cargué finaliza el programa y
     * da un error.
     *
     * @param nombre Nombre de la imagen a cargar
     * @return Objeto de tipo Image que permite dibujar dicho elemento dentro de un panel.
     */
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
