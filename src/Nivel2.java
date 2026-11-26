import java.util.Random;
import java.util.Scanner;

// Codigo mio

public class Nivel2_LaVida {

    // CONSTANTES ANSI PARA LOS COLORES
    static final String AZUL_CLARO = "\u001B[96m";
    static final String VIOLETA = "\u001B[95m";
    static final String MARRON = "\u001B[38;5;130m";
    static final String ROJO = "\u001B[91m";
    static final String AZUL = "\u001B[34m";
    static final String AMARILLO = "\u001B[93m";
    static final String RESET = "\u001B[0m";

    public static void main(String[] args) {

        int Max_fila_Tu = 10;
        int Max_columna_Tu = 10;
        char Tu = 'T';
        char LIBRE = '*';

        char[][] tablero = new char[Max_fila_Tu][Max_columna_Tu];

        for (int i = 0; i < Max_fila_Tu; i++) {
            for (int j = 0; j < Max_columna_Tu; j++) {
                tablero[i][j] = LIBRE;
            }
        }

        char fin = 'F';
        tablero[9][9] = fin;


        // 1 Tu
        Random aleat = new Random();
        int filaAl = -1;
        int colAl = -1;
        int filaOficialTu = -1;
        int columnaOficialTu = -1;

        for (int i = 0; i < 1; i++) {
            do {
                filaAl = aleat.nextInt(Max_fila_Tu);
                colAl = aleat.nextInt(Max_columna_Tu);
            } while (tablero[filaAl][colAl] != LIBRE);

            tablero[filaAl][colAl] = Tu;
        }

        // OFICIALIZO LAS POSICIONES DE MI Tu
        filaOficialTu = filaAl;
        columnaOficialTu = colAl;
        // FIN


        // Cajas
        char muros = 'X';

        for (int i = 0; i < 10; i++) {
            do {
                filaAl = aleat.nextInt(Max_fila_Tu);
                colAl = aleat.nextInt(Max_columna_Tu);
            } while (tablero[filaAl][colAl] != LIBRE);

            tablero[filaAl][colAl] = muros;
        }


        // Mujeres
        char mujeres = 'M';

        for (int i = 0; i < 7; i++) {
            do {
                filaAl = aleat.nextInt(Max_fila_Tu);
                colAl = aleat.nextInt(Max_columna_Tu);
            } while (tablero[filaAl][colAl] != LIBRE);

            tablero[filaAl][colAl] = mujeres;
        }


        // Piezas
        char RAM = 'R';
        int RamCont = 0;

        for (int i = 0; i < 3; i++) {
            do {
                filaAl = aleat.nextInt(Max_fila_Tu);
                colAl = aleat.nextInt(Max_columna_Tu);
            } while (tablero[filaAl][colAl] != LIBRE);

            tablero[filaAl][colAl] = RAM;;
        }


        char Cerveza = 'C';

        for (int i = 0; i < 5; i++) {
            do {
                filaAl = aleat.nextInt(Max_fila_Tu);
                colAl = aleat.nextInt(Max_columna_Tu);
            } while (tablero[filaAl][colAl] != LIBRE);

            tablero[filaAl][colAl] = Cerveza;
        }


        for (int i = 0; i < Max_fila_Tu; i++) {
            for (int j = 0; j < Max_columna_Tu; j++) {

                String color = RESET;

                if (tablero[i][j] == Tu) {
                    color = AZUL_CLARO;
                } else if (tablero[i][j] == fin) {
                    color = VIOLETA;
                } else if (tablero[i][j] == muros) {
                    color = MARRON;
                } else if (tablero[i][j] == mujeres) {
                    color = ROJO;
                } else if (tablero[i][j] == RAM) {
                    color = AZUL;
                } else if (tablero[i][j] == Cerveza) {
                    color = AMARILLO;
                }

                System.out.print(color + tablero[i][j] + RESET + " ");
            }

            System.out.println();
        }


        // EMPIEZA EL JUEGO !!!
        int vidas = 10;
        int fin1 = 0;

        Scanner lector = new Scanner(System.in);

        System.out.println("Puedes usar I para el inventario y M para buscar informacion sobre el nivel");

        do {
            System.out.println("Te puedes mover con WASD");
            System.out.print("Dime una letra para moverte por el tablero: ");

            char mov = lector.next().charAt(0);

            int filaAnteriorTu = filaOficialTu;
            int columnaAnteriorTu = columnaOficialTu;

            switch (Character.toUpperCase(mov)) { // W, A, S o D

                case 'W':
                    filaOficialTu--;
                    break;

                case 'A':
                    columnaOficialTu--;
                    break;

                case 'S':
                    filaOficialTu++;
                    break;

                case 'D':
                    columnaOficialTu++;
                    break;

                case 'I':
                    System.out.println("Tienes " + vidas + " vidas");
                    System.out.println("Tienes " + RamCont + " RAM");

                    int RAMRestante = 3 - RamCont;

                    System.out.println("Te quedan " + RAMRestante + " para poder finalizar el juego");
                    System.out.println("Las M o también llamadas mujeres te restan una vida");
                    break;
            }


            // Para que no se salga del trablero
            if (
                    filaOficialTu < 0 ||
                            filaOficialTu > 9 ||
                            columnaOficialTu < 0 ||
                            columnaOficialTu > 9
            ) {

                System.out.println("Limite del tablero");

                filaOficialTu = filaAnteriorTu;
                columnaOficialTu = columnaAnteriorTu;

            } else {

                if (tablero[filaOficialTu][columnaOficialTu] == muros) {

                    System.out.println("Te has chocado con una caja");

                    filaOficialTu = filaAnteriorTu;
                    columnaOficialTu = columnaAnteriorTu;

                } else if (tablero[filaOficialTu][columnaOficialTu] == Cerveza) {

                    tablero[filaOficialTu][columnaOficialTu] = Tu;
                    tablero[filaAnteriorTu][columnaAnteriorTu] = LIBRE;
                    vidas++;

                } else if (tablero[filaOficialTu][columnaOficialTu] == mujeres) {

                    tablero[filaOficialTu][columnaOficialTu] = Tu;
                    tablero[filaAnteriorTu][columnaAnteriorTu] = LIBRE;
                    vidas--;

                } else if (tablero[filaOficialTu][columnaOficialTu] == LIBRE) {

                    tablero[filaOficialTu][columnaOficialTu] = Tu;
                    tablero[filaAnteriorTu][columnaAnteriorTu] = LIBRE;

                } else if (tablero[filaOficialTu][columnaOficialTu] == fin) {

                    if (RamCont == 3) {

                        tablero[filaOficialTu][columnaOficialTu] = Tu;
                        tablero[filaAnteriorTu][columnaAnteriorTu] = LIBRE;

                        System.out.println("Fin del juego");

                        fin1 = 1;

                    } else {

                        System.out.println("Necestas tener las RAM de la nave");

                        filaOficialTu = filaAnteriorTu;
                        columnaOficialTu = columnaAnteriorTu;
                    }

                } else if (tablero[filaOficialTu][columnaOficialTu] == RAM) {

                    tablero[filaOficialTu][columnaOficialTu] = Tu;
                    tablero[filaAnteriorTu][columnaAnteriorTu] = LIBRE;

                    RamCont++;
                }
            }


            for (int i = 0; i < Max_fila_Tu; i++) {
                for (int j = 0; j < Max_columna_Tu; j++) {

                    String color = RESET;

                    if (tablero[i][j] == Tu) {
                        color = AZUL_CLARO;
                    } else if (tablero[i][j] == fin) {
                        color = VIOLETA;
                    } else if (tablero[i][j] == muros) {
                        color = MARRON;
                    } else if (tablero[i][j] == mujeres) {
                        color = ROJO;
                    } else if (tablero[i][j] == RAM) {
                        color = AZUL;
                    } else if (tablero[i][j] == Cerveza) {
                        color = AMARILLO;
                    }

                    System.out.print(color + tablero[i][j] + RESET + " ");
                }

                System.out.println();
            }

            System.out.println("Tienes " + vidas + " vidas");
            System.out.println("Tienes " + RamCont + " RAM");

        } while (vidas > 0 && fin1 == 0);

    }
}