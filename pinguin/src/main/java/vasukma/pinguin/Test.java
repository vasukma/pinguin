package vasukma.pinguin;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Date;

/**
 * Hello world!
 *
 */
public class Test {
	public static void main(String[] args) throws IOException {
		Date date = new Date();
		final int timeOut = 1500; // 1.5 seconds
		//System.out.println("Beginning...");
//		Hosts.readFileHosts();
		new Hosts().readFileHosts();
		//for (int port = startPort; port <= endPort; port++) {
		for(Host host : Hosts.getHosts()) {
			//Boolean isOpenConnection = Boolean.TRUE;
			host.setAvailable(true);
			
			
			System.out.print(date.toString());
			System.out.print(" ");
			System.out.print(host.getDescription());
			System.out.print(" ");
			try (Socket socket = new Socket()) {
				socket.connect(new InetSocketAddress(host.getAddress(), host.getPort()), timeOut);
			} catch (Throwable cause) {
				host.setAvailable(false);
			}

			if (host.isAvailable()) {
				System.out.println("AVAILABLE");
			} else {
				System.out.println("UNAVAILABLE");
			}

		}
		Hosts.writeFileReport();
		//System.out.println("Finishing...");
	}
}
