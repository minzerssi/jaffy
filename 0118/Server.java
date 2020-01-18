import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

/**
 * thread를 이용한 소켓통신 프로그램 만들기
 */

public class Server {

    // localhost
    private final String addr = "172.0.0.1";
    private static int threadNumber = 1;

    public static void main(String[] args) {
        ServerSocket serverSocket = null;
        try {
            // binding the port
            serverSocket = new ServerSocket(8000);
            // 연결 수신
            while (true) {
                Socket socket = serverSocket.accept();
                String clientAddr = socket.getRemoteSocketAddress().toString();

                System.out.println("[Server] " + clientAddr + " is access");

                new ServerThread(socket, threadNumber++).start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (serverSocket != null)
                    serverSocket.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

class ServerThread extends Thread {
    Socket socket;
    int threadNum;

    ServerThread() {
    }

    ServerThread(Socket socket, int num) {
        this.socket = socket;
        threadNum = num;
    }

    @Override
    public void run() {
        System.out.println("server "+ threadNum + " Start!! ");
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
            while (true) {
                String buffer = null;
                buffer = br.readLine();
                if (buffer == null) {
                    System.out.println("[Server " + threadNum + "] close");
                    break;
                }
                System.out.println("[client] :: " + buffer);
            }
            Thread.currentThread().interrupt();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}