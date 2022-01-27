package models;

public class OperationOfTerminal {
    public String phoneNumber;
    public String numberCode;
    public float summ;

    public OperationOfTerminal(String phoneNumber, String numberCode, float summ) {
        this.phoneNumber = phoneNumber;
        this.numberCode = numberCode;
        this.summ = summ;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getNumberCode() {
        return numberCode;
    }

    public void setNumberCode(String numberCode) {
        this.numberCode = numberCode;
    }

    public float getSumm() {
        return summ;
    }

    public void setSumm(float summ) {
        this.summ = summ;
    }

    @Override
    public String toString() {
        return "OperationOfTerminal{" +
                "phoneNumber='" + phoneNumber + '\'' +
                ", numberCode='" + numberCode + '\'' +
                ", summ=" + summ +
                '}';
    }
}
