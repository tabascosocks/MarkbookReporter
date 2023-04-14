/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package markbookreporterui.view;

import markbookreporterui.Application;

/**
 *
 * @author School
 */
public class ViewApplicationLogDialog extends javax.swing.JDialog {

    /**
     * Creates new form ViewApplicationLogDialog
     */
    public ViewApplicationLogDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        
        displayLog();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        logTextArea = new javax.swing.JTextArea();
        refreshButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("System Log Viewer");
        setPreferredSize(new java.awt.Dimension(640, 480));

        logTextArea.setEditable(false);
        logTextArea.setColumns(20);
        logTextArea.setRows(5);
        jScrollPane1.setViewportView(logTextArea);

        getContentPane().add(jScrollPane1, java.awt.BorderLayout.CENTER);

        refreshButton.setText("Refresh");
        refreshButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshButtonActionPerformed(evt);
            }
        });
        getContentPane().add(refreshButton, java.awt.BorderLayout.SOUTH);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void refreshButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshButtonActionPerformed
        displayLog();
    }//GEN-LAST:event_refreshButtonActionPerformed


    private void displayLog(){
        logTextArea.setText(Application.getInstance().getApplicationLogBuffer().toString());
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea logTextArea;
    private javax.swing.JButton refreshButton;
    // End of variables declaration//GEN-END:variables
}
