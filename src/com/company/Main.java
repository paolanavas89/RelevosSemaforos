package com.company;

import java.util.concurrent.Semaphore;
/*
 Codificar una clase llamada Relevos que simule una carrera de relevos de la siguiente
forma:
a. Crea cuatro hilos que se quedarán a la espera de recibir alguna señal para
comenzar a correr. Una vez creados los threads, se indicará que comience la
carrera pero solo uno de los hilos comenzará a correr.
b. Cuando un hilo termine de correr, imprimirá un mensaje y esperará un
segundo antes de pasar el testigo a otro hilo para que comience a correr.
c. Cuando el último hilo termine de correr, el hilo padre mostrará un mensaje
indicando que todos los hilos terminaron.

*/

public class Main {

    public static void main(String[] args) {
        Semaphore semáforos[] = new Semaphore[4];

        for (int i = 0; i < semáforos.length; i++) {
            semáforos[i] = new Semaphore(0);
        }

        Corredor corredores[] = new Corredor[4];
        for (int i = 0; i < corredores.length; i++) {
            corredores[i] = new Corredor(i, semáforos);
            corredores[i].start();
        }

        System.out.println("Todos los hilos creados");
        System.out.println("Doy la salida");
        semáforos[0].release();

        try {
            for (int i = 0; i < 4; i++) {
                corredores[i].join();
            }
        }
        catch(InterruptedException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("Carrera finalizada");
    }

}
