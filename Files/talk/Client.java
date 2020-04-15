import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Client {
	public static void main(String[] args) throws IOException {
		Socket sock = null;
		try {
			sock = new Socket("hebubai.monster", 39008);
		} catch (IOException ioe) {
			System.out.println("Connect failed.Please check IP/ports or Start the Server");
		}
		try {
			InputStream input = sock.getInputStream();
			OutputStream output = sock.getOutputStream();
			System.out.println("[Server] " + sock.getRemoteSocketAddress());
			handle(input, output);
		} catch (RuntimeException re) {
			System.out.println("!!! Server offline !!!");
		} finally {
			sock.close();
			System.out.println("---------Exit!---------");
		}
	}

	private static void handle(InputStream input, OutputStream output) throws IOException {
		var writer = new BufferedWriter(new OutputStreamWriter(output, StandardCharsets.UTF_8));
		var reader = new BufferedReader(new InputStreamReader(input, StandardCharsets.UTF_8));
		Scanner scanner = new Scanner(System.in);
		for (;;) {
			System.out.print("[You] ");
			String s = scanner.nextLine();
			writer.write(s);
			writer.newLine();
			writer.flush();
			System.out.println("---------Sent!---------");
			String resp = reader.readLine();
			System.out.println("[Server] " + resp);
			if (resp.equals("bye")) {
				break;
			}
		}
	}
}