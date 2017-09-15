package concorrente;

/**
 * Monitor dos recursos compartilhados do sistema.
 */
public class Monitor {
    
    private Conta conta;

    /**
     * Construtor da classe.
     * 
     * @param conta conta compartilhada do sistema
     */
    public Monitor(Conta conta) {
        this.conta = conta;
    }
    
    /**
     * Deposita um valor de forma sincronizada no recurso compartilhado. O
     * depósito só ocorre quando a conta está esgotada.
     * 
     * @param valor valor a ser depositado
     */
    public synchronized void depositar(double valor) {
        if (conta.getSaldo() > 0) {
            dormir();
        }
        DataLogger.log("----------------------------------------");
        DataLogger.log("=> Acabou o dinheiro");
        conta.depositar(valor);
        DataLogger.log("[DEPOSITO] " + Thread.currentThread().getName() + " depositou R$: " + valor + ". Saldo após o depósito R$: " + conta.getSaldo());
        DataLogger.setSaldoAtual(conta.getSaldo());
        DataLogger.log("=> Notificando as Threads consumidoras para acordar...");
        notify();
    }
    
    /**
     * Saca um valor da conta compartilhada caso seja possível.
     * 
     * @param valor valor a ser sacado
     */
    public synchronized void sacar(double valor) {
        if (conta.getSaldo() < valor) {
            Consumidor consumidor = (Consumidor) Thread.currentThread();
            DataLogger.log("=> " + consumidor.getName() + " não consegue mais sacar. Sacou em " + consumidor.getContSaques() + " vezes um total de R$: " + consumidor.getTotalSacado());
            DataLogger.log("=> Colocando " + consumidor.getName() + " para dormir...");
            dormir();
        }
        conta.sacar(valor);
        if (conta.getSaldo() == 0) {
            notify();
        }
    }
        
    /**
     * Coloca o monitor em estado de espera indefinida. Ele permanece nesse
     * estado até que seja notificado.
     */
    private synchronized void dormir() {
        try {
            wait();
        } catch (InterruptedException ex) {
            DataLogger.log("=> Interrupção no monitor");
        }
    }
    
}
