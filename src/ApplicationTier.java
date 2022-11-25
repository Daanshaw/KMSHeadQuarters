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

        DataTier dt = new DataTier();

        //menu options

        int option = scanner.nextInt();  // Read user input

        if (option == 2) {

            System.exit(0);


        } else if (option == 1) {

            //multiple scanners to read input from user

            String name = scanner.next();
            String surname = scanner.next();
            int nhsRegistrationNo = scanner.nextInt();
            String address = scanner.next();
            String medicalCondition = scanner.next();

            //creating new patient with the attributes input from user

            Patient patient = new Patient(name, surname, nhsRegistrationNo, address, medicalCondition);

            System.out.println(patient);

            //moving to data tier

            //establishing connection to the database

            dt.getConnection();

            //creating new table and populating it with columns
            dt.createTable();

            dt.checkIfExist(patient);

            dt.insert(patient);

            sendToHospital(patient);

            dt.extractPatient(patient);

            //sendToAmbulance(patient);









        }
    }

    public void sendToHospital(Patient patient){

        try
        {
            // Set up the keyboard input
            BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Patient RMI Server");

            // Ask for the IP address of the RMI Registry
            System.out.print("IP Address of RMI Registry: ");
            String ip = input.readLine();

            // Create a new student object
            //Patient patient = new Patient("John Dann", "01234567", 123, "1231", "sikc");

            // Create the remote version of the student object
            PatientInterface student_stub = (PatientInterface) UnicastRemoteObject.exportObject(patient, 0);

            // Connect to the RMI Registry
            Registry registry = LocateRegistry.getRegistry(ip);

            // Declare the object with the registry
            registry.rebind("patient", student_stub);
            System.out.println("Patient data sent");
        }
        catch (Exception e)
        {
            System.err.println("Error Occured");
            System.err.println(e.getMessage());
            System.exit(-1);
        }

    }

    public void sendToAmbulance(Patient patient){

        try
        {
            // Set up the keyboard input
            BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Patient RMI Server");

            // Ask for the IP address of the RMI Registry
            System.out.print("IP Address of RMI Registry: ");
            String ip = input.readLine();

            // Create a new student object
            //Patient patient = new Patient("John Dann", "01234567", 123, "1231", "sikc");

            // Create the remote version of the student object
            PatientInterface student_stub = (PatientInterface) UnicastRemoteObject.exportObject(patient, 0);

            // Connect to the RMI Registry
            Registry registry = LocateRegistry.getRegistry(ip);

            // Declare the object with the registry
            registry.rebind("patient", student_stub);
            System.out.println("Patient data sent");
        }
        catch (Exception e)
        {
            System.err.println("Error Occured");
            System.err.println(e.getMessage());
            System.exit(-1);
        }

    }

}
