package can.basic;

import java.io.BufferedInputStream;
import java.io.IOException;

import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;

//�ø�����Ʈ�� ���ؼ� �����Ͱ� ���۵Ǿ����� ����Ǵ� Ŭ����
public class SerialListener implements SerialPortEventListener{
	BufferedInputStream bis;//ĵ������� input�Ǵ� �����͸� �б� ���� ���µȽ�Ʈ��
	public SerialListener(BufferedInputStream bis) {
		this.bis = bis;
	}
	//�����Ͱ� ���۵ɶ����� ȣ��Ǵ� �޼ҵ�
	@Override
	public void serialEvent(SerialPortEvent event) {
		switch(event.getEventType()) {
			case SerialPortEvent.DATA_AVAILABLE:
				byte[] readBuffer = new byte[128];
				try {
					while(bis.available()>0) {
						int numbyte = bis.read(readBuffer);
					}
					String data = new String(readBuffer);
					System.out.println("Can�ø�����Ʈ�� ���۵� ������==>"+data);
					//���۵Ǵ� �޽����� �˻��ؼ� �����ϰ� �ٸ� ��ġ�� �����ϰų�
					//CarŬ���̾�Ʈ ��ü�� �����ؼ� ������ ���۵ǵ��� ó��
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
	}

}