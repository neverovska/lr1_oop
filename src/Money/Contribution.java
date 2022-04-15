package Money;

public class Contribution extends Bank_Account{
    private double perсent;

    public Contribution(String clientpass, String number, double count, boolean bloking, boolean freezing, String bik, double perсent){
        super(clientpass, number, count, bloking, freezing, bik);
        this.perсent = perсent;
    }

    public double getPerсent() {
        return perсent;
    }
}
