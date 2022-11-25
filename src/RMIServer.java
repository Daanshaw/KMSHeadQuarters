import java.io.*;
import java.rmi.registry.Registry; 
import java.rmi.registry.LocateRegistry; 
import java.rmi.RemoteException; 
import java.rmi.server.UnicastRemoteObject; 

public class RMIServer
{
	public static void main(String[] args)
	{
		try
		{
			// Set up the keyboard input
			BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("Patient RMI Server");

			// Ask for the IP address of the RMI Registry
			System.out.print("IP Address of RMI Registry: ");
			String ip = input.readLine();

			// Create a new student object
			Patient patient = new Patient("John Dann", "01234567", 123, "1231", "sikc");

			// Create the remote version of the student object
			PatientInterface student_stub = (PatientInterface) UnicastRemoteObject.exportObject(patient, 0);

			// Connect to the RMI Registry
			Registry registry = LocateRegistry.getRegistry(ip);

			// Declare the object with the registry
			registry.rebind("patient", student_stub);
			System.out.println("Student bound");
		}
		catch (Exception e)
		{
			System.err.println("Error Occured");
			System.err.println(e.getMessage());
			System.exit(-1);
		}
	}
}
