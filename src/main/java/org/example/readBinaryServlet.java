package org.example;

import jakarta.servlet.ServletInputStream;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;


@WebServlet("/api/binary-reader")
public class readBinaryServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        byte[] binaryData;

        // 1. Read from ServletInputStream into a ByteArrayOutputStream
        try (ServletInputStream inputStream = request.getInputStream();
             ByteArrayOutputStream buffer = new ByteArrayOutputStream()) {

            byte[] dataChunk = new byte[4096];
            int bytesRead;
            while ((bytesRead = inputStream.read(dataChunk)) != -1) {
                buffer.write(dataChunk, 0, bytesRead);
            }
            binaryData = buffer.toByteArray();
        }

        // 2. Process the raw byte array
        response.setContentType("text/plain");
        response.getWriter().write("Received binary payload of " + binaryData.length + " bytes.");
    }
}
