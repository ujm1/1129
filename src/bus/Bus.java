package bus;

public class Bus extends Thread {

    private int number; //버스번호
    private int time; //도착시간, 초단위
    private int nextTime; //도착 후 다음 회차 버스 도착 시간
    private int turn; //해당 버스가 운행이 종료될 까지의 총 회차

    public Bus(int number, int time, int nextTime, int turn) {
        this.number = number;
        this.time = time;
        this.nextTime=nextTime;
        this.turn=turn;
    }


    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getNextTime() {
        return nextTime;
    }

    public int getTurn() {
        return turn;
    }

    public void setTurn(int turn) {
        this.turn = turn;
    }

    public void run() {
        BusClient.check(number,time,nextTime,turn);
    }
}