package buscaminas;

/**
 * Clase que controla la lógica del juego BuscaMinas.
 * 
 *
 * @author a23SergioGR
 * @version 1.0
 */
public class BuscaMinas {
    private int[][] tablero;
    private int[][] visible;
    private int minas, filas, columnas;
    private static final int DEFAULT_SIZE = 10;

    /**
     * Clase que representa el juego BuscaMinas.
     * 
     * @param fila    La fila de la casilla que se desea descubrir.
     * @param columna La columna de la casilla que se desea descubrir.
     * @param minas   El número de minas que se colocarán en el tablero.
     */
    public BuscaMinas(int filas, int columnas, int minas) {
        try {
            this.filas = filas;
            this.columnas = columnas;
            this.minas = minas;
            this.tablero = new int[filas][columnas];
            this.visible = new int[filas][columnas];
        } catch (NegativeArraySizeException e) {
            this.filas = DEFAULT_SIZE;
            this.columnas = DEFAULT_SIZE;
            this.minas = minas;
            this.tablero = new int[DEFAULT_SIZE][DEFAULT_SIZE];
            this.visible = new int[DEFAULT_SIZE][DEFAULT_SIZE];
        }
        inicializarTablero();
        colocarMinas();
    }

    /**
     * Obtiene una representación del tablero visible para el jugador.
     *
     * @return Una matriz de caracteres que representa el tablero visible para el jugador.
     *         Cada posición contiene un carácter que representa el estado de la casilla:
     *         - Si la casilla está visible, muestra el número correspondiente del tablero original.
     *         - Si la casilla no está visible, muestra 'X'.  
     *         - Si la casilla está marcada como bandera, muestra 'F'.
     */
    public char[][] getTablero() {
        char[][] tableroVisible = new char[this.filas][this.columnas];
        for (int i = 0; i < this.filas; i++) {
            for (int j = 0; j < this.columnas; j++) {
                if (this.visible[i][j] == 0) {
                    tableroVisible[i][j] = (char) (this.tablero[i][j] + '0');
                    if(this.tablero[i][j] == 9){
                        tableroVisible[i][j] = 'M'; 
                    }
                } else {
                    tableroVisible[i][j] = 'X';
                }
                if(this.visible[i][j] == 2){
                    tableroVisible[i][j] = 'F'; 
                }
            }
        }
        return tableroVisible;
    }

    private void inicializarTablero() {
        for (int i = 0; i < this.filas; i++) {
            for (int j = 0; j < this.columnas; j++) {
                this.tablero[i][j] = 0;
                this.visible[i][j] = 1;
            }
        }
    }

    private void colocarMinas() {
        int numeroMinas = 0;
        while (numeroMinas < this.minas) {
            int fila = (int) (Math.random() * this.filas);
            int columna = (int) (Math.random() * this.columnas);
            if (this.tablero[fila][columna] != 9) {
                this.tablero[fila][columna] = 9;
                numeroMinas++;
            }
            
            for (int i = Math.max(0, fila - 1); i <= Math.min(this.filas - 1, fila + 1); i++) {
                for (int j = Math.max(0, columna - 1); j <= Math.min(this.columnas - 1, columna + 1); j++) {
                    if (!(i == fila && j == columna) && this.tablero[i][j] != 9) {
                        this.tablero[i][j]++;
                    }
                }
            }
        }
    }

    /**
     * Descubre la casilla especificada. Si la casilla contiene una mina, se descubren todas las casillas.
     *
     * @param fila    La fila de la casilla que se desea descubrir.
     * @param columna La columna de la casilla que se desea descubrir.
     * @return true si el jugador no ha perdido, false en caso contrario.
     */
    public boolean descubrirCasilla(int fila, int columna) {
        try {
            if (this.tablero[fila][columna] == 9) {
                for (int i = 0; i < this.filas; i++) {
                    for (int j = 0; j < this.columnas; j++) {
                        this.visible[i][j] = 0;
                    }
                }
                return false;
            } else {
                descubrirCasillaRecursivo(fila, columna);
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.err.println("Error: Índices de fila o columna fuera de los límites del tablero.");
        }
        return true;
    }

    private void descubrirCasillaRecursivo(int fila, int columna) {
        if (this.visible[fila][columna] == 1){
            this.visible[fila][columna] = 0;

            if(this.tablero[fila][columna] == 0){
                if(fila >= 1)
                    descubrirCasillaRecursivo(fila - 1, columna);
                if(fila < this.filas - 1)
                    descubrirCasillaRecursivo(fila + 1, columna);
                if(columna >= 1)
                    descubrirCasillaRecursivo(fila, columna - 1);
                if(columna < this.columnas - 1)
                    descubrirCasillaRecursivo(fila, columna + 1);

            }
        }
    }

    /**
     * Coloca una bandera en la casilla especificada.
     *
     * @param fila    La fila de la casilla donde se colocará la bandera.
     * @param columna La columna de la casilla donde se colocará la bandera.
     */
    public void ponerBandera(int fila, int columna){
        try {
            this.visible[fila][columna] = 2;
        } catch (ArrayIndexOutOfBoundsException e) {
            System.err.println("Error: Índices de fila o columna fuera de los límites del tablero.");
        }
    }

    /**
     * Quita la bandera de la casilla especificada.
     *
     * @param fila    La fila de la casilla de la que se quitará la bandera.
     * @param columna La columna de la casilla de la que se quitará la bandera.
     */
    public void quitarBandera(int fila, int columna){
        try {
            this.visible[fila][columna] = 1;
        } catch (ArrayIndexOutOfBoundsException e) {
            System.err.println("Error: Índices de fila o columna fuera de los límites del tablero.");
        }
    }

    /**
     * Comprueba si el jugador ha ganado el juego.
     *
     * @return true si el jugador ha ganado el juego, false en caso contrario.
     */
    public boolean comprobarVictoria(){
        for (int i = 0; i < this.filas; i++) {
            for (int j = 0; j < this.columnas; j++) {
                if(this.tablero[i][j] == 9 && this.visible[i][j] == 0){
                    return false;
                }
                if(this.tablero[i][j] != 9 && this.visible[i][j] == 1){
                    return false;
                }
            }
        }

        return true;
    }
}
