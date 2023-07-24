package com.services;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Logger;

public class Servlet extends HttpServlet {
    //logger
    private static final Logger logger = Logger.getLogger(Servlet.class.getName());
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String path = request.getRequestURI().substring(request.getContextPath().length());
        logger.info("path: " + path);
        System.out.println("path: " + request.getServletPath());
        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }
}
