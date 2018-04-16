package com.sample.http;
import java.io.*;
import java.net.Socket;
import java.util.Arrays;

import com.sample.utils.ConfigUtils;
import com.sample.utils.StatusCodeUtils;
//http协议的响应
public class HttpResponseImpl  implements  HttpResponse {
	 //声明初始变量
	Socket s;//客户端socket
	OutputStream os;//输出端字节流
	BufferedWriter bw;//输出端字符流
	PrintWriter pw;
	StringBuffer sbuffer;//放状态行，\r\n ,
	FileInputStream fis;
	File file;
	//构造器
	public HttpResponseImpl(Socket s) {
        this.s=s;
        System.out.println("HttpRequestImpl(Socket s)");
        os=null;
        bw=null;
        pw=null;
        sbuffer=new StringBuffer();//初始化，记得，否则以下的操作会遇见空指针异常
        fis=null;
        file=null;   			
        getInfos();
	}
	  
	private void getInfos() {
			try {
				os=s.getOutputStream();
				//bw=new BufferedWriter(new OutputStreamWriter(os));
				//pw=new PrintWriter(os);
			} catch (Exception e) {
				e.printStackTrace();
			}
	}
	//获得一个指向客户端的字节流
	public OutputStream getOutputStream()throws Exception {
		return os;
	}

	//获得一个指向客户端的字符流
	public PrintWriter getPrintWriter()throws Exception {
		return pw;
	}

	//设置响应的状态行 参数为String类型
	public void setStatusLine(String statusCode) {
		String str=StatusCodeUtils.getStatusCodeValue(statusCode);
		//System.out.println(str+"------"+str.length());
		sbuffer.append("HTTP/1.1 "+statusCode+" "+str);
		setCRLF();
	}

	//设置响应的状态行 参数为int类型
	public void setStatusLine(int statusCode)
	{
		setStatusLine(statusCode+"");//将int类型转化为String类型
	}

	//设置响应消息报头
	public void setResponseHeader(String hName,String hValue) {
		sbuffer.append(hName+": "+hValue);
		setCRLF();
	}

	//设置响应消息报头中Content-Type属性
	public void setContentType(String contentType) {
		setResponseHeader("Content-Type",contentType);
	}

	//设置响应消息报头中Content-Type属性 并且同时设置编码
	public void setContentType(String contentType,String charsetName) {
		//text/html;charset=utf-8
		setContentType(contentType+"; charset="+charsetName);
	}

	//设置CRLF 回车换行  \r\n
	public void setCRLF()
	{
		sbuffer.append("\r\n");
	}

	//把设置好的响应状态行、响应消息报头、固定空行这三部分写给浏览器
	public void printResponseHeader() {
		//设置setResponseLine,setResponseHeader,setResponseType
		String res=sbuffer.toString();
		/*pw.print(res);
		pw.write("\n");
		pw.flush();*/
		try {
			os.write(res.getBytes());
			//os.write(" \n".getBytes());

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	//把响应正文写给浏览器
	public void printResponseContent(String requestPath) {
		//响应正文
		String getPath= requestPath;//客户请求的地址
		String webHome=(new ConfigUtils()).getConfigValue("rootPath");	
		System.out.println("配置文件中目录:"+webHome);//输出从配置文件中获取的地址
		file=new File(webHome+getPath);
		System.out.println(file.getPath());
		System.out.println(file.exists());
		if(file.exists()){//如果文件存在就执行
			System.out.println("该文件存在");
			try {
				fis=new FileInputStream(file);
				byte[] buf=new byte[1024];
				int len=-1;
				while((len=fis.read(buf,0,1024))!=-1) {
					os.write(buf, 0, len);
					System.out.println(Arrays.toString(buf));
				}
				System.out.println("****");
				os.flush();
				//os.close();
			} catch (IOException e) {
				e.printStackTrace();
			}/*finally {
				try {
					if (os!=null) {
						os.close();
					}
					if(bw!=null){
						bw.close();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}*/
		}
	}
}