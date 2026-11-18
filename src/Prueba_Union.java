import java.util.Random;
import java.util.Scanner;

public class Prueba_Union{
    public static void main(String[] args){
        Scanner leer = new Scanner(System.in);

        // Definir
        int filamax = 10;
        int columnamax = 10;
        int vidas = 0;
        int fin1 = 0;
        int numlavas = 10;
        int nummanzanas = 5;
        int numperlas = 3;
        int nummuros = 5;
        int contnivel = 0;
        char Steve = 'S';
        char libre = '·';
        char fin = 'F';
        char lava = 'L';
        char manzana = 'M';
        char perlas = 'P';
        char muros = '#';

        // Sistema de vidas
        do {
            System.out.println("Selecciona dificultad");
            System.out.print("Numero del 1 al 5 (siendo 1 dificil y 5 gallina) : ");
            vidas = leer.nextInt();
        }while (vidas < 1  || vidas > 5);

        if (vidas == 1){
            System.out.println("Tienes 1 vida");
        } else if (vidas == 2) {
            System.out.println("Tienes 2 vidas");
        } else if (vidas == 3) {
            System.out.println("Tienes 3 vidas");
        } else if (vidas == 4) {
            System.out.println("Tienes 4 vidas");
        } else if (vidas == 5) {
            System.out.println("Tienes 5 vidas");
        }

        System.out.println("Te puedes mover con WASD");
        System.out.println("Usa I para ver la informacion necesaria");
        System.out.println("Utiliza M para ve la informacion de las entidades");

        // Colocar
        char [][] mundo = new char [filamax][columnamax];
        for (int i = 0; i < filamax; i++) {
            for (int j = 0; j < columnamax; j++) {
                mundo[i][j] = libre;
            }
        }
        mundo [9][9] = fin;

        // 1 Steve
        Random aleat = new Random();
        int filaAl = -1;
        int colAl = -1;
        int filaOficialSteve = -1;
        int columnaOficialSteve = -1;

        for (int i = 0; i < 1; i++) {
            do{
                filaAl = aleat.nextInt(filamax);
                colAl = aleat.nextInt(columnamax);
            }while(mundo[filaAl][colAl]!=libre);
            mundo[filaAl][colAl] = Steve;
        }

        // OFICIALIZO LAS POSICIONES DE MI Steve
        filaOficialSteve = filaAl;
        columnaOficialSteve = colAl;
        // FIN

        //Muros
        for (int i = 0; i < 10; i++) {
            do{
                filaAl = aleat.nextInt(filamax);
                colAl = aleat.nextInt(columnamax);
            }while(mundo[filaAl][colAl]!=libre);
            mundo[filaAl][colAl] = muros;
        }

        //Lava
        char Lava = 'L';
        for (int i = 0; i < 7; i++) {
            do{
                filaAl = aleat.nextInt(filamax);
                colAl = aleat.nextInt(columnamax);
            }while(mundo[filaAl][colAl]!=libre);
            mundo[filaAl][colAl] = Lava;
        }

        //Perlas
        int perlascont = 0;
        for (int i = 0; i < 3; i++) {
            do{
                filaAl = aleat.nextInt(filamax);
                colAl = aleat.nextInt(columnamax);
            }while(mundo[filaAl][colAl]!=libre);
            mundo[filaAl][colAl] = perlas;;
        }

        //Manzana
        for (int i = 0; i < 5; i++) {
            do{
                filaAl = aleat.nextInt(filamax);
                colAl = aleat.nextInt(columnamax);
            }while(mundo[filaAl][colAl]!=libre);
            mundo[filaAl][colAl] = manzana;
        }
        for (int i = 0; i < filamax; i++) {
            for (int j = 0; j < columnamax; j++) {
                // Colores del tablero
                if (mundo[i][j] == lava) {
                    System.out.print("\u001B[38;5;208m" + mundo[i][j] + "\u001B[0m ");
                } else if (mundo[i][j] == perlas) {
                    System.out.print("\u001B[32m" + mundo[i][j] + "\u001B[0m ");
                } else if (mundo[i][j] == manzana) {
                    System.out.print("\u001B[31m" + mundo[i][j] + "\u001B[0m ");
                } else if (mundo[i][j] == Steve) {
                    System.out.print("\u001B[36m" + mundo[i][j] + "\u001B[0m ");
                } else {
                    System.out.print(mundo[i][j] + " ");
                }
            }
            System.out.println();
        }

        // EMPIEZA EL JUEGO !!!
        Scanner lector = new Scanner(System.in);
        do {
            System.out.print("Dime el movimiento: ");
            char mov = lector.next().charAt(0);
            int filaAnteriorSteve = filaOficialSteve;
            int columnaAnteriorSteve = columnaOficialSteve;
            switch (Character.toUpperCase(mov)){ // W, A, S o D
                case 'W':
                    filaOficialSteve --;
                    break;
                case 'A':
                    columnaOficialSteve --;
                    break;
                case 'S':
                    filaOficialSteve ++;
                    break;
                case 'D':
                    columnaOficialSteve ++;
                    break;
                case 'I':
                    System.out.println("Tienes " + vidas + " vidas");
                    System.out.println("Tienes " + perlascont + " perlas");
                    int perlasrestantes = 3 - perlascont;
                    System.out.println("Te quedan " + perlasrestantes + " para poder finalizar el juego");
                    System.out.println("Hay " );
                    break;
                case 'M':
                    System.out.println("Tienes que recojer las " + numperlas + " para llegar al final y terminar el nivel");
                    System.out.println("Hay " + numlavas + " lavas que quitan 1 vida");
                    System.out.println("Hay " + nummanzanas + " manzanas que te añaden 1 vida");
                    System.out.println("Hay " + nummuros + " muros que no te dejan pasar");
                    break;
            }
            // Para que no se salga del trablero
            if(
                    filaOficialSteve < 0 ||
                            filaOficialSteve > 9 ||
                            columnaOficialSteve < 0 ||
                            columnaOficialSteve > 9
            ){
                System.out.println("Limite del mundo");
                filaOficialSteve = filaAnteriorSteve;
                columnaOficialSteve = columnaAnteriorSteve;
            }else{
                if(mundo[filaOficialSteve][columnaOficialSteve] == muros){
                    System.out.println("Te has chocado con una caja");
                    filaOficialSteve = filaAnteriorSteve;
                    columnaOficialSteve = columnaAnteriorSteve;
                }else if(mundo[filaOficialSteve][columnaOficialSteve] == manzana){
                    mundo[filaOficialSteve][columnaOficialSteve] = Steve;
                    mundo[filaAnteriorSteve][columnaAnteriorSteve] = libre;
                    vidas++;
                    nummanzanas--;
                }else if(mundo[filaOficialSteve][columnaOficialSteve] == Lava){
                    mundo[filaOficialSteve][columnaOficialSteve] = Steve;
                    mundo[filaAnteriorSteve][columnaAnteriorSteve] = libre;
                    vidas--;
                    numlavas = numlavas - 1;
                }else if(mundo[filaOficialSteve][columnaOficialSteve] == libre){
                    mundo[filaOficialSteve][columnaOficialSteve] = Steve;
                    mundo[filaAnteriorSteve][columnaAnteriorSteve] = libre;
                }else if(mundo[filaOficialSteve][columnaOficialSteve] == fin) {
                    if (perlascont==3){
                        mundo[filaOficialSteve][columnaOficialSteve] = Steve;
                        mundo[filaAnteriorSteve][columnaAnteriorSteve] = libre;
                        System.out.println("Fin del juego");
                        fin1 = 1;
                    }else {
                        System.out.println("Necestas tener las perlas de la nave");
                        filaOficialSteve = filaAnteriorSteve;
                        columnaOficialSteve = columnaAnteriorSteve;
                        numperlas = numperlas - 1;
                    }
                } else if (mundo[filaOficialSteve][columnaOficialSteve] == perlas){
                    mundo[filaOficialSteve][columnaOficialSteve] = Steve;
                    mundo[filaAnteriorSteve][columnaAnteriorSteve] = libre;
                    perlascont++;
                }

            }
            for (int i = 0; i < filamax; i++) {
                for (int j = 0; j < columnamax; j++) {
                    // Colores del tablero
                    if (mundo[i][j] == lava) {
                        System.out.print("\u001B[38;5;208m" + mundo[i][j] + "\u001B[0m ");
                    } else if (mundo[i][j] == perlas) {
                        System.out.print("\u001B[32m" + mundo[i][j] + "\u001B[0m ");
                    } else if (mundo[i][j] == manzana) {
                        System.out.print("\u001B[31m" + mundo[i][j] + "\u001B[0m ");
                    } else if (mundo[i][j] == Steve) {
                        System.out.print("\u001B[36m" + mundo[i][j] + "\u001B[0m ");
                    } else {
                        System.out.print(mundo[i][j] + " ");}
                }
                System.out.println();
            }
            System.out.println("Tienes " + vidas + " vidas");
            System.out.println("Tienes " + perlascont + " perlas");

        }while(vidas>0 && fin1 == 0);
        nivel2();
    }
    static void nivel2(){
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
        tablero [9][9] = fin;


        // 1 Tu
        Random aleat = new Random();
        int filaAl = -1;
        int colAl = -1;
        int filaOficialTu = -1;
        int columnaOficialTu = -1;

        for (int i = 0; i < 1; i++) {
            do{
                filaAl = aleat.nextInt(Max_fila_Tu);
                colAl = aleat.nextInt(Max_columna_Tu);
            }while(tablero[filaAl][colAl]!=LIBRE);
            tablero[filaAl][colAl] = Tu;
        }

        // OFICIALIZO LAS POSICIONES DE MI Tu
        filaOficialTu = filaAl;
        columnaOficialTu = colAl;
        // FIN

        // Cajas
        char muros = 'M';
        for (int i = 0; i < 10; i++) {
            do{
                filaAl = aleat.nextInt(Max_fila_Tu);
                colAl = aleat.nextInt(Max_columna_Tu);
            }while(tablero[filaAl][colAl]!=LIBRE);
            tablero[filaAl][colAl] = muros;
        }

        // Mujeres
        char mujeres = 'M';
        for (int i = 0; i < 7; i++) {
            do{
                filaAl = aleat.nextInt(Max_fila_Tu);
                colAl = aleat.nextInt(Max_columna_Tu);
            }while(tablero[filaAl][colAl]!=LIBRE);
            tablero[filaAl][colAl] = mujeres;
        }

        // Piezas
        char RAM = 'R';
        int RamCont = 0;
        for (int i = 0; i < 3; i++) {
            do{
                filaAl = aleat.nextInt(Max_fila_Tu);
                colAl = aleat.nextInt(Max_columna_Tu);
            }while(tablero[filaAl][colAl]!=LIBRE);
            tablero[filaAl][colAl] = RAM;;
        }

        char Cerveza = 'C';
        for (int i = 0; i < 5; i++) {
            do{
                filaAl = aleat.nextInt(Max_fila_Tu);
                colAl = aleat.nextInt(Max_columna_Tu);
            }while(tablero[filaAl][colAl]!=LIBRE);
            tablero[filaAl][colAl] = Cerveza;
        }
        for (int i = 0; i < Max_fila_Tu; i++) {
            for (int j = 0; j < Max_columna_Tu; j++) {
                System.out.print(tablero[i][j] + " ");
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
            switch (Character.toUpperCase(mov)){ // W, A, S o D
                case 'W':
                    filaOficialTu --;
                    break;
                case 'A':
                    columnaOficialTu --;
                    break;
                case 'S':
                    filaOficialTu ++;
                    break;
                case 'D':
                    columnaOficialTu ++;
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
            if(
                    filaOficialTu < 0 ||
                            filaOficialTu > 9 ||
                            columnaOficialTu < 0 ||
                            columnaOficialTu > 9
            ){
                System.out.println("Limite del tablero");
                filaOficialTu = filaAnteriorTu;
                columnaOficialTu = columnaAnteriorTu;
            }else{
                if(tablero[filaOficialTu][columnaOficialTu] == muros){
                    System.out.println("Te has chocado con una caja");
                    filaOficialTu = filaAnteriorTu;
                    columnaOficialTu = columnaAnteriorTu;
                }else if(tablero[filaOficialTu][columnaOficialTu] == Cerveza){
                    tablero[filaOficialTu][columnaOficialTu] = Tu;
                    tablero[filaAnteriorTu][columnaAnteriorTu] = LIBRE;
                    vidas++;
                }else if(tablero[filaOficialTu][columnaOficialTu] == mujeres){
                    tablero[filaOficialTu][columnaOficialTu] = Tu;
                    tablero[filaAnteriorTu][columnaAnteriorTu] = LIBRE;
                    vidas--;
                }else if(tablero[filaOficialTu][columnaOficialTu] == LIBRE){
                    tablero[filaOficialTu][columnaOficialTu] = Tu;
                    tablero[filaAnteriorTu][columnaAnteriorTu] = LIBRE;
                }else if(tablero[filaOficialTu][columnaOficialTu] == fin) {
                    if (RamCont==3){
                        tablero[filaOficialTu][columnaOficialTu] = Tu;
                        tablero[filaAnteriorTu][columnaAnteriorTu] = LIBRE;
                        System.out.println("Fin del juego");
                        fin1 = 1;
                    }else {
                        System.out.println("Necestas tener las RAM de la nave");
                        filaOficialTu = filaAnteriorTu;
                        columnaOficialTu = columnaAnteriorTu;
                    }
                } else if (tablero[filaOficialTu][columnaOficialTu] == RAM){
                    tablero[filaOficialTu][columnaOficialTu] = Tu;
                    tablero[filaAnteriorTu][columnaAnteriorTu] = LIBRE;
                    RamCont++;
                }

            }
            for (int i = 0; i < Max_fila_Tu; i++) {
                for (int j = 0; j < Max_columna_Tu; j++) {
                    System.out.print(tablero[i][j] + " ");
                }
                System.out.println();
            }
            System.out.println("Tienes " + vidas + " vidas");
            System.out.println("Tienes " + RamCont + " RAM");

        }while(vidas>0 && fin1 == 0) ;
    }
}