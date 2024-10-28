/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import connection.DatabaseConnection;
import model.UserModel;
/**
 *
 * @author Ngọ Văn Trọng
 */
public class UserController {
    //  SQL
    private final String INSERT_USER = "INSERT INTO users (username, password, score, win, draw, lose, avgCompetitor, avgTime) VALUES (?, ?, 0, 0, 0, 0, 0, 0)";
    
    private final String CHECK_USER = "SELECT userId from users WHERE username = ? limit 1";
    
    private final String LOGIN_USER = "SELECT username, password, score FROM users WHERE username=? AND password=?";
    
    private final String GET_INFO_USER = "SELECT username, password, score, win, draw, lose, avgCompetitor, avgTime FROM users WHERE username=?";
    
    private final String UPDATE_USER = "UPDATE users SET score = ?, win = ?, draw = ?, lose = ?, avgCompetitor = ?, avgTime = ? WHERE username=?";
    
    private final String GET_RANKING = "SELECT username, score, win, lose, avgCompetitor, avgTime FROM users ORDER BY score DESC, win DESC, lose ASC";


    //  Instance
    private final Connection con;
    
    public UserController() {
        this.con = DatabaseConnection.getInstance().getConnection();
    }

    public String register(String username, String password) {
    	//  Check user exit
        try {
            PreparedStatement p = con.prepareStatement(CHECK_USER);
            p.setString(1, username);
            ResultSet r = p.executeQuery();
            if (r.first()) {
                return "failed;" + "Người dùng đã tồn tại";
            } else {
                r.close();
                p.close();
                p = con.prepareStatement(INSERT_USER);
                p.setString(1, username);
                p.setString(2, password);
                p.executeUpdate();
                p.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "success;";
    }
  
    public String login(String username, String password) {
    	//  Check user exit
        try {
            PreparedStatement p = con.prepareStatement(LOGIN_USER);
            //  Login User 
            p.setString(1, username);
            p.setString(2, password);
            ResultSet r = p.executeQuery();
            
            if (r.first()) {
                float score = r.getFloat("score");
                int win = r.getInt("win");
                int draw = r.getInt("win");
                int lose = r.getInt("lose");
                float timewin = r.getFloat("avgTime");
                return "success;" + username + ";" + score + ";" + win + ";" + draw + ";" + lose ;
            } else {
                return "failed;" + "Mày hãy điền đúng tài khoản và mật khẩu";
            }
        } catch (SQLException e) {
        }
        return null;
    }
    
    public String getInfoUser(String username) {
    UserModel user = new UserModel();
    try {
        PreparedStatement p = con.prepareStatement(GET_INFO_USER);
        p.setString(1, username);
        
        ResultSet r = p.executeQuery();
        while (r.next()) {
            user.setUserName(r.getString("username"));
            user.setScore(r.getFloat("score"));
            user.setWin(r.getInt("win"));
            user.setDraw(r.getInt("draw"));
            user.setLose(r.getInt("lose"));
            user.setAvgCompetitor(r.getFloat("avgCompetitor"));
            user.setAvgTime(r.getFloat("avgTime"));
        }

        // Sử dụng String.format để định dạng số với 2 chữ số sau dấu phẩy
        return "success;" + user.getUserName() + ";" + 
               String.format("%.2f", user.getScore()) + ";" + 
               user.getWin() + ";" + 
               user.getDraw() + ";" + 
               user.getLose() + ";" + 
               String.format("%.2f", user.getAvgCompetitor()) + ";" + 
               String.format("%.2f", user.getAvgTime());
    } catch (SQLException e) {
        e.printStackTrace();
    }   
    return null;
}

    
    public boolean updateUser(UserModel user) throws SQLException {
        boolean rowUpdated;
        PreparedStatement p = con.prepareStatement(UPDATE_USER);
        //  Login User 
        p.setFloat(1, user.getScore());
        p.setInt(2, user.getWin());
        p.setInt(3, user.getDraw());
        p.setInt(4, user.getLose());
        p.setFloat(5, user.getAvgCompetitor());
        p.setFloat(6, user.getAvgTime());
        p.setString(7, user.getUserName());

//            ResultSet r = p.executeQuery();
        rowUpdated = p.executeUpdate() > 0;
        return rowUpdated;
    }

    public UserModel getUser(String username) {
        UserModel user = new UserModel();
        try {
            PreparedStatement p = con.prepareStatement(GET_INFO_USER);
            p.setString(1, username);
            
            ResultSet r = p.executeQuery();
            while(r.next()) {
                user.setUserName(r.getString("username"));
                user.setScore(r.getFloat("score"));
                user.setWin(r.getInt("win"));
                user.setDraw(r.getInt("draw"));
                user.setLose(r.getInt("lose"));
                user.setAvgCompetitor(r.getFloat("avgCompetitor"));
                user.setAvgTime(r.getFloat("avgTime"));
            }
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
        }   
        return null;
    }
      
      public String getRanking() {
    StringBuilder ranking = new StringBuilder();
    try {
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(GET_RANKING);

        ranking.append("Rank\tUsername\tScore\tWin\tLose\n");
        int rank = 1;
        while (rs.next()) {
            String username = rs.getString("username");
            float score = rs.getFloat("score");
            int win = rs.getInt("win");
            int lose = rs.getInt("lose");

            ranking.append(rank).append("\t")
                   .append(username).append("\t")
                   .append(score).append("\t")
                   .append(win).append("\t")
                   .append(lose).append("\n");
            rank++;
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }
    return "success;" + ranking.toString();
}

        
   
    public static void main(String[] args) {
        // Tạo một đối tượng UserController
        UserController userController = new UserController();

        // Gọi hàm getRanking() để lấy danh sách xếp hạng
        String ranking = userController.getRanking();

        // In ra kết quả của bảng xếp hạng
        System.out.println("===== BẢNG XẾP HẠNG NGƯỜI CHƠI =====");
        System.out.println(ranking);
    }


}
