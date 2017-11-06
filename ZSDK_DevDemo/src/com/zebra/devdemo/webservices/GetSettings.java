package com.zebra.devdemo.webservices;

import com.zebra.sdk.comm.ConnectionException;
import com.zebra.sdk.printer.ZebraPrinterFactory;
import com.zebra.sdk.printer.ZebraPrinterLanguageUnknownException;
import com.zebra.sdk.printer.ZebraPrinterLinkOs;
import com.zebra.sdk.printer.discovery.DiscoveredPrinter;
import com.zebra.sdk.remote.comm.RemoteConnection;
import com.zebra.sdk.remote.discovery.RemoteDiscoverer;
import com.zebra.sdk.settings.Setting;
import com.zebra.sdk.settings.SettingsException;

import javax.jws.WebMethod;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@WebServlet(urlPatterns = { "/PrinterSettings" })
public class GetSettings extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String serialNumber = request.getParameter("serialNumber");

		RemoteConnection remoteConnection = new RemoteConnection(serialNumber);
		try {
			remoteConnection.open();
			ZebraPrinterLinkOs printer = ZebraPrinterFactory.getLinkOsPrinter(remoteConnection);
			Map<String, Setting> allSettings = printer.getAllSettings();
			request.setAttribute("settings", allSettings);
		} catch (ConnectionException e) {
			System.err.println(e.getLocalizedMessage());
		} catch (SettingsException e) {
			System.err.println(e.getLocalizedMessage());
		}

		getServletContext().getRequestDispatcher("/WEB-INF/settings.jsp").forward(request, response);
	}


}
