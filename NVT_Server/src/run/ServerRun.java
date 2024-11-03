

package run;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import ControlServer.Client;
import ControlServer.ClientManager;
import ControlServer.RoomManager;
import view.ServerView;
/**
 *
 * @author Ngọ Văn Trọng
 */
public class ServerRun {

    public static volatile ClientManager clientManager;
    public static volatile RoomManager roomManager;
    public static boolean isShutDown = false;
    public static ServerSocket ss;
    private ServerView serverView;  // Tham chiếu tới ServerView

    public ServerRun(ServerView serverView) {  // Thêm tham chiếu ServerView trong constructor
        this.serverView = serverView;

        try {
            int port = 8889;
            ss = new ServerSocket(port);
            serverView.appendLog("Server đang mở cổng " + port + ".");  // Ghi log vào ServerView
            
            // init managers
            clientManager = new ClientManager();
            roomManager = new RoomManager();

            // create threadpool
            ThreadPoolExecutor executor = new ThreadPoolExecutor(
                    10, // corePoolSize
                    100, // maximumPoolSize
                    10, // thread timeout
                    TimeUnit.SECONDS,
                    new ArrayBlockingQueue<>(8) // queueCapacity
            );

            // server main loop - listen to client's connection
            while (!isShutDown) {
                try {
                    // socket object to receive incoming client requests
                    Socket s = ss.accept();
                    serverView.appendLog("+ Một Client mới đã kết nối: " + s);  // Ghi log kết nối mới

                    // create new client runnable object
                    Client c = new Client(s);
                    clientManager.add(c);
                    serverView.appendLog("Tổng số Client đang kết nối: " + clientManager.getSize());  // Ghi log số lượng client
                    // execute client runnable
                    executor.execute(c);

                } catch (IOException ex) {
                    isShutDown = true;
                }
            }

            serverView.appendLog("Đã đóng");
            executor.shutdownNow();

        } catch (IOException ex) {
            Logger.getLogger(ServerRun.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) {
        ServerView serverView = new ServerView();
        serverView.setVisible(true);
        serverView.setLocationRelativeTo(null);
        
        new ServerRun(serverView);  // Truyền ServerView vào ServerRun
    }
}
