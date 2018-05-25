<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<html>
<head>
<title>Struts Kadai</title>
</head>
<body>

<html:form action="login.do" method="post">
	<table>
		<tr>
			<th>ID</th><td><html:text property="id"/></td>
		</tr>
		<tr>
			<th>Password</th><td><html:text property="pass"/></td>
		</tr>
	</table>
	
	<html:submit>ログイン</html:submit>
</html:form>

<html:link page="user/join.jsp"></html:link>

</body>
</html>
