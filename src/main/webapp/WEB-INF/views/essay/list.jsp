<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="root" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<script type="text/javascript" src="http://web-res.qiniudn.com/jquery-1.11.1.js"></script>

<script type="text/javascript" src="http://web-res.qiniudn.com/jquery.validate.min.js"></script>
<script type="text/javascript" src="http://web-res.qiniudn.com/bootstrap.min.js"></script>
<script type="text/javascript" src="${root}/res/js/form-validation.js?v=${res_vesion}"></script>

<link href="http://web-res.qiniudn.com/bootstrap.min.css"  rel="stylesheet" media="screen">
<link href="http://web-res.qiniudn.com/bootstrap-responsive.min.css" rel="stylesheet" media="screen">

<title>无标题文档</title>
<script type="text/javascript">
$(document).ready(function() {
	$("#add_user").click(function(){
		window.location.href="${root}/essay/addEssay";
	});
});
</script>
<style type="text/css">
<!--
.STYLE2 {
	font-size: 10px;
	font-weight: bold;
}
-->
</style>
</head>

<body>
<ul class="nav nav-tabs" id="myTab">
  <li ><a href="${root}/index">用户列表</a></li>
  <li class="active"><a href="#profile">文章列表</a></li>
  <li><a href="${root}/essay/cacheEssayList">cache_list</a></li>
</ul>
	<div style="margin-left: 200px; margin-top: 20px;">
		<button type="button" id="add_user" class="btn btn-primary">增加文章</button>
	</div>
	<table width="800" border="0" align="center" cellpadding="5"
		cellspacing="1" bgcolor="#add3ef">
		<tr>
			<td bgcolor="#c7e1fa"><span class="STYLE2">编号</span></td>
			<td bgcolor="#c7e1fa">标题</td>
			<td bgcolor="#c7e1fa">内容</td>
			<td bgcolor="#c7e1fa">创建时间</td>
			<td bgcolor="#c7e1fa">详情</td>
			<td bgcolor="#c7e1fa">删除</td>
		</tr>
		<c:forEach items="${data.pageData}" var="essay">
			<tr>
				<td bgcolor="#FFFFFF">${essay.id }</td>
				<td bgcolor="#FFFFFF">${essay.title}</td>
				<td bgcolor="#FFFFFF">${essay.content}</td>
				<td bgcolor="#FFFFFF"><fmt:formatDate value="${essay.createTime}" pattern="yyyy-MM-dd  HH:mm:ss" /></td>
				<td bgcolor="#FFFFFF"><a href="${root}/viewUserInfo?id=${essay.id }">详情</a></td>
				<td bgcolor="#FFFFFF"><a href="${root}/user/delUserInfo?id=${essay.id }">删除</a></td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>
