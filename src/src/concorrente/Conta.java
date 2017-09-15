package concorrente;

/**
 * A conta compartilhada entre os usuários do sistema.
 */
public class Conta {

    private int numero;
    private String titular;
    private double saldo;

    /**
     * Construtor da conta.
     * 
     * @param numero número da conta
     * @param titular nome do titular da conta
     * @param saldo saldo inicial da conta
     */
    public Conta(int numero, String titular, double saldo) {
        this.numero = numero;
        this.titular = titular;
        this.saldo = saldo;
        DataLogger.log("Conta criada. Saldo inicial: R$: " + saldo);
        DataLogger.setSaldoAtual(saldo);
    }

    /**
     * Recupera o saldo corrente da conta.
     * 
     * @return o saldo da conta
     */
    public double getSaldo() {
        return saldo;
    }

    /**
     * Atualiza o saldo da conta.
     * 
     * @param saldo novo valor de saldo
     */
    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    /**
     * Realiza um saque na conta caso o valor a ser sacado não deixe o saldo
     * resultante negativo.
     * 
     * @param valor valor a ser sacado da conta
     * @return true caso o saque tenha ocorrido com sucesso e false caso
     * contrário
     */
    public boolean sacar(double valor) {
        if (saldo >= valor) {
            saldo = saldo - valor;
            DataLogger.log("[SAQUE] " + Thread.currentThread().getName() + " sacou R$: " + valor + ". Saldo após o saque R$: " + saldo);
            DataLogger.setSaldoAtual(saldo);
            return true;
        } 
        return false;
    }

    /**
     * Deposita um valor na conta.
     * 
     * @param valor valor a ser depositado
     */
    public void depositar(double valor) {
        saldo += valor;
    }
}
