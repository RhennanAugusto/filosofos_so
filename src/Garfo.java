import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Garfo {
    private final int id;  // Identificador do garfo
    private final Lock lock = new ReentrantLock();

    public Garfo(int id) {
        this.id = id;
    }

    public boolean pegar() {
        boolean conseguiu = lock.tryLock();
        if (conseguiu) {
            System.out.println("Garfo " + id + " foi pego.");
        } else {
            System.out.println("Garfo " + id + " está em uso.");
        }
        return conseguiu;
    }

    public void soltar() {
        lock.unlock();
        System.out.println("Garfo " + id + " foi solto.");
    }

    public int getId() {
        return id;
    }
}

