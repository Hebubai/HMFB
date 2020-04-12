package web;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ConnectException;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Client {
	public static void main(String[] args) throws IOException {
		Socket sock=null;
		try {
		sock = new Socket("localhost", 39001);}
		catch(IOException e) {
			System.out.println("Connect failed.Please check IP/ports or Start the Server");
			throw e;
		}
		try (InputStream input = sock.getInputStream()) {
			try (OutputStream output = sock.getOutputStream()) {
				System.out.println("[Server-IP] " + sock.getRemoteSocketAddress());
				handle(input, output);
			}
		}
		sock.close();
		System.out.println("---Disconnected---");
	}

	private static void handle(InputStream input, OutputStream output) throws IOException {
		var writer = new BufferedWriter(new OutputStreamWriter(output, StandardCharsets.UTF_8));
		var reader = new BufferedReader(new InputStreamReader(input, StandardCharsets.UTF_8));
		Scanner scanner = new Scanner(System.in);
		// System.out.println("[server] " + reader.readLine());
		for (;;) {
			System.out.print("[Your] ");
			String s = scanner.nextLine();
			writer.write(s);
			writer.newLine();
			writer.flush();
			String resp = reader.readLine();
			System.out.println("[Server] " + resp);
			if (resp.equals("bye")) {
				break;
			}
		}
	}
}