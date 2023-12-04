package test;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Test1 {
    private final ReentrantLock lock = new ReentrantLock();
    private final Condition condicion = lock.newCondition();
    private int contador = 0;

    public void accederZonaCritica() {
        lock.lock();
        try {
            while (contador >= 3) {
                // Esperar si ya hay tres hilos en la zona crítica
                condicion.await();
            }

            // Entrar en la zona crítica
            contador++;
            System.out.println("Hilo " + Thread.currentThread().getId() + " está en la zona crítica");
            Thread.sleep(2000); // Simular trabajo en la zona crítica

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            // Salir de la zona crítica
            contador--;
            System.out.println("Hilo " + Thread.currentThread().getId() + " salió de la zona crítica");
            condicion.signalAll(); // Notificar a otros hilos que puedan entrar
            lock.unlock();
        }
    }
    public static void main(String[] args) {
    	Test1 controlador = new Test1();

        // Crear varios hilos para probar el acceso a la zona crítica
        for (int i = 0; i < 10; i++) {
            Thread hilo = new Thread(() -> controlador.accederZonaCritica());
            hilo.start();
        }
    }
}