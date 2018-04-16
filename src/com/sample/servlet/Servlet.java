package com.sample.servlet;

import com.sample.http.HttpRequest;
import com.sample.http.HttpResponse;

import java.io.Serializable;

//只有实现这个接口的类,才可以被浏览器发送请求访问到
public interface Servlet{
	public void init();

	public void doPost(HttpRequest request,HttpResponse response);

	public void doGet(HttpRequest request,HttpResponse response);

	//被浏览器发请求访问到的对象会调用这个指定方法service,进行处理这次浏览器的请求
	public void service(HttpRequest request,HttpResponse response);

	public void destroy();
}
