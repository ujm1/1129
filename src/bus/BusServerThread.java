package bus;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

public class BusServerThread extends Thread {
    private Socket socket;
    private BusGUI busGUI;

    public BusServerThread(Socket socket, BusGUI busGUI) {
        this.socket = socket;
        this.busGUI = busGUI;
    }

    @Override
    public void run() {
        InputStreamReader isr = null;
        BufferedReader br = null;
        try {
            isr = new InputStreamReader(socket.getInputStream());
            br = new BufferedReader(isr);

            while (true) {
                String fromClientStr = br.readLine();
                if (fromClientStr != null) {
                    System.out.println(fromClientStr);
                    busGUI.display(fromClientStr);
                }

            }
        } catch (SocketException se) {
            System.out.println("연결 끊김");
        }
        catch (IOException ioe) {
            ioe.printStackTrace();
        } finally {
            try {
                br.close();
                isr.close();
                socket.close();
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }
    }

    public static class BusServer {

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
}