package bus;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class BusServer {

    public static void main(String[] args) {
        ServerSocket ss = null;

        try {
            ss = new ServerSocket(36129);
            System.out.println("서버 준비");
            BusGUI busGUI=new BusGUI(); //gui 실행

            while (true) {
                Socket socket = ss.accept();
                System.out.println(socket.getInetAddress() + "연결됨");
                new BusServerThread(socket, busGUI).start();
            }

        } catch (IOException ioe) {
            ioe.printStackTrace();
        } finally {
            try {
                if (ss != null) ss.close();
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }
    }
}