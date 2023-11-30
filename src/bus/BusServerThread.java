package bus;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

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
        } catch (IOException ioe) {
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
}