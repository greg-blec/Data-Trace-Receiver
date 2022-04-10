package org.gog.datatrace.receiver.listener;

import java.util.Queue;

import org.gog.datatrace.receiver.tools.ByteArray;

import com.fazecast.jSerialComm.SerialPort;
import com.fazecast.jSerialComm.SerialPortEvent;
import com.fazecast.jSerialComm.SerialPortMessageListener;

public class FooterEndedMessageListener implements SerialPortMessageListener {
	Queue<ByteArray> messagesQueue;
	

	public FooterEndedMessageListener(Queue<ByteArray> messagesQueue) {
		this.messagesQueue = messagesQueue;
	}

	@Override
	public int getListeningEvents() {
		return SerialPort.LISTENING_EVENT_DATA_RECEIVED;
	}

	@Override
	public void serialEvent(SerialPortEvent event) {
		messagesQueue.add(new ByteArray(event.getReceivedData()));
	}

	@Override
	public byte[] getMessageDelimiter() {
		return new byte[] { (byte)'D', (byte)'A', (byte)'$' };
	}

	@Override
	public boolean delimiterIndicatesEndOfMessage() {
		return true;
	}

}
