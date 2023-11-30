package bus;

import javax.swing.*;
import java.awt.*;

public class BusGUI extends JFrame {
    JPanel jpanel;
    JTextArea topArea; //버스 현황
    JTextArea botArea; //잠시후도착:

    BusGUI() {
        init();
    }

    void init() {
        this.setTitle("버스 대기판");
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setBounds(0, 0, 400, 400);
        this.setLayout(new GridLayout(2, 1));
        this.setLocationRelativeTo(null);
        topArea = new JTextArea("버스 현황");
        botArea = new JTextArea("잠시 후 도착");
        Font font=new Font("SansSerif",Font.BOLD,20);
        topArea.setFont(font);
        botArea.setFont(font);
        JScrollPane topScrollPane = new JScrollPane(topArea);
        JScrollPane bottomScrollPane = new JScrollPane(botArea);
        this.add(topScrollPane);
        this.add(bottomScrollPane);
        this.setAlwaysOnTop(true);
        this.setVisible(true);
    } //init

    public void display(String str) {
        //문자열에서 버스번호 뽑기;
        String busNum = str.substring(0, str.indexOf("번"));

        if (str.contains(":")) { //ex. "100번:1분 5초"
            //분 뽑기
            int minuteNum = Integer.parseInt(str.substring(str.indexOf("번")
                    + 2, str.indexOf("분")));

            //0분일때(도착 예정 시간 1분 미만일 때)
            // 아래칸(잠시후도착)에 출력하기
            if (minuteNum == 0) {
                displayBot(str, busNum);
            }
        }

        else if (!str.contains(":")) { //ex.100번 버스가 도착했습니다
            clearBot(str, busNum); //잠시후도착 칸에서 필요없는 부분 제거
        }

        //위쪽 칸(버스 현황)에 표시
        displayTop(str, busNum);

    } //display


    //위쪽 textArea(버스 현황)에 표시
    public void displayTop(String str, String busNum) {
        //해당 버스의 이전 기록이 textArea에 없을 때(최초 등록)
        if (!topArea.getText().contains(busNum)) {
            topArea.append("\n" + str);
        }
        //해당 버스의 이전 기록이 있을 때
        else if (topArea.getText().contains(busNum)) {
            String[] lines = topArea.getText().split("\n");
            String resultText = "";
            for (String line : lines) { //이전 기록(문자열)을 한 줄씩 가져옴
                if (line.contains(busNum)) {
                    line = str; //버스 번호가 포함된 줄만 변경 (중복 없기 위함)
                }
                resultText += line + "\n"; //변경된 줄들을 합침
            }
            resultText = resultText.trim(); //맨 마지막 공백 제거

            topArea.setText(resultText); //기존의 문자열을 합친 문자열로 대체
        }
    } //displayTop


    //0분이고 : 있을때 ex. "100번:0분 10초"
    public void displayBot(String str, String busNum) {
        if (!botArea.getText().contains(busNum)) {
            botArea.append("\n" + busNum); //버스 번호만 출력
        }

//        else if (botArea.getText().contains(busNum)) { //해당 버스의 이전 기록이 있을 때
//            String[] lines = botArea.getText().split("\n");
//            String resultText = "";
//            for (String line : lines) {
//                if (line.contains(busNum)) {
//                    line = busNum; //버스 번호가 포함된 줄만 변경
//                }
//                resultText += line + "\n";
//            }
//            resultText = resultText.trim();
//            botArea.setText(resultText);
//        }
    } //displayBot



    //잠시후도착 칸에서 필요없는 부분 제거
    public void clearBot(String str, String busNum) {
            String[] lines = botArea.getText().split("\n");
            String resultText = "";
            for (String line : lines) { //line:이전 로그
                if (line.contains(busNum)) {
                        line = ""; //해당 버스 번호 제거
                }
                resultText += line + "\n";
            }
            resultText = resultText.trim();
        botArea.setText(resultText);
    }




    public static void main(String[] args) {
        BusGUI busGUI = new BusGUI();
    }

}
