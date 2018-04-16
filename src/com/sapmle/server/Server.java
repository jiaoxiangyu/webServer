package com.sapmle.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.sample.utils.ServerPortUtils;

public class Server {
	public static void main(String[] args) {
		//声明变量
        ServerSocket ss=null;
        Socket s=null;
        //创建缓存线程池
		ExecutorService executor = Executors.newCachedThreadPool();
        boolean flag=true;
		int port=Integer.valueOf(ServerPortUtils.getPortValue("serverPort"));
		int i=1;
		System.out.println("Server Port:"+port);
		System.out.println("URL:http://localhost:"+port+"/HelloWorld?name=");
		try {
			ss=new ServerSocket(port);
			while(flag)
			{
				System.out.println(i++);
				//接受客户端发送过来的Socket
				s=ss.accept();
				executor.execute(new ServerThread(ss,s));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
