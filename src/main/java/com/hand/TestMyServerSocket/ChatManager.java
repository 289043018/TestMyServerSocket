package com.hand.TestMyServerSocket;

import java.util.Vector;

public class ChatManager {

	//单例化,只有一个聊谈窗口
	private ChatManager(){}
	private static final ChatManager cm = new ChatManager();
	public static ChatManager getChatManager() {
		return cm;
	}
	
	Vector<ChatSocket> vector = new Vector<ChatSocket>();
	public void add(ChatSocket cs) {
		vector.add(cs);
	}
	public void remove(ChatSocket cs) {
		vector.remove(cs);
	}
	
	public void publish(ChatSocket cs,String out) {
		for (int i = 0; i < vector.size(); i++) {
			ChatSocket csChatSocket = vector.get(i);
			if (!cs.equals(csChatSocket)) {
				csChatSocket.out(out);
			}
		}
	}
}
