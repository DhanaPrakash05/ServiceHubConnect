package Model;
import Resources.DbConnection;
import java.util.*;
import java.sql.*;
import Model.Classes.*;
public class UserDAO {
    Connection con=DbConnection.getConnection();
    private Map<Long, Thread> deletionThreads = new HashMap<>();
    static User currentUser;
    public String login(String email,String password) {
        String emailCheckQuery = "SELECT * FROM users WHERE email = ?";
        try (PreparedStatement emailCheckStmt = con.prepareStatement(emailCheckQuery)) {
            emailCheckStmt.setString(1, email);
            ResultSet resultSet = emailCheckStmt.executeQuery();
            if (!resultSet.next()) {
                return "Email not found";
            }
            String storedPassword = resultSet.getString("password");
            if (storedPassword.equals(password)) {
                String userId = resultSet.getString("user_id");
                String username = resultSet.getString("username");
                String mobileNo = resultSet.getString("mobileNo");
                String address = resultSet.getString("address");
                User user = User.createUser(username, email, password, mobileNo, address);
                user.setUserId(userId);
                currentUser=user;
                return "Login successful";

            } else {
                return "Incorrect password";
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return "Login failed due to a database error";
        }

     }

    public ArrayList<User> getAllUsers(){
        ArrayList<User> users=new ArrayList<>();
        String query="Select * from users";
        try{
            Statement stmt = con.createStatement();
            ResultSet resultSet = stmt.executeQuery(query);
            while (resultSet.next()) {
                String userId = resultSet.getString("user_id");
                String userName = resultSet.getString("username");
                String email=resultSet.getString("email");
                String password=resultSet.getString("password");
                String mobileNo=resultSet.getString("mobileNo");
                String address=resultSet.getString("address");
                User user=User.createUser(userName,email
                        ,password
                        ,mobileNo,address);
                user.setUserId(userId);
                users.add(user);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return users;
    }


    public String signup(User user){
        String username= user.getUsername();
        String email=user.getEmail();
        String password=user.getPassword();
        String mobileNo=user.getMobileNo();
        String address=user.getAddress();
         String insertQuery = "INSERT INTO users (username, email, password, mobileNo, address) VALUES (?, ?, ?, ?, ?)";
         try (PreparedStatement insertStmt = con.prepareStatement(insertQuery)) {
             insertStmt.setString(1, username);
             insertStmt.setString(2, email);
             insertStmt.setString(3, password);
             insertStmt.setString(4, mobileNo);
             insertStmt.setString(5, address);
             int rowsAffected = insertStmt.executeUpdate();
             if (rowsAffected > 0) {
                 String retrieveUserIdQuery = "SELECT user_id FROM users WHERE email = ?";
                 try (PreparedStatement retrieveUserIdStmt = con.prepareStatement(retrieveUserIdQuery)) {
                     retrieveUserIdStmt.setString(1, email);
                     ResultSet resultSet = retrieveUserIdStmt.executeQuery();
                     if (resultSet.next()) {
                         String userId = resultSet.getString("user_id");
                         user.setUserId(userId);
                         currentUser=user;
                         return user.getUserId();
                     } else {
                         return "Failed to retrieve user_id after user creation";
                     }
                 }
             } else {
                 return "Failed to create user";
             }
         } catch (SQLException e) {
             e.printStackTrace();
             return "Failed to create user due to a database error";
         }
     }


    public String checkExistingEmailAndMobile(String email, String mobileNo) {
        try {
            String checkEmailQuery = "SELECT COUNT(*) FROM users WHERE email = ?";
            try (PreparedStatement checkEmailStmt = con.prepareStatement(checkEmailQuery)) {
                checkEmailStmt.setString(1, email);
                ResultSet emailResult = checkEmailStmt.executeQuery();
                emailResult.next();
                int emailCount = emailResult.getInt(1);
                String checkMobileQuery = "SELECT COUNT(*) FROM users WHERE mobileNo = ?";
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

    public String getMobileNo(int reqId) {
        String mobileNo = null;
        String query = "SELECT mobileNo FROM users WHERE user_id IN (SELECT user_id FROM currentrequests WHERE request_id = ?)";
        try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
            preparedStatement.setInt(1, reqId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    mobileNo = resultSet.getString("mobileNo");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return mobileNo;
    }
    public String getAddress(int reqId) {
        String address = null;
        String query = "SELECT address FROM users WHERE user_id IN (SELECT user_id FROM currentrequests WHERE request_id = ?)";
        try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
            preparedStatement.setInt(1, reqId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    address  = resultSet.getString("address");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return address ;
    }




}


