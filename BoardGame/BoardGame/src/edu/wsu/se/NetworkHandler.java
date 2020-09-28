package edu.wsu.se;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class NetworkHandler {

	boolean isServer = false;
	int port = 8080;
	String ip = "";
	
	int numberOfClients = 0;

	Socket socket = null;
	ServerSocket serverSocket = null;
	DataInputStream[] inputStreams = null;
	DataOutputStream[] outputStreams = null;

	public NetworkHandler(boolean isServer, String ip, int port) {
		this.isServer = isServer;
		this.ip = ip;
		this.port = port;

		inputStreams = new DataInputStream[3];
		outputStreams = new DataOutputStream[3];

		if (isServer) {
			// setup server
			try {
				serverSocket = new ServerSocket(port);
			} catch (IOException e) {
				System.out.println("Failed to start the Server");
				e.printStackTrace();
				System.exit(0);
			}
			System.out.println("Server Started");

		} else {
			// setup client
			try {
				System.out.println("Trying to connect to " + ip);
				socket = new Socket(ip, port);
				System.out.println("Connected to server");
				
				inputStreams[numberOfClients++] = new DataInputStream(System.in);
				
				
			} catch (IOException e) {
				System.out.println("Failed to connect to client");
				e.printStackTrace();
				System.exit(0);
			}
		}
	}

	public NetworkHandler(boolean isServer, int port) {
		this(isServer, "localhost", port);
	}

}
