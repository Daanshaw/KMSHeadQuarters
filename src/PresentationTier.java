import java.sql.SQLException;
import java.util.Scanner;

public class PresentationTier {
    void presentationTier() throws Exception {

        ApplicationTier ap = new ApplicationTier();

            // UI

        while (true) {
            System.out.println("Welcome to KWIK MEDICAL SYSTEM");
            System.out.println("Choose form one of the following options:");
            System.out.println("");
            System.out.println("1. Enter patient info - name, surname, nhs registration number, address, medical condition");
            System.out.println("2. Exit program");

            //moves to application tier

            ap.applicationTier();




        }
    }
    void patientExistMessage(){
        System.out.println("Patient exist in the database! Try again!");

    }
}
