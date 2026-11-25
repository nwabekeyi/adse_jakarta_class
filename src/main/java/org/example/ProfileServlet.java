
package org.example;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/profile")
public class ProfileServlet extends HttpServlet {

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        if (session == null
                || session.getAttribute("username") == null) {

            response.sendRedirect("index.html");
            return;
        }

        String username =
                (String) session.getAttribute("username");

        Integer studentId =
                (Integer) session.getAttribute("studentId");

        response.setContentType("text/html;charset=UTF-8");

        response.getWriter().println("<h1>Student Profile</h1>");

        response.getWriter().println(
                "<p>Username: " + username + "</p>"
        );

        response.getWriter().println(
                "<p>Student ID: " + studentId + "</p>"
        );

        response.getWriter().println(
                "<a href='dashboard'>Back to Dashboard</a>"
        );
    }
}
