
package org.example;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {

        // 1. Retrieve form information
        String username =
                request.getParameter("username");

        String password =
                request.getParameter("password");

        // 2. Validate credentials
        if ("chinedu".equals(username)
                && "password123".equals(password)) {

            // 3. Create or retrieve session
            HttpSession session = request.getSession();

            // 4. Store user information
            session.setAttribute("username", username);
            session.setAttribute("studentId", 101);
            session.setAttribute("role", "STUDENT");

            // 5. Configure session timeout
            session.setMaxInactiveInterval(900);

            // 6. Rotate session ID after login
            request.changeSessionId();

            // 7. Redirect to dashboard
//            This is expected HTTP behavior because response.sendRedirect() sends an HTTP 302 Found status code,
//               which instructs browsers to make a brand-new GET request to the target URL.
            response.sendRedirect("dashboard");

        } else {

            // Invalid login
            response.sendRedirect("index.html");

        }
    }
}
