package com.ikamobile;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

@SpringBootApplication
public class Application {

	final public static int SHUTDOWN_PORT=8774;

	public static void main(String[] args) throws Exception {
		if(args!=null && args.length>0){
			boolean stop=false;
			int shutdownPort = SHUTDOWN_PORT;
			if(args.length==1 && "stop".equals(args[0].toLowerCase())){
				stop = true;
			}
			if(args.length==2 && "stop".equals(args[0].toLowerCase())){
				shutdownPort = Integer.valueOf(args[1]);
				stop = true;
			}
//            System.out.println("stop="+stop);
			if(stop){
				try {
					Socket socket = new Socket("127.0.0.1",shutdownPort);
					System.out.println("connect "+shutdownPort);
					OutputStream os = socket.getOutputStream();
					DataOutputStream dos = new DataOutputStream(os);
					dos.writeInt(Integer.MAX_VALUE);
					dos.flush();
					socket.close();
					System.out.println("停止服务成功");
				} catch (IOException e) {
					System.out.println("尝试停止服务失败，可能没有启动");
				}
				return;
			}
		}

		SpringApplication.run(Application.class, args);
	}
}
