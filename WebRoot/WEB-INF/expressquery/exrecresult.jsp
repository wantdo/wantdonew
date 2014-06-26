<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<title>订单查询成功</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<style type="text/css">
body,p {
	font-family: 'Microsoft YaHei', 微软雅黑, Verdana, arial, sans-serif;
	font-size: 16px;
	line-height: 16px;
	color: #000;
	font-weight: normal;
}

#PageContent {
	text-align: left;
	background-color: #FFF;
	padding: 0px;
	margin: 0px;
}

.pageheader {
	padding: 6px 0px 0px;
	border-bottom: 1px solid #3C78B5;
}

.pagetitle {
	margin: 0px 10px 12px;
	font-size: 22px;
	font-weight: bold;
	font-family: Arial, sans-serif;
	color: #036;
}

.greynavbar {
	padding: 2px 10px;
	margin: 0px;
	background-color: #F0F0F0;
	border-top: 1px solid #3C78B5;
	height: 18px;
	width: auto;
}

.pagecontent {
	padding: 10px;
	text-align: left;
}

.exresult {
	margin-top: 3px;
}

.exresult p {
	margin: 16px 0px;
	padding: 0px;
}

.table-wrap {
	width: 100%;
	overflow: auto;
}

table.exTable {
	font-family: 'Microsoft YaHei', 微软雅黑, Verdana, arial, sans-serif;
	font-size: 12px;
	color: #333333;
	border-width: 1px;
	border-color: #999999;
	border-collapse: collapse;
	table-layout: fixed;
}

table.exTable th {
	background-color: #C3DDE0;
	border-width: 1px;
	padding: 8px;
	border-style: solid;
	border-color: #A9C6C9;
}

table.exTable tr {
	background-color: #D4E3E5;
}

table.exTable td {
	border-width: 1px;
	padding: 8px;
	border-style: solid;
	border-color: #A9C6C9;
}
</style>

</head>

<body>
	<div id="PageContent">
		<div class="pageheader">
			<div class="pagetitle">
				<a href="/wantdonew/purchase.jsp">主页</a>
			</div>
			<div class="greynavbar"></div>
		</div>
		<div class="pagecontent">
			<div class="exresult">
				<p>
					处理成功，请点击下载： <a
						href="ExDownloadAction.action?fileName=<s:property value="excSucName"/>"
						target="_blank"> <s:property value="excSucName" />
					</a>
				</p>
				<s:if test="excErrName!=null">
					<p>
						部分订单查询失败，请点击下载： <a
							href="ExDownloadAction.action?fileName=<s:property value="excErrName"/>"
							target="_blank"> <s:property value="excErrName" />
						</a>
					</p>
				</s:if>
				<div class="table-wrap">
					<table class="exTable">
						<tr>
								<th>采购订单号</th>
								<th>供应商名称</th>
								<th>商品名称</th>
								<th>货号</th>
								<th>事物特性</th>
								<th>数量</th>
								<th>快递公司</th>
								<th>快递单号</th>
								<th>快递状态</th>
						</tr>
						<s:iterator value="disSucList" id="sucArray">
							<tr>
								<s:iterator value="#sucArray" id="sucElement">
									<td><s:property value="sucElement" /></td>
								</s:iterator>
							</tr>
						</s:iterator>
					</table>
				</div>
				<s:if test="excErrName!=null">
					<p>出错的订单信息如下：</p>
					<div class="table-wrap">
						<table class="exTable">
							<tr>
								<s:iterator value="displayHeader2" id="header">
									<th><s:property value="header" /></th>
								</s:iterator>
							</tr>
							<s:iterator value="disErrList" id="errArray">
								<tr>
									<s:iterator value="#errArray" id="errElement">
										<td><s:property value="errElement" /></td>
									</s:iterator>
								</tr>
							</s:iterator>
						</table>
					</div>
				</s:if>
			</div>
		</div>
	</div>
</body>
</html>
