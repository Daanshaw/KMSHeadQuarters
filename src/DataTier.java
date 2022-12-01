import java.sql.*;

import static java.sql.JDBCType.NULL;

public class DataTier {

    PresentationTier pt = new PresentationTier();
    ApplicationTier ap = new ApplicationTier();

    //connection to database

    public static Connection getConnection() {
        try {
            String driver = "com.mysql.cj.jdbc.Driver";
            String databaseUrl = "jdbc:mysql://localhost:3306/kms";
            String username = "root";
            String password = "";
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(databaseUrl, username, password);
            //System.out.println("Database connected");
            return conn;

        } catch (Exception e) {
            System.out.println(e);
        }


        return null;
    }

    //method to create new table in database
//    public void createTable() throws SQLException {
//        try {
//            Connection conn = getConnection();
//            PreparedStatement create = conn.prepareStatement(" CREATE TABLE `kms`.`patient` (`id` INT NOT NULL AUTO_INCREMENT , `name` VARCHAR(255) NOT NULL , `surname` VARCHAR(255) NOT NULL , `nhs_reg_no` INT(10) NOT NULL , `address` VARCHAR(255) NOT NULL , `med_cond` VARCHAR(255) NOT NULL , PRIMARY KEY (`id`)) ENGINE = InnoDB;");
//            create.executeUpdate();
//        } catch (SQLException e) {
//            System.out.println(e);
//        }
//
//
//    }

    //method to insert patient's info to database

    public void insert(Patient patient) {

        try {
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

    }

    //checks if patient exists in database by nhs number
    public void checkIfExist(Patient patient) throws Exception {

        try {
            Connection conn = getConnection();

            PreparedStatement exist = conn.prepareStatement("SELECT nhs_reg_no FROM patient WHERE nhs_reg_no =?");


            exist.setInt(1, patient.getNhsRegistrationNo());
            ResultSet rs = exist.executeQuery();
            while (rs.next()) {
                pt.patientExistMsg();
                //pt.presentationTier();
                ap.sendToHospitalAndAmbulance(patient);
            }


        } catch (Exception e) {

            System.out.println(e);
        }

    }

    //extracts patient's data from database

    public void extractPatient(Patient patient) throws Exception {

        PresentationTier pt = new PresentationTier();
        Statement stmt;
        ResultSet rs;
        String id;
        try {
            Connection conn = getConnection();


            stmt = conn.createStatement();     // Create a Statement object           1
            rs = stmt.executeQuery("SELECT * FROM patient WHERE nhs_reg_no=" + patient.getNhsRegistrationNo());

            // Get the result table from the query
            while (rs.next()) {               // Position the cursor
                //            3
                id = rs.getString(1);
                patient.setName(rs.getString(2));
                patient.setSurname(rs.getString(3));
                patient.setAddress(rs.getString(5));
                patient.setMedicalCondition(rs.getString(6));
                patient.setNhsRegistrationNo(rs.getInt(4));
                // Retrieve only the first column value

                pt.presentationTier();

            }

        } catch (Exception e) {

            System.out.println(e);
        }

    }


}

