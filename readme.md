# Clases



## Interface

La clase `Interface` es responsable de interactuar con el usuario a través de la consola para jugar al juego de BuscaMinas. Permite configurar el tamaño del tablero y controla el flujo del juego.

#### Métodos

- `public Interface()`: Constructor de la clase.
- `public static void main(String[] args)`: Método principal que inicia el juego.
- `public void partidas()`: Inicia una nueva partida y controla el ciclo del juego.
- `public void inicio()`: Solicita al usuario las dimensiones del tablero y el número de minas.
- `public void partida()`: Gestiona una partida del juego, permitiendo al usuario realizar movimientos.
- `public void imprimirTablero()`: Imprime el estado actual del tablero en la consola.

## BuscaMinas

La clase `BuscaMinas` implementa la lógica del juego BuscaMinas. Controla la generación del tablero, la colocación de minas y el estado de las casillas.

#### Métodos

- `public BuscaMinas(int filas, int columnas, int minas)`: Constructor de la clase.
- `public char[][] getTablero()`: Obtiene una representación del tablero visible para el jugador.
- `private void inicializarTablero()`: Inicializa el tablero del juego.
- `private void colocarMinas(int filaJugada, int columnaJugada)`: Coloca las minas en el tablero.
- `private boolean tableroCercano(int fila, int columna, int filaJugada, int columnaJugada)`: Verifica si una casilla está cerca de la jugada inicial.
- `public boolean descubrirCasilla(int fila, int columna)`: Descubre una casilla del tablero.
- `private void descubrirCasillaRecursivo(int fila, int columna)`: Descubre recursivamente las casillas adyacentes vacías.
- `public void ponerBandera(int fila, int columna)`: Coloca una bandera en una casilla.
- `public void quitarBandera(int fila, int columna)`: Quita una bandera de una casilla.
- `public boolean comprobarVictoria()`: Verifica si el jugador ha ganado el juego.