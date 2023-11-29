import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class BusClient extends Thread {
    final static String SERVER_IP = "172.30.1.10";
    final static int SERVER_PORT = 36129;
    final static int SECOND = 5;

    static void check ( int number, int time){
        Socket socket = null;
        BufferedWriter bw = null;

        try {
            socket = new Socket(SERVER_IP, SERVER_PORT);
            System.out.println("소켓 연결");

            bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            Bus bus = new Bus(number, time);

            while (true) {
                String str1 = null;
                String str2 = "";

                if (bus.getTime() > 0) {
                    str1 = bus.getNumber() + "번:" + bus.getTime() / 60 + "분 "
                            + bus.getTime() % 60 + "초";

                } else if (bus.getTime() == 0) {
                    str1 = bus.getNumber() + "번 버스가 도착했습니다";
                } else if (bus.getTime() < 0) {
                    str1 = bus.getNumber() + "번 버스가 지나갔습니다";
                    bus.setTime(200);
                }

                bw.write(str1);
                bw.newLine();
                bw.flush();
                bus.setTime(bus.getTime() - SECOND);



                System.out.println(str1);


                Thread.sleep(SECOND * 1000);
            }
        } catch (IOException | InterruptedException ioe) {
            ioe.printStackTrace();
        } finally {
            try {
                if (bw != null) bw.close();
                if (socket != null) socket.close();
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }
    }
}