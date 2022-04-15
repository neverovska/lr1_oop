package Methods.Logi;

public class Log {
    private String flag;
    private String number1;
    private String number2;
    private String bik;
    private double count;

    public Log(String flag, String number1, String bik, String number2, double count) {
        this.flag = flag;
        this.number1 = number1;
        this.bik = bik;
        this.number2 = number2;
        this.count = count;
    }

    public double getCount() {
        return count;
    }
    public String getFlag() {
        return flag;
    }
    public String getNumber1() {
        return number1;
    }
    public String getNumber2() {
        return number2;
    }
    public void setCount(double count) {
        this.count = count;
    }
    public void setFlag(String flag) {
        this.flag = flag;
    }
    public void setNumber1(String number1) {
        this.number1 = number1;
    }
    public void setNumber2(String number2) {
        this.number2 = number2;
    }
    public String getBik() {
        return bik;
    }
}
