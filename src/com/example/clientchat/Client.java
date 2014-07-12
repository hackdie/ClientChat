package com.example.clientchat;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.Socket;

import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class Client {

	private String action;
	ClientTask clientTask;
	String ip_server = "192.168.1.80";
	int puerto = 4444;
	private OnMessageReceived mMessageListener = null;

	public Client(OnMessageReceived listener) {

	}

	public Client(String action) {
		this.action = action;
	}

	public void setAction(String... data) {

	}

	public void Run() {

	}

	
	public interface OnMessageReceived {
        public void messageReceived(String message);
    }
}
