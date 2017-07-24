<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>

<head>
    <link rel="stylesheet" type="text/css" href="style.css">
    <title>Error</title>
</head>

<body>

	<div class="error">Error: ${errorMsg}</div>
	<div><a href="${url}"><input type="button" value="Back" /></a></div>

</body>

</html>