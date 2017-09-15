package concorrente;

import java.util.List;

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
        DataLogger.log("=> Extrato");
        List<Consumidor> consumidores = FabricaConsumidor.getConsumidores();
        for (Consumidor consumidor : consumidores) {
            consumidor.imprimirExtrato();
            consumidor.zerarExtrato();
        }
        DataLogger.log("----------------------------------------");
        conta.depositar(valor);
        DataLogger.log("[DEPOSITO] " + Thread.currentThread().getName() + " depositou R$: " + valor + ". Saldo após o depósito R$: " + conta.getSaldo());
        DataLogger.setSaldoAtual(conta.getSaldo());
        DataLogger.log("======> " + Thread.currentThread().getName() + " notificando todos");
        notifyAll();
    }

    /**
     * Saca um valor da conta compartilhada caso seja possível.
     *
     * @param valor valor a ser sacado
     * @return true caso o saque tenha sido efetuado com sucesso e false caso
     * contrário
     */
    public synchronized boolean sacar(double valor) {
        if (!conta.sacar(valor)) {
            dormir();
            return false;
        }
        if (conta.getSaldo() == 0) {
            DataLogger.log("======> " + Thread.currentThread().getName() + " notificando todos");
            notifyAll();
        }
        return true;
    }

    /**
     * Coloca o monitor em estado de espera indefinida. Ele permanece nesse
     * estado até que seja notificado.
     */
    private synchronized void dormir() {
        try {
            DataLogger.log("======> " + Thread.currentThread().getName() + " dormindo");
            wait();
            DataLogger.log("======> " + Thread.currentThread().getName() + " acordando");
        } catch (InterruptedException ex) {
            DataLogger.log("=> Interrupção no monitor");
        }
    }

}
