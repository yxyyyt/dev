package com.sciatta.dev.java.database.elasticsearch.resthighlevelclient;

import com.sciatta.dev.java.database.elasticsearch.resthighlevelclient.module.User;
import com.sciatta.dev.java.database.elasticsearch.resthighlevelclient.service.impl.UserServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yangxiaoyu on 2021/9/9<br>
 * All Rights Reserved(C) 2017 - 2021 SCIATTA<br><p/>
 * UserServiceTests
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class UserServiceTests {
    public static final String INDEX_NAME = "users";
    
    @Autowired
    private UserServiceImpl userService;
    
    @Test
    public void createIndex() {
        userService.createIndexRequest(INDEX_NAME); // 只创建索引，设置分片和副本数量，不做映射
    }
    
    @Test
    public void deleteIndex() {
        userService.deleteIndexRequest(INDEX_NAME);
    }
    
    
    @Test
    public void insertTest() {
        List<User> list = new ArrayList<>();
        list.add(User.builder().id(1L).name("德玛西亚之力").realName("盖伦").desc("作为一名自豪而高贵的勇士，盖伦将自己当做无畏先锋中的普通一员参与战斗。他既受到同袍手足的爱戴，也受到敌人对手的尊敬--尤其作为尊贵的冕卫家族的子嗣，他被委以重任，守卫德玛西亚的疆土和理想。他身披抵御魔法的重甲，手持阔剑，时刻准备着用正义的钢铁风暴在战场上正面迎战一切操纵魔法的狂人。").build());
        list.add(User.builder().id(2L).name("疾风剑豪").realName("亚索(快乐风男)").desc("亚索是一个百折不屈的艾欧尼亚人，也是一名身手敏捷的御风剑客。这位生性自负的年轻人，被误认为杀害长老的凶手--由于无法证明自己的清白，他出于自卫而杀死了自己的哥哥。虽然长老死亡的真相已然大白，亚索还是无法原谅自己的所作所为。他在家园的土地上流浪，只有疾风指引着他的剑刃。").build());
        list.add(User.builder().id(3L).name("魂锁典狱长").realName("锤石").desc("暴虐又狡猾的锤石是一个来自暗影岛的亡灵，野心勃勃、不知疲倦。他曾经是无数奥秘的看守，寻找着超越生死的力量，而现在他则使用自己独创的钻心痛苦缓慢地折磨并击溃其他人，以此作为自己存在下去的手段。被他迫害的人需要承受远超死亡的痛苦，因为锤石会让他们的灵魂也饱尝剧痛，将他们的灵魂囚禁在自己的灯笼中，经受永世的折磨。").build());
        list.add(User.builder().id(4L).name("圣枪游侠").realName("卢锡安").desc("曾担光明哨兵的卢锡安是一位冷酷的死灵猎人。他用一对圣物手枪无情地追猎并灭绝不死亡灵。为亡妻复仇的意念吞噬了他，让他无止无休。除非消灭锤石，那个手握她灵魂的恶鬼。冷酷而且决绝的卢锡安不允许任何东西挡住自己的复仇之路。如果有什么人或者什么东西愚蠢到敢挑衅他的原则，就必将接受压倒性的神圣枪火狂轰滥炸。").build());
        list.add(User.builder().id(5L).name("法外狂徒格雷福斯").realName("格雷福斯").desc("马尔科姆.格雷福斯是有名的佣兵、赌徒和窃贼，凡是他到过的城邦或帝国，都在通缉悬赏他的人头。虽然他脾气暴躁，但却非常讲究黑道的义气，他的双管散弹枪“命运”就经常用来纠正背信弃义之事。几年前他和老搭档崔斯特.菲特冰释前嫌，如今二人一同在比尔吉沃特的地下黑道纷争中再次如鱼得水。").build());
        list.add(User.builder().id(6L).name("光辉女郎").realName("拉克丝").desc("拉克珊娜.冕卫出身自德玛西亚，一个将魔法视为禁忌的封闭国度。只要一提起魔法，人们总是带着恐惧和怀疑。所以拥有折光之力的她，在童年的成长过程中始终担心被人发现进而遭到放逐，一直强迫自己隐瞒力量，以此保住家族的贵族地位。虽然如此，拉克丝的乐观和顽强让她学会拥抱自己独特的天赋，现在的她正在秘密地运用自己的能力为祖国效力。").build());
        list.add(User.builder().id(7L).name("发条魔灵").realName("奥莉安娜").desc("奥莉安娜曾是一个拥有血肉之躯的好奇女孩，而现在则是全身上下部由发条和齿轮构成的科技奇观。祖安下层地区的一次事故间接导致了她身染重病，日渐衰竭的身体必须替换为精密的人造器官，一个接一个，直到全身上下再也没有人类的肉体。她给自己制作了一枚奇妙的黄铜球体，既是伙伴，也是保镖。如今她已经可以自由地探索壮观的皮尔特沃夫，以及更遥远的地方。").build());
        
        userService.insertBach(INDEX_NAME, list);
    }
    
    @Test
    public void updateTest() {
        User user = User.builder().id(6L).name("殇之木乃伊").realName("阿木木").desc("或许阿木木是英雄联盟世界里最古老的保卫者英雄之一，他对加入联盟前的生活仍一无所知。阿木木唯一记得的是自己在Shuima沙漠的一座金字塔内独自醒来。他全身缠着裹尸布，感受不到自己的心跳。此外，他感到一股强大而莫名的悲伤；他知道他失去了亲人，虽然他已不记得他们是谁。阿木木跪下来，在绷带内哭泣。不论做什么，似乎他都无法阻止眼泪或悲伤。最后他站起来在这个世界上游荡，并进入了联盟").build();
        userService.updateRequest(INDEX_NAME, user.getId().toString(), user);
    }
    
    @Test
    public void deleteTest() {
        userService.deleteRequest(INDEX_NAME, "1");
    }
    
    /**
     * 测试查询
     */
    @Test
    public void searchListTest() {
        List<User> userList = userService.searchList(INDEX_NAME);
        System.out.println(userList);
    }
}
