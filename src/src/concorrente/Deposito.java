package concorrente;

public class Deposito extends Thread {

    private Conta conta;

    public Deposito(String name, Conta conta) {
        super(name);
        this.conta = conta;
    }

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
