
package org.example;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {

        // Retrieve existing session
        HttpSession session = request.getSession(false);

        // Check whether session exists
        if (session != null) {

            // Invalidate session
            session.invalidate();
        }

        // Redirect to login page
        response.sendRedirect("index.html");
    }
}
