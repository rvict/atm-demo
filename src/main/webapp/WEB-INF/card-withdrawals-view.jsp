<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>

<head>
    <link rel="stylesheet" type="text/css" href="style.css">
    <title>Card withdrawals</title>
</head>

<body>

    <div>
        Enter an amount
    </div>

	<form:form method="POST" action="/withdrawal" modelAttribute="operation">
		<div>
			<form:input path="amount" />
			<form:errors path="amount" cssClass="error" />
		</div>

		<div>
			<input type="reset" value="Delete" />
			<input type="submit" value="Ok" />
			<a href="/exit"><input type="button" value="Exit" /></a>
		</div>
	</form:form>

</body>

</html>