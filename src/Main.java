//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        int numFilosofos = 5;
        Garfo[] garfos = new Garfo[numFilosofos];
        for (int i = 0; i < numFilosofos; i++) {
            garfos[i] = new Garfo(i + 1);  //  cada garfo recebe um ID único (1 a numFilosofos)
        }

        Filosofo[] filosofos = new Filosofo[numFilosofos];

        for (int i = 0; i < numFilosofos; i++) {
            garfos[i] = new Garfo(i + 1);  //
        }


        for (int i = 0; i < numFilosofos; i++) {
            filosofos[i] = new Filosofo(i, garfos[i], garfos[(i + 1) % numFilosofos]);
            filosofos[i].start();
        }
    }
}