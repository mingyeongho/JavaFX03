package chat;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Iterator;
import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class ChatServer extends Application{
	
	ExecutorService threadPool; // Thread 개수를 제한
	ServerSocket serverSocket; // 서버의 socket 객체 선언
	Vector<Client> clients = new Vector<Client>();
	
	class Client { // send(), receive()
		Socket socket;
		
		public Client(Socket socket) {
			this.socket = socket;
		}
		
		public void receive() {
			Thread thread = new Thread(()-> {
				try {
					while(true) {
						InputStream in = socket.getInputStream();
						byte[] buf = new byte[100];
						
						int len = in.read(buf);
						if (len == -1) {
							throw new IOException();
						}
						System.out.println("[Success]" + socket.getRemoteSocketAddress() + ": " +Thread.currentThread().getName());
						String msg = new String(buf, 0, len, "UTF-8");
						for (Client client : clients) {
							client.send(msg);
						}
					}
				} catch (Exception e) {
					try {
						System.out.println("[Failure] " + socket.getRemoteSocketAddress() + ": " +Thread.currentThread().getName());
						clients.remove(Client.this);
						socket.close();
					} catch(Exception e2) {
						
					}
				}
			});
			threadPool.submit(thread);
//			thread.start();
		}
		
		public void send(String msg) {
			Thread thread = new Thread(()-> {
				try {
					OutputStream out = socket.getOutputStream();
					byte[] buf = msg.getBytes("UTF-8");
					out.write(buf);
					out.flush();
				} catch(Exception e) {
					try {
						System.out.println("[Failure] " + socket.getRemoteSocketAddress() + ": " + Thread.currentThread().getName());
						clients.remove(Client.this);
						socket.close();
					} catch(Exception e2) {
						
					}
				}
			});
			threadPool.submit(thread);
		}
	}
	
	public void startServer(String ipAddress, int port) {
		try {
			serverSocket = new ServerSocket();
			serverSocket.bind(new InetSocketAddress(ipAddress, port));
		} catch(Exception e) {
			if (!serverSocket.isClosed()) {
				stopServer();
			}
			return;
		}
		Thread thread = new Thread(()-> {
			while (true) {
				try {
					Socket socket = serverSocket.accept();
					clients.add(new Client(socket));
					System.out.println("[Connected with client] " + socket.getRemoteSocketAddress() + ": " + Thread.currentThread().getName());
				} catch(Exception e) {
					if (!serverSocket.isClosed()) {
						stopServer();
					}
					break;
				}
			}
		});
		threadPool = Executors.newCachedThreadPool();
		threadPool.submit(thread);
	}
	
	public void stopServer() {
		try {
			Iterator<Client> iterator = clients.iterator();
			while (iterator.hasNext()) {
				Client client = iterator.next();
				client.socket.close();
				iterator.remove();
			}
			if (serverSocket != null && !serverSocket.isClosed()) {
				serverSocket.close();
			}
			if (threadPool != null && !threadPool.isShutdown()) {
				threadPool.shutdown();
			}
		} catch(Exception e) {
			
		}
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		BorderPane root = new BorderPane();
		root.setPadding(new Insets(5));
		
		TextArea txtDisplay = new TextArea();
		txtDisplay.setEditable(false);
		txtDisplay.setFont(new Font("Consolas", 14));
		root.setCenter(txtDisplay);
		
		Button btnStartStop = new Button("Start");
		btnStartStop.setMaxWidth(Double.MAX_VALUE);
		BorderPane.setMargin(btnStartStop, new Insets(1,0,0,0));
		root.setBottom(btnStartStop);
		
		String ipAddress = "localhost";
		int port = 4000;
		
		
		
		Scene scene = new Scene(root, 500, 400);
		primaryStage.setTitle("Chatting Server");
		primaryStage.setOnCloseRequest(event->stopServer());
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}
	
	public void main(String[] args) {
		launch(args);
	}
}
