package week_05_01_Databases;

import java.util.Scanner;

//import org.junit.platform.commons.util.ExceptionUtils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;


public class week05_01_Base_Databases {

    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        String name, color, answer;
        int age=0;
        Connection connection;
        ResultSet rs;

        //optional
        String JDBC_URL_Sync = "jdbc:sqlite:cats.db";

        try{
            //Connect to a database
            connection = DriverManager.getConnection(JDBC_URL_Sync);

            //Create a table called cats
            String createSQL = "CREATE TABLE IF NOT EXISTS CATS(catid int PRIMARY KEY, Name varchar(10), Age int, Color varchar(10))";      
            PreparedStatement statement = connection.prepareStatement(createSQL);
            statement.execute();
        }catch (SQLException e){
            System.out.printf("Error! Creating '%s' table failed or could not connect '%s' to Database.\n", "CATS", JDBC_URL_Sync);
            System.out.println("Exiting Program.");
            in.close();
            return;
        }//end of try/catch Connect and Create

        boolean done = false;
        //Get all info on the cat
        while(!done){
            //Get cat's name
            System.out.print("Please enter the name of a cat (or hit <Enter> to quit): ");
            name = in.nextLine();

            //Exit program if user hits <Enter>.
            if (name.equals("")){
                done = true;
            }else{
                //Get cat's age
                boolean valid = false;
                age = getValidInt(in, "Please enter the cat's age: ", "Error.  Age must be a whole number.\n");

                //Get cat's color
                System.out.printf("Please enter %s's color: ", name);
                color = in.nextLine();

                String insertSQL = String.format("INSERT INTO CATS (catid, Name, Age, Color) VALUES (%d, '%s', %d, '%s')", System.currentTimeMillis(), name, age, color);
                try{
                    PreparedStatement statement2 = connection.prepareStatement(insertSQL);
                    statement2.execute();
                }catch(SQLException e){
                    System.out.printf("Error! Inserting record into %s failed.\n", "CATS");
                    System.out.println("Exiting Program.");
                    in.close();
                    return;
                }//end of try/catch Insert

            }//end of if/else name == ""
        }//end of while !done

        System.out.println("                 Cat Listing");
        System.out.printf(" %s    %s   %s\n", "Name", "Age","Color");
        System.out.println("--------------------------------------------");

        String selectSQL = "SELECT * FROM CATS";
        try{
            PreparedStatement statement3 = connection.prepareStatement(selectSQL);
            rs = statement3.executeQuery();
            while(rs.next()){
                System.out.printf("%-10s%-5d%-10s\n", rs.getString("Name"), rs.getInt("Age"), rs.getString("Color"));
            }
        }catch(SQLException e){
            System.out.printf("Error! Failed retrieving data from %s.\n", "CATS");
            System.out.println("Exiting Program.");
            in.close();
            return;
        }//end of try/catch Insert  

        in.close();
        System.out.println("Goodbye.");

    }//end of main()


public static int getValidInt(Scanner in, String question, String warning){
    boolean valid = false;
    int value;
    while(!valid){
        System.out.printf(question);
        String answer = in.nextLine();
        try{
            value = Integer.parseInt(answer);
            return value;
        }catch (Exception e){
            System.out.println(warning);
        }//end of try catch
    }//end of while
    return 0;
}//end of getValidInt()


}//end of Class()

