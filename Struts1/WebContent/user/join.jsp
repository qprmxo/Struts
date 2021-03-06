<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<html>
<head>
<title>Struts Kadai Join</title>
<script src="/Struts1/js/jquery-3.3.1.js"></script>
<style type="text/css">
table td {
	text-align: center;
}
</style>
<script type="text/javascript">
	$(function(){
		$("#btnBack").on("click",function(){
			history.back();
		});
	});
</script>
</head>
<body>
	<div style="color: red;">
		<html:errors />
	</div>
	<h1>新規登録入力画面</h1>
	
	<html:form action="joinCheck.do" method="post">
		<table>
			<tr>
				<th>ID</th><td><html:text name="userForm" property="id"/></td>
			</tr>
			<tr>
				<th colspan="2"><html:submit property="cmd" value="使用できるか確認"/>
			</tr>
			<tr>
				<th>Password</th><td><html:password name="userForm" property="pass"/></td>
			</tr>
			<tr>
				<th>Password再入力</th><td><html:password name="userForm" property="passCheck"/></td>
			</tr>
			<tr>
				<th>名前</th><td><html:text name="userForm" property="name"/></td>
			</tr>
			<tr>
				<th>カナ</th><td><html:text name="userForm" property="kana"/></td>
			</tr>
			<tr>
				<th>生年月日</th><td><html:text name="userForm" property="birth"/></td>
			</tr>
			<tr>
				<th>委員会</th><td><html:text name="userForm" property="club"/></td>
			</tr>
		</table>	
		<html:submit property="cmd" value="登録します"/>
		<button type="button" id="btnBack">戻る</button>
	</html:form>

</body>
</html>