<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="root" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="${root}/res/css/style.css" rel="stylesheet" type="text/css" media="screen"/>
<title>添加文章</title>

<style type="text/css">
.update {
	margin: 0 auto;
	padding: 0;
	width: 98%;
}

.update td {
	margin: 0;
	height: 30px;
	padding: 5px;
}

.update .name {
	text-align: right;
}

.title_div {
	width: 350px;
}

</style>

</head>
<body>

	<div class="rounded table">
		<table style="width:60%;"  cellpadding="0" cellspacing="0" border="0"
			class="box_top">
			<tbody>
				<tr>
					<td class="title"></td>
					<td></td>
				</tr>
			</tbody>
		</table>

		<div class="error rounded top_error hide">
			<ol></ol>
			<span></span>
		</div>
		<form id="form1" action="${root}/essay/saveEssayInfo" method="post">
			<input type="hidden" name="creatorId" value="1"> 
			<table class="update" cellpadding="0" cellspacing="1" border="0">
				<tbody>
					<tr>
						<td class="name">标题：</td>
						<td align="left">
							<input name="title" type="text"  maxlength="50" class="input rounded" />
						</td>
					</tr>
					<tr class="even">
						<td class="name">正文 ：</td>
						<td align="left">
								<textarea class="required form-control" name="content" maxlength="200" rows="3" style="margin: 0px -5.5px 0px 0px;resize:both; height: 70px; width: 400px;" aria-required="true"></textarea>						
						</td>
					</tr>
					<tr class="even">
						<td class="name">当前状态：</td>
						<td align="left">
							<label class="radio-inline" style="display: inline; margin-bottom: 5px; margin-right: 20px;">
								<input type="radio" name="status" checked="checked" id="status" value="1">启用
							</label> 
							<label class="radio-inline" style="display: inline; margin-bottom: 5px; margin-right: 20px;">
								<input type="radio" name="status" id="status" value="0">禁用
							</label> 
						</td>
					</tr>
					<tr>
						<td align="right" height="60" rowspan="2" >
							<button type="submit" class="large color green button">保存</button>
						</td>
					</tr>
				</tbody>
			</table>
		</form>
	</div>

</body>
</html>
