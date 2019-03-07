<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<style>
	tr td:nth-child(1){
	width:268px;
	height:50px
	}
	
@font-face {font-family: "iconfont";
  src: url('iconfont.eot?t=1515486014818'); /* IE9*/
  src: url('iconfont.eot?t=1515486014818#iefix') format('embedded-opentype'), /* IE6-IE8 */
  url('data:application/x-font-woff;charset=utf-8;base64,d09GRgABAAAAAATgAAsAAAAABygAAQAAAAAAAAAAAAAAAAAAAAAAAAAAAABHU1VCAAABCAAAADMAAABCsP6z7U9TLzIAAAE8AAAAQwAAAFZW7kgcY21hcAAAAYAAAABeAAABhpm8Br5nbHlmAAAB4AAAAQ0AAAEUfpFlzWhlYWQAAALwAAAALwAAADYQFHxyaGhlYQAAAyAAAAAeAAAAJAfeA7ZobXR4AAADQAAAAAwAAAAMDBsAAGxvY2EAAANMAAAACAAAAAgAdgCKbWF4cAAAA1QAAAAfAAAAIAESAF1uYW1lAAADdAAAAUUAAAJtPlT+fXBvc3QAAAS8AAAAIQAAADJldPN/eJxjYGRgYOBikGPQYWB0cfMJYeBgYGGAAJAMY05meiJQDMoDyrGAaQ4gZoOIAgCKIwNPAHicY2Bk4WScwMDKwMHUyXSGgYGhH0IzvmYwYuRgYGBiYGVmwAoC0lxTGBwYKp4pMjf8b2CIYW5gaAAKM4LkANiXC58AeJzFkMENgDAMAy9t6QOxRH88GIgXc3TirlFMKA8mqCXHimMpUYAFiOIhJrAL48Ep19yPrO4nz2SpEait9K7+U0U0y65BJTMNNm/1H5vXfXT6CnVQJ7byknADolcMEwAAeJwVj71OwlAcR/+/e+0XQiu334UCpcL1k8RaWIxlcdE4mLjI6APoyqIJi4mDg89gTHwJBvUh3I2+htV6cnL2QwrR7ydf8oBs2qA9OqIzIqjb6JssRiLzEduGmyiu75hcpjLR0v6IH8Lvq46XTfKhr2qqBRMd7CfZRI6YxDgv2AEyLwbCVnQuBm3BH1ELZOeuPGFPcLtp2yp2y+OdqZP1bH1eFyIU4kFXFUVnbMUyceV7hmLU1PJZsSJ32d1kXdRDGZ3OGr2WuLzPr+OBbwCLBexWz3yZNqNm5U3k2SLU1hp6EDXSdQfz79XArsfDL6rAf/iMvVN1bWBcyS9+XvFRbuG2fCugAQXRHwTJKyQAAAB4nGNgZGBgAOJugeIF8fw2Xxm4WRhA4FrVHDsE/f8hCwOzBJDLwcAEEgUAGGUJvAB4nGNgZGBgbvjfwBDDYsQABCwMDIwMqIAZAEvrAp4AAAQAAAAD6QAABDIAAAAAAAAAdgCKeJxjYGRgYGBmCGRgZQABJiDmAkIGhv9gPgMAEPcBcAB4nGWPTU7DMBCFX/oHpBKqqGCH5AViASj9EatuWFRq911036ZOmyqJI8et1ANwHo7ACTgC3IA78EgnmzaWx9+8eWNPANzgBx6O3y33kT1cMjtyDRe4F65TfxBukF+Em2jjVbhF/U3YxzOmwm10YXmD17hi9oR3YQ8dfAjXcI1P4Tr1L+EG+Vu4iTv8CrfQ8erCPuZeV7iNRy/2x1YvnF6p5UHFockikzm/gple75KFrdLqnGtbxCZTg6BfSVOdaVvdU+zXQ+ciFVmTqgmrOkmMyq3Z6tAFG+fyUa8XiR6EJuVYY/62xgKOcQWFJQ6MMUIYZIjK6Og7VWb0r7FDwl57Vj3N53RbFNT/c4UBAvTPXFO6stJ5Ok+BPV8bUnV0K27LnpQ0kV7NSRKyQl7WtlRC6gE2ZVeOEXpc0Yk/KGdI/wAJWm7IAAAAeJxjYGKAAC4G7ICZkYmRmZGFgbGCJSWxspSBAQAOYQJMAAAA') format('woff'),
  url('iconfont.ttf?t=1515486014818') format('truetype'), /* chrome, firefox, opera, Safari, Android, iOS 4.2+*/
  url('iconfont.svg?t=1515486014818#iconfont') format('svg'); /* iOS 4.1- */
}

.iconfont {
  font-family:"iconfont" !important;
  font-size:16px;
  font-style:normal;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
}

.icon-dayu:before { content: "\e621"; }

	
</style>
<div id="createCard" ng-controller="checkDetailController" ng-init='init()'>
	<div class="">
	    <p style="font-size:16px">本月记录<i class="iconfont icon-dayu" style="color:deepskyblue"></i><span style="color:deepskyblue">查看</span></p>
	</div>
	<div class="createContent">
		<div class="cardContent">
			<div class="activated" style="display: block;">
				<table id="settle_accounts" border="0" cellspacing="0"
					cellpadding="0">
					<thead>
						<tr>
							<td>消费日期</td>
							<td>消费项目</td>
							<td>消费金额</td>
							<td>实付金额</td>
						</tr>
					</thead>
					<tfooter>
					<tr ng-repeat="o in orders">
						<td>{{o.startDate | date:'yyyy-MM-dd'}}</td>
						<td>{{o.serveName}}</td>
						<td>￥{{o.price}}</td>
						<td>￥{{o.payMoney}}</td>
					</tr>
					</tfooter>
				</table>
				<div id="page" style="text-align: center"></div>
			</div>
		</div>
	</div>
</div>