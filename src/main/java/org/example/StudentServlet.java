package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet("/students")
public class StudentServlet extends HttpServlet {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws IOException
    {

        if(request.getContentLength() <=0){
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
        Student student = objectMapper.readValue(request.getInputStream(), Student.class);
        student.setId(101);

        response.setContentType("appliation/json");
        response.setStatus(HttpServletResponse.SC_CREATED);

        objectMapper.writeValue(response.getWriter(), student);
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws IOException
    {
        Student student = new Student(
                1,
                "chidi",
                "Simeon",
                "chidi@yahoo.com",
                44
        );

        response.setContentType("application/json");


        //object mapping
        objectMapper.writeValue(response.getWriter(), student);
    }
}
