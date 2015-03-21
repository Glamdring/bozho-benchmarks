package com.test;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import org.apache.commons.io.IOUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns="/read")
public class TestServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
            IOException {
        resp.setContentType("text/html");
        IOUtils.copy(new BufferedInputStream(new FileInputStream("C:\\workspace-any\\async-benchmark\\nodejs\\largefile")), resp.getOutputStream());
    }
}
