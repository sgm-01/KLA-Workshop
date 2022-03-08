package project.solution;

public class Data {
    Integer ID;
    Integer X;
    Integer Y;
    Integer Signal;

    public Integer getID() {
        return ID;
    }

    public Integer getSignal() {
        return Signal;
    }

    public Integer getX() {
        return X;
    }

    public Integer getY() {
        return Y;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public void setSignal(Integer signal) {
        Signal = signal;
    }

    public void setX(Integer x) {
        X = x;
    }

    public void setY(Integer y) {
        Y = y;
    }

    @Override
    public String toString() {
        return "Data{" +
                "ID=" + ID +
                ", X=" + X +
                ", Y=" + Y +
                ", Signal=" + Signal +
                '}';
    }
}
