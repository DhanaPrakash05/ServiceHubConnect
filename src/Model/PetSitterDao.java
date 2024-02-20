package Model;

//import Controller.PetSitter;
import Resources.DbConnection;
import Model.Classes.PetSitter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalTime;

public class PetSitterDao {
    Connection con= DbConnection.getConnection();
    public String loginPetSitter(String email, String password) {
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
                String petSittingService_id = resultSet.getString("petSittingService_id");
                String query = "SELECT * FROM petsittingservices WHERE petSittingService_id=?";
                try (PreparedStatement petSitterStmt = con.prepareStatement(query)) {
                    petSitterStmt.setString(1, petSittingService_id);
                    ResultSet petSitterServiceResultSet = petSitterStmt.executeQuery();
                    if (petSitterServiceResultSet.next()) {
                        boolean dogService = petSitterServiceResultSet.getBoolean("dogService");
                        boolean catService = petSitterServiceResultSet.getBoolean("catService");
                        boolean birdService = petSitterServiceResultSet.getBoolean("birdService");
                        boolean cattleService = petSitterServiceResultSet.getBoolean("cattleService");
                        PetSitter petSitter = PetSitter.createPetSitter(
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
                                dogService,
                                catService,
                                birdService,
                                cattleService
                        );
                        petSitter.setServiceProviderId(serviceProviderId);
                        return "successfully logged in";
                    } else {
                        return "No result for pet sitter";
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
