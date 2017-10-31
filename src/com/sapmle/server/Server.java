package com.sapmle.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import com.sample.utils.ServerPortUtils;

public class Server {
	public static void main(String[] args) {
		//声明变量
        ServerSocket ss=null;
        Socket s=null;
        boolean flag=true;
        	int port=Integer.valueOf(ServerPortUtils.getPortValue("serverPort"));
        	int i=1;
        	System.out.println("Server Port:"+port);
			try {
				ss=new ServerSocket(port);
				while(flag)
				{
					//接受客户端发送过来的Socket
					s=ss.accept();
					System.out.println("accept count:"+i++);
					ServerThread st=new ServerThread(s);
					st.start();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
	}
