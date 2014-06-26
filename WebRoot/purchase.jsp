<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<title>采购订单查询</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<style type="text/css">
html,body {
	height: 100%
}

html {
	overflow-y: auto
}

body {
	font: 12px arial:text-align:;
	background: #fff;
}

body,p,form,ul,li {
	margin: 0;
	padding: 0;
	list-style: none;
}

body {
	position: relative
}

td {
	text-align: left
}

img {
	border: 0
}

a {
	color: #00c
}

a:active {
	color: #f60
}

input {
	border: 0px none;
	padding: 0px;
}

#wrapper {
	position: relative;
	_position:;
	min-height: 100%;
}

#content {
	padding-bottom: 100px;
	text-align: center;
}

#m {
	width: 720px;
	margin: 100px auto 0 auto;
}

.pagetitle {
	font-size: 48px;
	line-height: 70px;
	font-family: Montserrat, sans-serif;
	color: #E10602;
	padding: 3px 15px;
	letter-spacing: -2px;
	display: inline-block;
}

.pagenote {
	font-size: 21px;
	line-height: 70px;
	font-family: Helvetica, Arial, sans-serif;
	color: #2A6496;
	padding: 8px 15px;
	letter-spacing: -2px;
	display: inline-block;
}

.ipt {
	width: 418px;
	height: 30px;
	font-size: 16px;
	line-height: 22px;
	font-family: arial;
	margin: 5px 0 0 7px;
	background: none repeat scroll 0% 0% #FFF;
	outline: 0px none;
	border: 1px solid #B6B6B6;
	border-color: #9A9A9A #CDCDCD #CDCDCD #9A9A9A;
}

.btn_wr {
	width: 97px;
	height: 34px;
	display: inline-block;
	position: relative;
	vertical-align: top
}

.btn {
	width: 95px;
	height: 32px;
	padding-top: 2px\9;
	font-size: 14px;
	background-color: #DDD;
	background-position: 0 -48px;
	cursor: pointer;
}

.ipf {
	width: 535px;
	height: 32px;
	margin-left: -535px;
	font-size: 20px;
	filter: alpha(opacity =             0);
	opacity: 0;
}

.sub {
	box-shadow: 0 1px 2px #fff inset, 0 -1px 0 #a8abae inset;
	background: -webkit-linear-gradient(top, #f2f3f7, #e4e8ec);
	background: -moz-linear-gradient(top, #f2f3f7, #e4e8ec);
	background: linear-gradient(top, #f2f3f7, #e4e8ec);
	background-color: #F5F5F5;
	border: 1px solid #DCE1E6;
	border-radius: 2px;
	color: #8C96A0;
	font-family: arial, sans-serif;
	font-size: 12px;
	font-weight: bold;
	height: 29px;
	line-height: 27px;
	margin: 11px 6px;
	min-width: 60px;
	padding: 0px 8px;
	text-align: center;
}
</style>
<script type="text/javascript">
	function check() {
		var t = document.getElementById('ipst').value;
		if (t == "") {
			alert("上传文件不能为空，请重新上传！");
			return false;
		}
		if (t.lastIndexOf(".xls") != -1 || t.lastIndexOf(".XLS") != -1
				|| t.lastIndexOf(".xlsx") != -1 || t.lastIndexOf(".XLSX") != -1) {
			return true;
		} else {
			alert("上传文件的格式有误，请上传以xls或xlsx结尾的文件！");
			return false;
		}
	}
</script>
</head>
<body>
	<div id="wrapper">
		<div id="content">
			<div id="m">
				<p>
				<div class="pagetitle">快递查询</div>
				</p>
				<div class="fm">
					<form action="ExpressAction" method="post"
						onsubmit="return check()" enctype="multipart/form-data">
						<div>
							<input class="ipt" id="ipst" type="text" name="uptxt"></input> <input
								class="btn" type="button" value="浏览"></input> <input class="ipf"
								type="file" size="100" name="sendupload"
								onchange="document.getElementById('ipst').value=this.value"></input>
						</div>
						<div style="padding-top:5px;">
							<center>
								<input class="sub" type="submit" name="sendupbtn" value="上传"></input>
								<input class="sub" type="reset" name="sendrePos" value="重置"></input>
							</center>
						</div>
					</form>
				</div>
			</div>
		</div>
</body>
</html>
