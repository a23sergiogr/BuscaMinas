package principal;

import buscaminas.BuscaMinas;
import java.util.Scanner;

public class Interface {
    BuscaMinas juego;
    Scanner sc;
    int[] config;

    public Interface() {
        sc = new Scanner(System.in);
        config = new int[3];
        partidas();
    }

    public static void main(String[] args) {
        new Interface();
    }

    
    public void partidas(){
        while(true){
            inicio();
            juego = new BuscaMinas(config[0], config[1], config[2]);
            partida();
            System.out.println("¿Desea jugar otra partida? (s/n)");
            if(sc.next().equals("n")){
                break;
            }
        }
    }

    public void inicio() {
        System.out.println("Bienvenido al juego BuscaMinas");
        System.out.println("Introduzca las filas, columnas y minas que desea");
        System.out.println("Filas: ");
        config[0] = sc.nextInt();
        System.out.println("Columnas: ");
        config[1] = sc.nextInt();
        System.out.println("Minas: ");
        config[2] = sc.nextInt();
    }


    public void partida(){
        int fila, columna, opcion;
        while (true) {
            imprimirTablero();
            System.out.println("Introduzca 1 para poner bandera, 2 para quitar bandera o 3 para descubrir casilla");
            opcion = sc.nextInt();

            if (opcion == 1) {
                System.out.println("Introduzca la fila y columna que desea marcar con bandera");
                System.out.println("Fila: ");
                fila = sc.nextInt();
                System.out.println("Columna: ");
                columna = sc.nextInt();

                juego.ponerBandera(fila - 1, columna - 1);

            } else if (opcion == 2) {
                System.out.println("Introduzca la fila y columna que desea quitar la bandera");
                System.out.println("Fila: ");
                fila = sc.nextInt();
                System.out.println("Columna: ");
                columna = sc.nextInt();

                juego.quitarBandera(fila - 1, columna - 1);

            } else if (opcion == 3) {
                System.out.println("Introduzca la fila y columna que desea descubrir");
                System.out.println("Fila: ");
                fila = sc.nextInt();
                System.out.println("Columna: ");
                columna = sc.nextInt();
    
                if(!juego.descubrirCasilla(fila - 1, columna - 1)){
                    imprimirTablero();
                    System.out.println("Has perdido");       
                    break;             
                }
                
                if(juego.comprobarVictoria()){
                    imprimirTablero();
                    System.out.println("Has ganado");
                    break;
                }
            }
        }
    }

    public void imprimirTablero() {
        char[][] tablero2 = juego.getTablero();
        for (int i = 0; i < tablero2.length; i++) {
            for (int j = 0; j < tablero2[0].length; j++) {
                    System.out.print("| " + tablero2[i][j] + " "); 
            }
            System.out.println("|"); 
            if (i < tablero2.length - 1) {
                for (int k = 0; k < tablero2[0].length; k++) {
                    System.out.print("----"); 
                }
                System.out.println("-");
            }
        }
    }
}
