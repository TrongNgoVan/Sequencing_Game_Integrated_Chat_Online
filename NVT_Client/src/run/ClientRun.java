package run;

import controller.ClientController;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import view.ConnectServer;
import view.GameView;
import view.HomeView;
import view.InfoPlayerView;
import view.LoginView;
import view.MessageView;
import view.RegisterView;
import view.RankingView;
import view.HistoryView;
        
/**
 *
 * @author Ngọ Văn Trọng
 */
public class ClientRun {
    public enum SceneName {
        CONNECTSERVER,
        LOGIN,
        REGISTER,
        HOMEVIEW,
        INFOPLAYER,
        RANKINGVIEW,
        HISTORYVIEW,
        MESSAGEVIEW,
        GAMEVIEW
        
    }

    // scenes
    public static ConnectServer connectServer;
    public static LoginView loginView;
    public static RegisterView registerView;
    public static HomeView homeView;
    public static GameView gameView;
    public static InfoPlayerView infoPlayerView;
    public static MessageView messageView;
    public static RankingView rankingView;
    public static HistoryView historyView;

    // controller 
    public static ClientController socketHandler;
 


    public ClientRun() {
        socketHandler = new ClientController();
        initScene();
     
        openScene(SceneName.CONNECTSERVER);
    }

    public void initScene() {
        connectServer = new ConnectServer();
        loginView = new LoginView();
        registerView = new RegisterView();
        homeView = new HomeView();
        infoPlayerView = new InfoPlayerView();
        rankingView = new RankingView();
        messageView = new MessageView();
        gameView = new GameView();
        historyView = new HistoryView();
    }
    
    
    public static void openScene(SceneName sceneName) {
        if (null != sceneName) {
            switch (sceneName) {
                case CONNECTSERVER:
                    connectServer = new ConnectServer();
                    connectServer.setVisible(true);
                    break;
                case LOGIN:
                    loginView = new LoginView();
                    loginView.setVisible(true);
                    break;
                case REGISTER:
                    registerView = new RegisterView();
                    registerView.setVisible(true);
                    break;
                case HOMEVIEW:
                    homeView = new HomeView();
                    homeView.setVisible(true);
                    break;
                case INFOPLAYER:
                    infoPlayerView = new InfoPlayerView();
                    infoPlayerView.setVisible(true);
                    break;
                case RANKINGVIEW:
                    rankingView = new RankingView();
                    rankingView.setVisible(true);
                    break;
                case MESSAGEVIEW:
                    messageView = new MessageView();
                    messageView.setVisible(true);
                    break;
                case GAMEVIEW:
                    gameView = new GameView();
                    gameView.setVisible(true);
                    break;
                case HISTORYVIEW:
                    historyView = new HistoryView();
                    historyView.setVisible(true);
                    break;
                default:
                    break;
            }
        }
    }

    public static void closeScene(SceneName sceneName) {
        if (null != sceneName) {
            switch (sceneName) {
                case CONNECTSERVER:
                    connectServer.dispose();
                    break;
                case LOGIN:
                    loginView.dispose();
                    break;
                case REGISTER:
                    registerView.dispose();
                    break;
                case HOMEVIEW:
                    homeView.dispose();
                    break;
                case INFOPLAYER:
                    infoPlayerView.dispose();
                    break;
                case RANKINGVIEW:
                    rankingView.dispose();
                    break;
                case HISTORYVIEW:
                    historyView.dispose();
                    break;
                case MESSAGEVIEW:
                    messageView.dispose();
                    break;
                case GAMEVIEW:
                    gameView.dispose();
                    break;
                
                default:
                    break;
            }
        }
    }

    public static void closeAllScene() {
        connectServer.dispose();
        loginView.dispose();
        registerView.dispose();
        homeView.dispose();
        infoPlayerView.dispose();
        messageView.dispose();
        rankingView.dispose();
        historyView.dispose();
        gameView.dispose();
       
    }

    public static void main(String[] args) {
        new ClientRun();
        System.out.println("Client is oke");
    }
}
