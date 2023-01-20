import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;

/**
 * ATMSystem
 *
 * @author LLDW
 * @date 2023-01-20 17:49:35
 */
public class ATMSystem {
    public static void main(String[] args) {
        ArrayList<Account> accountArrayList = new ArrayList<>();
        welcome(accountArrayList);
    }

    /**
     * 欢迎页 界面方法 用户输入业务编号 接着挑战到相应的方法
     */
    public static void welcome(ArrayList<Account> list) {
        Scanner in = new Scanner(System.in);
        System.out.println("=========欢迎登录颖哥ATM系统=========");
        System.out.println("1.登录账户\n2.注册账户\n请输入您需要办理的业务前的编号:");
        int number;
        boolean ok;
        while (true) {
            ok = true;
            number = in.nextInt();
            if (number != 1 && number != 2) {
                System.out.println("眼睛瞎了看不起是吧？请您重新输入一遍编号喵");
                System.out.println("1.登录账户\n2.注册账户\n请输入您需要办理的业务前的编号:");
                ok = false;
            }
            if (number == 1 && list.isEmpty()) {
                ok = false;
                System.out.println("当前系统无任何账户，请先注册再登录!");
                System.out.println("2.注册账户\n请输入您需要办理的业务前的编号:");
            }
            if (ok) {
                break;
            }
        }
        if (number == 1) {
            loginAccount(list);
        } else {
            registerAccount(list);
        }
        welcome(list);
    }

    /**
     * 登录账户
     */
    public static void loginAccount(ArrayList<Account> list) {
        Scanner in = new Scanner(System.in);
        //输入账户
        Account tempaccount = new Account();
        //输入卡号和密码
        inputCardNumber(list, tempaccount);
    }

    /**
     * 输入卡号
     *
     * @param list        账户集合
     * @param tempaccount 传递卡号对应的账户
     */
    public static void inputCardNumber(ArrayList<Account> list, Account tempaccount) {
        Scanner in = new Scanner(System.in);
        String cardNumber;
        while (true) {
            System.out.println("请输入您的卡号:");
            cardNumber = in.next();
            //查询该账户存在
            if ((tempaccount = queryCardNumber(list, cardNumber)) != null) {  //如果存在
                break;
            } else {
                System.out.println("该卡号不存在！");
            }
        }
        inputPassWord(list, tempaccount);
    }

    /**
     * 输入密码
     *
     * @param list        账户集合
     * @param tempaccount 输完卡号 卡号对应的账户
     */
    public static void inputPassWord(ArrayList<Account> list, Account tempaccount) {
        Scanner in = new Scanner(System.in);
        System.out.println("请输入您的密码:");
        String password = in.next();
        while (true) {
            if (password.equals(tempaccount.getPassword())) {
                break;
            } else {
                System.out.println("密码或者账户输入错误!");
                inputCardNumber(list, tempaccount);
                break;
            }
        }
        userMenu(list, tempaccount);
    }


    public static void userMenu(ArrayList<Account> list, Account acc) {
        Scanner in = new Scanner(System.in);
        while (true) {
            System.out.println("===============用户操作页===================");
            System.out.println("1、查询账户");
            System.out.println("2、存款");
            System.out.println("3、取款");
            System.out.println("4、转账");
            System.out.println("5、修改密码");
            System.out.println("6、退出");
            System.out.println("7、注销账户");
            System.out.println("请选择:");
            int command = in.nextInt();
            switch (command) {
                case 1:
                    //查询账户
                    showAccount(acc);
                    break;
                case 2:
                    //存款
                    depositMoney(acc);
                    break;
                case 3:
                    //取款
                    drawMoney(acc);
                    break;
                case 4:
                    //转账
                    transferMoney(list, acc);
                    break;
                case 5:
                    //修改密码
                    changePassword(acc);
                    break;
                case 6:
                    //退出
                    //welcome(list);
                    return;

                case 7:
                    //销毁账户
                    destroyAccount(list,acc);
                    return ;
                default:
                    System.out.println("数字都看不懂是吧？奖励重新输一遍!!");
            }
        }
    }

    /**
     * 注销账户
     * @param list  账户集合
     * @param acc   当前登录账户
     */
    private static void destroyAccount(ArrayList<Account> list, Account acc) {
        Scanner in = new Scanner(System.in);

        //再次询问是否注销账户
        System.out.println("您确定要注销账户吗？\n输入1 继续注销\n输入2或其他 取消注销账号");
        int command = in.nextInt();
        if(command!=1)
        {
            //取消注销
            System.out.println("退出注销成功！");
        } else{
            //确定注销账户
            System.out.println("您正在注销账户,请您输入当前账户密码:");
            String password = in.next();
            if(password.equals(acc.getPassword()))
            {
                //账户密码正确 注销账户
                if(list.remove(acc))
                {
                    System.out.println("删除成功！");
                }else{
                    System.out.println("删除失败");
                }
            }else{
                //密码错误 取消注销
                return ;
            }
        }
    }

    /**
     *  修改密码
     * @param acc   当前登录账户
     */
    private static void changePassword(Account acc) {
        Scanner in = new Scanner(System.in);

        //输入原密码
        System.out.println("请输入原始密码:");
        while (true) {
            String tempPassword = in.next();
            //判断输入的密码是否正确
            if (tempPassword.equals(acc.getPassword())) {
                //如果密码正确
                break;
            } else {
                //密码不正确
                System.out.println("您输入的密码有误,呆jio古，请您重新输入密码:");
            }
        }
        String newPassword;
        //判断是否与原密码相同
        while (true) {
            System.out.println("请您输入新的密码");
            newPassword = in.next();
            if (newPassword.equals(acc.getPassword())) {
                //如果相同
                System.out.println("果咩那塞,新密码不能与原密码相同");
            } else {
                //如果不相同 则修改密码
                acc.setPassword(newPassword);
                System.out.println("恭喜您，密码修改成功");
                return;
            }
        }

    }

    /**
     * 转账
     *
     * @param list 账户集合
     * @param acc  当前登录账户
     */
    private static void transferMoney(ArrayList<Account> list, Account acc) {
        Scanner in = new Scanner(System.in);
        //判断当前账户数量是否大于等于2
        if (list.size() < 2) {
            System.out.println("对不起,当前系统账户少于2为,无法进行转账,请您开户");
            //结束当前方法
            return;
        }
        //账户数量大于等于2
        if (acc.getMoney() == 0) {
            System.out.println("对不起,您没钱啦~，转不了啦");
            //结束当前方法
            return;
        }
        //真正开始转账
        while (true) {
            //输入卡号
            System.out.println("请您输入对方的卡号");
            String opposingCardNumber = in.next();

            //如果输入的卡号为自身的卡号
            if (acc.getCardNumber().equals(opposingCardNumber)) {
                System.out.println("转账不能向自己转账,呆jio不,您可以重新输入卡号");
                continue;
            }
            //不是自己卡号 判断该卡号是否存在
            Account opposingAccount = queryCardNumber(list, opposingCardNumber);

            //卡号为null时
            if (opposingAccount == null) {
                System.out.println("对不起,您输入的账号不存在~~");
                return;
            }
            //卡号不为null时
            else {
                //验证对方的姓氏
                String opposingUserName = opposingAccount.getName();

                //弄个小tip
                String tip = "*" + opposingUserName.substring(1);
                System.out.println("请您输入" + tip + "的姓氏:");

                //输入姓氏
                String tempName = in.next();
                //如果姓氏回答错误
                if (!tempName.equals(opposingUserName.substring(0, 1))) {
                    System.out.println("果咩那塞,您输入的信息有误");
                }

                //回答正确
                else {
                    System.out.println("请您输入转账金额:");
                    double money = in.nextDouble();

                    //余额不足
                    if (money > acc.getMoney()) {
                        System.out.println("果咩那塞,您的余额不足,您的当前总余额为" + acc.getMoney());
                    } else {   //余额足够

                        //更新自己账户余额
                        acc.setMoney(acc.getMoney() - money);

                        //更新对方账户余额
                        opposingAccount.setMoney(opposingAccount.getMoney() + money);

                        //转账成功
                        System.out.println("转账成功,您当前余额为" + acc.getMoney());
                        return;
                    }
                }

            }
        }
    }

    /**
     * 用户取款
     *
     * @param acc 当前登录账户
     */
    public static void drawMoney(Account acc) {
        Scanner in = new Scanner(System.in);
        //判断是否足够100rmb
        if (acc.getMoney() < 100) {
            System.out.println("对不起，当前账户中不够100元，不能取钱喵");
            return;
        }
        //输入取款金额
        System.out.println("请您输入取款金额:");
        double money = in.nextDouble();
        //判断这个金额是否超过取款限额
        if (money > acc.getLimitMoney()) {
            System.out.println("对不起，您当前取款金额超过每次限额，每次最多可取:" + acc.getLimitMoney());
        }
        //判断这个金额是否超过余额
        else if (money > acc.getMoney()) {
            System.out.println("余额不足,您账户当前的总余额为" + acc.getMoney());
        }
        //可取钱
        else {
            //更新账户余额
            acc.setMoney(acc.getMoney() - money);
            //取款成功
            System.out.println("恭喜您,取款成功，取出" + money + "元");
            //展示账户信息
            System.out.println("恭喜您,存钱成功,当前账户信息为:");
            showAccount(acc);
        }
    }

    /**
     * 存款
     *
     * @param acc 当前登录账户
     */
    private static void depositMoney(Account acc) {
        Scanner in = new Scanner(System.in);
        System.out.println("=============存款页面=================");
        System.out.println("请输入存款金额");
        double money = in.nextDouble();
        //特判
        while (money <= 0) {
            System.out.println("正数!请重新输入存款金额:");
            money = in.nextDouble();
        }
        //更新账户余额
        acc.setMoney(acc.getMoney() + money);
        //展示账户信息
        System.out.println("恭喜您,存钱成功,当前账户信息为:");
        showAccount(acc);
    }

    /**
     * 1.查询账户
     *
     * @param acc 当前登录账户
     */
    private static void showAccount(Account acc) {
        System.out.println("===============您的用户信息为===================");
        System.out.println("卡号:" + acc.getCardNumber());
        System.out.println("户主:" + acc.getName());
        System.out.println("余额: " + acc.getMoney());
        System.out.println("限额:" + acc.getLimitMoney());
    }


    /**
     * 注册账户
     *
     * @param list 账户集合
     */
    public static void registerAccount(ArrayList<Account> list) {
        Scanner in = new Scanner(System.in);
        //名字
        String name;
        //密码
        String password;
        //取现额度
        double limitMoney;
        System.out.println("请输入您的姓名:");
        name = in.next();
        System.out.println("请输入您的密码:");
        password = in.next();
        System.out.println("请您再次确认密码:");
        String password2 = in.next();
        boolean ok = true;
        while (true) {
            ok = true;
            if (!password2.equals(password)) {
                ok = false;
                System.out.println("您两次输入的密码不一致喵！！");
                System.out.println("请输入您的密码:");
                password = in.next();
                System.out.println("请您再次确认密码:");
                password2 = in.next();
            }
            if (ok) {
                break;
            }
        }
        System.out.println("请您设置取现额度:");
        limitMoney = in.nextDouble();

        //随机生成一个卡号
        String cardNumber = createAccountNumberRandomly(list);

        //new一个账户
        Account account = new Account(cardNumber, name, password, 0, limitMoney);

        //存到账户集合中
        list.add(account);
        System.out.println("恭喜您," + name + "先生/女生," + "开户成功！" + "\n" + "您的卡号是:" + cardNumber);
        welcome(list);
    }

    /**
     * 随机生成一个 八位卡号 且不和集合内重复 不以0开头
     *
     * @param list 账号集合
     * @return cardNumber 随机生成的八位卡号
     */
    public static String createAccountNumberRandomly(ArrayList<Account> list) {
        Random random = new Random();
        String cardNumber = "";
        int temp;
        while (true) {
            //随机生成一个新的八位卡号
            for (int i = 0; i < 8; i++) {
                //随即生成0到9之间的数
                temp = random.nextInt(10);
                //当账户第一位为0时,重新生成
                while (i == 0 && temp == 0) {
                    temp = random.nextInt(10);
                }
                cardNumber += (char) (temp + '0');
            }
            //判断是否重号 不重号则跳出while循环 否则重新生成
            if (queryCardNumber(list, cardNumber) == null) {
                break;
            }
        }
        return cardNumber;
    }


    /**
     * 查询账户 返回账户or返回空
     *
     * @param list       账户集合
     * @param cardNumber 输入的卡号
     * @return 返回输入的卡号对应的账户  查询不到则返回null
     */
    public static Account queryCardNumber(ArrayList<Account> list, String cardNumber) {
        for (int i = 0; i < list.size(); i++) {
            //遍历 根据输入的卡号 查询账户
            if (cardNumber.equals(list.get(i).getCardNumber())) {
                return list.get(i);
            }
        }
        return null;
    }
}