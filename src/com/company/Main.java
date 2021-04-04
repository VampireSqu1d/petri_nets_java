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
        final int[][] pos = {
                // esta es una matriz 5x5 se pueden  cambiar de acuerdo a la red de petri
                {1, 0, 1, 0, 0},
                {0, 1, 0, 0, 0},
                {1, 0, 0, 0, 0},
                {1, 0, 0, 0, 1},
                {0, 0, 0, 1, 0} };

        final int[][] pre = {
                // esta es una matriz 5x5 se pueden  cambiar de acuerdo a la red de petri
                {0, 1, 0, 0, 0},
                {1, 0, 1, 0, 0},
                {0, 0, 0, 1, 0},
                {0, 0, 0, 1, 0},
                {1, 0, 0, 0, 1} };

        // se inicializa la matriz de incidencia con el mismo largo de la matriz pos
        int[][] incidence = new int[pos.length][pos[0].length];
        // en este loop se hacen las resta de la matriz pre sobre la matriz pos C(t) = pos - pre, y se agregan a la matriz incidence
        for (int i = 0; i < pos.length; i++) {
            for (int j = 0; j < pos[i].length; j++) {
                incidence[i][j] = pos[i][j] - pre[i][j];
            }
        }
        // aqui se imprimen las matrices 2x2
        imprimirMatriz(pos, "matriz pos");
        imprimirMatriz(pre, "matriz pre");
        imprimirMatriz(incidence, "matriz de incidencia");

        int[] M0 = {1, 0, 1, 0, 1}; // cada celda representa el número de tokens en cada place
        imprimirVector(M0, "vector M0");

        int[] tpd = new int[incidence.length]; // este vector representa las Transiciones que se Pueden Disparar
        final int ciclos = 1;
        int ciclo = 0;
        while (ciclo < ciclos) {
            // en este loop se revisan las transiciones disponibles para dispararse
            for (int i = 0; i < incidence.length; i++) {
                int pre_j = 0;
                for (int j = 0; j < incidence[0].length; j++) {
                    if (incidence[i][j] > 0) {
                        if (M0[j] >= incidence[i][j]) pre_j = 1;
                        else {
                            pre_j = 0;
                            break;
                        }
                    }
                }
                if (pre_j > 0) tpd[i] = 1;
                else tpd[i] = 0;
            }
            imprimirVector(tpd, "Transiciones que se pueden disparar");













            /* int[] vectorCxE = new int[pos[0].length]; // este vector es la multiplicacion del vectorE por la matriz de incidencia
            for (int i = 0; i < pos[0].length; i++) {
                // en este loop se hace la multiplicacion y suma del vector CxE
                // cada celda del vectorE se multiplica con la celda correspondiente de la columna de la matriz de incidencia
                int CxE = 0;
                for (int j = 0; j < incidence[i].length; j++) {
                    CxE = CxE + (incidence[j][i] * tpd[j]);
                }
                vectorCxE[i] = CxE;
            }
            imprimirVector(vectorCxE, "Vector CxE");*/

            // en este loop se calcula M1 = M0 + Cxe1 y se guarda en el vector M0
            /* for (int i = 0; i < M0.length; i++) {
                M0[i] = M0[i] + vectorCxE[i];
                if (M0[i] < 0) {
                    M0[i] = 0;
                }
            }*/
            //imprimirVector(M0, "vector M1");
            ciclo++;
        }
    }

    // método que imprime cada celda de un arreglo de un a dimensión
    private static void imprimirVector(int[] vector, String nombreVector){
        System.out.printf("%s: ",nombreVector);
        for (int j : vector) {
            System.out.printf("%d ", j);
        }
        System.out.print("\n");
    }
    // método que imprime cada celda de una matriz de dos dimensiones
    private static void imprimirMatriz(int[][] matriz, String nombreMatriz) {
        System.out.println(nombreMatriz);
        for (int[] ints : matriz) {
            for (int anInt : ints) {
                System.out.printf("%d ", anInt);
            }
            System.out.print("\n");
        }
        System.out.print("\n");
    }
}
