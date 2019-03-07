<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@include file="/context/mytags.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>${info.title}</title>
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0" />
    <meta name="apple-mobile-web-app-status-bar-style" content="black" />
    <meta name="format-detection" content="telphone=no, email=no" />
    <meta name="apple-mobile-web-app-capable" content="yes" />
    <meta name="msapplication-tap-highlight" content="no">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="screen-orientation" content="portrait">
    <meta name="x5-orientation" content="portrait">
    <meta name="browsermode" content="application">
    <meta name="MobileOptimized" content="320">
    <meta name="HandheldFriendly" content="true">
    <meta name="x5-fullscreen" content="true">
    <meta name="x5-page-mode" content="app">
    <meta name="renderer" content="webkit">
    <meta name="full-screen" content="yes">
    <link rel="stylesheet" href="../static/common/css/reset.css">
    <script>
    (function (doc, win) {
        var docEl = doc.documentElement,
            resizeEvt = 'orientationchange' in window ? 'orientationchange' : 'resize',
            recalc = function () {
                var clientWidth = docEl.clientWidth;
                if (!clientWidth) return;
                if(clientWidth>=640){
                    docEl.style.fontSize = '100px';
                }else{
                    docEl.style.fontSize = 100 * (clientWidth / 640) + 'px';
                }
            };

        if (!doc.addEventListener) return;
        win.addEventListener(resizeEvt, recalc, false);
        doc.addEventListener('DOMContentLoaded', recalc, false);
    })(document, window);
        /** TODO 正则替换src路径 */
        window.onload = function() {
            var infoContent = document.querySelector(".infoContent");
            infoContent.innerHTML = infoContent.innerHTML.replace(new RegExp(/src="lifekeepers_files/gi), 'src="../');
        }
    </script>
    <style>
        .container .item-content{
            padding:.18rem .15rem .15rem;
        } 
        .item-content-left section{
            margin-bottom:.1rem;
        }
        .item-content-left h3{
            font-size: .32rem;
            line-height: 1.7;
            color:#333;
        }
        
        .item-content-left p{
            line-height: 1.7;
            font-size: .288rem;
            color: #333;
            max-width: 100%;
            word-wrap: break-word;
            word-break: break-all;
            margin-top: .12rem;
            margin-bottom:.1rem;
            -webkit-tap-highlight-color: rgba(255, 255, 255, 0);
        }
        .item-content-left .infoContent{
            padding-bottom:.2rem;
        }
        .item-content-left a.source-url{
            font-size:.22rem;
            color:#333;
            vertical-align:middle;
        }
        .item-content-left strong{
            font-weight: bold;
        }
        .item-content-left span{
            font-size:.28rem !important;
        }
        .item-content-left span.date{
            vertical-align:middle;
            font-size:.22rem !important;
            color:#555;
            margin-left:.32rem;
        }
        .item-content-left a.source-url img{
            margin-right:.08rem;
            vertical-align:middle;
        }
    </style>
</head>
<body>
    <div class="container">
        <div class="item-content">
            <div class="item-content-left">
                <section>
                    <div class="infoContent">
                        ${info.content}
                    </div>
                </section>
            </div>
        </div>
    </div>
</body>
</html>