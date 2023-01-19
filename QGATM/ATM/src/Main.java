import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        menu();
    }

    /**
     * 登录前主界面方法 用户输入业务编号 接着挑战到相应的方法
     */
    public static void menu()
    {
        Scanner in = new Scanner(System.in);
        System.out.println("=========欢迎登录颖哥ATM系统=========");
        System.out.println("1.登录账户\n2.注册账户\n请输入您需要办理的业务前的编号:");
        int number;
        boolean ok;
        while(true){
            ok =true;
            number = in.nextInt();
            if(number!=1&&number!=2){
                System.out.println("眼睛瞎了看不起是吧？请您重新输入一遍编号喵:");
                ok = false;
            }
            if(ok){break;}
        }
        if(number==1){}
        else if(number==2){}
    }
    public static void loginAccount()
    {
        System.out.println("请输入您的账号:");
        System.out.println("请输入您的密码:");
        System.out.println("");
    }

}