package ru.stankin.uits;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerArray;

public class Main {

    private static AtomicInteger[][] a_matrixA;
    private static AtomicInteger[][] a_matrixB;
    private static AtomicInteger[][] a_result;


    public static void enter_execute_Tasks(int i, int j, ExecutorService service, ArrayList<String> tasks){
        if (j < a_matrixB[i].length){
            tasks.add("\nЗадача № " + tasks.size() + " result[" + i +"][" + j + "] из операции: строка A[" + i + "] * стоблец B[" + j + "]" );
            MatrixMulti multiply = new MatrixMulti(i, j, a_matrixA[i], a_matrixB, a_result);
            service.execute(multiply);
            enter_execute_Tasks(i, j+1,service,tasks);
        } else if(i+1 < a_matrixA.length) {
            enter_execute_Tasks(i + 1, 0, service, tasks);
        }
    }
    public static void main(String[] args) throws InterruptedException, ExecutionException {

        System.out.print("\n[C использованием CachedThreadPool]");

        // Генерируем матрицы
        int[][] matrixA = Matrix.generate(4, 5); // Матрица А
        int[][] matrixB = Matrix.generate(5, 4); // Матрица B

        System.out.print("\nМатрица A:");
        Matrix.print(matrixA);
        System.out.print("\nМатрица B:");
        Matrix.print(matrixB);

        /////////////////////////////////////////////////////////////
        // CachedThreadPool - Распределяющий задачи по потокам пул //
        /////////////////////////////////////////////////////////////

        System.out.print("\nВывод потоков:\n");

        // Делаем atomic массивы
        a_matrixA = Matrix.makeAtomic(matrixA);
        a_matrixB = Matrix.makeAtomic(matrixB);
        a_result = Matrix.fillAtomic(a_matrixA.length,a_matrixA[0].length);

        // Пул потоков, который распределяет введенные задачи по потокам
        ExecutorService service = Executors.newCachedThreadPool();

        // Запись названия задач, которые будут распределены по потокам
        ArrayList<String> tasks = new ArrayList<String>();

        // Запускаем рекурсивную функцию распределения задач в пул потоков
        enter_execute_Tasks(0,0,service,tasks);

        service.shutdown();

        // Вывод результатов
        try {
            service.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            System.out.println("Ошибка в потоках");
        }finally{
            System.out.print("\nМассив выполненных задач:");
            for (int t = 0; t<tasks.size();t++){
                System.out.print(tasks.get(t));
            }

            System.out.print("\n\nРезультат:\n");
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    System.out.print(a_result[i][j] + "  ");
                }
                System.out.println();
            }
        }

    }
}