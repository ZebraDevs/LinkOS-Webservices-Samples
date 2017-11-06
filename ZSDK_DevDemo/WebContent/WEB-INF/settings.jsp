<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix='fn' uri='http://java.sun.com/jsp/jstl/functions' %>

<html>
<head>
    <link href="resources/styles/table_style.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="resources/scripts/jquery-1.8.0.js"></script>
</head>
<body>
<div class="ZebraCSSTableStyle">
    <table id="settings" border="1" align="center">
        <thead>
        <tr>
            <th>Name</th>
            <th>Value</th>
            <th>Type</th>
            <th>Cloneable</th>
            <th>Archiveable</th>
            <th>Range</th>
        </tr>
        </thead>

        <c:forEach items="${settings}" var="settingEntry" varStatus="status">
            <tr class="${status.index % 2 == 0 ? '' : 'alt'}">
                <td>${settingEntry.key}</td>
                <td>${settingEntry.value.value}</td>
                <td>${settingEntry.value.type}</td>
                <td>${settingEntry.value.clone}</td>
                <td>${settingEntry.value.archive}</td>
                <td>${settingEntry.value.range}</td>
            </tr>

        </c:forEach>

        <tfoot>
        <tr>
            <td colspan="6">
                <div id="no-paging" align="right">&#169;
                    ZIH Corp. All Rights Reserved.
                </div>
        </tr>
        </tfoot>

    </table>
</div>
</body>
</html>