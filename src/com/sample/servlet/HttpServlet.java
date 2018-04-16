package com.sample.servlet;

import com.sample.http.HttpRequest;
import com.sample.http.HttpResponse;

import java.io.Serializable;

public class HttpServlet implements Servlet, Serializable {

    private static final long serialVersionUID = 1L;

    public HttpServlet() {

    }

    @Override
    public void init() {

    }

    @Override
    public void doPost(HttpRequest request, HttpResponse response) {

    }

    @Override
    public void doGet(HttpRequest request, HttpResponse response) {

    }

    @Override
    public void service(HttpRequest request, HttpResponse response) {
        if (request.getRequestMethod().equals("GET")){
            doGet(request,response);
        }else if (request.getRequestMethod().equals("POST")){
            doPost(request,response);
        }
    }

    @Override
    public void destroy() {

    }
}
