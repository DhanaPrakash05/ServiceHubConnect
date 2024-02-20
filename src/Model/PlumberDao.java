package Model;

//import Controller.Plumber;
//import Controller.ServiceProvider;
import Resources.DbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalTime;
import Model.Classes.*;
public class PlumberDao {

    Connection con= DbConnection.getConnection();
    public String loginPlumber(String email,String password){
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
                LocalTime endWorkingHour = resultSet.getTime("endWorkingHour").toLocalTime();;
                String role = resultSet.getString("role");
                int rating = resultSet.getInt("rating");
                String plumbingService_id=resultSet.getString("plumbingService_id");
                String query="select * from plumbingservices where plumbingService_id=?";
                try (PreparedStatement plumbingServiceStmt = con.prepareStatement(query)) {
                    plumbingServiceStmt.setString(1, plumbingService_id);
                    ResultSet plumbingServiceResultSet = plumbingServiceStmt.executeQuery();
                    if (plumbingServiceResultSet.next()) {
                        boolean leakRepair = plumbingServiceResultSet.getBoolean("leakRepair");
                        boolean installation = plumbingServiceResultSet.getBoolean("installation");
                        boolean drainCleaning = plumbingServiceResultSet.getBoolean("drainCleaning");
                        boolean overallService = plumbingServiceResultSet.getBoolean("overallService");
                        ServiceProvider plumber = Plumber.createPlumber(
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
                                leakRepair,
                                installation,
                                drainCleaning,
                                overallService
                        );
                        plumber.setServiceProviderId(serviceProviderId);
                        return "successfully logged in";
                    }
                    else{
                        return "no result for plumber";
                    }


                }
                catch (SQLException e){
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
