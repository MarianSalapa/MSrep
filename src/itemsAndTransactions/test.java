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
        
            ArrayList<Transaction> list=itemsAndTransactions.AddTransaction.listAllTrArrayList();
            for(Transaction k:list) {  
            System.out.println(k.getNo()+" "+k.getDescription()+" "+k.getQ()+" "+k.getPrice()+" "+k.getDate()+" "+k.getType()+" "+k.getCode());
            }
        }
	
}