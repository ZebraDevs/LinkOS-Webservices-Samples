<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix='fn' uri='http://java.sun.com/jsp/jstl/functions' %>

<html>
<head>
    <link href="resources/styles/table_style.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="resources/scripts/jquery-1.8.0.js"></script>
</head>
<body>

<div>
    First Name:<br>
    <input id="firstName" type="text"><br>
    Last Name:<br>
    <input id="lastName" type="text">
    <input id="printFormat" type="button" value="Print Format">
    <script type="text/javascript">
        $("#printFormat").click(function (event) {
            $.post('/ZebraWebServicesDeveloperDemo/PrintFormat?serialNumber=${serialNumber}&firstName=' + $('#firstName').val() + '&lastName=' + $('#lastName').val());
        });
    </script>
</div>
</body>
</html>