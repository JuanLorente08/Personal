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
            System.out.print("Numero del 1 al 5 (siendo 1 dificil y 5 imposible: ");
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

        // Colocar
        char [][] mundo = new char [filamax][columnamax];
        for (int i = 0; i < filamax; i++) {
            for (int j = 0; j < columnamax; j++) {
                mundo[i][j] = libre;
            }
        }

        // Posicionar las siguientes variables
        //Fin
        mundo[9][9] = fin;
        //Hago el aleatorio
        Random alea = new Random();
        int filaAl = -1;
        int colAl = -1;
        int filaOficialSteve = -1;
        int columnaOficialSteve = -1;

        //Steve
        for (int i = 0; i < 1; i++) {
            do {
                filaAl = alea.nextInt(filamax);
                colAl = alea.nextInt(columnamax);
            } while (mundo[filaAl][colAl] != libre);
                mundo[filaAl][colAl] = Steve;
        }
        //Lava
        for (int i = 0; i < 10; i++) {
            do {
                filaAl = alea.nextInt(filamax);
                colAl = alea.nextInt(columnamax);
            } while (mundo[filaAl][colAl] != libre);
            mundo[filaAl][colAl] = lava;
        }
        //Manzanas
        for (int i = 0; i < 5; i++) {
            do {
                filaAl = alea.nextInt(filamax);
                colAl = alea.nextInt(columnamax);
            } while (mundo[filaAl][colAl] != libre);
            mundo[filaAl][colAl] = manzana;
        }
        //Perlas
        int perlascont = 0;
        for (int i = 0; i < 3; i++) {
            do{
                filaAl = alea.nextInt(filamax);
                colAl = alea.nextInt(columnamax);
            }while(mundo[filaAl][colAl]!=libre);
            mundo[filaAl][colAl] = perlas;;
        }
        //Muros
        for (int i = 0; i < 1; i++) {
            do {
                filaAl = alea.nextInt(filamax);
                colAl = alea.nextInt(columnamax);
            } while (mundo[filaAl][colAl] != libre);
            mundo[filaAl][colAl] = muros;
        }

        // Imprimir la matriz al principio para saber donde esta cada cosa
        for (int i = 0; i < filamax; i++) {
            for (int j = 0; j < columnamax; j++) {
                System.out.print(mundo[i][j] + " ");
            }
            System.out.println();
        }

        // Juego con movilidad y demas
        do {
            System.out.println("Te puedes mover con WASD");
            System.out.print("Dime una letra para moverte por el mundo: ");
            char mov = leer.next().charAt(0);
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
            }
            // Para que no se salga del trablero
            if(
                    filaOficialSteve < 0 || filaOficialSteve > 9 || columnaOficialSteve < 0 || columnaOficialSteve > 9
            ){
                System.out.println("Limite del mundo");
                filaOficialSteve = filaAnteriorSteve;
                columnaOficialSteve = columnaAnteriorSteve;
            }else{
                if(mundo[filaOficialSteve][columnaOficialSteve] == muros){
                    System.out.println("Te has chocado con un muro");
                    filaOficialSteve = filaAnteriorSteve;
                    columnaOficialSteve = columnaAnteriorSteve;
                }else if(mundo[filaOficialSteve][columnaOficialSteve] == manzana){
                    mundo[filaOficialSteve][columnaOficialSteve] = Steve;
                    mundo[filaAnteriorSteve][columnaAnteriorSteve] = libre;
                    vidas++;
                }else if(mundo[filaOficialSteve][columnaOficialSteve] == lava){
                    mundo[filaOficialSteve][columnaOficialSteve] = Steve;
                    mundo[filaAnteriorSteve][columnaAnteriorSteve] = libre;
                    vidas--;
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
                        System.out.println("Necestas tener las piezas de la nave");
                        filaOficialSteve = filaAnteriorSteve;
                        columnaOficialSteve = columnaAnteriorSteve;
                    }
                } else if (mundo[filaOficialSteve][columnaOficialSteve] == perlas){
                    mundo[filaOficialSteve][columnaOficialSteve] = Steve;
                    mundo[filaAnteriorSteve][columnaAnteriorSteve] = libre;
                    perlascont++;
                }

            }
            for (int i = 0; i < filamax; i++) {
                for (int j = 0; j < columnamax; j++) {
                    System.out.print(mundo[i][j] + " ");
                }
                System.out.println();
            }
            System.out.println("Tienes " + vidas + " vidas");
            System.out.println("Tienes " + perlascont + " perlas");

        }while(vidas>0 && fin1 == 0) ;
    }
}