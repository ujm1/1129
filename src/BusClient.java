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
                    str1 = bus.getNumber() + "번 버스가 정류장에 " +
                            "도착하기까지 " +
                            "남은 시간: " + bus.getTime() / 60 + "분 "
                            + bus.getTime() % 60 + "초";
                    if (bus.getTime() / 60 == 0) {
                        str2 = bus.getNumber() + "번";
                    }
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

                Thread.sleep(1);

                if (!str2.isEmpty()) {
                    bw.write(str2);
                    bw.newLine();
                    bw.flush();
                }

                System.out.println(str1);
                if (!str2.isEmpty()) {
                    System.out.println(str2);
                }

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