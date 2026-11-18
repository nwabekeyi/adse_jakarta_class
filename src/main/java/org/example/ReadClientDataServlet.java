package org.example;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;

@WebServlet("/api/text-reader")
public class ReadClientDataServlet extends HttpServlet {
    @Override
    protected  void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //Declare stringbuiler payload
        StringBuilder payload =  new StringBuilder();

        //reading request body into string builder payload
        try(BufferedReader reader = request.getReader()){
            String line;
             while((line =  reader.readLine()) != null){
                 payload.append(line);
             }
        }
        String jsonOrTextData = payload.toString();

        response.setContentType("text/plain");
        response.getWriter().write("received payload length of " + jsonOrTextData.length());
    }
}
