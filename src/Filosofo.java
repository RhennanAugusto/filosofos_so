import java.util.HashSet;
import java.util.Set;

public class Filosofo extends Thread {
    private final int id;
    private final Garfo garfoEsquerdo;
    private final Garfo garfoDireito;
    private static final Set<Integer> garfosOcupados = new HashSet<>();

    public Filosofo(int id, Garfo garfoEsquerdo, Garfo garfoDireito) {
        this.id = id;
        this.garfoEsquerdo = garfoEsquerdo;
        this.garfoDireito = garfoDireito;
    }

    @Override
    public void run() {
        while (true) {
            pensar();
            if (pegarGarfos()) {
                comer();
                soltarGarfos();
            }
        }
    }

    private void pensar() {
        System.out.println("Filósofo " + id + " está pensando...");
        try {
            Thread.sleep((long) (Math.random() * 2000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private boolean pegarGarfos() {
        if (garfoEsquerdo.pegar()) {
            synchronized (garfosOcupados) {
                if (garfosOcupados.contains(garfoEsquerdo.hashCode())) {
                    System.out.println("⚠ ERRO: O Garfo " + garfoEsquerdo.hashCode() + " já está em uso!");
                }
                garfosOcupados.add(garfoEsquerdo.hashCode());
            }

            if (garfoDireito.pegar()) {
                synchronized (garfosOcupados) {
                    if (garfosOcupados.contains(garfoDireito.hashCode())) {
                        System.out.println("⚠ ERRO: O Garfo " + garfoDireito.hashCode() + " já está em uso!");
                    }
                    garfosOcupados.add(garfoDireito.hashCode());
                }
                return true;
            } else {
                garfoEsquerdo.soltar();
                synchronized (garfosOcupados) {
                    garfosOcupados.remove(garfoEsquerdo.hashCode());
                }
            }
        }
        return false;
    }

    private void soltarGarfos() {
        synchronized (garfosOcupados) {
            garfosOcupados.remove(garfoEsquerdo.hashCode());
            garfosOcupados.remove(garfoDireito.hashCode());
        }
        garfoEsquerdo.soltar();
        garfoDireito.soltar();
    }

    private void comer() {
        System.out.println("🍝 Filósofo " + id + " está comendo...");
        try {
            Thread.sleep((long) (Math.random() * 3000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
