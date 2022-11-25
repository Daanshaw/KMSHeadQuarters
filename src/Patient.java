import java.io.Serializable;
import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;

public class Patient implements Serializable, PatientInterface, Remote {

    private String name;
    private String surname;
    private int nhsRegistrationNo;
    private String address;
    private String medicalCondition;

    public Patient(String name, String surname, int nhsRegistrationNo, String address, String medicalCondition) {
        this.name = name;
        this.surname = surname;
        this.nhsRegistrationNo = nhsRegistrationNo;
        this.address = address;
        this.medicalCondition = medicalCondition;
    }



    @Override
    public String toString() {
        return "Patient{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", nhsRegistrationNo=" + nhsRegistrationNo +
                ", address='" + address + '\'' +
                ", medicalCondition='" + medicalCondition + '\'' +
                '}';
    }

    @Override
    public String getName() throws RemoteException {
        return name;
    }

    @Override
    public void setName(String name) throws RemoteException {
        this.name = name;
    }

    @Override
    public String getSurname() throws RemoteException {
        return surname;
    }

    @Override
    public void setSurname(String surname) throws RemoteException {
        this.surname = surname;
    }

    @Override
    public int getNhsRegistrationNo() throws RemoteException {
        return nhsRegistrationNo;
    }

    @Override
    public void setNhsRegistrationNo(int nhsRegistrationNo) throws RemoteException {
        this.nhsRegistrationNo = nhsRegistrationNo;
    }

    @Override
    public String getAddress() throws RemoteException {
        return address;
    }

    @Override
    public void setAddress(String address) throws RemoteException {
        this.address = address;
    }

    @Override
    public String getMedicalCondition()throws RemoteException {
        return medicalCondition;
    }

    @Override
    public void setMedicalCondition(String medicalCondition) throws RemoteException {
        this.medicalCondition = medicalCondition;
    }



}