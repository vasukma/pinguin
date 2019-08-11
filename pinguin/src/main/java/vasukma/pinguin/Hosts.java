package vasukma.pinguin;

import java.util.List;

import java.util.Date;

import java.io.*;
import java.util.ArrayList;

class Host {
	// private static int count = 0;
	// private final int id = count++;
	private String description;
	private String address;
	private String port;
	private String rawString;
	private boolean available;

	public String getRawString() {
		return rawString;
	}

	public boolean isAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}

	public String getDescription() {
		return description;
	}

	public String getAddress() {
		return address;
	}

	public Host(String[] rawSplitString) {
		description = rawSplitString[0];
		address = rawSplitString[1];
		port = rawSplitString[2];
	}

	public int getPort() {
		return Integer.parseInt(port);
	}

}

public class Hosts {// extends LinkedList {
	static private List<Host> hosts = new ArrayList<Host>();
	static private String hostsFileName = "hosts.txt";
//	static private String hostsFileName = "/home/maximus/git/pinguin/pinguin/src/main/resources/hosts.txt";
	static private String reportFileName = "pinguin.log";

	public void readFileHosts() throws IOException {
		

		BufferedReader hostsFileReader = new BufferedReader(new FileReader(hostsFileName));
		String rawString;
		// StringBuilder sb = new StringBuilder();
		while ((rawString = hostsFileReader.readLine()) != null)
			// s.split(":");
			hosts.add(new Host(rawString.split(":")));
		hostsFileReader.close();
		// return sb.toString();
	}

	public static void writeFileReport() throws IOException {
		BufferedWriter reportFileWriter = new BufferedWriter(new FileWriter(reportFileName, true));
		StringBuilder reportString = new StringBuilder();
		Date date = new Date();
		for (Host host : Hosts.getHosts()) {
			reportString.append(date.toString()).append(": ").append(host.getDescription())
			.append(" (").append(host.getAddress()).append(":").append(host.getPort()).append(") ");
			if (host.isAvailable())
				reportString.append("AVAILABLE");
			else
				reportString.append("UNAVAILABLE");
			reportString.append("\r\n");
			reportFileWriter.write(reportString.toString());
			reportString.delete(0, reportString.length());
		}

		reportFileWriter.close();
	}

	public static void main(String[] argc) throws IOException {
		new Hosts().readFileHosts();
		for (Host host : hosts)
			System.out.println(host.getDescription());

	}

	public static List<Host> getHosts() {
		return hosts;
	}

}
