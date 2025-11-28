import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;

public class week_05_01_Write_Files {
    public static void main(String[] args) {
        //Declare variables
        Scanner kyb = new Scanner(System.in);
        ArrayList<String> dogs = new ArrayList<>();

        //Let the user enter any number of dogs
        boolean done = false;
        while(!done){
            System.out.print("Please enter the name of one of you dogs (or hit <Enter> to quit): ");
            String dog = kyb.nextLine();
            if(dog.equals("")){
                done = true;
            }else{
                dogs.add(dog);
            }//end of if/else
        }//end of while

        //Open a file to where you will write all the dog names
        String fileName = "dogs.txt";
        File outFile = new File(fileName);
        PrintWriter writer = null;

        try {
            System.out.printf("Opening '%s' file...\n", fileName);
            writer = new PrintWriter(outFile);
        } catch (FileNotFoundException e) {
            System.out.printf("Error.  The file '%s' is not found or accessible.\n", fileName);
        }//end of try/catch

        //Print all the dog names out to the file
        System.out.println("Copying dog names to file...");
        for(String dog : dogs){
            writer.println(dog);
            System.out.printf("     Writing '%s' to file '%s'.\n", dog, fileName);
        }//end of for

        writer.close();
        System.out.printf("Process complete.  Your data can be found in '%S'.\n","C:\\user\\jeffl\\Ensign\\VSCode\\105\\" + fileName );

    }//end of main()
}//end of class
