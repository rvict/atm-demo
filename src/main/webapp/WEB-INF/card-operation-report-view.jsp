<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>

<head>
    <link rel="stylesheet" type="text/css" href="style.css">
    <script src="script.js"></script>
    <title>Operation report</title>
</head>

<body>

	<div>Card number: ${card.number}</div>
	<div>Date/Time: ${date}</div>
	<div>Amount: ${operation.amount}</div>
	<div>Balance: ${card.balance}</div>

	<div>
		<a href="/withdrawal"><input type="button" value="Back" /></a>
		<a href="/exit"><input type="button" value="Exit" /></a>
	</div>
</body>

</html>