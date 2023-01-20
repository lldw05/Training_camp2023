import java.util.ArrayList;
import java.util.Random;

/**
 * Account class
 *
 * @author LLDW
 * @date 2023-01-20 17:49:40
 */
public class Account {
    /**
     * 卡号
     */
    private String cardNumber;

    /**
     * 姓名
     */
    private String name;

    /**
     * 密码
     */
    private String password;

    /**
     * 余额
     */
    private double money;

    /**
     * 限取金额
     */
    private double limitMoney;


    /**
     * 无参数构造器
     */
    public Account() {
    }

    /**
     * 有参数构造器
     * @param cardNumber 卡号
     * @param name       姓名
     * @param password    密码
     * @param money        余额
     * @param limitMoney    限取金额
     */
    public Account(String cardNumber, String name, String password, double money, double limitMoney) {
        this.cardNumber = cardNumber;
        this.name = name;
        this.password = password;
        this.money = money;
        this.limitMoney = limitMoney;
    }


    /**
     * getter 和setter
     *
     */
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public double getLimitMoney() {
        return limitMoney;
    }

    public void setLimitMoney(double limitMoney) {
        this.limitMoney = limitMoney;
    }






}
