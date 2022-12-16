package ru.stankin.uits;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

//
//
public class Matrix {

    public static int[][] generate(int rows, int columns) {

        int[][] result = new int[rows][columns];

        Random random = new Random();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {

                result[i][j] = random.nextInt(10) * 10;

            }
        }

        return result;
    }

    public static void print(int[][] matrix) {

        System.out.println();

        int rows = matrix.length;
        int columns = matrix[0].length;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                System.out.print(matrix[i][j] + "  ");
            }
            System.out.println();
        }

    }

    public static int[][] getRows(int[][] matrix, int from, int to) {
        // Алгос получения строк
        int len = to-from;
        int[][] a = new int[len][];

        for (int i = 0; i < len; i++) {
            a[i] = Arrays.copyOfRange(matrix[from+i], 0, matrix[i].length);
        }
        return a;
    }

    public static int[][] getCol(int[][] matrix, int from, int to) {
        // Алгос получения колонок

        int[][] a = new int[matrix.length][];

        for (int i = 0; i < matrix.length; i++) {
            a[i] = Arrays.copyOfRange(matrix[i], from, to);
        }
        return a;
    }

    public static Runnable makeTask(int[][] matrix){
        return null;
    }

    public static AtomicInteger[][] makeAtomic(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;

        AtomicInteger[][] a_matrix = new AtomicInteger[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                a_matrix[i][j] = new AtomicInteger(matrix[i][j]);
            }
        }

        return a_matrix;
    }

    public static AtomicInteger[][] fillAtomic(int rows,int cols) {

        AtomicInteger[][] fill_matrix = new AtomicInteger[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                fill_matrix[i][j] = new AtomicInteger(0);
            }
        }

        return fill_matrix;
    }






}
