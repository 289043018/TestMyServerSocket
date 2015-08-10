package com.hand.TestMyServerSocket;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.Socket;

public class ChatSocket extends Thread {
	
	Socket socket;
	public ChatSocket(Socket s) {
		this.socket = s;
		
	}
	public void out(String out) {
		try {
			socket.getOutputStream().write((out+"\n").getBytes("UTF-8"));
			
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("断开了一个客户链接");
			ChatManager.getChatManager().remove(this);
			e.printStackTrace();
		};
	}
	
	@Override
	public void run() {

//		try {
//
//			BufferedWriter bw = 
//					new BufferedWriter(
//							new OutputStreamWriter(
//									socket.getOutputStream()));
//			int count = 0;
//			while (true) {
//				bw.write("loop:"+count);
//				sleep(1000);
//			}
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
//		int count = 0;
//		while (true) {
//			count++;
//			out("loop="+count);
//			try {
//				sleep(1000);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
		try {
			BufferedReader br = new BufferedReader(
					new InputStreamReader(
							socket.getInputStream(),"UTF-8"));
			String line = null;
			while ((line = br.readLine())!=null) {
				System.out.println(line);
				ChatManager.getChatManager().publish(this, line);
			}
			br.close();
			System.out.println("断开了一个客户端链接");
			ChatManager.getChatManager().remove(this);
		} catch (IOException e) {
			System.out.println("断开了一个客户端链接");
			ChatManager.getChatManager().remove(this);
			e.printStackTrace();
		}
		
	}
	
}
