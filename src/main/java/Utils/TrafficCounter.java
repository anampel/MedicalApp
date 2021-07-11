package Utils;

public  class TrafficCounter {
    private int counter = 0;

    public int getCounter() {
        return counter;
    }

    public void count() {
        this.counter++;
    }
}
