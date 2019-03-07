function createNode(tag) {
	return document.createElement(tag);
}

var TimePicker = {
        dateInputId: null,
        isShowing: false,
        timeSub: "",// 时间数组截断
        showTimePicker: function (obj) {
            if (this.isShowing) {
                this.onHidden();
                return;
            }
            $(obj).val() == '' ? this.timeSub = "00:00".split(":")
                    : this.timeSub = $(obj).val().split(":");
            this.dateInputId = $(obj).attr("id");
           
            var timer = this.createTimePicker();
            
            timer.style.position = 'absolute';
            timer.style.left = obj.getBoundingClientRect().left + "px";
            timer.style.top = obj.getBoundingClientRect().top + obj.offsetHeight
                    + $(document).scrollTop() + "px";
            document.body.appendChild(timer);
           
            this.onSelect(0, this.timeSub[0]);
            this.onSelect(1, this.timeSub[1]);
            
            this.isShowing = true;
            $(document).bind("click", function (event) {
                if ($(event.target).attr("id") != $(obj).attr("id") && $(event.target).attr("class") != "timeBox" && $(event.target).parents(".timeBox").length <= 0) {
                    TimePicker.onHidden();
                }
            });
        },
        createTimePicker: function (parent) {
            var timerContainer = createNode("div");
            timerContainer.setAttribute("class", "timeBox");
            var timePickerTitle = createNode("div");
            timePickerTitle.setAttribute("class", "titleTop");
            timePickerTitle.innerText = "时间选择";
            var timeControl = createNode("div");
            timeControl.setAttribute("class", "timeControl");
            var timeBot = createNode("div");
            timeBot.setAttribute("class", "timeBottom");
            var hourItemContainer = createNode("div");
            var minItemContainer = createNode("div");
            hourItemContainer.setAttribute("class", "itemContain");
            minItemContainer.setAttribute("class", "itemContain");
            var hourItem_title = createNode("div");
            hourItem_title.setAttribute("class", "hourTitle");
            hourItem_title.innerText = "小时";
            var hourItemSelect = createNode("div");
            hourItemSelect.setAttribute("id", "hourSelect");
            this.initData(hourItemSelect, 0);
            var minItemTitle = createNode("div");
            minItemTitle.setAttribute("class", "minTitle");
            minItemTitle.innerText = "分钟";
            var minItemSelect = createNode("div");
            minItemSelect.setAttribute("id", "minSelect");
            this.initData(minItemSelect, 1);
            hourItemContainer.appendChild(hourItem_title);
            hourItemContainer.appendChild(hourItemSelect);
            minItemContainer.appendChild(minItemTitle);
            minItemContainer.appendChild(minItemSelect);
            timeControl.appendChild(hourItemContainer);
            timeControl.appendChild(minItemContainer);
            var timeTextContain = createNode("div");
            timeTextContain.setAttribute("class", "timeTextContain");
            var hoursSelectText = createNode("input");
            hoursSelectText.setAttribute("id", "hoursText");
            hoursSelectText.setAttribute("maxlength", "2");
            var minSelectText = createNode("input");
            minSelectText.setAttribute("id", "minText");
            minSelectText.setAttribute("maxlength", "2");
            $(hoursSelectText).val(this.timeSub[0]);
            $(minSelectText).val(this.timeSub[1]);
            $(hoursSelectText).attr("type", "tel");
            $(minSelectText).attr("type", "tel");
            $(hoursSelectText).attr("oninput", "TimePicker.onTextChange(0,this.value)");
            $(minSelectText).attr("oninput", "TimePicker.onTextChange(1,this.value)");
            var maohao = createNode("span");
            maohao.innerText = "：";
            timeTextContain.appendChild(hoursSelectText);
            timeTextContain.appendChild(maohao);
            timeTextContain.appendChild(minSelectText);
            var enterBtn = createNode("div");
            enterBtn.setAttribute("class", "timeEnter");
            enterBtn.innerText = "确认";
            $(enterBtn).click(function () {
                TimePicker.onHidden();
            });
            timeBot.appendChild(timeTextContain);
            timeBot.appendChild(enterBtn);
            timerContainer.appendChild(timePickerTitle);
            timerContainer.appendChild(timeControl);
            timerContainer.appendChild(timeBot);
            return timerContainer;
        },
        initData: function (obj, target) {
            switch (target) {
                case 0:// 小时
                    for (var i = 0; i < 24; i++) {
                        var item = createNode("p");
                        i < 10 ? item.innerText = "0" + i : item.innerText = i;
                        item.setAttribute("onclick", "TimePicker.onAction(this,this.innerText)");
                        if (item.innerText == this.timeSub[0])
                            $(item).addClass("action");
                        obj.appendChild(item);
                    }
                    break;
                case 1:// 分钟
                    for (var i = 0; i < 60; i++) {
                        var item = createNode("p");
                        i < 10 ? item.innerText = "0" + i : item.innerText = i;
                        item.setAttribute("onclick", "TimePicker.onAction(this,this.innerText)");
                        if (item.innerText == this.timeSub[1])
                            $(item).addClass("action");
                        obj.appendChild(item);
                    }
                    break;
            }
        },
        onAction: function (obj, value) {
            $(obj).siblings("p").removeClass("action");
            $(obj).addClass("action");
            var parentId = $(obj).parent().attr("id");
            switch (parentId) {
                case "hourSelect":
                    $("#hoursText").val(value);
                    $("#" + this.dateInputId).val($("#hourSelect p.action").text() + ":" + $("#minSelect p.action").text());
                    console.log($("#hourSelect p.action").text() + ":" + $("#minSelect p.action").text())
                    break;
                case "minSelect":
                    $("#minText").val(value);
                    $("#" + this.dateInputId).val($("#hourSelect p.action").text() + ":" + $("#minSelect p.action").text());
                    break;
            }
        },
        onHidden: function () {
            this.isShowing = false;
            $(".timeBox").remove();
            $(document).unbind("click");
        }, onSelect: function (target, value) {
        	
            var key = value.length == 2 ? value : '0' + value;
            switch (target) {
                case 0://小时
                    var mainContainer = $('#hourSelect'),
                    scrollToContainer = mainContainer.find('p:contains(' + key + ')');
                    mainContainer.scrollTop(
                      scrollToContainer.offset().top - mainContainer.offset().top + mainContainer.scrollTop()
                    );
                    this.onAction(scrollToContainer, value);
                    break;
                case 1://分钟
                    var mainContainer = $('#minSelect'),
                   scrollToContainer = mainContainer.find('p:contains(' + key + ')');
                    mainContainer.scrollTop(
                      scrollToContainer.offset().top - mainContainer.offset().top + mainContainer.scrollTop()
                    );
                    this.onAction(scrollToContainer, value);
                    break;
            }
        }, onTextChange: function (target, value) {
            if (value == '')
                return;
            if (isNaN(value)) {
                layer.msg("请输入数字");
                return;
            }
            if (value > 23 && target == 0) {
            	layer.msg("不能输入大于23的数字");
                return;
            } else if (value > 59 && target == 1) {
            	layer.msg("不能输入大于59的数字");
                return;
            }
            this.onSelect(target, value);
        }
    }