package com.test;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;

@WebServlet(urlPatterns="/read")
public class TestServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private boolean readInMemory;
    
    @Override
    public void init() throws ServletException {
        readInMemory = System.getProperty("readInMemory") != null;
        super.init();
    }
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
            IOException {
        resp.setContentType("text/html");
        if (readInMemory) {
            String content = IOUtils.toString(new BufferedInputStream(new FileInputStream(req.getServletContext().getRealPath("/largefile"))));
            IOUtils.write(content, resp.getOutputStream());
        } else {
            IOUtils.copy(new BufferedInputStream(new FileInputStream(req.getServletContext().getRealPath("/largefile"))), resp.getOutputStream());
        }
    }
}
