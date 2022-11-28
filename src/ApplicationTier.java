import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.util.Scanner;


public class ApplicationTier {

    void applicationTier() throws Exception {
        Scanner scanner = new Scanner(System.in);  // Create a Scanner object

        PresentationTier pt = new PresentationTier();

        DataTier dt = new DataTier();

        //menu options

        int option = scanner.nextInt();  // Read user input

        if (option == 2) {

            System.exit(0);


        } else if (option == 1) {

            //multiple scanners to read input from user
            pt.enterName();
            String name = scanner.next();
            pt.enterSurname();
            String surname = scanner.next();
            pt.enterNhsNo();
            int nhsRegistrationNo = scanner.nextInt();
            pt.enterAddress();
            String address = scanner.next();
            pt.enterMedicalCond();
            String medicalCondition = scanner.next();

            //creating new patient with the input from user

            Patient patient = new Patient(name, surname, nhsRegistrationNo, address, medicalCondition);


            //moving to data tier

            //establishing connection to the database

            dt.getConnection();

            //creating new table and populating it with columns
            dt.createTable();

            //checks if patient already exists in database

            dt.checkIfExist(patient);

            //if not - insert patient's info to database

            dt.insert(patient);


            dt.extractPatient(patient);

            //automatically sends patient's data to regional hospital

            sendToHospitalAndAmbulance(patient);


        }
    }

    void sendToHospitalAndAmbulance(Patient patient) {

        try {
            // Set up the keyboard input
            BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Sending patient's info...");

            String ip = "192.168.0.3";


            // Create the remote version of the student object
            PatientInterface student_stub = (PatientInterface) UnicastRemoteObject.exportObject(patient, 0);

            // Connect to the RMI Registry
            Registry registry = LocateRegistry.getRegistry(ip);

            // Declare the object with the registry
            registry.rebind("patient", student_stub);
            System.out.println("Patient data sent successfully!");
        } catch (Exception e) {
            System.err.println("Error Occurred");
            System.err.println(e.getMessage());
            System.exit(-1);
        }

    }

//    public void sendToAmbulance(Patient patient){
//
//        try
//        {
//            // Set up the keyboard input
//            BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
//            System.out.println("Patient RMI Server");
//
//            // Ask for the IP address of the RMI Registry
//            System.out.print("IP Address of RMI Registry: ");
//            String ip = input.readLine();
//
//            // Create a new student object
//            //Patient patient = new Patient("John Dann", "01234567", 123, "1231", "sikc");
//
//            // Create the remote version of the student object
//            PatientInterface student_stub = (PatientInterface) UnicastRemoteObject.exportObject(patient, 0);
//
//            // Connect to the RMI Registry
//            Registry registry = LocateRegistry.getRegistry(ip);
//
//            // Declare the object with the registry
//            registry.rebind("patient", student_stub);
//            System.out.println("Patient data sent");
//        }
//        catch (Exception e)
//        {
//            System.err.println("Error Occured");
//            System.err.println(e.getMessage());
//            System.exit(-1);
//        }
//
//    }

}
