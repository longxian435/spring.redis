<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="zh">
<style type="text/css">
.dzh-mainBj {
	background: url(../res/img/dzh-bodyBj.png);
}
</style>
<body class="dzh-forum1 dzh dzh-mainBj" >
	<table width="669" border="0" align="center" cellpadding="5" cellspacing="1" bgcolor="#add3ef">
		<tr>
			<td bgcolor="#c7e1fa">用户名</td>
			<td bgcolor="#FFFFFF">${user.userName}</td>
		</tr>
		<tr>
			<td bgcolor="#c7e1fa">状态</td>
			<td bgcolor="#FFFFFF">${user.status}</td>
		</tr>
		<tr>
			<td bgcolor="#c7e1fa">创建时间</td>
			<td bgcolor="#FFFFFF"> <fmt:formatDate value="${user.createTime}" pattern="yyyy-MM-dd  HH:mm:ss" /></td>
		</tr>
		<tr>
			<td bgcolor="#c7e1fa">查看次数</td>
			<td bgcolor="#FFFFFF">${count}</td>
		</tr>
		<tr>
			<td bgcolor="#c7e1fa">hincr</td>
			<td bgcolor="#FFFFFF">${hincr}</td>
		</tr>
	</table>
</body>
</html>
