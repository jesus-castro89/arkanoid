package graphics.brick;

import java.io.Serializable;

/**
 * Clase que servirá como la lista de tipos de bloques para los niveles de juego.
 */
public enum BrickType implements Serializable {

    BLUE("brick-blue.png", 1, 5),
    RED("brick-red.png", 2, 10);

    /**
     * Nombre de la imagen asociada el tipo de bloque.
     */
    private final String imageName;
    /**
     * Resistencia o número de golpes que deberá recibir el bloque para ser destruido.
     */
    private final int life;
    /**
     * Puntos que otorgará el bloque al usuario una vez destruido.
     */
    private final int points;

    /**
     * Función constructora del elemento enumerado que recibe la imagen, resitencia
     * y puntos del tipo de bloque a ser creado.
     *
     * @param imageName Imagen del bloque.
     * @param life      Puntos de resistencia del bloque.
     * @param points    Puntaje del bloque cuando se destruye.
     */
    BrickType(String imageName, int life, int points) {
        this.imageName = imageName;
        this.life = life;
        this.points = points;
    }

    public String getImageName() {
        return imageName;
    }

    public int getLife() {
        return life;
    }

    public int getPoints() {
        return points;
    }
}
