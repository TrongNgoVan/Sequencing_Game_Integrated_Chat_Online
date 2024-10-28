package model;

import java.time.LocalDateTime;

public class Matchs {
    private String id_match;
    private String user1;
    private String user2;
    private String user_win;
    private float score_win;
    private LocalDateTime time_begin;
    private float score_lose;

    // Constructor
    public Matchs(String id_match, String user1, String user2, String user_win, float score_win, LocalDateTime time_begin, float score_lose) {
        this.id_match = id_match;
        this.user1 = user1;
        this.user2 = user2;
        this.user_win = user_win;
        this.score_win = score_win;
        this.time_begin = time_begin;
        this.score_lose = score_lose;
    }

    // Getters and Setters
    public String getId_match() {
        return id_match;
    }

    public void setId_match(String id_match) {
        this.id_match = id_match;
    }

    public String getUser1() {
        return user1;
    }

    public void setUser1(String user1) {
        this.user1 = user1;
    }

    public String getUser2() {
        return user2;
    }

    public void setUser2(String user2) {
        this.user2 = user2;
    }

    public String getUser_win() {
        return user_win;
    }

    public void setUser_win(String user_win) {
        this.user_win = user_win;
    }

    public float getScore_win() {
        return score_win;
    }

    public void setScore_win(float score_win) {
        this.score_win = score_win;
    }

    public LocalDateTime getTime_begin() {
        return time_begin;
    }

    public void setTime_begin(LocalDateTime time_begin) {
        this.time_begin = time_begin;
    }

    public float getScore_lose() {
        return score_lose;
    }

    public void setScore_lose(float score_lose) {
        this.score_lose = score_lose;
    }

    // ToString method for easy display
    @Override
    public String toString() {
        return "Matchs{" +
                "id_match='" + id_match + '\'' +
                ", user1='" + user1 + '\'' +
                ", user2='" + user2 + '\'' +
                ", user_win='" + user_win + '\'' +
                ", score_win=" + score_win +
                ", time_begin=" + time_begin +
                ", score_lose=" + score_lose +
                '}';
    }
}
