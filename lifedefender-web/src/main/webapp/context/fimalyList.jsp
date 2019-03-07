<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<div class="familyList nav_none" id= "familyList">
	<dl>
		<dt>亲情号码</dt>
		<dd>C3可选择3个亲情号码</dd>
	</dl>
	
	<div class="familyList_add item-fr"><span>添加号码</span></div>
	<table id="familyLists">
		<thead>
			<tr>
				<th>姓名</th>
				<th>手机号码</th>
				<th>操作</th>
			</tr>
			
		</thead>
		<tbody>
		
		</tbody>
	</table>
</div>
<ul class="familyList_form" style="display: none">
	<li><input type="text" name="familyList_number" placeholder="请输入手机号码"></li>
</ul>
<div class="dialog_container" style = "padding: 30px; display: none;">
	<p>
		<select id = "allContact" style = "width: 200px; height:35px; border: solid 1px #ccc;">
			<!-- <option>蔡文贤&nbsp;&nbsp;&nbsp;&nbsp;<span>134012119	</span></option>
			<option>蔡文贤&nbsp;&nbsp;&nbsp;&nbsp;<span>134012119	</span></option>
			<option>蔡文贤&nbsp;&nbsp;&nbsp;&nbsp;<span>134012119	</span></option> -->
		</select>
		<a href="javascript:void(0)<%--memberControl.do?showWarn--%>">
			<span class = "addContact" id = "addContact" style = "padding-left: 5px;" onclick="addContact()">添加</span>
		</a>
	</p>
</div>

<div class="addContactDialog" style="display: none">
	<ul name="addInfor" class="addInfor layui-layer-wrap" style="">
		<li><label>姓名</label><br> <input type="text" name="userName" class="userName" placeholder="请输入姓名"></li>
		<li><label>手机号码</label><br> <input type="text" name="contactNumber" class="contactNumber" placeholder="请输入手机号码"></li>
	</ul>
</div>
<style>
	.addInfor {
		padding: 20px;
	}
	ul, ol {
		list-style: none;
	}
	.addInfor li {
		margin-bottom: 10px;
	}
	.addInfor li input {
		width: 290px;
		height: 40px;
		border: 1px solid #ddd;
		border-radius: 3px;
		padding: 0 8px;
		margin-top: 6px;
	}
	.delete {
		cursor: pointer;
	}
</style>
<script>
	var addContact = function () {
	    layer.closeAll('page');
        layer.open({
            type: 1,
            area: ['350px', '300px'],
            title: ['添加亲情号码',
                'text-align:center;font-size:16px;background:#fff;'],
            moveType: 1,
            scrollbar: false,
            zIndex: 9999,
            content: $('.addContactDialog'),
            btn: ['确定', '取消'],
            success: function () {

            },
            yes: function (index) {
                var obj = {
                    name: $('.userName').val(),
                	contactNumber: $('.contactNumber').val(),
                    isSOS: false
				}
                if (obj.name == '' ) {
                    layer.msg('请输入联系人姓名');
                    return;
				}
                if (obj.contactNumber == '' ) {
                    layer.msg('请输入手机号码');
                    reutrn;
                }
                var chReg = /^[\u4e00-\u9fa5 ]{2,20}$/;//中文正则表达
                var enReg=/^[a-z\/ ]{2,20}$/i ;//英文正则表达
                if(!chReg.test(obj.name)&&!enReg.test(obj.name)){
                    layer.msg("请输入正确的姓名格式");
                    return ;
                }
                var regex = /^[1][3,4,5,7,8][0-9]{9}$/;
                if(!regex.test(obj.contactNumber)){
                    layer.msg("手机号码格式不正确");
                    return ;
                }
                $.ajax({
                    async : true,
                    cache : false,
                    type : 'POST',
                    url: "/memberControl.do?addContact",
                    data: obj,
                    dataType: 'json',
                    contentType: 'application/x-www-form-urlencoded; charset=utf-8',
                    beforeSend:function(){
                        layer.load();
                        setTimeout(function(){
                            layer.closeAll("page");
                        }, 800);
                    },
                    complete:function(){
                        setTimeout(function(){
                            layer.closeAll('loading');
                        }, 1000);
                    },
                    success: function(result) {
                        layer.msg(result.msg);
                        if(result.success){
                            obj.contactId = result.obj;
                            obj.terminalType = 'C3';
                            addFamily(obj);
                        }
                    }
                });
            }
        });
    }

    var addFamily = function (obj) {
        jQuery.ajax({
            async: true,
            cache: false,
            type: 'POST',
            url: '/terminalWebControl.do?addFamily',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(obj),
            beforeSend: function(){
                setTimeout(function(){
                    layer.closeAll("page");
                }, 800);
            },
            success: function(result){
                layer.msg(result.msg);
            },
            complete: function(){
                var $this = jQuery("div.container>ul>li.action");
                setTimeout(function(){
                    _clickNav($this);
                }, 1000);
            }
        });
    }
</script>