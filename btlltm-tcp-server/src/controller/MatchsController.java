


package controller;

/**
 *
 * @author Ngọ Văn Trọng
 */


import model.Matchs;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import connection.DatabaseConnection;
public class MatchsController {
    private final Connection con;

   

    public MatchsController() {
         this.con = DatabaseConnection.getInstance().getConnection();
    }

    // Thêm một trận đấu mới vào cơ sở dữ liệu
    public boolean addMatch(Matchs match) throws SQLException {
        String sql = "INSERT INTO matchs (id_match, user1, user2, user_win, score_win, time_begin, score_lose) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, match.getId_match());
            stmt.setString(2, match.getUser1());
            stmt.setString(3, match.getUser2());
            stmt.setString(4, match.getUser_win());
            stmt.setFloat(5, match.getScore_win());
            stmt.setTimestamp(6, Timestamp.valueOf(match.getTime_begin()));
            stmt.setFloat(7, match.getScore_lose());

            return stmt.executeUpdate() > 0;
        }
    }

    // Cập nhật thông tin một trận đấu
    public boolean updateMatch(Matchs match) throws SQLException {
        String sql = "UPDATE matchs SET user1 = ?, user2 = ?, user_win = ?, score_win = ?, time_begin = ?, score_lose = ? WHERE id_match = ?";
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, match.getUser1());
            stmt.setString(2, match.getUser2());
            stmt.setString(3, match.getUser_win());
            stmt.setFloat(4, match.getScore_win());
            stmt.setTimestamp(5, Timestamp.valueOf(match.getTime_begin()));
            stmt.setFloat(6, match.getScore_lose());
            stmt.setString(7, match.getId_match());

            return stmt.executeUpdate() > 0;
        }
    }

    // Xóa một trận đấu theo ID
    public boolean deleteMatch(String id_match) throws SQLException {
        String sql = "DELETE FROM matchs WHERE id_match = ?";
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, id_match);
            return stmt.executeUpdate() > 0;
        }
    }

    // Lấy lịch sử trận đấu theo tên người dùng
    public List<Matchs> getMatchHistoryByUser(String username) throws SQLException {
        List<Matchs> matches = new ArrayList<>();
        String sql = "SELECT * FROM matchs WHERE user1 = ? OR user2 = ? ORDER BY time_begin DESC";
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, username);
            stmt.setString(2, username);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Matchs match = new Matchs(
                        rs.getString("id_match"),
                        rs.getString("user1"),
                        rs.getString("user2"),
                        rs.getString("user_win"),
                        rs.getFloat("score_win"),
                        rs.getTimestamp("time_begin").toLocalDateTime(),
                        rs.getFloat("score_lose")
                    );
                    matches.add(match);
                }
            }
        }
        return matches;
    }

   

    
}

