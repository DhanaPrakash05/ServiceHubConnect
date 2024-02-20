package Model;

//import Controller.Request;
import Resources.DbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import Model.Classes.*;
public class RequestDao {
    Connection con= DbConnection.getConnection();
    private Map<Long, Thread> deletionThreads = new HashMap<>();
    public String createRequest(Request rqst) {
        String query = "INSERT INTO currentRequests(user_id, service, serviceProviders,status) VALUES (?, ?, ?, ?)";
        String userId = rqst.getUserId();
        String service = rqst.getService();
        String serviceProviders = rqst.getServiceProviders();
        boolean status=rqst.getStatus();

        try (PreparedStatement preparedStatement = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, userId);
            preparedStatement.setString(2, service);
            preparedStatement.setString(3, serviceProviders);
            preparedStatement.setBoolean(4, status);


            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows > 0) {
                try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        long requestId = generatedKeys.getLong(1);
                        startDeletion(requestId);
                    }
                }

                return "Request created successfully";
            } else {
                return "Failed to create request";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Unknown error occurred while creating the request";
    }

    void deleteRequest(Long requestId){
        String deleteQuery = "DELETE FROM currentRequests WHERE request_id = ? limit 1";
        try (PreparedStatement deleteStatement = con.prepareStatement(deleteQuery)) {
            deleteStatement.setLong(1, requestId);
            int affectedRows = deleteStatement.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("Request record deleted ");
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    private void startDeletion(long requestId) {
        Thread deletionThread = new Thread(() -> {
            try {
                Thread.sleep(20 * 60 * 1000);
                if (!Thread.interrupted()) {
                    deleteRequest(requestId);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        deletionThreads.put(requestId, deletionThread);
        deletionThread.start();
    }

    public void stopDeletionThread(long requestId) {
        Thread deletionThread = deletionThreads.get(requestId);
        if (deletionThread != null && deletionThread.isAlive()) {
            deletionThread.interrupt();
        }
    }

    public boolean updateRequestStatus(int requestId) {
        String query = "UPDATE currentRequests SET status = true WHERE request_id = ?";

        try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
            preparedStatement.setInt(1, requestId);
            int affectedRows = preparedStatement.executeUpdate();

            return affectedRows > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean updateRequestServices(int requestId, String serviceProviderId) {
        String query = "UPDATE currentrequests SET serviceProviders = ? WHERE request_id = ?";

        try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
            preparedStatement.setString(1, serviceProviderId);
            preparedStatement.setInt(2, requestId);

            int affectedRows = preparedStatement.executeUpdate();

            return affectedRows > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean doesUserExist(String userId) {
        String query = "SELECT * FROM service WHERE isCompleted = false AND request_id IN (SELECT request_id FROM currentrequests WHERE status = true AND user_id = ?)";

        try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
            preparedStatement.setString(1, userId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return resultSet.next();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }








}
