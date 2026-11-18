package org.example;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class HelloServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    String name = request.getParameter("username");
    String category = request.getParameter("category");

//    response object methods
    response.setContentType("text/plain");
    response.setCharacterEncoding("UTF-8");
    response.setHeader("Custom-Header", "jakarta");
    response.setHeader("Authorization", "Bearer uqwfrtf3uygiuef8twqufgwu7gqe8fugty7fg8yqcfg8ydqgfy8u");

    //get user agent
        String userDeviceInfo = request.getHeader("User-Agent");
 //user Ip address
 String clientIp = request.getRemoteAddr();

 response.setStatus(HttpServletResponse.SC_ACCEPTED);

    if(category == null){
        response.getWriter().println(name +  " " + clientIp);
    }else{
        response.getWriter().println(name + " " + category);
    }
    }
}
