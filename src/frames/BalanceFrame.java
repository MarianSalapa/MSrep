/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frames;

import itemsAndTransactions.AddTransaction;
import itemsAndTransactions.InventoryItem;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author MMM
 */
public class BalanceFrame extends javax.swing.JInternalFrame {

    /**
     * Creates new form BalanceFrameN
     */
    public BalanceFrame() {
        initComponents();
        //jTextArea1.setText(itemsAndTransactions.AddItem.listBalance());
        addRowToTable();
    }
    
    public void addRowToTable(){
        DefaultTableModel model= (DefaultTableModel)jTable1.getModel();
        ArrayList<InventoryItem> list=itemsAndTransactions.AddItem.listAllArrayList();
        float totalValue=0;
        Object rowData[]=new Object[8];
        for (int i=0;i<list.size();i++){
            rowData[0]=list.get(i).getItemCode();
            rowData[1]=list.get(i).getName();
            rowData[2]=list.get(i).getUm();
            rowData[3]=AddTransaction.SumQCodeType(list.get(i).getItemCode(),"in");
            rowData[4]=AddTransaction.SumQCodeType(list.get(i).getItemCode(),"out");
            rowData[5]=list.get(i).getItemQ();           
            rowData[6]=String.format("%.02f",list.get(i).getPrice());
            rowData[7]=String.format("%.02f",list.get(i).getPrice()*list.get(i).getItemQ());
            model.addRow(rowData);
            totalValue+=list.get(i).getPrice()*list.get(i).getItemQ();
            
        }
        
            rowData[0]="Total";
            rowData[1]="";
            rowData[2]="";
            rowData[3]="";
            rowData[4]="";       
            rowData[5]="";       
            rowData[6]="";       
            rowData[7]=String.format("%.02f",totalValue);           
            model.addRow(rowData);
        
    }
    
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setVisible(true);

        jLabel1.setText("Current Balance");

        jButton1.setText("Close");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Code", "Name", "UM", "Q in", "Q out", "Q final", "Price", "Value"
            }
        ));
        jTable1.setColumnSelectionAllowed(true);
        jScrollPane2.setViewportView(jTable1);
        jTable1.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 612, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(186, 186, 186)
                        .addComponent(jButton1)))
                .addContainerGap(101, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 314, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(55, 55, 55))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        super.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
