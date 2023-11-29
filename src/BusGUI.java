import javax.swing.*;
import java.awt.*;

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

        if(str.contains(":")) {
            int colonIndex=str.indexOf(":");
            String busNum=str.substring(0,colonIndex-1);
            listArea.append("\n"+str);
            int minuteIndex=str.indexOf("분");
            int minuteNum=Integer.parseInt(str.substring(colonIndex+1,minuteIndex));
            if(minuteNum==0) {
                almostArea.append("\n"+busNum);
            }


        } else if (!str.contains(":")&&str!=null) {
            //~번 버스가 도착했습니다, ~번 버스가 지나갔습니다
            listArea.append("\n" + str);}

//        if (str.length() > 4) {
//            listArea.append("\n" + str);
//        }
        // JTextArea에 데이터를 출력

//        listArea.append("\n"+str);
//                listArea.setText("\n"+str);
//        else if (str.length() <= 3) {
//            almostArea.setText("\n" + str.substring(0, 3));
//        }
    }
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
