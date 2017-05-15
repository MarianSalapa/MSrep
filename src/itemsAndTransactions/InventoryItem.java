/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itemsAndTransactions;

/**
 *
 * @author MMM
 */
public class InventoryItem {
    private String itemCode;
    private String name;
    private int itemQ;
    private String um;
    private float price;

    public InventoryItem(String itemCode) {
        this.itemCode = itemCode;
    }



    public String getItemCode() {
        return itemCode;
    }

    public String getName() {
        return name;
    }

    public int getItemQ() {
        return itemQ;
    }

    public String getUm() {
        return um;
    }

    public float getPrice() {
        return price;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUm(String um) {
        this.um = um;
    }

    public void setItemQ(int itemQ) {
        this.itemQ = itemQ;
    }

    public void setPrice(float price) {
        this.price = price;
    }
    
    
    
}
