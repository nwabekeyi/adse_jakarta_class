package org.example;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/dashboard")
public class DashboardServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Read attributes set by LoginServlet on the exact same request
        String username = (String) request.getAttribute("authenticatedUser");
        String note = (String) request.getAttribute("loginNote");

        // Guard against direct access if someone bypasses LoginServlet
        if (username == null) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Direct access denied.");
            return;
        }

        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html><html><body>");
            out.println("<h2>Welcome to your Dashboard, " + username + "!</h2>");
            out.println("<p>" + note + "</p>");
            out.println("<a href='" + request.getContextPath() + "/login'>Back to Login</a>");
            out.println("</body></html>");
        }
    }
}
