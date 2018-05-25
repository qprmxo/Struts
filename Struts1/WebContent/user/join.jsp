<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<html>
<head>
<title>Struts Kadai Join</title>
<style type="text/css">
table td {
	text-align: center;
}
</style>
</head>
<body>

	<h1>新規登録入力画面</h1>

	<html:form action="join.do" method="post">

		<table>

			<tr>
				<th>ID :</th>
				<td><input type="text" name="id" id="id" value="${requestScope.id }"></td>
			</tr>

			<tr>
				<th colspan="2"><button type="button" id="idCheck">使用できるか確認</button></th>
			</tr>

			<tr>
				<th>パスワード :</th>
				<td><input type="password" name="pass" id="pass"></td>
			</tr>

			<tr>
				<th>パスワード再入力 :</th>
				<td><input type="password" name="passCheck" id="passCheck"></td>
			</tr>

			<tr>
				<th>名前 :</th>
				<td><input type="text" name="name" id="name" value="${name }"></td>
			</tr>

			<tr>
				<th>カナ :</th>
				<td><input type="text" name="kana" id="kana" value="${kana }"></td>
			</tr>

			<tr>
				<th>生年月日（yyyy/mm/dd) :</th>
				<td><input type="date" name="birth" id="birth" value="${birth }"></td>
			</tr>

			<tr>
				<th>委員会 :</th>
				<td><input type="text" name="club" id="club" value="${club }"></td>
			</tr>

		</table>
	</html:form>
	
	<button type="button" id="btnSubmit">登録します</button>
	<button type="button" id="btnRt">戻る</button>

</body>
</html>