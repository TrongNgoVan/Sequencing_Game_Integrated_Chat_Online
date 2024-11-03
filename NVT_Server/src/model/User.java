package model;

import java.io.Serializable;
/**
 *
 * @author Ngọ Văn Trọng
 */
public class User implements Serializable {
    private static final long serialVersionUID = 1L;
	
    private String userName;
    private String password;
    private String status;
    private float score;
    private int win;
    private int draw;
    private int lose;
  
    private float avgTime;
    
    public User() { }
    
    public User(String userName, String password, String status) {
    	super();
        this.userName = userName;
        this.password = password;
        this.status = status;
    }
    
    public User(String userName, String password, float score) {
    	super();
        this.userName = userName;
        this.password = password;
        this.score = score;
        
    }
    
    public User(String userName, String password, String status, float score) {
    	super();
        this.userName = userName;
        this.password = password;
        this.status = status;
        this.score = score;
    }
     public User(String userName, String password, float score, int win, int draw, int lose, float avgTime) {
    	super();
        this.userName = userName;
        this.password = password;
       
        this.score = score;
        this.win = win;
        this.draw = draw;
        this.lose = lose;
        this.avgTime = avgTime;
    }
    
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getStatus() {
        return status;
    }

    public void setStatus(String password) {
        this.status = password;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    public int getWin() {
        return win;
    }

    public void setWin(int win) {
        this.win = win;
    }

    public int getDraw() {
        return draw;
    }

    public void setDraw(int draw) {
        this.draw = draw;
    }

    public int getLose() {
        return lose;
    }

    public void setLose(int lose) {
        this.lose = lose;
    }


    public float getAvgTime() {
        return avgTime;
    }

    public void setAvgTime(float avgTime) {
        this.avgTime = avgTime;
    }

    
}
