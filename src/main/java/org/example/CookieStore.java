package org.example;

import java.util.Scanner;

public class CookieStore {

public static void main(String args[]){
    Scanner in = new Scanner(System.in);
    String name = "Jeff";
    int number = 7;
    int fingerCount = 10;
    System.out.println(name);

    CookieTray tray1 = new CookieTray("Choc Chip", 24);
    CookieTray tray2 = new CookieTray("Oatmeal", 24);

    System.out.println(tray1.getType());
    System.out.println(tray2.getType());


    System.out.print("How many Cookies? ");
    int amount = in.nextInt();

    tray1.sellCookies(5);

    System.out.printf("You now have %d cookies left.\n", tray1.getQty());


in.close();
}


}
