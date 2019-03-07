<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="x-ua-compatible" content="IE=Edge,chrome=1">
    <meta name="renderer" content="webkit">
    <meta name="description" content="keywords">
    <link rel="stylesheet" href="css/common.css">
    <link rel="stylesheet" type="text/css" href="http://apps.bdimg.com/libs/bootstrap/3.3.4/css/bootstrap.css">
    <link rel="stylesheet" href="css/healthInformation.css">
    <script src="http://apps.bdimg.com/libs/jquery/2.1.4/jquery.js"></script>
    <title>正文</title>
</head>
<body>
   <div class="webPage wrap" style="border: 1px solid #ddd !important">
		<%@include file="/context/header.jsp"%>
		<div class="view-body wrap">
			<%@include file="/context/nav_left.jsp"%>
            <div class="right-wrap" style="padding-top: 30px">
			<div class="container fr" style="margin-top: 0">
        <div class="healthInfor">
            <h3>正文</h3>
            <div>
                <form id="searchForm">
                    <input type="search" id="searchCondition" autocomplete="off" placeholder="站内搜索">
                    <button id="searchUser">搜索</button>
                </form>
            </div>
        </div>
        <div class="item-content">
            <div class="item-content-left">
                <section>
                    <h3 style="text-align:center;font-weight:500;font-size:20px;">大笑健身 何乐而不为</h3>
                    <p>
                        <a href="#"><img src="/static/images/sADtor.png" alt="">求医网</a>
                        <span>2016-09-12</span>
                        <button>+收藏</button>
                    </p>
                    <div style="line-height: 26px;padding-bottom: 20px ;border-bottom: 1px solid #ddd">　　
                        <p>　　印度有笑诊疗所，法国有笑俱乐部，瑞士有笑面馆，日本有笑学校，德国有笑比赛，美国有笑医院……</p>

                        <p>　　笑是一种良好的健身运动，笑是一种最有效的消化剂，笑能增强人体的免疫力、提高机体的抗病能力。爱笑，是长寿者的共同特点，这是日本学者在印度调查长寿老人得出的结论。</p>
                        <p  style="text-align:center;padding:10px"><img src="/static/images/smile.jpg" alt="" width="200"></p>
                        <p>　　有学者把20～60岁的志愿者请到演出厅观看了3小时喜剧小品，使之开怀大笑。结果从血液中测出的淋巴细胞比原来都有升高，笑了3小时尚且如此，那么经常欢笑的人肯定效果更好。淋巴细胞特别能战斗，是身体的守护神，但它们需要“一日三笑”作为“精神食粮”。不仅是大笑，就是微笑，乃至有个笑的表情，也能调动起淋巴细胞的积极性。</p>

                        <p>　　伟大作家高尔基说：“只有爱笑的人，生活才能过得更美好。”笑的好处和对健康的作用已为越来越多的人们所认识。现在，笑的疗法风靡世界，笑的行业应运而生———印度有笑诊疗所、法国有笑俱乐部、瑞士有笑面馆、日本有笑学校、德国有笑比赛、美国有笑医院。</p>

                        <p>　　人在笑时，下颌处于下移状态，该部位的下移是人体放松的关键。能使人从紧张状态中放松的方法，莫过于一笑，平时万念纷飞的大脑只有在笑的时候，才进入了无念无为的纯净状态，大脑处于一片空白。德国科伦大学的乌伦克鲁教授说，笑一分钟，相当于一个病人进行了45分钟的松弛锻炼，这就是精神放松法。</p>

                        <p>　　一个人大笑时肩膀会耸动、胸膛摇摆、横膈膜震荡，血液含氧量于呼吸加速时增加，而更重要的是脑部会释放出一种化学物质，令人感到心旷神怡，实在是最佳的自然药物。乌伦克鲁教授说，大笑过后，血压会回降、减少分泌令人紧张的荷尔蒙，发自内心的笑是精神状态与免疫系统之间直接相连的“天线”，可以在瞬间增强免疫系统的功能。</p>

                        <p>　　世界卫生组织认为，健康是身体的、精神的健康和社会幸福的完善状态，不难看出，笑是唯一能覆盖身体、精神、社会这三个方面的“全能”高手。那就让我们开怀大笑吧!</p>
                    </div>
                    <p style="padding:20px 0">
                        <label>分享：</label>
                        <img src="/static/images/sina.png" alt="">
                        <img src="/static/images/tx.png" alt="">
                        <img src="/static/images/zoom.png" alt="">
                        <img src="/static/images/wechat_2.png" alt="">
                    </p>
                </section>
            </div>
            <div class="item-content-right">
                <div class="item-content-top">
                    <div class="ystuijian">
                        <p class="title"><a href="#">养生推荐</a></p>
                    </div>
                    <div class="slider">
                        <ul class="slide-main">
                            <li><a href="#"><img src="/static/images/extendBanner.png" alt=""></a></li>
                        </ul>
                        <ul class="slider-extra">
                            <li></li>
                            <li></li>
                            <li></li>
                            <li></li>
                        </ul>
                    </div>
                </div>
                <div class="item-content-top">
                    <div class="ystuijian">
                        <p class="title"><a href="#">本月排行</a></p>
                    </div>
                    <ul class="hotNews">
                        <li>
                            <a href="#" style="display:block;overflow:hidden;">
                                <div style="padding:10px 0;">
                                    <img src="/static/images/coffee.png" alt="">
                                </div>
                                <p>
                                    <strong>NO.1</strong><br>
                                    养脾就是养身材，脾是人体中消化排毒的重要器官，无论是胖子还是... <strong>[详细]</strong>
                                </p>
                            </a>
                        </li>
                        <li class="news-title"><a href="#"><span>四叶参的食用禁忌 药用特性介绍</span></a></li>
                        <li class="news-title"><a href="#"><span>人中白的治病验方有哪些？</span></a></li>
                        <li class="news-title"><a href="#"><span>人中白的治病验方有哪些？</span></a></li>
                        <li class="news-title"><a href="#"><span>人中白的治病验方有哪些？</span></a></li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
                </div>
    </div>
    </div>
</body>
</html>
<script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
<script>
    if(/Android|webOS|iPhone|iPod|BlackBerry/i.test(navigator.userAgent)){

    }
</script>