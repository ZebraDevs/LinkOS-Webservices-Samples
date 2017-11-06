<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix='fn' uri='http://java.sun.com/jsp/jstl/functions' %>

<html>
<head>
    <link href="resources/styles/table_style.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="resources/scripts/jquery-1.8.0.js"></script>
</head>
<body>
<%
    response.setIntHeader("Refresh", 5);
%>
<div class="ZebraCSSTableStyle">
    <table id="connectedPrintersTable" border="1" align="center">
        <thead>
        <tr>
            <th>Name (${fn:length(connectedPrinters)} printers connected)</th>
            <th>IP Address</th>
            <th>Firmware Ver</th>
            <th>DNS Name</th>
            <th>Serial Number</th>
            <th>Actions</th>
        </tr>
        </thead>

        <c:forEach items="${connectedPrinters}" var="printer" varStatus="count">
            <tr class="${count.index % 2 == 0 ? '' : 'alt'}">
                <td>${printer.productName}</td>
                <td>${printer.address}</td>
                <td>${printer.firmwareVer}</td>
                <td>${printer.dnsName}</td>
                <td>${printer.serialNumber}</td>
                <td>
                    <a id="printConfig-${printer.serialNumber}" href="#">Print Config</a>
                    <script type="text/javascript">
                        $("#printConfig-${printer.serialNumber}").click(function (event) {
                            event.preventDefault();
                            $.post('/ZebraWebServicesDeveloperDemo/PrintConfig?serialNumber=${printer.serialNumber}');
                        });
                    </script>

                    &nbsp;---&nbsp;

                    <a href="/ZebraWebServicesDeveloperDemo/PrinterSettings?serialNumber=${printer.serialNumber}">
                        Get Settings</a>

                    &nbsp;---&nbsp;

                    <a id="printFormat-${printer.serialNumber}" href="/ZebraWebServicesDeveloperDemo/PrintFormat?serialNumber=${printer.serialNumber}">
                        Print Format</a>
                </td>
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


        <tbody id="connectedPrintersTableBody">
        </tbody>
    </table>
</div>
</body>
</html>