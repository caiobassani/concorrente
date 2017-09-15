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
    private Monitor monitor;
    private double totalSacado;
    private int contSaques;

    /**
     * Construtor da classe.
     * 
     * @param name nome do consumidor
     * @param tempo intervalo de tempo esperado entre cada saque efetuado
     * @param valor valor a ser retirado em cada saque
     * @param monitor monitor dos recursos compartilhados (conta)
     */
    public Consumidor(String name, int tempo, double valor, Monitor monitor) {
        super(name);
        this.tempo = tempo;
        this.valor = valor;
        this.monitor = monitor;
        this.totalSacado = 0;
        this.contSaques = 0;
    }

    /**
     * Retorna o total sacado até o momento.
     * 
     * @return total sacado
     */
    public double getTotalSacado() {
        return totalSacado;
    }

    /**
     * Retorna a quantidade de saques efetuados pelo consumidor até o momento.
     * 
     * @return a quantidade de saques
     */
    public int getContSaques() {
        return contSaques;
    }
    
    /**
     * Rotina de execução do consumidor. Faz saques periódicos na conta até
     * seu esgotamento e espera até que a conta ganhe recursos para então
     * repetir o processo.
     */
    @Override
    public void run() {
        while (true) {
            monitor.sacar(valor);
            totalSacado += valor;
            contSaques++;
            try {
                Thread.sleep(tempo);
            } catch (InterruptedException ex) {
                DataLogger.log("=> " + Thread.currentThread().getName() + " foi interrompida!");
            }
        }
    }
}
