package concorrente;

/**
 * Produtor dos recursos da conta do sistema. O produtor é independente, isto é,
 * representa uma Thread separada. O produtor aguarda até que a conta esteja
 * esgotada para então injetar recursos e aguardar até que seja notificado
 * novamente.
 */
public class Produtor extends Thread {

    private Conta conta;

    /**
     * Construtor da classe.
     * 
     * @param name nome do produtor
     * @param conta conta que receberá os recursos produzidos
     */
    public Produtor(String name, Conta conta) {
        super(name);
        this.conta = conta;
    }

    /**
     * Rotina de execução do produtor. Espera até que a conta esteja esgotada
     * para injetar novos recursos e então aguarda até seu esgotamento novamente
     * e repetir o processo.
     */
    @Override
    public void run() {
        while (true) {
            //Deposita R$100,00 ao saldo da conta quando não for mais possivel sacar nada
            if (conta.getSaldo() <= 0) {
                synchronized (conta) {
                    DataLogger.log("----------------------------------------");
                    DataLogger.log("=> Acabou o dinheiro");
                    conta.depositar(100);
                    //Notifica os consumidores que há saldo
                    DataLogger.log("=> Notificando as Threads consumidoras para acordar...");
                    conta.notifyAll();
                }
            }
        }
    }
}
