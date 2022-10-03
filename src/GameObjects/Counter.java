package GameObjects;

public class Counter {
    private int value;

    public Counter(int startingVal) {
        value = startingVal;
    }
    // add number to current count.
    public void increase(int number) {
        value += number;
    }
    // subtract number from current count.
    public void decrease(int number) {
        value -= number;
    }
    // get current count.
    public int getValue() {
        return value;
    }
}