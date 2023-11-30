public class Bus extends Thread {

    private int number; //버스번호
    private int time; //초단위
    private int nextTime;
    private int turn;

    public Bus(int number, int time, int nextTime, int turn) {
        this.number = number;
        this.time = time;
        this.nextTime=nextTime; // 다음 버스와의 시간 격차
        this.turn=turn; //총 회차
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