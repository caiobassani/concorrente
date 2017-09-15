package concorrente;

import javax.swing.text.DefaultCaret;

/**
 * JFrame que representa a janela principal da interface gráfica do sistema.
 */
public class Tela extends javax.swing.JFrame {

    /**
     * Construtor da interface gráfica
     */
    public Tela() {
        initComponents();
    }

    /**
     * Inicializa os componentes da tela
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        caixaTexto = new javax.swing.JTextArea();
        botaoIniciar = new javax.swing.JButton();
        textFieldSaldoAtual = new javax.swing.JTextField();
        labelSaldoAtual = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        caixaTexto.setColumns(20);
        caixaTexto.setFont(new java.awt.Font("Consolas", 0, 12)); // NOI18N
        caixaTexto.setRows(5);
        caixaTexto.setCaret(getCaret());
        jScrollPane1.setViewportView(caixaTexto);

        botaoIniciar.setFont(new java.awt.Font("Trebuchet MS", 0, 24)); // NOI18N
        botaoIniciar.setText("Iniciar");
        botaoIniciar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoIniciarActionPerformed(evt);
            }
        });

        textFieldSaldoAtual.setEditable(false);
        textFieldSaldoAtual.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        labelSaldoAtual.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        labelSaldoAtual.setText("Saldo Atual:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(textFieldSaldoAtual, javax.swing.GroupLayout.PREFERRED_SIZE, 293, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelSaldoAtual, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(botaoIniciar, javax.swing.GroupLayout.DEFAULT_SIZE, 379, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(labelSaldoAtual, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(textFieldSaldoAtual, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(botaoIniciar, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 435, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Método executado quando o usuário clica no botão Iniciar da interface.
     * 
     * @param evt evento de clique do mouse
     */
    private void botaoIniciarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoIniciarActionPerformed
        iniciar();
    }//GEN-LAST:event_botaoIniciarActionPerformed

    /**
     * Método main do sistema.
     * 
     * @param args argumentos da execução do sistema
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Tela.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Tela.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Tela.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Tela.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Tela tela = new Tela();
                DataLogger.setTela(tela);
                tela.setVisible(true);
            }
        });
    }
    
    /**
     * Inicia o processamento dos saques e depósitos na conta do sistema.
     */
    public void iniciar() {
        //Cria a conta com saldo inicial de R$10000,00
        Conta conta = new Conta(10, "Caio", 500);
        //Cria as Threads que irão consumir o recurso da conta
        Consumidor AGastadora = new Consumidor("AGastadora", 3000, 10, conta);
        Consumidor AEsperta = new Consumidor("AEsperta", 6000, 50, conta);
        Consumidor AEconomica = new Consumidor("AEconomica", 12000, 5, conta);
        //Cria a Thread que irá produzir o recurso da conta
        Produtor APatrocinadora = new Produtor("APatrocinadora", conta);       
        
        //Coloca as Threads para rodar
        AGastadora.start();               
        AEsperta.start();               
        AEconomica.start();
        
        APatrocinadora.start();
        
        this.botaoIniciar.setEnabled(false);
    }
    
    /**
     * Insere uma mensagem no final da caixa de texto.
     * 
     * @param message a mensagem a ser exibida
     */    
    public void append(String message) {
        this.caixaTexto.append(message + "\n");
    }
    
    /**
     * Atualiza o campo que contém o saldo atual da conta.
     * 
     * @param saldo valor do saldo
     */
    public void setSaldoAtual(double saldo) {
        this.textFieldSaldoAtual.setText(String.format("R$ %.2f", saldo));
    }
    
    /**
     * Determina o autoscroll da caixa de texto à medida que as mensagens são
     * adicionadas.
     * 
     * @return o elemento caret pertencente à caixa de texto
     */
    private DefaultCaret getCaret() {
        DefaultCaret caret = (DefaultCaret) caixaTexto.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
        return caret;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botaoIniciar;
    private javax.swing.JTextArea caixaTexto;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelSaldoAtual;
    private javax.swing.JTextField textFieldSaldoAtual;
    // End of variables declaration//GEN-END:variables
}
