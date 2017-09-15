package concorrente;

/**
 * Produtor dos recursos da conta do sistema. O produtor é independente, isto é,
 * representa uma Thread separada. O produtor aguarda até que a conta esteja
 * esgotada para então injetar recursos e aguardar até que seja notificado
 * novamente.
 */
public class Produtor extends Thread {

    private Monitor monitor;

    /**
     * Construtor da classe.
     * 
     * @param name nome do produtor
     * @param monitor monitor dos recursos compartilhados (conta)
     */
    public Produtor(String name, Monitor monitor) {
        super(name);
        this.monitor = monitor;
    }

    /**
     * Rotina de execução do produtor. Espera até que a conta esteja esgotada
     * para injetar novos recursos e então aguarda até seu esgotamento novamente
     * e repetir o processo.
     */
    @Override
    public void run() {
        while (true) {
            monitor.depositar(100);
        }
    }
}
