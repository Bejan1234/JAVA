//1. [0.5p] Implementează o metodă care primește ca parametru un array de numere întregi și returnează un array,
// de aceeași dimensiune, care conține ultima cifră a fiecărui număr din primul array, în aceeași ordine.

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);


        System.out.println("Introduceti dimensiunea vectorului: ");
        int n = scanner.nextInt();
        int[] vector = new int[n];
        //memoria dinamica este automata dezalogata datorita Garbage-collector.

        for (int i = 0; i < n; i++) {
            vector[i] = scanner.nextInt();
        }

//        for (int i = 0; i < n; i++) {
//            System.out.println(vector[i]);
//        }
        int[] vectorRezultat = new int[n];
        for (int i = 0; i < n; i++) {
            int ultimaCifra = vector[i] % 10;
            vectorRezultat[i] = ultimaCifra;
        }
        for (int i = 0; i < n; i++) {
            System.out.println(vectorRezultat[i]);
        }
    }
}
