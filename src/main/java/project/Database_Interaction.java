package project;

import java.math.BigInteger;
import java.sql.DriverManager;
import java.sql.*;
import java.util.ArrayList;

public class Database_Interaction {
    private static int id;
    public Connection database_connectin(String user, String password){
        Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Date_EmergencyNotificationSystem_users",user,password);
            if (conn != null){
                System.out.println("Connection Established");
            }else {
                System.out.println("Connection Failed");
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return conn;
    }

    public boolean search_by_number(Connection conn, String table_name, String phone_number){
        ResultSet rs = null;
        Statement statement = null;
        boolean finder_user = false;
        String query = String.format("SELECT * FROM %s WHERE phone_number = %s",table_name,phone_number);
        try{
            statement = conn.createStatement();
            rs = statement.executeQuery(query);
                if (rs.next()) {
                    finder_user = true;
                }
        }catch (Exception e){
            e.printStackTrace();
        }
        return finder_user;
    }

    public String return_id(Connection conn,String table_name, String phone_number){
        String user_id = null;
        ResultSet rs = null;
        Statement statement = null;
        String query = String.format("SELECT id FROM %s WHERE phone_number = %s",table_name,phone_number);
        try{
            statement = conn.createStatement();
            rs = statement.executeQuery(query);
            while(rs.next()) {
                user_id = rs.getString("id");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return user_id;
    }

    public void insert_user(Connection conn, String table_name, String name_user, String last_name_user, String phone_number,String password){
               id += 1;
               Statement statement;
               String query = String.format("INSERT INTO %s(id,first_name,last_name,phone_number,password) " +
                       "VALUES ('%d','%s','%s','%s','%s');",table_name,id,name_user,last_name_user,phone_number,password);
               try{
                   statement = conn.createStatement();
                   statement.executeUpdate(query);
               }catch (Exception e){
                   e.printStackTrace();
               }
    }

    public void insert_text_to_send(Connection conn,String table_name, String id, String text_to_send, String numbers_pool){
        Statement statement;
        String query = String.format("INSERT INTO %s(user_id,message_output,pool_numbers) VALUES ('%s','%s','%s');", table_name,id,text_to_send,numbers_pool);
        try{
            statement = conn.createStatement();
            statement.executeUpdate(query);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public String return_text_to_send(Connection conn, String table_name,String id){
        Statement statement;
        String text_to_send = null;
        ResultSet rs = null;
        String query = String.format("SELECT message_output FROM %s WHERE user_id = %s;", table_name,id);
        try{
           statement = conn.createStatement();
           rs = statement.executeQuery(query);
           while(rs.next()){
               text_to_send = rs.getString("message_output");
           }
        }catch (Exception e){
            e.printStackTrace();
        }
        return text_to_send;
    }

    public String return_pool_numbers(Connection conn,String table_name, String id){
        String numbers = null;
        Statement statement;
        ResultSet rs = null;
        String query = String.format("SELECT pool_numbers FROM %s WHERE user_id = %s;",table_name,id);
        try{
            statement =conn.createStatement();
            rs = statement.executeQuery(query);
            while(rs.next()){
              numbers = rs.getString("pool_numbers");
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return  numbers;
    }

    public void update_text_message(Connection conn, String table_name,String message_update, String id){
        Statement statement;
        ResultSet rs = null;
        String query = String.format("UPDATE %s SET message_output = '%s' WHERE user_id = '%s';",table_name,message_update,id);
        try{
            statement = conn.createStatement();
            statement.executeUpdate(query);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
