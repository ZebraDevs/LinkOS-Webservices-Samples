package com.zebra.devdemo.webservices;

import com.zebra.sdk.comm.ConnectionException;
import com.zebra.sdk.printer.discovery.DiscoveredPrinter;
import com.zebra.sdk.remote.discovery.RemoteDiscoverer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * Servlet implementation class WebServicesDevDemo
 */
@WebServlet(urlPatterns = { "/WebServicesDevDemo" }, loadOnStartup = 1)
public class WebServicesDevDemo extends HttpServlet {

	private static final long serialVersionUID = 4259174667963602122L;

	/**
	 * Custom GET implementation which will retrieve a <code>Set</code> of connected printers and return the JSP.
	 * 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Set<SlimDiscoPrinter> printers = new HashSet<SlimDiscoPrinter>();

		try {
			// Convert the DiscoveredPrinter to a SlimDiscoPrinter which only contains the information
			// we wish to display on the JSP.
			for (DiscoveredPrinter discoPrinter : RemoteDiscoverer.getConnectedPrinters(11995)) {
				printers.add(new SlimDiscoPrinter(discoPrinter));
			}
		} catch (ConnectionException e) {
			System.err.println(e.getLocalizedMessage());
		} catch (NumberFormatException e) {
			System.err.println(e.getLocalizedMessage());
		}
		// Attach the SlimDiscoPrinter object to the request for the JSP
		request.setAttribute("connectedPrinters", printers);
		// Forward the built JSP to the client
		getServletContext().getRequestDispatcher("/WEB-INF/devdemo.jsp").forward(request, response);
	}

}
