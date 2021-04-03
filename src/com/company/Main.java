package com.company;

public class Main {

    public static void main(String[] args) {
        // las columnas son los places y los renglones son transiciones en todas las matrices
        /*
        *       P1 P2 P3 P4
        *  T1  0
        *  T2  1
        *  T3  0
        *  T4  1
        */
        int[][] pos = {
                {1, 0, 1, 0, 0},
                {0, 1, 0, 0, 0},
                {1, 0, 0, 0, 0},
                {1, 0, 0, 0, 1},
                {0, 0, 0, 1, 0} };

        int[][] pre = {
                {0, 1, 0, 0, 0},
                {1, 0, 1, 0, 0},
                {0, 0, 0, 1, 0},
                {0, 0, 0, 1, 0},
                {1, 0, 0, 0, 1} };

        // se inicializa la matriz de incidencia con el mismo largo de la matriz pos
        int[][] incidence = new int[pos.length][pos.length];
        // en este loop se hacen las resta de la matriz pre sobre la matriz pos C(t) = pos - pre y se agregan a la matriz incidence
        for (int i = 0; i < pos.length; i++) {
            for (int j = 0; j < pos.length; j++) {
                incidence[i][j] = pos[i][j] - pre[i][j];
            }
        }

        imprimirMatriz(pos, "matriz pos");
        imprimirMatriz(pre, "matriz pre");
        imprimirMatriz(incidence, "matriz de incidencia");

        // cada celda representa la transicion que se puede disparar en el modelo
        int[] vectorE = {0, 0, 1, 0, 0};
        // este vector es la multiplicacion del vectorE por la matriz de incidencia
        int[] vectorCxE = new int[incidence.length];
        // en este loop se hace la multiplicacion y suma del vector CxE  cada celda del vectorE se multiplica con el vector la columna de la matriz de incidencia
        for (int i = 0; i < incidence.length; i++) {
            int CxE = 0;
            for (int j = 0;  j < incidence[i].length ; j++) {
                CxE = CxE +  (incidence[j][i]  * vectorE[j]) ;
            }
            vectorCxE[i] = CxE;
        }
        // aquí se imprimen los arreglos de una dimensión
        imprimirVector(vectorE, "Vector E");
        imprimirVector(vectorCxE, "Vector CxE");

        // cada celda representa el número de tokens en cada place
        // se le conoce como marca inicial y se denomina M0
        int[] M0 = {1, 0, 1, 0, 1};
        int[] M1 = new int[M0.length];
        for (int i = 0; i < M0.length; i++) {
            M1[i] = M0[i] + vectorCxE[i];
        }
        imprimirVector(M0, "vector M0");
        imprimirVector(M1, "vector M1");
        //int ciclos = 10;
    }

    // método que imprime cada celda de un arreglo de un a dimensión
    private static void imprimirVector(int[] vector, String nombreVector){
        System.out.println(nombreVector);
        for (int j : vector) {
            System.out.printf("%d ", j);
        }
        System.out.print("\n");
    }
    // método que imprime cada celda de la matriz
    private static void imprimirMatriz(int[][] matriz, String nombreMatriz) {
        System.out.println(nombreMatriz);
        for (int[] ints : matriz) {
            for (int j = 0; j < matriz.length; j++) {
                System.out.printf("%d ", ints[j]);
            }
            System.out.print("\n");
        }
        System.out.print("\n");
    }

}
