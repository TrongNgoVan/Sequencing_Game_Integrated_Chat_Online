


package controller;

/**
 *
 * @author Ngọ Văn Trọng
 */


import model.Matchs;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import DAO.DBConnect;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.Comparator;
public class MatchsController {
    private final Connection con;

   

    public MatchsController() {
         this.con = DBConnect.getInstance().getConnection();
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
public List<String> getHistory(String username) throws SQLException {
    List<String> matches = new ArrayList<>();
    String sql = "SELECT * FROM matchs WHERE user1 = ? OR user2 = ? ORDER BY time_begin DESC";
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"); // Định dạng thời gian

    try (PreparedStatement stmt = con.prepareStatement(sql)) {
        stmt.setString(1, username);
        stmt.setString(2, username);
        try (ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                // Chuyển đổi dữ liệu của mỗi trận đấu thành một chuỗi
                String match = String.format(
                    "%s/ %s/ %s/ %s/ %.1f/ %s/ %.1f",
                    rs.getString("id_match"),
                    rs.getString("user1"),
                    rs.getString("user2"),
                    rs.getString("user_win"),
                    rs.getFloat("score_win"),
                    rs.getTimestamp("time_begin").toLocalDateTime().format(formatter), // Định dạng thời gian
                    rs.getFloat("score_lose")
                );
                matches.add(match);
            }
        }
    }

    // Sắp xếp danh sách theo thứ tự ngày tháng
    Collections.sort(matches, new Comparator<String>() {
        @Override
        public int compare(String match1, String match2) {
            // Tách chuỗi để lấy phần thời gian (phần thứ 6 trong chuỗi)
            String time1 = match1.split("/ ")[5]; // Phần thời gian của match1
            String time2 = match2.split("/ ")[5]; // Phần thời gian của match2

            // Chuyển đổi chuỗi thành LocalDateTime
            LocalDateTime dateTime1 = LocalDateTime.parse(time1, formatter);
            LocalDateTime dateTime2 = LocalDateTime.parse(time2, formatter);

            // So sánh ngày tháng
            return dateTime1.compareTo(dateTime2);
        }
    });

    return matches;
}


   

    
}

