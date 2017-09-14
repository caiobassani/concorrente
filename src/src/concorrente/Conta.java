package concorrente;

public class Conta {

    private int numero;
    private String titular;
    private double saldo;

    public Conta(int numero, String titular, double saldo) {
        this.numero = numero;
        this.titular = titular;
        this.saldo = saldo;
        DataLogger.log("Conta criada. Saldo inicial: R$: " + saldo);
        DataLogger.setSaldoAtual(saldo);
    }

    public synchronized double getSaldo() {
        return saldo;
    }

    public synchronized void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public synchronized boolean sacar(double valor) {
        //Testa para ver se existe saldo
        if (saldo - valor >= 0) {
            saldo = saldo - valor;
            DataLogger.log("[SAQUE] " + Thread.currentThread().getName() + " sacou R$: " + valor + ". Saldo após o saque R$: " + saldo);
            DataLogger.setSaldoAtual(saldo);
            return true;
        } 
        return false;
    }

    public synchronized void depositar(double valor) {
        saldo += valor;
        DataLogger.log("[DEPOSITO] " + Thread.currentThread().getName() + " depositou R$: " + valor + ". Saldo após o depósito R$: " + saldo);
        DataLogger.setSaldoAtual(saldo);
    }
}
