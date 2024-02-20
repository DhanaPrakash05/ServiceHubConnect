package Model.Classes;

import Resources.DbConnection;

import java.sql.*;
import java.time.LocalTime;

public class ServiceProviderDao {
    Connection con=DbConnection.getConnection();

    public String checkExistingEmailAndMobile(String email, String mobileNo) {
        try {
            String checkEmailQuery = "SELECT COUNT(*) FROM serviceproviders WHERE email = ?";
            try (PreparedStatement checkEmailStmt = con.prepareStatement(checkEmailQuery)) {
                checkEmailStmt.setString(1, email);
                ResultSet emailResult = checkEmailStmt.executeQuery();
                emailResult.next();
                int emailCount = emailResult.getInt(1);
                String checkMobileQuery = "SELECT COUNT(*) FROM serviceproviders WHERE mobileNo = ?";
                try (PreparedStatement checkMobileStmt = con.prepareStatement(checkMobileQuery)) {
                    checkMobileStmt.setString(1, mobileNo);
                    ResultSet mobileResult = checkMobileStmt.executeQuery();
                    mobileResult.next();
                    int mobileCount = mobileResult.getInt(1);
                    if (emailCount > 0) {
                        return "Email already exists";
                    } else if (mobileCount > 0) {
                        return "Mobile number already exists";
                    } else {
                        return "Success";
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return "Database error";
        }
    }

    public String signup(ServiceProvider serviceProvider) {
        String username = serviceProvider.getName();
        String email = serviceProvider.getEmail();
        String password = serviceProvider.getPassword();
        String mobileNo = serviceProvider.getMobileNo();
        String address = serviceProvider.getAddress();
        boolean isAvailable = serviceProvider.getIsAvailable();
        LocalTime startWorkingTime = serviceProvider.getStartWorkingHour();
        LocalTime endWorkingTime = serviceProvider.getEndWorkingHour();
        String role = serviceProvider.getRole();
        int rating = serviceProvider.getRating();
        String foreignKey = "";
        if (role.equals("Plumber")) {
            boolean leakRepair = serviceProvider.getLeakRepair();
            boolean installation = serviceProvider.getInstallation();
            boolean drainCleaning = serviceProvider.getDrainCleaning();
            boolean overallService = serviceProvider.getOverAllService();
            try {
                String query = "INSERT INTO plumbingservices (leakRepair, installation, drainCleaning, overallService) VALUES (?, ?, ?, ?)";
                try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
                    preparedStatement.setBoolean(1, leakRepair);
                    preparedStatement.setBoolean(2, installation);
                    preparedStatement.setBoolean(3, drainCleaning);
                    preparedStatement.setBoolean(4, overallService);

                    int affectedRows = preparedStatement.executeUpdate();

                    if (affectedRows > 0) {
                        query = "SELECT plumbingService_id FROM plumbingservices ORDER BY plumbingService_id DESC LIMIT 1";
                        try (PreparedStatement stmt = con.prepareStatement(query)) {
                            try (ResultSet rslt = stmt.executeQuery()) {

                                if (rslt.next()) {
                                    foreignKey = rslt.getString("plumbingService_id");
                                } else {

                                    System.out.println("No plumbingService_id found.");
                                }
                            }
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    } else {
                        System.out.println("Error Creating Record");
                        System.exit(1);
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();

            }

        } else if (role.equals("Electrician")) {
            boolean wiringInstallation = serviceProvider.getWiringInstallation();
            boolean communication = serviceProvider.getCommunication();
            boolean generatorInstallation = serviceProvider.getGeneratorInstallation();
            boolean electronicalRepairs = serviceProvider.getElectronicalRepairs();
            boolean lightingInstallation = serviceProvider.getLightingInstallation();

            try {
                String query = "INSERT INTO electricianservices (wiringInstallation, communication, generatorInstallation, electronicalRepairs, lightingInstallation) VALUES (?, ?, ?, ?, ?)";
                try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
                    preparedStatement.setBoolean(1, wiringInstallation);
                    preparedStatement.setBoolean(2, communication);
                    preparedStatement.setBoolean(3, generatorInstallation);
                    preparedStatement.setBoolean(4, electronicalRepairs);
                    preparedStatement.setBoolean(5, lightingInstallation);

                    int affectedRows = preparedStatement.executeUpdate();

                    if (affectedRows > 0) {
                        query = "SELECT electricianService_id FROM electricianservices ORDER BY electricianService_id DESC LIMIT 1";
                        try (PreparedStatement stmt = con.prepareStatement(query)) {
                            try (ResultSet rslt = stmt.executeQuery()) {
                                if (rslt.next()) {
                                    foreignKey = rslt.getString("electricianService_id");
                                } else {
                                    System.out.println("No electricianService_id found.");
                                }
                            }
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    } else {
                        System.out.println("Error Creating Record");
                        System.exit(1);
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else if (role.equals("PetSitter")) {
            boolean dogService = serviceProvider.getDogService();
            boolean catService = serviceProvider.getCatService();
            boolean birdService = serviceProvider.getBirdsService();
            boolean cattleService = serviceProvider.getCattleService();

            try {
                String query = "INSERT INTO petsittingservices (dogService, catService, birdService, cattleService) VALUES (?, ?, ?, ?)";
                try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
                    preparedStatement.setBoolean(1, dogService);
                    preparedStatement.setBoolean(2, catService);
                    preparedStatement.setBoolean(3, birdService);
                    preparedStatement.setBoolean(4, cattleService);

                    int affectedRows = preparedStatement.executeUpdate();

                    if (affectedRows > 0) {
                        query = "SELECT petSittingService_id FROM petsittingservices ORDER BY petSittingService_id DESC LIMIT 1";
                        try (PreparedStatement stmt = con.prepareStatement(query)) {
                            try (ResultSet rslt = stmt.executeQuery()) {
                                if (rslt.next()) {
                                    foreignKey = rslt.getString("petSittingService_id");
                                } else {
                                    System.out.println("No petSittingService_id found.");
                                }
                            }
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    } else {
                        System.out.println("Error Creating Record");
                        System.exit(1);
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        String query;

        if (role.equals("Plumber")) {
            query = "INSERT INTO serviceproviders (name, email, password, mobileNo, address, isAvailable, startWorkingHour, endWorkingHour, role, rating, plumbingService_id, electricianService_id, petSittingService_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, NULL, NULL)";
        } else if (role.equals("Electrician")) {
            query = "INSERT INTO serviceproviders (name, email, password, mobileNo, address, isAvailable, startWorkingHour, endWorkingHour, role, rating, plumbingService_id, electricianService_id, petSittingService_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, NULL, ?, NULL)";
        } else {
            query = "INSERT INTO serviceproviders (name, email, password, mobileNo, address, isAvailable, startWorkingHour, endWorkingHour, role, rating, plumbingService_id, electricianService_id, petSittingService_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, NULL, NULL, ?)";
        }

        try (PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setString(1, username);
            stmt.setString(2, email);
            stmt.setString(3, password);
            stmt.setString(4, mobileNo);
            stmt.setString(5, address);
            stmt.setBoolean(6, isAvailable);
            stmt.setTime(7, Time.valueOf(startWorkingTime));
            stmt.setTime(8, Time.valueOf(endWorkingTime));
            stmt.setString(9, role);
            stmt.setInt(10, rating);
            stmt.setString(11, foreignKey);
            int affectedRows = stmt.executeUpdate();

            if (affectedRows > 0) {
                String idQuery = "SELECT serviceProvider_id FROM serviceproviders ORDER BY serviceProvider_id DESC LIMIT 1";

                try (PreparedStatement idStmt = con.prepareStatement(idQuery)) {
                    ResultSet idResultSet = idStmt.executeQuery();

                    if (idResultSet.next()) {
                        String serviceProviderId = idResultSet.getString("serviceProvider_id");
                        serviceProvider.setServiceProviderId(serviceProviderId);
                        return "successfully signed up"+serviceProvider.getServiceProviderId();
                    } else {
                        System.out.println("No serviceProvider_id found.");
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            } else {
                return "sorry...something went wrong . Please try again";
            }
        }
        catch (SQLException e){
            e.printStackTrace();
    }
        return "";
    }

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

    public ResultSet findIfWorkexists(String serviceProviderId){

        String query="select * from currentrequests where status=false and serviceProviders like ?";

        try{
            PreparedStatement stmt=con.prepareStatement(query);
            stmt.setString(1,"%" + serviceProviderId + "%");
            ResultSet rslt=stmt.executeQuery();
            return rslt;
        }
        catch (Exception e){
            e.printStackTrace();

        }
        return null;
    }

    public ResultSet getAvailableServiceProviders(String column, String tableName, String serviceProvidersId) {
        String query = "SELECT * FROM serviceproviders WHERE isAvailable = true AND " +
                serviceProvidersId + " IN (SELECT " + serviceProvidersId +
                " FROM " + tableName + " WHERE " + column + " = true)";

        ResultSet rslt = null;

        try {
            Statement statement = con.createStatement();
            rslt = statement.executeQuery(query);

            if (rslt!=null) {
                return rslt;
            } else {
                System.out.println("No records");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("No");
        return rslt;
    }

    public String getMobileNo(String userId) {
        String query = "SELECT mobileNo FROM serviceproviders WHERE serviceProvider_id IN (SELECT serviceProviders FROM currentrequests WHERE user_id = ?)";

        try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
            preparedStatement.setString(1, userId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getString("mobileNo");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean updateRating(int rating, String userId) {
        String selectQuery = "SELECT rating FROM serviceproviders WHERE serviceProvider_id IN (SELECT serviceProviders FROM currentrequests WHERE user_id = ?)";
        String updateQuery = "UPDATE serviceproviders SET rating = ? WHERE serviceProvider_id IN (SELECT serviceProviders FROM currentrequests WHERE user_id = ?)";
        try (PreparedStatement selectStatement = con.prepareStatement(selectQuery);
             PreparedStatement updateStatement = con.prepareStatement(updateQuery)) {
            selectStatement.setString(1, userId);
            try (ResultSet resultSet = selectStatement.executeQuery()) {
                if (resultSet.next()) {
                    int currentRating = resultSet.getInt("rating");
                    rating = (currentRating + rating) / 2;
                }
            }
            updateStatement.setInt(1, rating);
            updateStatement.setString(2, userId);
            int affectedRows = updateStatement.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}

