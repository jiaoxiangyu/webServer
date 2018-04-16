package com.sample.annontation;

import com.sample.servlet.Servlet;
import com.sample.utils.ConfigUtils;
import com.sun.jndi.toolkit.url.UrlUtil;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class ServletMapping {

    //存放servlet映射关系 key:urlPatterns value:className
    public static ConcurrentMap<String,String> servletMapping=null;

    public ServletMapping() {
        servletMapping=new ConcurrentHashMap<String,String>();
        init();
    }

    public void init(){
        Class<Servlet> clazz;
        WebServlet webServlet;
        try {
            String packageDir=ConfigUtils.getConfigValue("rootPath").substring(0,23) +"src/"
                    +ConfigUtils.getConfigValue("servletMapping").replace(".","/");
            //dirs = Thread.currentThread().getContextClassLoader().getResources(packageDirName);
            if (packageDir!=null){
                File dir=new File(packageDir);
                if (dir.exists()){
                    File[] files=dir.listFiles();
                    for (File file:files){
                        String className=ConfigUtils.getConfigValue("servletMapping") +"."
                                +file.getName().replace(".","&").split("&")[0];
                        clazz= (Class<Servlet>) Class.forName(className);
                        if (clazz.isAnnotationPresent(WebServlet.class)){
                            webServlet=(WebServlet) clazz.getAnnotation(WebServlet.class);
                            String urlPatterns=webServlet.urlPatterns();
                            if (urlPatterns!=null && urlPatterns!=""){
                                servletMapping.put(urlPatterns,clazz.getName());
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static ConcurrentMap<String,String> getServletMapping(){
        if (servletMapping==null){
           new ServletMapping();
        }
        return servletMapping;
    }

    public static void main(String[] args) {
        ConcurrentMap<String,String> map=ServletMapping.getServletMapping();
        System.out.println(map);
        try {
            Class clazz=Class.forName(map.get("/hello"));
            Servlet servlet=(Servlet) clazz.newInstance();
            System.out.println(servlet);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
