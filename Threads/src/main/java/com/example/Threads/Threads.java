package com.example.Threads;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class Threads {
    // Количество потоков
    private static final int NUM_THREADS = 5;
    // Длина расчёта (количество шагов прогресс-бара)
    private static final int PROGRESS_BAR_LENGTH = 20;

    // Объект для синхронизации вывода в консоль
    private static final Object consoleLock = new Object();

    public static void main(String[] args) {
        String[] progressBars = new String[NUM_THREADS]; // Массив для хранения строк прогресс-баров

        Random rand = new Random();

        for (int i = 0; i < NUM_THREADS; i++) {
            int delay = rand.nextInt(101) + 300;
            final int threadNumber = i + 1;

            Runnable task = () ->{
                long startTime = System.currentTimeMillis();
                // Идентификатор потока
                long threadId = Thread.currentThread().threadId();
                // Счетчик для прогресс-бара
                AtomicInteger progress = new AtomicInteger(0);

                // Выполняем "расчёт"
                while (progress.get() < PROGRESS_BAR_LENGTH) {
                    try {
                        Thread.sleep(delay);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    progress.incrementAndGet(); //Увеличивает на единицу текущее значение

                    // Обновляем прогресс-бар
                    synchronized (consoleLock) {
                        progressBars[threadNumber - 1] = buildProgressBar(threadNumber, threadId, progress.get(), startTime);
                        printAllProgressBars(progressBars);
                    }
                }
            };
            Thread thread = new Thread(task);
            thread.start();
        }
    }

    // Метод для построения строки прогресс-бара
    private static String buildProgressBar(int threadNumber, long threadId, int progress, long startTime) {
        StringBuilder progressBar = new StringBuilder();
        progressBar.append("Thread ").append(threadNumber).append(" (ID: ").append(threadId).append(") [");

        for (int i = 0; i < PROGRESS_BAR_LENGTH; i++) {
            if (i < progress) {
                progressBar.append("#");
            } else {
                progressBar.append("-");
            }
        }

        progressBar.append("] ");
        long endTime = System.currentTimeMillis();
        progressBar.append(progress * 100 / PROGRESS_BAR_LENGTH).append("%");

        if (progress == PROGRESS_BAR_LENGTH)
            progressBar.append(" Time: ").append(endTime - startTime).append(" ms.");

        return progressBar.toString();
    }

    // Метод для вывода всех прогресс-баров
    private static void printAllProgressBars(String[] progressBars) {
        // Перемещаем курсор в начало консоли
        System.out.print("\033[H");
        System.out.print("\n");
        System.out.flush();

        // Выводим все прогресс-бары
        for (String bar : progressBars) {
            System.out.println(bar);
        }
    }
}