package concorrente;

public class Saque extends Thread {

    private int tempo;
    private double valor;
    private Conta conta;
    private double totalSacado;
    private int contSaques;

    public Saque(String name, int tempo, double valor, Conta conta) {
        super(name);
        this.tempo = tempo;
        this.valor = valor;
        this.conta = conta;
        this.totalSacado = 0;
        this.contSaques = 0;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (conta) {
                if (conta.sacar(valor)) {
                    totalSacado += valor;
                    contSaques++;
                    try {
                        conta.wait(tempo);
                    } catch (InterruptedException ex) {
                        DataLogger.log("=> " + Thread.currentThread().getName() + " foi interrompida!");
                    }
                } else {
                    try {
                        DataLogger.log("=> " + Thread.currentThread().getName() + " não consegue mais sacar. Sacou em " + contSaques + " vezes um total de R$: " + totalSacado);
                        DataLogger.log("=> Colocando " + Thread.currentThread().getName() + " para dormir...");
                        conta.wait();
                    } catch (InterruptedException ex) {
                        DataLogger.log("=> " + Thread.currentThread().getName() + " foi interrompida!");
                    }
                }
            }
        }
    }
}
