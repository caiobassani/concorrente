package concorrente;

/**
 * Controla o log de qualquer operação executada no sistema.
 */
public class DataLogger {
    
    private static Tela tela;
    
    /**
     * Determina o objeto de tela do sistema que receberá as informações de log.
     * 
     * @param tela o objeto da tela do sistema
     */
    public static void setTela(Tela tela) {
        DataLogger.tela = tela;
    }
    
    /**
     * Faz o log de uma mensagem no sistema na interface gráfica e no console.
     * 
     * @param message a mensagem a ser exibida
     */
    public static void log(String message) {
        if (tela != null) {
            tela.append(message);
        }
        System.out.println(message);
    }
    
    /**
     * Atualiza o saldo na interface gráfica do sistema.
     * 
     * @param saldo valor atual do saldo
     */
    public static void setSaldoAtual(double saldo) {
        tela.setSaldoAtual(saldo);
    }
}
