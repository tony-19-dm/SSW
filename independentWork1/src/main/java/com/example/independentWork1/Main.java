package com.example.independentWork1;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;
import java.util.Random;

public class Main {
    private static int countFiles = 5;
    private static int countVriters = 10;

    public static void main(String[] args) throws InterruptedException {
        //массив файлов
        Fle[] fles = new Fle[countFiles];

        for (int i = 0; i < countFiles; i++) {
            fles[i] = new Fle(i + 1);
        }

        Semaphore semaphore = new Semaphore(countFiles);
        //массив врайтеров
        Thread [] threads = new Thread[countVriters];

        for (int i = 0; i < countVriters; i++){
            threads[i] = new Thread(new Vriter(i + 1, fles, semaphore));
            threads[i].start();
        }
    }

    public static class Fle {
        private final int id;
        private final ReentrantLock lock = new ReentrantLock();

        public Fle(int id) {
            this.id = id;
        }

        public boolean tryVrite(int tId){
            if(lock.tryLock()){
                try{
                    Random rand = new Random();
                    //случайная задержка от 1 до 3
                    int delay = rand.nextInt(3) + 1;
                    int rNum = rand.nextInt(10);
                    System.out.println("Thread Vriter " + tId + " write " + rNum + " to file №" + id);
                    Thread.sleep(delay * 1000);
                    System.out.println("Thread Vriter " + tId + " leave the file №" + id);
                }
                catch(InterruptedException e){
                    Thread.currentThread().interrupt();
                }
                finally{
                    lock.unlock();// снимаем блокировку
                    return true;
                }
            }
            return false;
        }
    }

    public static class Vriter implements Runnable{
        private final int id;
        private final Fle[] fles;
        private final Semaphore semaphore;

        public Vriter(int id, Fle[] fles, Semaphore semaphore) {
            this.id = id;
            this.fles = fles;
            this.semaphore = semaphore;
        }

        @Override
        public void run() {
            try {
                for (int i = 0; i < 5; i++) {
                    semaphore.acquire();
                    for (Fle fle : fles) {
                        if (fle.tryVrite(id)) break;
                    }
                    semaphore.release();
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
