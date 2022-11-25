import java.rmi.Remote;
import java.rmi.RemoteException;

public interface PatientInterface extends Remote {
	public String getName() throws RemoteException;

	public void setName  (String name) throws RemoteException ;

	public String getSurname  () throws RemoteException ;

	public void setSurname(String surname) throws RemoteException ;

	public int getNhsRegistrationNo() throws RemoteException;

	public void setNhsRegistrationNo(int nhsRegistrationNo) throws RemoteException;

	public String getAddress() throws RemoteException;

	public void setAddress(String address) throws RemoteException;

	public String getMedicalCondition() throws RemoteException;

	public void setMedicalCondition(String medicalCondition) throws RemoteException;
}
