package com.zebra.devdemo.webservices;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zebra.sdk.comm.ConnectionException;
import com.zebra.sdk.printer.ZebraPrinter;
import com.zebra.sdk.printer.ZebraPrinterFactory;
import com.zebra.sdk.printer.ZebraPrinterLanguageUnknownException;
import com.zebra.sdk.remote.comm.RemoteConnection;


/**
 * Servlet implementation class PrintConfig
 */
@WebServlet("/PrintConfig")
public class PrintConfig extends HttpServlet {

	private static final long serialVersionUID = -5602313335397756250L;
	/**
	 * Custom POST implementation which takes in a SerialNumber as the parameter.
	 * A <code>ZebraPrinter</code> object is created and used to print a config label
	 * to the printer.
	 * 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			//Get the serial number off the request.
			String serialNumber = request.getParameter("serialNumber");
			//Create a RemoteConnection on port 11995
			RemoteConnection connection = new RemoteConnection(serialNumber, 11995);
			connection.open();
			
			ZebraPrinter printer = ZebraPrinterFactory.getInstance(connection);
			//Decorate the printer as a LinkOsPrinter and print the configuration label.
			ZebraPrinterFactory.createLinkOsPrinter(printer).printConfigurationLabel();
		} catch (ConnectionException e) {
			System.err.println(e.getLocalizedMessage());
		} catch (ZebraPrinterLanguageUnknownException e) {
			System.err.println(e.getLocalizedMessage());
		}
	}
}
