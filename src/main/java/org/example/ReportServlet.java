package org.example;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


public class ReportServlet extends HttpServlet {

    // Local fields for ServletConfig parameters
    private long maxUploadSize;
    private String allowedExtension;

    @Override
    public void init(ServletConfig config) throws ServletException {
        // MANDATORY: Passes the config object to GenericServlet so helper methods work
        super.init(config);

        // Reading multiple ServletConfig parameters locally
        this.maxUploadSize = Long.parseLong(config.getInitParameter("maxUploadSize"));
        this.allowedExtension = config.getInitParameter("allowedExtension");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        // Reading multiple ServletContext global parameters anywhere in the app
        String supportEmail = getServletContext().getInitParameter("supportEmail");
        String environment = getServletContext().getInitParameter("environment");

        out.println("<h3>Report Servlet (web.xml Method)</h3>");
        out.println("<p><strong>Context - Environment:</strong> " + environment + "</p>");
        out.println("<p><strong>Context - Support Email:</strong> " + supportEmail + "</p>");
        out.println("<p><strong>Config - Max Upload Size:</strong> " + maxUploadSize + " bytes</p>");
        out.println("<p><strong>Config - Allowed Extension:</strong> " + allowedExtension + "</p>");
    }
}