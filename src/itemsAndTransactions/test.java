/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itemsAndTransactions;

import java.util.ArrayList;

/**
 *
 * @author MMM
 */

public class test {
    
	public static void main(String[] args) {
        
            ArrayList<InventoryItem> list=itemsAndTransactions.AddItem.listAllArrayList();
            for(InventoryItem k:list) {  
            System.out.println(k.getItemCode()+" "+k.getName()+" "+k.getUm()+" "+k.getItemQ()+" "+k.getPrice());
            }
        }
	
}