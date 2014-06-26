<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>订单查询等待页面</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta http-equiv="refresh"content="2;url=/wantdonew/ExpressAction.action" >
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
   <div class="main">
			<span class="processing" >
				正在处理中，请稍候(已处理<s:property value="dealNum" />/<s:property value="totalNum"/>)。。。
			</span><br/><br/>
			<a href="/wantdonew/purchase.jsp">
				<span class="back">
					&lt;&lt;&nbsp;&nbsp;如果长时间无反应，请点击返回
				</span>
			</a>  
		</div>
  </body>
</html>
