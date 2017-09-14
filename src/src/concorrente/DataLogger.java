package concorrente;

public class DataLogger {
    
    private static Tela tela;
    
    public static void setTela(Tela tela) {
        DataLogger.tela = tela;
    }
    
    public static void log(String message) {
        if (tela != null) {
            tela.append(message);
        }
        System.out.println(message);
    }
    
    public static void setSaldoAtual(double saldo) {
        tela.setSaldoAtual(saldo);
    }
}
