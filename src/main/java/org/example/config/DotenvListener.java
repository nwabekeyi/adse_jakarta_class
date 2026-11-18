package org.example.config;


import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import io.github.cdimascio.dotenv.Dotenv;

@WebListener
public class DotenvListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {

        Dotenv dotenv = Dotenv.configure()
                .ignoreIfMissing()
                .load();

        sce.getServletContext().setAttribute("dotenv", dotenv);
    }
}
