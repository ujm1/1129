package bus;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.ConnectException;
import java.net.Socket;

public class BusClient extends Thread {
    final static String SERVER_IP = "172.30.1.10";
    final static int SERVER_PORT = 36129;
    final static int SECOND = 5;
                    //버스번호, 도착시간, 도착 후 다음 도착시간, 회차시간
    static void check(int number, int time, int nextTime, int turn) {
        Socket socket = null;
        BufferedWriter bw = null;

        try {
            socket = new Socket(SERVER_IP, SERVER_PORT);
            System.out.println("소켓 연결");

            bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            Bus bus = new Bus(number, time, nextTime, turn);
            String str = null;
            while (bus.getTurn() >= 0) {
                //회차가 종료되지 않았을 경우
                if (bus.getTime() > 0) {
                    str = bus.getNumber() + "번:"
                            + bus.getTime() / 60 + "분 "
                            + bus.getTime() % 60 + "초";

                } else if (bus.getTime() == 0) {
                    str = bus.getNumber() + "번 버스가 도착했습니다";
                } else if (bus.getTime() < 0) {
                    str = bus.getNumber() + "번 버스가 지나갔습니다";
                    bus.setTime(bus.getNextTime());
                    bus.setTurn(bus.getTurn() - 1);
                }

                bw.write(str);
                bw.newLine();
                bw.flush();
                bus.setTime(bus.getTime() - SECOND);

                System.out.println(str);

                Thread.sleep(SECOND * 1000);
                //설정한 초마다 한번씩 보냄
            }

            //회차 종료
            if (bus.getTurn() < 0) {
                str = bus.getNumber() + "번 버스의 운행이 종료되었습니다";
                bw.write(str);
                bw.newLine();
                bw.flush();
                System.out.println(str);
            }


        } catch (ConnectException ce) {
            System.out.println("서버가 열려 있지 않음");
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } catch (InterruptedException ie) {
            ie.printStackTrace();
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