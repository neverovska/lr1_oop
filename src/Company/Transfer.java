package Company;

import Money.Bank_Account;
import Money.Money_Transfer;

public class Transfer {
    private String number1;
    private String number2;
    private double count;
    private boolean approve;

    public Transfer(String number1, String number2, double count) {
        this.number1 = number1;
        this.number2 = number2;
        this.approve = false;
        this.count = count;
    }

    public void Approve(Bank_Account buf1, Bank_Account buf, double count) {
        Money_Transfer.Transfer(buf1, buf, count);
    }
}
