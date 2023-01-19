/**
 * LoL class
 * @author LLDW
 * @date 2023/01/15 14:10
 */
public class Main {
    public static void main(String[] args)
    {
        Lol naHiDa = new Lol("Nahida","所闻遍计",16245,10000,
                500,1000,3000);
        Lol maChao = new Lol("马超","插秧",5000,1000,
                500,500,2000);
        launchAnAttack(naHiDa,maChao);
    }

    /**
     * 两个英雄开战函数 同时判断血量是否大于0 一旦任何一方血量小于0 则结束
     * @param hero1
     * @param hero2
     */
    public static void launchAnAttack(Lol hero1,Lol hero2)
    {
        System.out.println(hero1.getName()+ " "+hero2.getName()+"你们不要再打啦!!");
        //当两个英雄的血量都大于0时 轮流发起进攻
        while(hero1.whetherBloodEnough()&&hero2.whetherBloodEnough())
        {
            //1对2发起进攻
            hero1.choiceAttack(hero2);
            //判断2血量是否大于0 是则跳出循环 否则到2发起进攻
            if(!hero2.whetherBloodEnough()){break;}
            //2对1发起进攻
            hero2.choiceAttack(hero1);
        }
        //定义一个变量 找到胜利的一方并存给该变量
        Lol winHero = hero1.getBlood()>0?hero1:hero2;
        //定义一个变量 找到失败的一方并存给该变量
        Lol defeatHero = hero1.getBlood()<=0?hero1:hero2;
        //对决结束对话语
        System.out.println(winHero.getName()+"打败了"+defeatHero.getName());
        System.out.print(winHero.getName()+"对"+defeatHero.getName()+"轻轻的说:嘿嘿小菜坤");
    }
}