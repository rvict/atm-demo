<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
	<!DOCTYPE html>
	<html>

	<head>
        <link rel="stylesheet" type="text/css" href="style.css">
        <script src="script.js"></script>
		<title>Card number</title>
	</head>

	<body>
		<div>
			Enter a card number
		</div>

		<form:form method="POST" action="/card-number" modelAttribute="card">
			<div>
				<form:input path="number" />
				<form:errors path="number" cssClass="error" />
			</div>

			<div>
				<input type="submit" value="Ok" />
				<input type="button" value="Clear" onclick="clearInput('number')" />
			</div>
		</form:form>

	</body>

	</html>