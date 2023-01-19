import java.util.Scanner;

/**
 * LoL class
 *
 * @author LLDW
 * @date 2023/01/15 14:10
 */
public class Lol {
    /**
     * name 角色名称
     */
    private String name;

    /**
     * skillName 技能名称
     */
    private String skillName;

    /**
     * blood 血量
     */
    private int blood;

    /**
     * blueBlood 蓝量
     */
    private int blueBlood;

    /**
     * blueConsumptionOfLaunchSkill
     * 发动技能所消耗蓝量
     */
    private int blueConsumptionOfLaunchSkill;

    /**
     * hurtOfNormalAttack
     * 普通攻击所造成的伤害
     */
    private int hurtOfNormalAttack;

    /**
     * hurtOfSkill
     * 发动技能所造成的伤害
     */
    private int hurtOfSkill;

    /**
     * 无参数构造器
     */
    public Lol() {
    }

    /**
     *  有参数构造器
     *
     * @param name          角色名称
     * @param skillName     技能名称
     * @param blood         血量
     * @param blueBlood     蓝量
     * @param blueConsumptionOfLaunchSkill 使用技能所消耗的蓝量
     * @param hurtOfNormalAttack    普通攻击伤害
     * @param hurtOfSkill           技能伤害
     */
    public Lol(String name, String skillName, int blood, int blueBlood,
               int blueConsumptionOfLaunchSkill, int hurtOfNormalAttack, int hurtOfSkill) {
        this.name = name;
        this.skillName = skillName;
        this.blood = blood;
        this.blueBlood = blueBlood;
        this.blueConsumptionOfLaunchSkill = blueConsumptionOfLaunchSkill;
        this.hurtOfNormalAttack = hurtOfNormalAttack;
        this.hurtOfSkill = hurtOfSkill;
    }



    //Getting 和 Setting
    /**
     *  输出英雄名字
     * @return name
     */
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public int getBlood() {
        return blood;
    }

    public void setBlood(int blood) {
        this.blood = blood;
    }

    public int getBlueBlood() {
        return blueBlood;
    }

    public void setBlueBlood(int blueBlood) {
        this.blueBlood = blueBlood;
    }

    public int getBlueConsumptionOfLaunchSkill() {
        return this.blueConsumptionOfLaunchSkill;
    }

    public void setBlueConsumptionOfLaunchSkill(int blueConsumptionOfLaunchSkill) {
        this.blueConsumptionOfLaunchSkill = blueConsumptionOfLaunchSkill;
    }

    public int getHurtOfSkill() {
        return hurtOfSkill;
    }

    public void setHurtOfSkill(int hurtOfSkill) {
        this.hurtOfSkill = hurtOfSkill;
    }

    public String getSkillName() {
        return skillName;
    }

    public void setSkillName(String skillName) {
        this.skillName = skillName;
    }

    public int getHurtOfNormalAttack() {
        return hurtOfNormalAttack;
    }

    public void setHurtOfNormalAttack(int hurtOfNormalAttack) {
        this.hurtOfNormalAttack = hurtOfNormalAttack;
    }



    /**
     * 玩家选择对 敌人 发动技能还是使用普攻
     * @param enemy 输入lol类对象 敌人
     */
    public void choiceAttack(Lol enemy) {
        Scanner in = new Scanner(System.in);
        int choice;
        System.out.println(this.name + "玩家请选择:\n1.发动普通攻击 2.发动技能 "+
                "\""+ this.skillName+"\""+" 3.躺平(bushi)");
        choice = in.nextInt();

        //对敌人发动技能 且蓝量够
        if (choice == 2 && whetherBlueBloodEnough()) {
            launchSkill(enemy);
        }
        //对敌人发动技能 但蓝量不足
        else if (choice == 2 && !whetherBlueBloodEnough()) {
            System.out.println(this.name + "的蓝量不足,无法发动技能,自动选择发起普通攻击喵");
            launchNormalAttack(enemy);
        }
        //对敌人发动普通攻击
        else if(choice==1){
            launchNormalAttack(enemy);
        }
        //选择躺平
        else if(choice==3){
            doNothing();
        }
        //输入错误 直接躺平一回合
        else {
            System.out.println("1 2 3一个都不输是吧? 默认躺平了");
            doNothing();
        }
        System.out.println("--------------");

    }

    /**
     * 对敌人发动普通攻击
     *
     * @param enemy 输入Lol类对象 敌人
     */
    public void launchNormalAttack(Lol enemy) {
        enemy.blood=Math.max(0,enemy.blood-this.hurtOfNormalAttack);
        System.out.println(this.name+" 对 "+enemy.name+"发动了普通攻坤 造成了"+
                this.hurtOfNormalAttack+"伤害 "+enemy.name+"剩余血量为"+ enemy.blood);
    }

    /**
     * 对敌人发动技能
     *
     * @param enemy 输入lol类对象 敌人
     */
    public void launchSkill(Lol enemy) {
        enemy.blood=Math.max(0,enemy.blood-this.hurtOfSkill);
        this.blueBlood-=this.blueConsumptionOfLaunchSkill;
        System.out.println(this.name+" 对 "+enemy.name+"发动了技能"+"\""+this.skillName+
                "\""+ " 造成了"+ this.hurtOfSkill+"伤害 "+enemy.name+"剩余血量为"+ enemy.blood);
    }
    public void doNothing()
    {
        System.out.println(this.name+" 选择该回合躺平..??!!");
    }

    /**
     * 判断自身血量是否大于0
     *
     * @return 返回布尔类型 血量大于0为真 否则为假
     */
    public boolean whetherBloodEnough() {
        return this.blood > 0;
    }

    /**
     * 判断蓝量是否足够
     *
     * @return 返回布尔类型 蓝量足够真true 否则为假
     */
    public boolean whetherBlueBloodEnough() {
        return this.blueBlood > this.blueConsumptionOfLaunchSkill;
    }
}