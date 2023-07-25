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

    public Brick[][] getBrickPad() {
        return brickPad;
    }

    public void setBrickPad(Brick[][] brickPad) {
        this.brickPad = brickPad;
    }

    public String getBackground() {
        return background;
    }

    public void setBackground(String background) {
        this.background = background;
    }

    public String getAudio() {
        return audio;
    }

    public void setAudio(String audio) {
        this.audio = audio;
    }
}
