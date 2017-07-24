<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
	<!DOCTYPE html>
	<html>

	<head>
        <link rel="stylesheet" type="text/css" href="style.css">
        <script src="script.js"></script>
		<title>PIN</title>
	</head>

	<body>
		<div>
			Enter a PIN
		</div>

		<form:form method="POST" action="/card-pin" modelAttribute="card">
			<div>
				<form:input path="pin" />
				<form:errors path="pin" cssClass="error" />
			</div>

			<div>
				<input type="submit" value="Ok" />
				<input type="button" value="Clear" onclick="clearInput('pin')" />
			</div>
		</form:form>

	</body>

	</html>