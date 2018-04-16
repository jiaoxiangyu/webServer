package com.sapmle.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import com.sample.http.*;

public class ServerThread extends Thread {
	ServerSocket ss;
	private Socket s;

	public ServerThread(ServerSocket ss, Socket s) {
		this.ss=ss;
		this.s=s;
	}

	@Override
	public void run() {
		System.out.println("*******************socket:"+s.hashCode());
		System.out.println("*******************socketIP:"+s.getLocalAddress()+s.getPort());

		try {

			s.setKeepAlive(false);   //表示对于长时间处于空闲状态的Socket, 是否要自动把它关闭.
//			while (true) {
				//if (s.getInputStream()!=null){
					HttpCreator hci = new HttpCreatorImpl(s);
					HttpRequest request = hci.getHttpRequest();
					System.out.println(request.getProtocol());
					HttpResponse response = hci.getHttpResponse();
					HttpAccessProcessor hapi = hci.getHttpAccessProcessor();
					if (request.isStaticResource()) {
						hapi.processStaticResource(request, response);
					} else if (request.isDynamicResource()) {
						hapi.processDynamicResource(request, response);
					} else {
						System.out.println("无法处理");
						hapi.sendError(404, request, response);
					}
					System.out.println("*******************socket是否关闭:" + s.isClosed());
					System.out.println("*******************socket输入流是否关闭:" + s.isInputShutdown());
					System.out.println("*******************socket输出流是否关闭:" + s.isOutputShutdown());
					System.out.println("*******************socket是否连接:" + s.isConnected());

					System.out.println("*******************socket是否关闭:" + s.isClosed());
					System.out.println("*******************socket输入流是否关闭:" + s.isInputShutdown());
					System.out.println("*******************socket输出流是否关闭:" + s.isOutputShutdown());

				//}

			//}
			/*s.shutdownOutput();
			s.isInputShutdown();*/
			//sleep(8000);

			//s.close();

		} catch (Exception e) {
			e.printStackTrace();
		}


	}
}
