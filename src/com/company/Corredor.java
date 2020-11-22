package com.company;

import java.util.concurrent.Semaphore;

class Corredor extends Thread {
    static final int MAX_DELAY = 1000;
    int id;
    Semaphore[] semáforos;

    Corredor (int id, Semaphore[] semáforos) {
        this.id = id;
        this.semáforos = semáforos;
    }

    @Override
    public void run() {
        try {
            semáforos[id].acquire();
            System.out.println("Soy el corredor " + id + " corriendo");
            Thread.sleep((int)Math.random()*MAX_DELAY);
            if (id != 3){
                int receptor = id + 1;
                System.out.println("Terminé. Paso el testigo al corredor " + receptor);
                semáforos[receptor].release();
            }
            else
                System.out.println("Terminé!");
        }
        catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }
}
