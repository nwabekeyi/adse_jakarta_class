
package org.example;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/dashboard")
public class DashboardServlet extends HttpServlet {

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {

        // Retrieve existing session
        HttpSession session = request.getSession(false);

        // Check if user has a session
        if (session == null
                || session.getAttribute("username") == null) {

            response.sendRedirect("index.html");
            return;
        }

        // Retrieve session information
        String username =
                (String) session.getAttribute("username");

        Integer studentId =
                (Integer) session.getAttribute("studentId");

        String role =
                (String) session.getAttribute("role");

        // Prepare HTTP response
        response.setContentType("text/html;charset=UTF-8");

        // Display information
        response.getWriter().println("<h1>Student Dashboard</h1>");

        response.getWriter().println(
                "<h2>Welcome, " + username + "</h2>"
        );

        response.getWriter().println(
                "<p>Student ID: " + studentId + "</p>"
        );

        response.getWriter().println(
                "<p>Role: " + role + "</p>"
        );

        response.getWriter().println(
                "<a href='profile'>View Profile</a><br>"
        );

        response.getWriter().println(
                "<a href='logout'>Logout</a>"
        );
    }
}
