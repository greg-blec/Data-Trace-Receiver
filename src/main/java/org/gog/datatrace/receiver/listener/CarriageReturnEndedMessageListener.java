package org.gog.datatrace.receiver.listener;

import java.util.Queue;

import org.gog.datatrace.receiver.tools.ByteArray;

import com.fazecast.jSerialComm.SerialPort;
import com.fazecast.jSerialComm.SerialPortEvent;
import com.fazecast.jSerialComm.SerialPortMessageListener;

public class CarriageReturnEndedMessageListener implements SerialPortMessageListener {
	Queue<ByteArray> messagesQueue;
	

	public CarriageReturnEndedMessageListener(Queue<ByteArray> messagesQueue) {
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
		return new byte[] { (byte)'\n' };
	}

	@Override
	public boolean delimiterIndicatesEndOfMessage() {
		return true;
	}

}
