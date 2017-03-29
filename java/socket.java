import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class SocketUtil {
	Socket socket;

	private String HOSTNAME;
	private int PORTNUMBER;

	public SocketUtil( String hostName, int portNumber) {
		HOSTNAME = hostName;
		PORTNUMBER = portNumber;

	}
	
	private void socketConnect(String host, int port) {
		try {
			socket = new Socket(host, port);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void socketClose(){
		if (socket != null) {
			try {
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			socket = null;
		}
	}

	public String executaBusca(String commando, String finalMarker){

		socketConnect(HOSTNAME, PORTNUMBER);
		
		int c;
		
		InputStream in = null;
		try {
			in = socket.getInputStream();
		} catch (IOException e) {
			e.printStackTrace();
		}
		OutputStream out = null;
		try {
			out = socket.getOutputStream();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		byte buf[] = commando.getBytes();
		
		try {
			out.write(buf);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		try {
			in.read();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		StringBuffer result = new StringBuffer();

		boolean isHeader = true;

		char caractere;
		try {
			while ((c = in.read()) != -1) {
				caractere = ((char) c);
				result.append(caractere);

				if (result.toString().contains(finalMarker)) {
					break;
				}
			
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		socketClose();
		
		return result.toString();
	}
}
