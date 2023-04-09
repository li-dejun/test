package com.l.dj.util;

import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;

import java.util.Random;


public class CreateName {

    /**
     * 姓名生成
     */
    public static String getName (){
        Random random = new Random();

        //  赵钱孙李周吴郑王冯陈褚卫蒋沈韩杨朱秦尤许何吕施张孔曹严华金魏陶姜戚谢邹喻柏水窦章云苏潘葛奚范彭郎鲁韦昌马苗凤花方俞任袁柳酆鲍史唐费廉岑薛雷贺倪汤滕殷罗毕郝邬安常乐于时傅皮卞齐康伍余元卜顾孟平黄和穆萧尹姚邵湛汪祁毛禹狄米贝明臧计伏成戴谈宋茅庞熊纪舒屈项祝董梁杜阮蓝闵席季麻贾路娄危江童颜郭梅盛林刁钟徐邱骆高夏蔡田樊胡凌霍虞万支柯昝管卢莫经房裘缪干解应宗丁宣贲邓郁单杭洪包诸左石崔吉龚程嵇邢裴陆荣翁荀羊於惠甄曲家封芮储靳汲邴糜井段富巫乌焦巴牧隗山谷侯宓蓬全郗班秋仲伊宁仇栾甘厉戎祖武符刘景詹束龙叶幸司韶郜黎蓟薄印宿白怀蒲邰从鄂索咸籍赖卓蔺屠蒙池乔阴郁胥能苍双闻莘党翟谭贡劳姬申扶冉宰郦雍却璩桑桂濮牛寿通边扈燕冀郏尚温别庄晏柴瞿阎慕连习艾鱼容向古易戈廖庾终暨居衡步都耿满弘匡文寇阙殳蔚越夔师巩聂晁敖冷訾辛阚简饶曾沙养关蒯相荆游竺权盖桓公
        String[] xingshi = {"赵", "钱", "孙", "李", "周", "吴", "郑", "王", "冯", "陈", "褚", "卫", "蒋", "沈", "韩", "杨", "朱", "秦", "尤", "许",

                "何", "吕", "施", "张", "孔", "曹", "严", "华", "金", "魏", "陶", "姜", "戚", "谢", "邹", "喻", "柏", "水", "窦", "章", "云", "苏",

                "潘", "葛", "奚", "范", "彭", "郎", "鲁", "韦", "昌", "马", "苗", "凤", "花", "方", "俞", "任", "袁", "柳", "酆", "鲍", "史", "唐",

                "费", "廉", "岑", "薛", "雷", "贺", "倪", "汤", "滕", "殷", "罗", "毕", "郝", "邬", "安", "常", "乐", "于", "时", "傅", "皮", "卞",

                "齐", "康", "伍", "余", "元", "卜", "顾", "孟", "平", "黄", "和", "穆", "萧", "尹", "姚", "邵", "湛", "汪", "祁", "毛", "禹", "狄",

                "米", "贝", "明", "臧", "计", "伏", "成", "戴", "谈", "宋", "茅", "庞", "熊", "纪", "舒", "屈", "项", "祝", "董", "梁", "杜", "阮",

                "蓝", "闵", "席", "季", "麻", "贾", "路", "娄", "危", "江", "童", "颜", "郭", "梅", "盛", "林", "刁", "钟", "徐", "邱", "骆", "高",

                "夏", "蔡", "田", "樊", "胡", "凌", "霍", "虞", "万", "支", "柯", "昝", "管", "卢", "莫", "经", "房", "裘", "缪", "干", "解", "应",

                "宗", "丁", "宣", "贲", "邓", "郁", "单", "杭", "洪", "包", "诸", "左", "石", "崔", "吉", "龚", "程", "嵇", "邢", "裴", "陆", "荣",

                "翁", "荀", "羊", "於", "惠", "甄", "曲", "家", "封", "芮", "储", "靳", "汲", "邴", "糜", "井", "段", "富", "巫", "乌", "焦", "巴",

                "牧", "隗", "山", "谷", "侯", "宓", "蓬", "全", "郗", "班", "秋", "仲", "伊", "宁", "仇", "栾", "甘", "厉", "戎", "祖", "武", "符",

                "刘", "景", "詹", "束", "龙", "叶", "幸", "司", "韶", "郜", "黎", "蓟", "薄", "印", "宿", "白", "怀", "蒲", "邰", "从", "鄂", "索",

                "咸", "籍", "赖", "卓", "蔺", "屠", "蒙", "池", "乔", "阴", "郁", "胥", "能", "苍", "双", "闻", "莘", "党", "翟", "谭", "贡", "劳",

                "姬", "申", "扶", "冉", "宰", "郦", "雍", "却", "璩", "桑", "桂", "濮", "牛", "寿", "通", "边", "扈", "燕", "冀", "郏", "尚", "温",

                "别", "庄", "晏", "柴", "瞿", "阎", "慕", "连", "习", "艾", "鱼", "容", "向", "古", "易", "戈", "廖", "庾", "终", "暨", "居", "衡",

                "步", "都", "耿", "满", "弘", "匡", "文", "寇", "阙", "殳", "蔚", "越", "夔", "师", "巩", "聂", "晁", "敖", "冷", "訾", "辛", "阚",

                "简", "饶", "曾", "沙", "养", "关", "蒯", "相", "荆", "游", "竺", "权", "盖", "桓", "公"};
        String[] xs_S = {"赵", "李", "周", "吴", "郑", "王", "陈", "张", "刘"};
        String[] xs_H = {"孙", "孔", "宋", "钱", "冯", "沈", "韩", "杨", "朱", "秦", "许", "何", "吕", "曹", "严", "魏", "陶", "姜", "谢", "潘", "葛", "范", "马", "方", "俞", "任", "袁", "柳", "史", "唐", "薛", "罗", "常", "于", "时", "傅", "齐", "伍", "余", "元", "顾", "孟", "邵", "汪", "庞", "纪", "董", "梁", "杜", "阮", "贾", "江", "颜", "郭", "林", "徐", "邱", "高", "夏", "蔡", "田", "樊", "胡", "凌", "霍", "万", "莫", "丁", "单", "左", "石", "崔", "裴", "陆", "封", "段", "牧", "侯", "叶", "白", "卓", "郦", "桑", "濮", "牛", "边", "扈", "燕", "冀", "尚", "温", "别", "庄", "晏", "柴", "瞿", "向", "易", "文", "寇", "聂", "晁", "曾", "权"};
        String[] xs_M = {"褚", "卫", "蒋", "尤", "施", "华", "金", "戚", "邹", "喻", "柏", "窦", "章", "云", "苏", "彭", "郎", "鲁", "韦", "苗", "凤", "花", "鲍", "费", "廉", "岑", "雷", "贺", "倪", "汤", "滕", "殷", "毕", "郝", "康", "黄", "穆", "萧", "尹", "姚", "祁", "毛", "计", "伏", "狄", "成", "戴", "谈", "熊", "舒", "屈", "项", "祝", "路", "娄", "童", "梅", "盛", "钟", "管", "卢", "房", "裘", "解", "应", "宗", "宣", "邓", "郁", "洪", "龚", "程", "嵇", "邢", "荀", "甄", "曲", "储", "靳", "谷", "班", "秋", "仇", "栾", "甘", "厉", "祖", "伊", "宁", "武", "符", "景", "赖", "蔺", "屠", "蒙", "乔", "阴", "郁", "闻", "翟", "谭", "姬", "申", "阎", "慕", "连", "古", "戈", "廖", "庾", "步", "都", "耿", "满", "弘", "越", "师", "巩", "简", "养", "关", "蒯",};
        String[] xs_L = {"容", "幸", "司", "韶", "郜", "黎", "蓟", "薄", "印", "宿", "怀", "蒲", "邰", "从", "鄂", "索", "咸", "籍", "盖", "桓", "游", "竺", "刁", "包", "荣", "翁", "芮", "汲", "詹", "束", "龙", "池", "习", "艾", "匡", "夔", "敖", "冷", "阚", "饶", "沙", "水", "卜", "平",
                "司马", "上官", "欧阳", "夏侯", "诸葛", "闻人", "东方","赫连", "皇甫", "尉迟", "公羊", "澹台", "公冶", "宗政", "濮阳", "公孙", "令狐", "钟离", "宇文", "长孙", "慕容", "司徒", "司空", "端木", "拓跋", "百里", "呼延", "归海", "左丘", "西门", "伯赏", "南宫"};
        String name1 = "登仕光国名显家邦天呈祖德万世永昌力昭诒训芳远泽深美彦振起宣述儒林际逢景连为琛忠孝纯和懿恭盛肃雍礼乐耀文章周道宏元化明才立大纲雄姿腾夏校媚寿养虞庠温润珪璋器清芬兰惠香乃云承武蕃衍久传添汝鸾循女志思心廷金之长厚人存启裔经营多秀实绩治载黄博学诗书富宗月桂乾坤敦蔚仲伯笃燕翼贻谋基良季孙分派代与作仁本克树其崇凤贵钦维先有利朝占阵宜庭子以曰可斯用展成尚修于奕后品智孔从中则仰资绳济广观裕咸齐发祥友守统绪必哲士正高恩兴龙善积福隆读华炳慈继匡鸿行泰教衣冠第叶贞全节义弟伦常联惟翠应荣公佐相焕尧锡巨杨恒运敬诵红田业余年真勤俭千秋卜愈奇献时肇纪希贤求是自生皇史得顺定再政通甲绍升魁位嗣庆宪殿一珍培事示君帮民安宾玉允康英雅声信弘典选言扬四举照来戎赵贲威峻卿辅程皓策楚如建新益秉加植在诚筩阔熙亦佰祚能导科室勇飞柏东西南北春前促保姬开纹佑兆冈彰跃馨勋掌珠超群众古海悠平达干嘉敏锦穗吉阳御重少采次轻青倾羽寻药羡碧弼上淳见间弗伏夫孚甫缚芙负晨沉尘寰环幻还欢桓嬛会回梦禹奉衡";

        String name2 = "元绪允启熙康英才昌济明德纯雅学智名馨锡勋华器珠天必有常群益众万古传扬善积海兰桂腾芳恒心立业福悠长泰平安文章达朝廷庆仲干嘉裕敏锦穗礼孝仁荣士风维振世步岳亦宏鹰定霖前成坤树其云邦基能仕科高光瑜新茂猷盛宗秀毓百展观胜来信双崇祝尚至君家佑永声尔兴肖孙春俊烈贤润殿开绍远绵玉希顺致弘歧勲宽克赵汉净应正山上支武广兆昭惟政月星培延贵继友金臣悦子一放须为松竹守志逖洪书增承聚义谨怀先台凤林祥奉庭升进忠转登言配求多魁伯全秉耀宣龙待育衡湘伟申显再本客杰厚翠懋方思弟诗资谦复勤俭代规廉庞呈瑞良幸逢太第保斯惠之道师钟源焕鉴栋炽标炳均汝材辉轲伍胥相如西施白坚尧问不韦九龄舞阳周拯知渊潜石晦恭殊几颜真卿期商隐物玄机灵运景匡胤邈年起素圣叹籍咸聪睿温柔毅斋庄中理密察溥博泉禄寿辨东壁图帧府园翰墨诵闻讲易见雍时日礽祖水薪似续隆殷从公翁若久训衍聿令嗣且遗六千笃诚肃肇典牍敦睦循秩和郊厘司季贯通表阐孟以手仍存行丕敬江桐庐亨利贞昂彦浙汤溪宁蕃生演宜地黄荒盈昃辰宿列张寒遵意姻序伦讳字母用因可川此语洵然岸苾芬廊庙堂优柏煌封镐徽休力诒泽深美述儒际连琛懿乐化纲雄姿夏校媚养虞庠珪璋清香添人经营实绩治载乾蔚燕翼贻谋派与作钦占阵曰修于奕后品孔仰绳齐北统哲恩读慈教衣冠叶节联佐杨红田余秋卜愈奇献纪是皇史得珍示民宾选举照戎贲威峻辅程皓策楚加植筩阔佰祚飞南促姬彰跃极嬴钧青倾陵御羽予钰煜誉鹭鹤雁鸾鹏雀鸿缘象午巽珣寻逊尺丹璧弼布罡陌默铭冥鸣瞑铮峥州舟洲胄淳瑃蹇简建谏翦戬伤殇伏夫孚甫仪甲乙丙丁易异译逸疑夷遗颐缚芙负辛帧臻镇震桢祯蓁禛晨沉尘宸忱崖涯环寰幻还欢桓嬛会晖回卉珲牧虚栩";
//        System.out.println("xingshi 长度-->"+xingshi.length);//365
//        System.out.println("name1 长度-->"+name1.length());//377
//        System.out.println("name2 长度-->"+name2.length());//597

        //男名
        if(true){
            for (int i = 0; i< 50 ; i++){
                //姓氏概率： 0：稀有姓氏；(0,3]:少量使用率姓氏； (3,6]:高使用率率姓氏； (6,9]:大姓
                int xsProbability = (int)(Math.random()*10);
                String x = "";
                if (xsProbability < 1){
                    x = xs_L[(int)(Math.random()*xs_L.length)];
                }else if ( xsProbability >0 && xsProbability <= 4){
                    x = xs_M[(int)(Math.random()*xs_M.length)];
                }else if (xsProbability > 4 && xsProbability <= 7){
                    x = xs_H[(int)(Math.random()*xs_H.length)];
                }else {
                    x = xs_S[(int)(Math.random()*xs_S.length)];
                }
                int b_num = (int)(Math.random()*name1.length());
                String b = name1.substring(b_num,b_num+1);
                int m_num = (int)(Math.random()*name2.length());
                String m = name2.substring(m_num,m_num+1);
                System.out.println(x+b+m);
            }
        }
        System.out.println("------------");

        String womenName1 = "鲜慧巧美颖露瑶怡婵雁蓓纨仪荷丹蓉眉君琴蕊薇菁梦岚苑婕馨瑗琰娜静淑惠珠翠雅芝玉萍红娥玲芬芳燕彩春菊兰凤洁梅琳素云莲真环雪荣爱妹霞香月莺媛艳瑞凡佳嘉琼勤珍贞莉桂娣叶璧璐娅琦晶妍茜秋珊莎锦黛青倩婷姣婉娴瑾韵融园艺咏卿聪澜纯毓悦昭冰爽琬茗羽希宁欣飘育滢馥筠柔竹霭凝晓欢霄枫芸菲寒伊亚宜可姬舒影荔枝思丽秀娟英华依蓝蝶寻姿佩怜俞倪倰偀偲妆亿曼桃卉画容紫宛南娉朵绮童桦涵向映珞诗婧千语采苏萌姝如夏雨梵沛汐虹慕若友易文楚彦洋芙逸媱冉灵妤雯含恬以淇玥白睿妮苇飞碧泉儿潼清金兮菡苛娇奕水觅筱萱蕾初平然乐念双妙安笑贝惜知元晴歆龄忆一小夕子见风匀心允予书归甲禾仙冬立奴圣芒臣至师尘曲同帆岁回年朱先迁乔后庄齐次衣阳好观麦花芹杏杨求辰还来连时伯近余谷迎言辛忘间灿阿妖现规幸苹苗范直林杰枣非畅明图季鱼净泽宝承绍荐药柳轻盼显星适俭信剑勉亭亮度庭音闻养浊济恒觉宣宫祖神姻盈盏起都莫晋桐栗殊顾眠唤圆铃乘值倾俱途颂席准离唐资凉益兼烛烟酒润继黄萝雀常晨野银甜符敏渐深情惊绿敬朝葵景遗智程善羡道温游谣缘蒙榆楼感鉴睛暖照遥意慈源璃暮歌裳舞箩樱瞒墨黎德颜薪默镜衡霜艾玄廷仲旭讳玖芜杈轩肖牡佑彤灼沐忱纬拓茉昔枚郁歧卓昙秉弥函茵勃钦钧衍洛屏姚莱莹鸯殷鸳斋菱萤萧梧冕婴铭矫笙凰阐鸿渊惋寂谚棠鹃媚椿频稚靖赫熙榛箫舆澈鹤薛穆徽藤瞻仕光名天呈万世永训远述儒际逢为琛孝和懿恭盛肃礼章周化才虞乃鸾志之长存经营多实治学宗翼良孙与作本克其贵维占展成尚修于从中则仰必士正恩兴龙积福匡行冠第节义伦惟应公佐焕尧田业卜愈献纪贤是自生皇得定再通嗣庆殿培事示宾声弘典选扬四新加植在诚筩亦勇东西北前兆超群古海悠达穗吉御重少上淳弗缚负沉寰幻桓嬛会禹奉";
        String womenName2 = "鲜慧巧美颖露瑶怡婵雁蓓纨仪荷丹蓉眉君琴蕊薇菁梦岚苑婕馨瑗琰娜静淑惠珠翠雅芝玉萍红娥玲芬芳燕彩春菊兰凤洁梅琳素云莲真环雪荣爱妹霞香月莺媛艳瑞凡佳嘉琼勤珍贞莉桂娣叶璧璐娅琦晶妍茜秋珊莎锦黛青倩婷姣婉娴瑾韵融园艺咏卿聪澜纯毓悦昭冰爽琬茗羽希宁欣飘育滢馥筠柔竹霭凝晓欢霄枫芸菲寒伊亚宜可姬舒影荔枝思丽秀娟英华依蓝蝶寻姿佩怜俞倪倰偀偲妆亿曼桃卉画容紫宛南娉朵绮童桦涵向映珞诗婧千语采苏萌姝如夏雨梵沛汐虹慕若易文楚彦洋芙逸媱冉灵妤雯含恬以淇玥白睿妮苇飞碧泉儿潼清金兮菡苛娇奕水觅筱萱蕾初平然乐念双妙安笑贝惜知元晴歆龄忆一小夕子见风匀心允予书归禾仙冬立奴圣芒臣至师尘曲同帆回朱乔后庄衣好观麦花芹杏杨辰还来连时迎言辛忘灿阿妖幸苹苗直非畅图鱼净宝荐药柳轻盼显星勉亭亮音闻养宣宫神姻盈盏起莫晋桐栗殊顾眠唤圆铃乘值倾俱颂席离资凉烟酒黄萝雀晨银甜符敏渐情惊绿敬朝葵景程善羡道温游谣缘蒙榆楼感鉴睛暖照意慈源璃暮歌裳舞箩樱瞒墨黎德颜薪默镜霜艾玄玖芜杈轩肖牡佑彤灼沐忱纬茉昔枚郁卓昙秉弥函茵衍洛屏莱莹鸯殷鸳斋菱萤萧梧冕婴铭矫笙凰阐鸿惋寂谚棠鹃媚椿靖赫熙榛箫舆澈鹤穆徽藤瞻昕";
        //女名
        if(true){
            for (int i = 0; i< 50 ; i++){
                //姓氏概率： 0：稀有姓氏；(0,3]:少量使用率姓氏； (3,6]:高使用率率姓氏； (6,9]:大姓
                int xsProbability = (int)(Math.random()*10);
                String x = "";
                if (xsProbability < 1){
                    x = xs_L[(int)(Math.random()*xs_L.length)];
                }else if ( xsProbability >0 && xsProbability <= 4){
                    x = xs_M[(int)(Math.random()*xs_M.length)];
                }else if (xsProbability > 4 && xsProbability <= 7){
                    x = xs_H[(int)(Math.random()*xs_H.length)];
                }else {
                    x = xs_S[(int)(Math.random()*xs_S.length)];
                }
                int b_num = (int)(Math.random()*womenName1.length());
                String b = womenName1.substring(b_num,b_num+1);
                int m_num = (int)(Math.random()*womenName2.length());
                String m = womenName2.substring(m_num,m_num+1);
                System.out.println(x+b+m);
            }
        }

        return null;
    }

    public static void createDongTianName(){
        String words1 = "玄神光泰正灵元一二三四五六七八九十百千万阴阳日月乾坤星辰金木水火土炎绛朱赤丹苍青黄白碧清心方至观天端尘上真镇帧定度都渡烈行参同封照明持显传重夺浮沉谌寻策还环乘悬遗疾裂原源渊诸惊钧落寒游虚玉通图伐秘密无乌鉴斗云霄胜泊更开始煞气合劫界轮关回泽诛隐潮血禁冲绝离机相引化巨具聚拘见擒捉易召伏缚幽影盘微涌觅芒德乱洞断";
        String words2 = "玄神光泰正灵元一千万阴阳日月乾坤星辰金木水火土炎绛朱赤丹苍青黄白碧清心方至观天端冥尘上真镇帧定度都渡烈行参同封照明持显传重夺浮沉谌寻策还环乘悬遗疾裂原源渊诸惊钧落寒游虚玉通图伐秘密无乌鉴斗云霄胜泊更开始煞气合劫界轮关回泽诛隐潮血禁冲绝离机相引化巨具聚拘见擒捉易召伏缚幽影盘微涌觅芒德乱洞断";

        for (int i = 0; i < 200; i++ ){
            int word1_num = (int)(Math.random()*words1.length());
            int word2_num = (int)(Math.random()*words2.length());
            String word1 = words1.substring(word1_num,word1_num+1);
            String word2 = words2.substring(word2_num,word2_num+1);

            System.out.println(word1+word2);
        }

    }

    /**
     * 去除重复字
     */
    private static String removeReWord(String words){
        words = words.trim();
        StringBuffer result = new StringBuffer();
        int n1Length = words.length();
        for (int i = 0; i< n1Length; i++){
            String nameString = words.substring(i,i+1);
            if (result.indexOf(nameString) < 0){//
                result.append(nameString);
            }
        }
        System.out.println(result.toString());
        return  result.toString();
    }

    /**
     * 字符串转集合
     */
    private static void test (String a){
//        a = "赵钱孙李周吴郑王冯陈褚卫蒋沈韩杨朱秦尤许何吕施张孔曹严华金魏陶姜戚谢邹喻柏水窦章云苏潘葛奚范彭郎鲁韦昌马苗凤花方俞任袁柳酆鲍史唐费廉岑薛雷贺倪汤滕殷罗毕郝邬安常乐于时傅皮卞齐康伍余元卜顾孟平黄和穆萧尹姚邵湛汪祁毛禹狄米贝明臧计伏成戴谈宋茅庞熊纪舒屈项祝董梁杜阮蓝闵席季麻贾路娄危江童颜郭梅盛林刁钟徐邱骆高夏蔡田樊胡凌霍虞万支柯昝管卢莫经房裘缪干解应宗丁宣贲邓郁单杭洪包诸左石崔吉龚程嵇邢裴陆荣翁荀羊於惠甄曲家封芮储靳汲邴糜井段富巫乌焦巴牧隗山谷侯宓蓬全郗班秋仲伊宁仇栾甘厉戎祖武符刘景詹束龙叶幸司韶郜黎蓟薄印宿白怀蒲邰从鄂索咸籍赖卓蔺屠蒙池乔阴郁胥能苍双闻莘党翟谭贡劳姬申扶冉宰郦雍却璩桑桂濮牛寿通边扈燕冀郏尚温别庄晏柴瞿阎慕连习艾鱼容向古易戈廖庾终暨居衡步都耿满弘匡文寇阙殳蔚越夔师巩聂晁敖冷訾辛阚简饶曾沙养关蒯相荆游竺权盖桓公";
        int aLength = a.length();
        System.out.println("length -->"+aLength);
        String[] x = new String[aLength];
        String result = "{";
        String dunhao = "";
        for (int i = 0; i < aLength; i++){
            result = result+ "\"" + a.substring(i,i+1)+ "\", ";
            dunhao = dunhao+a.substring(i,i+1)+"、";
        }
        result= result.substring(0,result.length()-2)+"}";

        System.out.println("result -->"+result);
        System.out.println("dunhao -->"+dunhao);
    }

    public static void main(String[] args) {
        getName();
        createDongTianName();
    }

}
