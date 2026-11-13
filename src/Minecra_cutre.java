// Juego de minecra 2d y que cuando llegue al final se mas dicifil de hacer
// valores 1ºnivel (10lavas, 5 manzanas, 3 perlas; 5 muros)

import java.util.Random;
import java.util.Scanner;

public class Minecra_cutre{
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
            System.out.print("Numero del 1 al 5 (siendo 1 dificil y 5 gallina: ");
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
        contnivel++;
        if (contnivel = 1){
            System.out.println("Enorabena has pasado al siguiente nivel");
        }

    }
}