package org.example;

import io.github.cdimascio.dotenv.Dotenv;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.http.Cookie;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        renderLoginForm(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if ("admin".equals(username) && "secret123".equals(password)) {
            // Attach data directly to the current request (preserved across forward)
            request.setAttribute("authenticatedUser", username);
            request.setAttribute("loginNote", "Forwarded internally via RequestDispatcher.");

            // Obtain RequestDispatcher for the target servlet path (relative to context root)
            RequestDispatcher dispatcher = request.getRequestDispatcher("/dashboard");
            dispatcher.forward(request, response);
        } else {
            // Set error message as a request attribute and re-render the form
            request.setAttribute("errorMessage", "Invalid username or password.");
            renderLoginForm(request, response);
        }
    }

    private void renderLoginForm(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        String error = (String) request.getAttribute("errorMessage");


        //fecth environmental variables
        Dotenv dotenv = (Dotenv) getServletContext().getAttribute("dotenv");

        String appEnv = dotenv.get("APP_ENV");

        //setting cookie
        Cookie cookie =  new Cookie("serveName", "jakartaPractice");
        cookie.setMaxAge(60 * 60);
        cookie.setHttpOnly(true);

        if(!appEnv.equals("production")){
            cookie.setSecure(true);
        }

        response.addCookie(cookie);

        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html><html><body>");
            out.println("<h2>Login</h2>");

            if (error != null) {
                out.println("<p style='color:red;'>" + error + "</p>");
            }
            out.println("Server environment is: " + appEnv);
            out.println("<form method='post' action='" + request.getContextPath() + "/login'>");
            out.println("Username: <input type='text' name='username' required><br><br>");
            out.println("Password: <input type='password' name='password' required><br><br>");
            out.println("<button type='submit'>Sign In</button>");
            out.println("</form>");
            out.println("</body></html>");
        }
    }
}
