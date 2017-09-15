package concorrente;

/**
 * Consumidor dos recursos da conta do sistema. Cada consumidor é independente,
 * isto é, representa uma Thread separada. O consumidor retira de tempos em
 * tempos algum valor da conta até que essa se esgote; nesse caso, o consumidor
 * aguarda até que seja notificado que a conta contém recursos novamente.
 */
public class Consumidor extends Thread {

    private int tempo;
    private double valor;
    private Conta conta;
    private double totalSacado;
    private int contSaques;

    /**
     * Construtor da classe.
     * 
     * @param name nome do consumidor
     * @param tempo intervalo de tempo esperado entre cada saque efetuado
     * @param valor valor a ser retirado em cada saque
     * @param conta conta cujos recursos serão consumidos
     */
    public Consumidor(String name, int tempo, double valor, Conta conta) {
        super(name);
        this.tempo = tempo;
        this.valor = valor;
        this.conta = conta;
        this.totalSacado = 0;
        this.contSaques = 0;
    }

    /**
     * Rotina de execução do consumidor. Faz saques periódicos na conta até
     * seu esgotamento e espera até que a conta ganhe recursos para então
     * repetir o processo.
     */
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
