package Model;

//import Controller.Electrician;
import Resources.DbConnection;
import Model.Classes.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalTime;

public class ElectricianDao {
    Connection con= DbConnection.getConnection();
    public String loginElectrician(String email, String password) {
        String emailCheckQuery = "SELECT * FROM serviceproviders WHERE email = ?";
        try (PreparedStatement emailCheckStmt = con.prepareStatement(emailCheckQuery)) {
            emailCheckStmt.setString(1, email);
            ResultSet resultSet = emailCheckStmt.executeQuery();
            if (!resultSet.next()) {
                return "Email not found";
            }
            String storedPassword = resultSet.getString("password");
            if (storedPassword.equals(password)) {
                String serviceProviderId = resultSet.getString("serviceProvider_id");
                String name = resultSet.getString("name");
                String mobileNo = resultSet.getString("mobileNo");
                String address = resultSet.getString("address");
                boolean isAvailable = resultSet.getBoolean("isAvailable");
                LocalTime startWorkingHour = resultSet.getTime("startWorkingHour").toLocalTime();
                LocalTime endWorkingHour = resultSet.getTime("endWorkingHour").toLocalTime();
                String role = resultSet.getString("role");
                int rating = resultSet.getInt("rating");
                String electricianService_id = resultSet.getString("electricianService_id");
                String query = "SELECT * FROM electricianservices WHERE electricianService_id=?";
                try (PreparedStatement electricianStmt = con.prepareStatement(query)) {
                    electricianStmt.setString(1, electricianService_id);
                    ResultSet electricianServiceResultSet = electricianStmt.executeQuery();
                    if (electricianServiceResultSet.next()) {
                        boolean wiringInstallation = electricianServiceResultSet.getBoolean("wiringInstallation");
                        boolean communication = electricianServiceResultSet.getBoolean("communication");
                        boolean generatorInstallation = electricianServiceResultSet.getBoolean("generatorInstallation");
                        boolean electronicalRepairs = electricianServiceResultSet.getBoolean("electronicalRepairs");
                        boolean lightingInstallation = electricianServiceResultSet.getBoolean("lightingInstallation");
                        Electrician electrician = Electrician.createElectrician(
                                name,
                                email,
                                password,
                                mobileNo,
                                address,
                                role,
                                isAvailable,
                                rating,
                                startWorkingHour.toString(),
                                endWorkingHour.toString(),
                                wiringInstallation,
                                communication,
                                generatorInstallation,
                                electronicalRepairs,
                                lightingInstallation
                        );
                        electrician.setServiceProviderId(serviceProviderId);
                        return "successfully logged in";
                    } else {
                        return "No result for electrician";
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } else {
                return "Incorrect password";
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return "Login failed due to a database error";
        }
        return "";

    }
}
