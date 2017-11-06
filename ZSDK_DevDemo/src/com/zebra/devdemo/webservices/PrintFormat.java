package com.zebra.devdemo.webservices;

import com.zebra.sdk.comm.ConnectionException;
import com.zebra.sdk.device.ZebraIllegalArgumentException;
import com.zebra.sdk.printer.ZebraPrinterFactory;
import com.zebra.sdk.printer.ZebraPrinterLinkOs;
import com.zebra.sdk.remote.comm.RemoteConnection;
import com.zebra.sdk.settings.Setting;
import com.zebra.sdk.settings.SettingsException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(urlPatterns = {"/PrintFormat"})
public class PrintFormat extends HttpServlet {

    private static final String NAME_BADGE_DESTINATION = "R:NAMEBADGE.ZPL";

    public static final String NAME_BADGE_FORMAT = "^XA\n" +
            "^DF" + NAME_BADGE_DESTINATION + "\n" +
            "^FS\n" +
            "^FO25,25\n" +
            "^AD,36,20^FN1^FS\n" +
            "^FO25,55\n" +
            "^AD,36,20^FN2^FS\n" +
            "^FO25,95\n" +
            "^AD,36,20^FDZebra Technologies^FS\n" +
            "^XZ \n" +
            "^XA ";

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String serialNumber = request.getParameter("serialNumber");
        request.setAttribute("serialNumber", serialNumber);
        getServletContext().getRequestDispatcher("/WEB-INF/printFormat.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String serialNumber = request.getParameter("serialNumber");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");

        RemoteConnection remoteConnection = new RemoteConnection(serialNumber);
        try {
            remoteConnection.open();
            ZebraPrinterLinkOs printer = ZebraPrinterFactory.getLinkOsPrinter(remoteConnection);

            //Store format to printer
            remoteConnection.write(NAME_BADGE_FORMAT.getBytes());

            Map<Integer, String> formatValues = new HashMap<>();
            formatValues.put(1, firstName);
            formatValues.put(2, lastName);

            printer.printStoredFormat(NAME_BADGE_DESTINATION, formatValues);
        } catch (ConnectionException e) {
            System.err.println(e.getLocalizedMessage());
        } finally {
            if (remoteConnection.isConnected()) {
                try {
                    remoteConnection.close();
                } catch (ConnectionException e) {
                }
            }
        }

    }


}
