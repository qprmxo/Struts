<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Struts Kadai</title>
</head>
<body>

	<div style="color: red;">
		<html:errors />
	</div>
	<h1>ログイン画面</h1>

	<html:form action="login.do" method="post">
		<table>
			<tr>
				<th>ID</th><td><html:text property="id"/></td>
			</tr>
			<tr>
				<th>Password</th><td><html:password property="pass"/></td>
			</tr>
		</table>
		
		<html:submit property="cmd" value="ログイン"/>
	</html:form>
	
	<html:link page="/user/join.jsp">新規登録</html:link>

</body>
</html>
