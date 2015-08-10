package com.hand.TestMyServerSocket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JOptionPane;

public class ServerListener extends Thread {

	@Override
	public void run() {
		//1-65535
        try {
			ServerSocket serverSocket = new ServerSocket(12345);
			while (true) {
				//阻塞式监听方法
				Socket socket =  serverSocket.accept();
				//建立连接
				JOptionPane.showMessageDialog(null, "有客户端连接到了12345端口");
				//将socket传递给新的线程
				ChatSocket cs =  new ChatSocket(socket);
				cs.start();
				ChatManager.getChatManager().add(cs);
			}
			
			
		} catch (IOException e) {

			e.printStackTrace();
		}
	}
	
}
