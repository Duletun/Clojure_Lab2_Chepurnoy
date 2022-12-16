package ru.stankin.uits;

import java.util.concurrent.atomic.AtomicIntegerArray;
import java.util.concurrent.atomic.AtomicInteger;

public class MatrixMulti implements Runnable {
    private int row;
    private int col;
    private AtomicInteger[] matrixA;
    private AtomicInteger[][] matrixB;
    private AtomicInteger[][] result;

    public MatrixMulti(final int row, final int col,
                              final AtomicInteger[] matrixA, final AtomicInteger[][] matrixB,
                              final AtomicInteger[][] result) {
        this.row = row;
        this.col = col;
        this.matrixA = matrixA;
        this.matrixB = matrixB;
        this.result = result;
    }

    public void run() {
        int value = 0;
        for (int i = 0; i < matrixA.length; i++) {
            value = value + (matrixA[i].get() * matrixB[i][col].get());
        }
        result[row][col].set(value);
        System.out.println("В потоке: " + Thread.currentThread().getName() + " на элементе row: " + row + " col: " + col + " поставлено значение " + value);
    }
}