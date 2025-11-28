import java.sql.*;
import java.util.Scanner;

public class week_05_02_Databases {
    public static void main(String[] args) {
        //Declare variables
        Scanner kyb = new Scanner(System.in);
        Connection connection;
        String JDBC_URL_Sync = "jdbc:sqlite:dogs.db";
        PreparedStatement statementCreateTable;
        PreparedStatement statementInsertSQL;
        PreparedStatement statementSelectSQL;
        ResultSet rs;

        //Try to create and connect to my dogs.db
        try{
            connection = DriverManager.getConnection(JDBC_URL_Sync);

            String createTableSQL = "CREATE TABLE IF NOT EXISTS DOGS(dogID int PRIMARY KEY, NAME varchar(10), Age int, Color varchar(10))";
            statementCreateTable = connection.prepareStatement(createTableSQL);
            statementCreateTable.execute();

        }catch (SQLException e){
            System.out.println(e);
            System.out.printf("Error creating database %s, or Creating %s table.",JDBC_URL_Sync, "DOGS");
            System.out.printf("Exiting program...");
            return;
        }//end off try/catch

        /**********************************************
         Populate table 'DOGS' with user input
         *********************************************/

        boolean done = false;
        while(!done){
            System.out.print("Please enter the name of a dog (or hit <Enter> to quit): ");
            String name = kyb.nextLine();

            if(name.equals("")){
                done = true;
            }else{
                int age = getValidInt(kyb, "Please enter the dog's age: ", "Error!  Age must be a whole number.");

                System.out.print("Please enter the dog's color: ");
                String color = kyb.nextLine();

                String insertSQL = String.format("INSERT INTO DOGS(dogID, Name, Age, Color) VALUES(%d, %s, %d, %s)", System.currentTimeMillis(), name, age, color);
                try {
                    statementInsertSQL = connection.prepareStatement(insertSQL);
                    statementInsertSQL.execute();
                } catch (SQLException e) {
                    System.out.println(e);
                    System.out.printf("\n\nError!  Inserting record into %s failed.\n", "DOGS");
                    System.out.println("Exiting Program.");
                    kyb.close();
                    return;
                }//end of try/catch
            }//end of if/else
        }//end of while - get the list of dogs from the user.

        /***********************************************
         * Print out data from the data
         ***********************************************/

        String selectSQL = "SELECT * FROM DOGS";
        try{
            statementSelectSQL = connection.prepareStatement(selectSQL);
            rs = statementSelectSQL.executeQuery();

            while(rs.next()){
                System.out.printf("%-15s%-5d%s\n", rs.getString("Name"), rs.getInt("Age"), rs.getString("Color"));
            }
        }catch (SQLException e){
            System.out.println(e);
            System.out.printf("Error retrieving data from %s\n","DOGS");
            return;
        }






    }//end of main


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





}//end of class
