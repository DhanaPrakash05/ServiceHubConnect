package Model;
//import Controller.Service;
import Resources.DbConnection;
import java.sql.*;
import Model.Classes.*;
public class ServiceDao {
    Connection con= DbConnection.getConnection();
    public boolean insertService(Service service) {
        String query = "INSERT INTO service (request_id, amount, isCompleted, reviews, isPaid) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = con.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setInt(1, service.getRequestId());
            preparedStatement.setInt(2, service.getAmount());
            preparedStatement.setBoolean(3, service.isCompleted());
            preparedStatement.setString(4, null);
            preparedStatement.setBoolean(5, false);
            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        service.setServiceId(generatedKeys.getInt(1));
                        return true;
                    }
                }
            }
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean addReview(String review, String userId) {
        String query = "UPDATE service SET reviews = ? WHERE request_id IN (SELECT request_id FROM currentrequests WHERE user_id = ?)";

        try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
            preparedStatement.setString(1, review);
            preparedStatement.setString(2, userId);

            int affectedRows = preparedStatement.executeUpdate();

            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean setCompletionStatus(String userId) {
        String query = "UPDATE service SET isCompleted = true WHERE request_id IN (SELECT request_id FROM currentrequests WHERE user_id = ?)";
        try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
            preparedStatement.setString(1, userId);

            int affectedRows = preparedStatement.executeUpdate();

            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public int getAmount(String userId) {
        String query = "SELECT amount FROM service WHERE request_id IN (SELECT request_id FROM currentrequests WHERE user_id = ?)";

        try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
            preparedStatement.setString(1, userId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt("amount");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public boolean updateIsPaid(String userId) {
        String query = "UPDATE service SET isPaid = true WHERE request_id IN (SELECT request_id FROM currentrequests WHERE user_id = ?)";
        try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
            preparedStatement.setString(1, userId);

            int affectedRows = preparedStatement.executeUpdate();

            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}
