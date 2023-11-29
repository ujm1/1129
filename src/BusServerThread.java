import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class BusServerThread extends Thread {
    private Socket socket;

    public BusServerThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try (
                InputStreamReader isr = new InputStreamReader(socket.getInputStream());
                BufferedReader br = new BufferedReader(isr)
        ) {
            BusGUI busGUI = new BusGUI();

            while (true) {
                String fromClient = br.readLine();
                System.out.println(fromClient);
                busGUI.topDisplay(fromClient);

                Thread.sleep(1);

                String fromClient2 = br.readLine();
                System.out.println(fromClient2);
                Thread.sleep(1);
                busGUI.bottomDisplay(fromClient2);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}