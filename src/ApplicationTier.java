import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.util.Scanner;


public class ApplicationTier {
    PresentationTier pt = new PresentationTier();

    void applicationTier() throws Exception {
        Scanner scanner = new Scanner(System.in);  // Create a Scanner object


        DataTier dt = new DataTier();

        //menu options

        int option = scanner.nextInt();  // Read user input

        if (option == 2) {

            System.exit(0);


        } else if (option == 1) {

            //multiple scanners to read input from user
            pt.enterNameMsg();
            String name = scanner.next();
            pt.enterSurnameMsg();
            String surname = scanner.next();
            pt.enterNhsNoMsg();
            int nhsRegistrationNo = scanner.nextInt();
            pt.enterAddressMsg();
            String address = scanner.next();
            pt.enterMedicalCondMsg();
            String medicalCondition = scanner.next();

            //creating new patient with the input from user

            Patient patient = new Patient(name, surname, nhsRegistrationNo, address, medicalCondition);


            //moving to data tier

            //establishing connection to the database

            dt.getConnection();


            //dt.createTable();

            //checks if patient already exists in database

            dt.checkIfExist(patient);

            dt.extractPatient(patient);

            //if not - insert patient's info to database

            dt.insert(patient);


            //automatically sends patient's data to regional hospital


        }
    }

    void sendToHospitalAndAmbulance(Patient patient) {

        try {
            // Set up the keyboard input
            BufferedReader input = new BufferedReader(new InputStreamReader(System.in));


            String ip = "192.168.0.3";


            // Create the remote version of the student object
            PatientInterface student_stub = (PatientInterface) UnicastRemoteObject.exportObject(patient, 0);

            // Connect to the RMI Registry
            Registry registry = LocateRegistry.getRegistry(ip);

            // Declare the object with the registry
            registry.rebind("patient", student_stub);
            pt.dataSendSuccessfullyMsg();
        } catch (Exception e) {
            System.err.println("Error Occurred");
            System.err.println(e.getMessage());
            System.exit(-1);
        }

    }


}
