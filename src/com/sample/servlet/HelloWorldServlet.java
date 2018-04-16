package com.sample.servlet;

import java.io.OutputStream;
import java.io.PrintWriter;

import com.sample.annontation.WebServlet;
import com.sample.http.HttpRequest;
import com.sample.http.HttpResponse;

//只有实现这个接口的类,才可以被浏览器发送请求访问到
@WebServlet(urlPatterns = "/hello")
public class HelloWorldServlet extends HttpServlet{

	public HelloWorldServlet() {
		super();
	}

	@Override
	public void doPost(HttpRequest request, HttpResponse response) {
		String name=request.getParameter("name");
		System.out.println(name);
		/*for (int i = 0; i < 1000000000; i++) {
			System.out.println(name+":"+i);
		}*/
		/*try {
			PrintWriter pw=response.getPrintWriter();
			pw.println("<!DOCTYPE html>");
			pw.println("<html lang=\"en\">");
			pw.println("<head>");
			pw.println("<meta charset=\"UTF-8\">");
			pw.println("<title>hello</title>");
			pw.println("</head>");
			pw.println("<body>");
			pw.println("<center>"+name+":这是我的Servlet</center>");
			pw.println("</body>");
			pw.println("</html>");
			pw.flush();
			pw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}*/
		try {
			OutputStream out=response.getOutputStream();
			out.write("sdjgdfijgi".getBytes());
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void doGet(HttpRequest request, HttpResponse response) {
		doPost(request,response);
	}




}
