<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Struts Kadai Search</title>
</head>
<body>

	<div style="color: red;">
		<html:messages id="msg" message="true">
			<bean:write name="msg"/>
		</html:messages>
		<html:messages id="msg" message="false">
			<bean:write name="msg"/>
		</html:messages>
	</div>
	
	<div>前方一致で検索します。</div>
	
	<h1>検索画面</h1>
	
	<html:form action="search.do" method="post">
	
		<table border="1">
			<tr>
				<th>ID</th><td><html:text name="searchForm" property="id"/></td>
			</tr>
			<tr>
				<th>名前</th><td><html:text name="searchForm" property="name"/></td>
			</tr>
			<tr>
				<th>カナ</th><td><html:text name="searchForm" property="kana"/></td>
			</tr>
		</table>
		
		<html:submit property="cmd" value="検索"/>
		<html:link page="/user/join.jsp">新規登録</html:link>
		<html:link action="login.do?cmd=logout">ログアウト</html:link>
		
	</html:form>
	
	<logic:notEmpty name="list">
	
		<table border="1">
			<tr>
				<th>ID</th><th>名前</th><th>カナ</th><th>生年月日</th><th>委員会</th><th>操作</th>
			</tr>
			
			<logic:iterate id="form" name="list">
			<tr>
				<td><bean:write name="form" property="id"/></td>
				<td><bean:write name="form" property="name"/></td>
				<td><bean:write name="form" property="kana"/></td>
				<td><bean:write name="form" property="birth"/></td>
				<td><bean:write name="form" property="club"/></td>
				<td>
					<html:form action="move.do" method="post">
						<html:hidden name="form" property="id"/>
						<html:submit property="cmd" value="更新"/>
						<html:submit property="cmd" value="削除"/>
					</html:form>
				</td>
			</tr>
			</logic:iterate>
		</table>
		
	</logic:notEmpty>
	
</body>
</html>