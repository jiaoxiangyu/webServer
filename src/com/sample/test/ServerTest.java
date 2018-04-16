package com.sample.test;
import com.sample.http.HttpAccessProcessor;
import com.sample.http.HttpCreatorImpl;
import com.sample.http.HttpRequest;
import com.sample.http.HttpResponse;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
public class ServerTest {
	public static void main(String[] args) {
		//声明变量
		ServerSocket ss=null;
		Socket s=null;
		boolean flag=true;
		try {
			int port=10002;
			System.out.println("Server Port:"+port);
			ss=new ServerSocket(port);
			//while(flag)
			{
				//接受客户端发送过来的Socket
				s=ss.accept();
				HttpCreatorImpl hci=new HttpCreatorImpl(s);
				HttpRequest request=hci.getHttpRequest();
				HttpResponse response=hci.getHttpResponse();
				HttpAccessProcessor hapi=hci.getHttpAccessProcessor();
				//	用于测试收到的信息
				if(request.isStaticResource())//处理静态信息
				{
					hapi.processStaticResource(request, response);
				}
				else if(request.isDynamicResource())//处理动态请求
				{
					hapi.processDynamicResource(request, response);
				}
				else//无法处理时
				{
					System.out.println("无法处理");
					hapi.sendError(404, request, response);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
