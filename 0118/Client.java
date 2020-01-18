import java.io.*;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.*;

public class Client {
    static Socket socket = null;

    public static void main(String[] args) throws Exception {
        socket = new Socket();
        Scanner sc = new Scanner(System.in);
        try {
            socket.connect(new InetSocketAddress("localhost", 8000));
            while (true) {
                String data = sc.nextLine();
                if ("exit".equals(data)) {
                    break;
                }
                PrintWriter pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));

                pw.write(data+"\n");
                pw.flush();

            }
        } catch (Exception e) {

        } finally {
            sc.close();
            try {
                if (socket != null && !socket.isClosed()) {
                    socket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}