/**
 * Account class
 *
 * @author LLDW
 * @date 2023/1/15
 */
public class Account {
    String cardNumber;
    String name;
    long money;
    long limitMoney;

    public Account() {
    }

    public Account(String cardNumber, String name, long money, long limitMoney) {
        this.cardNumber = cardNumber;
        this.name = name;
        this.money = money;
        this.limitMoney = limitMoney;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getMoney() {
        return money;
    }

    public void setMoney(long money) {
        this.money = money;
    }

    public long getLimitMoney() {
        return limitMoney;
    }

    public void setLimitMoney(long limitMoney) {
        this.limitMoney = limitMoney;
    }

    public boolean drawMoney(long money)
    {
        if(money>limitMoney)
        {

        }
    }
}
