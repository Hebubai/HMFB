package web;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Server {
	public static void main(String[] args) throws IOException {
		ServerSocket ss = null;
		try {
			ss = new ServerSocket(39008);
		} catch (IOException e) {
			System.out.println("Please change the ports");
		}
		System.out.println("---Server is running---");
		System.out.println("---Waitting Client/s---");
		for (;;) {
			Socket sock = ss.accept();
			System.out.println(sock.getRemoteSocketAddress() + " online!!!");
			Thread t = new Handler(sock);
			t.start();
		}
	}
}

class Handler extends Thread {
	Socket sock;

	public Handler(Socket sock) {
		this.sock = sock;
	}

	@Override
	public void run() {
		try (InputStream input = this.sock.getInputStream()) {
			try (OutputStream output = this.sock.getOutputStream()) {
				handle(input, output);
			}
		} catch (Exception e) {
			try {
				this.sock.close();
			} catch (IOException ioe) {
			}
		}
	}

	private void handle(InputStream input, OutputStream output) throws IOException {
		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(output, StandardCharsets.UTF_8));
		BufferedReader reader = new BufferedReader(new InputStreamReader(input, StandardCharsets.UTF_8));
		Scanner scanner = new Scanner(System.in);
		// writer.write("hello\n");
		// writer.flush();
		for (;;) {
			String s = reader.readLine();
			if (s.equals("bye")) {
				writer.write("bye");
				writer.newLine();
				writer.flush();
				System.out.println(sock.getRemoteSocketAddress() + " offline!!!");
				System.out.println("---Waitting Client/s---");
				break;
			}
			System.out.println("[Client] " + s);
			System.out.print("[Server] ");
			String sc = scanner.nextLine();
			writer.write(sc);
			writer.newLine();
			writer.flush();
			System.out.println("---------Done!---------");
		}
	}
}