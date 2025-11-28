package week_05_01_Databases;
import java.sql.*;
import java.util.Scanner;

public class week05_01_Classroom_Databases {

    public static void main(String[] args){

        /**********************************************
        Create Database 'dogs.db' with a table 'DOGS'
         *********************************************/
        Connection connection;
        Scanner in = new Scanner(System.in);
        PreparedStatement statement1;
        PreparedStatement statement2;
        PreparedStatement statement3;
        ResultSet rs;

        //Optional save the name in a string
        String JDBC_URL_Sync = "jdbc:sqlite:cats.db";

        try {
            //Create and or connect to the dogs.db database.
            connection = DriverManager.getConnection(JDBC_URL_Sync);

            //Create a table called dogs
            String createTableSQL  = "CREATE TABLE IF NOT EXISTS DOGS(dogID int PRIMARY KEY, Name varchar(10), Age int, Color varchar(10)";
            statement1 = connection.prepareStatement(createTableSQL);
            statement1.execute();

        } catch (SQLException e) {
            System.out.println(e);
            System.out.printf("\n\nError! Creating database '%s' OR creating table %s failed.\n", JDBC_URL_Sync, "DOGS");
            System.out.println("Exiting program.");
            in.close();
            return;
        }


        /**********************************************
         Populate table 'DOGS' with user input
         *********************************************/

        boolean done = false;
        while(!done){
            System.out.print("Please enter the name of a dog (or hit <Enter> to quit): ");
            String name = in.nextLine();

            if(name.equals("")){
                done = true;
            }else{
                int age = getValidInt(in, "Please enter the dog's age: ", "Error!  Age must be a whole number.");

                System.out.print("Please enter the dog's color: ");
                String color = in.nextLine();

                String insertSQL = String.format("INSERT INTO DOGS(dogID, Name, Age, Color) VALUES(%d, %s, %d, %s)", System.currentTimeMillis(), name, age, color);
                try {
                    statement2 = connection.prepareStatement(insertSQL);
                    statement2.execute();
                } catch (SQLException e) {
                    System.out.println(e);
                    System.out.printf("\n\nError!  Inserting record into %s failed.\n", "DOGS");
                    System.out.println("Exiting Program.");
                    in .close();
                    return;
                }//end of try/catch
            }//end of if/else

        }//end of while - get the list of dogs from the user.

        /**********************************************
         Print out data from the database
         *********************************************/

        String selectSQL = "SELECT * FROM DOGS";
        try {
            statement3 = connection.prepareStatement(selectSQL);
            rs = statement3.executeQuery();

            //Print out results

            while(rs.next()){
                System.out.printf("%-10s%-5d%s\n", rs.getString("Name"), rs.getInt("Age"), rs.getString("Color"));
            }

        } catch (SQLException e) {
            System.out.println(e);
            System.out.println("\n\nError retrieving data from DOGS.");
            in.close();
            return;
        }//end of try/catch

    }//end of main()

    public static int getValidInt(Scanner in, String question, String warning){
        while(true){
            System.out.print(question);
            String answer = in.nextLine();
            try{
                int value = Integer.parseInt(answer);
                return value;
            }catch (Exception e){
                System.out.println(warning);
            }//end of try/catch
        }//end of while
    }//end of getValidInt()

}//end of Class()

