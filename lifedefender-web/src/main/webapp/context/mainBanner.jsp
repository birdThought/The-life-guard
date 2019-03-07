<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<div id="banner_tabs" class="flexslider">
	<ul class="slides">
		<li><a title="" target="_blank" href="#"> <img alt=""
				src="/static/images/banner11.jpg">
		</a></li>
		<li><a title="" href="#"> <img alt=""
				src="/static/images/banner22.jpg">
		</a></li>
		<li><a title="" href="#"> <img alt=""
				src="/static/images/banner33.jpg">
		</a></li>
	</ul>
	<ul class="flex-direction-nav">
		<li><a class="flex-prev" href="javascript:;">Previous</a></li>
		<li><a class="flex-next" href="javascript:;">Next</a></li>
	</ul>
	<ol id="bannerCtrl" class="flex-control-nav flex-control-paging">
		<li><a>1</a></li>
		<li><a>2</a></li>
		<li><a>3</a></li>
	</ol>
</div>
<script type="text/javascript">
    $(function() {
        var bannerSlider = new Slider($('#banner_tabs'), {
            time: 5000,
            delay: 400,
            event: 'hover',
            auto: true,
            mode: 'fade',
            controller: $('#bannerCtrl'),
            activeControllerCls: 'active'
        });
        $('#banner_tabs .flex-prev').click(function() {
            bannerSlider.prev()
        });
        $('#banner_tabs .flex-next').click(function() {
            bannerSlider.next()
        });
    })
</script>