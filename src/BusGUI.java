import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

//test
public class BusGUI extends JFrame {
    JPanel jpanel;
    JTextArea listArea; //대기중인 버스. area로 하니 안되다가 field로 바꾸니 정상
    JTextArea almostArea; //잠시후도착

    BusGUI() {
        init();
    }

    void init() {
        this.setTitle("버스 대기판");
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setBounds(0, 0, 400, 400);
        this.setLayout(new GridLayout(2, 1));
        this.setLocationRelativeTo(null); //화면 중앙에 표시
//        jpanel = new JPanel(new GridLayout(2, 1));
        listArea = new JTextArea("버스 현황");
        almostArea = new JTextArea("잠시 후 도착");
//        jpanel.add(listArea);
//        jpanel.add(almostArea);
//        this.add(jpanel);
        JScrollPane topScrollPane = new JScrollPane(listArea);
        JScrollPane bottomScrollPane = new JScrollPane(almostArea);
        this.add(topScrollPane);
        this.add(bottomScrollPane);
//        getContentPane().add(jpanel, BorderLayout.CENTER);
        this.setVisible(true);
    }

    public void display(String str) {
        int numIndex=str.indexOf("번");
        String busNum = str.substring(0, numIndex); //100번:~~에서 100 뽑기;
        if (str.contains(":")) {
            delTopDuple(str, busNum); //중복 확인 후 제거
            listArea.append("\n" + str); //위 칸에 출력하기
            int minuteIndex = str.indexOf("분");
            int minuteNum = Integer.parseInt(str.substring(numIndex+2, minuteIndex));
            //~~번:3분~~에서 3 뽑기
            if (minuteNum == 0) {
                almostArea.append("\n" + busNum);
            } //0분일때(도착 예정 시간 1분 미만일때) 아래칸에 출력하기


        } else if (!str.contains(":") && str != null) {
            //~번 버스가 도착했습니다, ~번 버스가 지나갔습니다
            delTopDuple(str, busNum);
            listArea.append("\n" + str); //위 칸에 출력하기
        }
    }



    public void delTopDuple(String str, String busNum) {
        if (listArea!=null) {
            String[] lines = listArea.getText().split("\n");
            String resultText="";
            for (String line : lines) {
                if (!line.contains(busNum)) {
//                    line.replaceAll("");
                   resultText+= line+"\n";
                }
            } resultText = resultText.trim();
//            String.join("\n", lines);
            listArea.setText(resultText);
        }
    }


//        String[] lines = listArea.getText().split("\n");
//        StringBuilder newListArea = new StringBuilder();

//        for (String line : lines) {
//            if (!line.contains(busNum)) {
//                newListArea.append(line).append("\n");
//            }
//        }
//
//        listArea.setText(newListArea.toString());


//        if (str.length() > 4) {
//            listArea.append("\n" + str);
//        }
    // JTextArea에 데이터를 출력

//        listArea.append("\n"+str);
//                listArea.setText("\n"+str);
//        else if (str.length() <= 3) {
//            almostArea.setText("\n" + str.substring(0, 3));
//        }

//    public void bottomDisplay(String str) {
//        if (str != null && str.length()>=3) {
////            almostArea.append("\n"+str.substring(0, 3));
//            almostArea.setText("\n"+str.substring(0, 3));
//
//        }


    public static void main(String[] args) {
        BusGUI busGUI = new BusGUI();
    }

}
