package can.basic;

import java.io.IOException;
import java.io.OutputStream;
import java.util.TooManyListenersException;

import gnu.io.SerialPort;
/* CAN����� ���� ó��
 * 1. ���Ž����ϱ� - �����͸� ������ �غ� �Ǿ����� ����
 * 2. ������ �����ϱ�
 * 3. ������ �۽��ϱ�
 * 
 */
import serial.SerialConnect;

//ĵ����ϱ� ���� ���� ���ø� ���
public class CANConnect {
	SerialConnect serialConnect;//CAN�ø�����Ʈ ����
	OutputStream out;//CAN�� output����� ��Ʈ��
	public CANConnect(String portname) {
		//�ø�������� ���� ����
		serialConnect = new SerialConnect();
		serialConnect.connect(portname, this.getClass().getName());
		
		//input, output�۾��� �ϱ� ���� �����ʸ� port�� ����
		SerialPort commport = (SerialPort)serialConnect.getCommPort();
		SerialListener listener = 
						new SerialListener(serialConnect.getBis());
		try {
			commport.addEventListener(listener);
			commport.notifyOnDataAvailable(true);
		} catch (TooManyListenersException e) {
			e.printStackTrace();
		}
		//output��Ʈ�� ���
		out = serialConnect.getOut();
		
		
		//�����尡 ����� �� �ֵ��� 
		new Thread(new CanSerialWrite()).start();
		System.out.println("ĵ ����� ���۵Ǿ����ϴ�...");
	}
	//CAN����� �ϱ� ���� ������ - CAN output����� �ϴ� ������
	class CanSerialWrite implements Runnable{
		String data;//CAN���� ������ ������
		CanSerialWrite(){
			//1. CAN����� �� �� �ִ� ���¶�� ���� �˷��ֱ� - CAN������ ����
			this.data = ":G11A9\r";//���Ž��ۿ� ���õ� ������ ������
		}
		@Override
		public void run() {
			byte[] outputdata = data.getBytes();
			try {
				out.write(outputdata);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	public static void main(String[] args) {
		new CANConnect("COM17");
	}
}





