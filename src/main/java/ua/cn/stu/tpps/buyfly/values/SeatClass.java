package ua.cn.stu.tpps.buyfly.values;

public enum SeatClass {
    Y("ECONOMY"),
    W("PREMIUM_ECONOMY"),
    J("BUSINESS"),
    F("FIRST");

    private final String seatClass;

    SeatClass(String seatClass) {
        this.seatClass = seatClass;
    }

    public String getSeatClass() {
        return seatClass;
    }
}
