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

	ServerSocket serverSoc = null;
	Server server = null;
	Client[] clients = null;

	public NetworkHandler(boolean isServer, String ip, int port) {
		this.isServer = isServer;
		this.ip = ip;
		this.port = port;

		if (isServer) {
			//setup server
			clients = new Client[3];
			// start server
			try {
				serverSoc = new ServerSocket(port);
			} catch (IOException e) {
				System.out.println("Failed to start server");
				e.printStackTrace();
				System.exit(0);
			}

			// wait for clients to connect
			while (numberOfClients < 3) {
				clients[numberOfClients] = new Client(numberOfClients);
				clients[numberOfClients++].waitForConnection(serverSoc);
			}
		} else {
			// client setup
			server = new Server(ip, port);
			server.connectToServer();
		}
	}

	public NetworkHandler(boolean isServer, int port) {
		this(isServer, "localhost", port);
	}

	public class Client {

		DataInputStream dis = null;
		DataOutputStream dos = null;

		Socket socket = null;

		int clientNumber = 0;

		public Client(int number) {
			clientNumber = number;
		}

		public void waitForConnection(ServerSocket soc) {
			try {
				socket = soc.accept();
				dos = new DataOutputStream(socket.getOutputStream());
				dis = new DataInputStream(socket.getInputStream());
				System.out.println("Client Connected!");
			} catch (IOException e) {
				System.out.println("Client Failed To Connect");
				e.printStackTrace();
				System.exit(0);
			}
		}

		public String readUTF() {
			System.out.println("Reading Message From (" + clientNumber + ")");
			String s = "";
			try {
				s = dis.readUTF();
			} catch (IOException e) {
				System.out.println("Failed to read message");
				e.printStackTrace();
				System.exit(0);
			}
			return s;
		}

		public void writeUTF(String s) {
			System.out.println("Writing Message to (" + clientNumber + ")");
			try {
				dos.writeUTF(s);
			} catch (IOException e) {
				System.out.println("Failed to write message");
				e.printStackTrace();
			}
			System.out.println("message written");
		}

	}

	public class Server {

		DataInputStream dis = null;
		DataOutputStream dos = null;

		Socket socket = null;

		String ip = "";
		int port = 8080;

		public Server(String ip, int port) {
			this.ip = ip;
			this.port = port;
		}

		public void connectToServer() {
			try {
				System.out.println("Trying to connect to server at " + ip + ":" + port);
				socket = new Socket(ip, port);
				dos = new DataOutputStream(socket.getOutputStream());
				dis = new DataInputStream(socket.getInputStream());
				System.out.println("Connected To Server");
			} catch (IOException e) {
				System.out.println("Failed to connect to server");
				e.printStackTrace();
				System.exit(0);
			}
		}

		public String readUTF() {
			System.out.println("Reading Message From Server");
			String s = "";
			try {
				s = dis.readUTF();
			} catch (IOException e) {
				System.out.println("Failed to read message");
				e.printStackTrace();
				System.exit(0);
			}
			return s;
		}

		public void writeUTF(String s) {
			System.out.println("Writing Message to Server");
			try {
				dos.writeUTF(s);
			} catch (IOException e) {
				System.out.println("Failed to write message");
				e.printStackTrace();
			}
			System.out.println("message written");
		}
	}

}
