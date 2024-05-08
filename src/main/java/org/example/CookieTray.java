package org.example;

public class CookieTray {
    private String type;
    private int qty;

    //constructor
    public CookieTray(String type, int qty){
        this.type = type;
        this.qty = qty;
    }

    public String getType(){
        return this.type;
    }

    public int getQty(){
        return this.qty;
    }

    public void sellCookies(int amount){
        if(this.qty <= amount){
            this.qty -= amount;
        }
        if (this.qty < 10){
            System.out.println("Bake More Cookies.");
        }
    }



}
