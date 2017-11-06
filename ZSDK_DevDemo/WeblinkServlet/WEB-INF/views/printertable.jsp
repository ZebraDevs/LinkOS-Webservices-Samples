<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix='fn' uri='http://java.sun.com/jsp/jstl/functions'%>
<html>
<head>
<link href="resources/styles/table_style.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="resources/scripts/jquery-1.8.0.js"></script>
</head>
<body>
	<div class="ZebraCSSTableStyle">
		<table id="connectedPrintersTable">
			<thead>
				<tr>
					<th>Name (${fn:length(connectedPrinters)} printers connected)</th>
					<th>IP Address</th>
					<th>Firmware Ver</th>
					<th>DNS Name</th>
					<th>Serial Number</th>
				</tr>
			</thead>

			<tbody id="connectedPrintersTableBody">
			<c:forEach items="${connectedPrinters}" var="printer"
				varStatus="status">
				<tr class="${status.index % 2 == 0 ? '' : 'alt'}">
					<td>${printer.productName}</td>
					<td>${printer.address}</td>
					<td>${printer.firmwareVer}</td>
					<td>${printer.dnsName}</td>
					<td>${printer.serialNumber}</td>
				</tr>
			</c:forEach>
			</tbody>

			<tfoot>
				<tr>
					<td colspan="1" align="left"><div> Version: ${implementationVersion} </div></td>
					<td colspan="5"><div id="no-paging" align="right">&#169;
							ZIH Corp. All Rights Reserved.</div>
				</tr>
			</tfoot>


		</table>
	</div>
</body>
</html>