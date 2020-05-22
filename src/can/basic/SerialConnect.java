package can.basic;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import gnu.io.CommPort;
import gnu.io.CommPortIdentifier;
import gnu.io.NoSuchPortException;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;
import gnu.io.UnsupportedCommOperationException;
//�ø�������� ��� ��ü
public class SerialConnect{
	InputStream in;
	BufferedInputStream bis;
	OutputStream out;
	CommPort commPort;
	public void connect(String portName,String appName) {
		try {
			CommPortIdentifier commPortIdentifier = 
					CommPortIdentifier.getPortIdentifier(portName);
			//��Ʈ�� ��������� üũ
			if(commPortIdentifier.isCurrentlyOwned()) {
				System.out.println("��Ʈ ����� �� �����ϴ�.");
			}else {
				System.out.println("��Ʈ ��밡��.");
				commPort =	commPortIdentifier.open(appName,
								5000);
				System.out.println(commPort);
				if(commPort instanceof SerialPort) {
					System.out.println("SerialPort");
					SerialPort serialPort = (SerialPort)commPort;
					serialPort.setSerialPortParams(38400,
							SerialPort.DATABITS_8,
							SerialPort.STOPBITS_1,
							SerialPort.PARITY_NONE);
					in = serialPort.getInputStream();
					bis = new BufferedInputStream(in);
					out = serialPort.getOutputStream();
				}
			}
		} catch (NoSuchPortException e) {
			e.printStackTrace();
		} catch (PortInUseException e) {
			e.printStackTrace();
		} catch (UnsupportedCommOperationException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public BufferedInputStream getBis() {
		return bis;
	}
	public OutputStream getOut() {
		return out;
	}
	public CommPort getCommPort() {
		return commPort;
	}
	
}//end class








