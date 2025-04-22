package de.tum.in.ase.eist;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        detectDeadlock(new SwimmingPool());

    }

    public static void detectDeadlock(SwimmingPool swimmingPool) {
        // TODO 2
        SwimmingPoolActionOrder SPAO1 = SwimmingPoolActionOrder.CHANGING_ROOM_BEFORE_LOCKER;
        SwimmingPoolActionOrder SPAO2 = SwimmingPoolActionOrder.LOCKER_BEFORE_CHANGING_ROOM;
/*
        Locker locker = new Locker();
        ChangingRoom changingRoom = new ChangingRoom();

        Thread t1 =new Thread(() -> {
            changingRoom.acquireKey(new Swimmer());
            System.out.println("t1 acquireKey");
            locker.storeClothes(new Swimmer());
            System.out.println("t1 storeClothes");

            locker.retrieveClothes();
            System.out.println("t1 retrieveClothes");
            changingRoom.releaseKey();
            System.out.println("t1 releaseKey");
        });

        Thread t2 = new Thread(() -> {
            locker.storeClothes(new Swimmer());
            System.out.println("t2 storeClothes");
            changingRoom.acquireKey(new Swimmer());
            System.out.println("t2 acquireKey");

            changingRoom.releaseKey();
            System.out.println("t2 releaseKey");
            locker.retrieveClothes();
            System.out.println("t2 retrieveClothes");
        });


 */
        // create deadlock
        Thread thread1 = new Thread(() -> {
            print("thread1 start");
            Swimmer swimmer1 = new Swimmer();
            swimmer1.goToSwimmingPool(swimmingPool, SPAO1);
            print("thread1 end");
        });
        Thread thread2 = new Thread(() -> {
            print("thread2 start");
            Swimmer swimmer2 = new Swimmer();
            swimmer2.goToSwimmingPool(swimmingPool, SPAO1);
            print("thread2 end");
        });
        Thread thread3 = new Thread(() -> {
            print("thread3 start");
            Swimmer swimmer3 = new Swimmer();
            swimmer3.goToSwimmingPool(swimmingPool, SPAO2);
            print("thread3 end");
        });
        Thread thread4 = new Thread(() -> {
            print("thread4 start");
            Swimmer swimmer4 = new Swimmer();
            swimmer4.goToSwimmingPool(swimmingPool, SPAO2);
            print("thread4 end");
        });

        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        try {
            thread1.join();
            thread2.join();
            thread3.join();
            thread4.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public static void print(String s) {
        System.out.println(s);
    }
}
