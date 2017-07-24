<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>

<head>
    <link rel="stylesheet" type="text/css" href="style.css">
    <script src="script.js"></script>
    <title>Balance</title>
</head>

<body>
	<div>Current Date: ${date}</div>
	<div>Balance: ${card.balance}</div>

	<div>
		<a href="/operations"><input type="button" value="Back" /></a>
		<a href="/exit"><input type="button" value="Exit" /></a>
	</div>
</body>

</html>