<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix='fn' uri='http://java.sun.com/jsp/jstl/functions'%>

<html>
<head>
<script type="text/javascript" src="resources/scripts/jquery-1.8.0.js"></script>
</head>
<body>
	<%
		response.setIntHeader("Refresh", 5);
	%>
	<table id="connectedPrintersTable" border="1" align="center">
		<thead>
			<tr>
				<th>Name (${fn:length(connectedPrinters)} printers connected)</th>
				<th>IP Address</th>
				<th>Firmware Ver</th>
				<th>DNS Name</th>
				<th>Serial Number</th>
			</tr>
		</thead>

		<c:forEach items="${connectedPrinters}" var="printer" varStatus="status">
			<tr class="${status.index % 2 == 0 ? '' : 'alt'}">
				<td>
					<table>
						<tr>
							<td>${printer.productName}</td>
							<td>
								<form id="printConfigForm${printer.serialNumber}" action="/ZebraWebServicesDeveloperDemo/WebServicesDevDemo/PrintConfig">
									<input type="hidden" name="SerialNumber" value="${printer.serialNumber}">
									<input type="submit" value="Print Config" />
								</form>
								<script type="text/javascript">                                         
								 $("#printConfigForm${printer.serialNumber}").submit(function(event) {
										event.preventDefault();
								
										var $form = $(this);
										term = $form.find( 'input[name="SerialNumber"]' ).val(),
								     	url = $form.attr( 'action' );
										
										$.post(url, { SerialNumber : term });
									});                                 
								</script> 
							</td>

						</tr>
					</table>
				</td>
				<td>${printer.address}</td>
				<td>${printer.firmwareVer}</td>
				<td>${printer.dnsName}</td>
				<td>${printer.serialNumber}</td>
			</tr>

		</c:forEach>

		<tfoot>
			<tr>
				<td colspan="5"><div id="no-paging" align="right">&#169;
						ZIH Corp. All Rights Reserved.</div>
			</tr>
		</tfoot>


		<tbody id="connectedPrintersTableBody">
		</tbody>
	</table>
</body>
</html>