import java.sql.*;

import static java.sql.JDBCType.NULL;

public class DataTier {

    PresentationTier pt = new PresentationTier();

    public static Connection getConnection(){
        try{
            String driver = "com.mysql.cj.jdbc.Driver";
            String databaseUrl = "jdbc:mysql://localhost:3306/kms";
            String username= "root";
            String password= "";
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(databaseUrl, username, password);
            System.out.println("Database connected");
            return conn;

        } catch (Exception e) {
            System.out.println(e);
        }


        return null;
    }


    public void createTable() throws SQLException {
        try {
            Connection conn = getConnection();
            PreparedStatement create = conn.prepareStatement(" CREATE TABLE `kms`.`patient` (`id` INT NOT NULL AUTO_INCREMENT , `name` VARCHAR(255) NOT NULL , `surname` VARCHAR(255) NOT NULL , `nhs_reg_no` INT(10) NOT NULL , `address` VARCHAR(255) NOT NULL , `med_cond` VARCHAR(255) NOT NULL , PRIMARY KEY (`id`)) ENGINE = InnoDB;");
            create.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
        finally{
            System.out.println("Table created succesfully.");
        }

    }

    public void insert(Patient patient){

        try{
            Connection conn = getConnection();

            PreparedStatement insert = conn.prepareStatement("INSERT INTO Patient(id, name, surname,nhs_reg_no, address, med_cond)" + "VALUES (?,?, ?, ?, ?, ?)");

            insert.setNull(1, 1);
            insert.setString(2, patient.getName());
            insert.setString(3, patient.getSurname());
            insert.setInt(4, patient.getNhsRegistrationNo());
            insert.setString(5, patient.getAddress());
            insert.setString(6, patient.getMedicalCondition());

            insert.executeUpdate();

        } catch (Exception e) {
            System.out.println(e);
        }
        finally {
            System.out.println("Data inserted successfully.");
        }
    }
    //checks if patient exists in database by nhs number, if exists, redirects to beginning of the program
    public void checkIfExist(Patient patient) throws Exception {

        try{
            Connection conn = getConnection();

            PreparedStatement exist = conn.prepareStatement("SELECT nhs_reg_no FROM patient WHERE nhs_reg_no =?");


            exist.setInt(1, patient.getNhsRegistrationNo());
//            insert.setString(3, patient.getSurname());
//            insert.setInt(4, patient.getNhsRegistrationNo());
//            insert.setString(5, patient.getAddress());
//            insert.setString(6, patient.getMedicalCondition());
            ResultSet rs = exist.executeQuery();
            while(rs.next()){
            pt.patientExistMessage();
            pt.presentationTier();

            }



        } catch (Exception e) {

            System.out.println(e);
        }
        finally {
            System.out.println("Existed checked successfully.");
        }
    }


}

