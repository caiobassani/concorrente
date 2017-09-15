package concorrente;

import java.util.List;
import java.util.ArrayList;

/**
 * Controladora das instâncias de consumidor do sistema.
 */
public class FabricaConsumidor {
    
    private static List<Consumidor> consumidores = new ArrayList<>();
    
    /**
     * Cria um consumidor e o armazena na lista de consumidores do sistema.
     * 
     * @param name nome do consumidor
     * @param tempo intervalo de tempo esperado entre cada saque efetuado
     * @param valor valor a ser retirado em cada saque
     * @param monitor monitor dos recursos compartilhados (conta)
     * @return o consumidor criado
     */
    public static Consumidor criarConsumidor(String name, int tempo, double valor, Monitor monitor) {
        Consumidor consumidor = new Consumidor(name, tempo, valor, monitor);
        consumidores.add(consumidor);
        return consumidor;
    }
    
    /**
     * Recupera a lista de consumidores do sistema.
     *   
     * @return a lista de consumidores
     */
    public static List<Consumidor> getConsumidores() {
        return consumidores;
    }
}
