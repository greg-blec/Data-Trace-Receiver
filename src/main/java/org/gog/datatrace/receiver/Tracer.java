package org.gog.datatrace.receiver;

import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.gog.datatrace.receiver.dictionnary.Dictionnary;
import org.gog.datatrace.receiver.formatting.MessageFormat;
import org.gog.datatrace.receiver.formatting.MessagetBuilder;
import org.gog.datatrace.receiver.listener.CarriageReturnEndedMessageListener;
import org.gog.datatrace.receiver.listener.FooterEndedMessageListener;
import org.gog.datatrace.receiver.tools.ByteArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fazecast.jSerialComm.SerialPort;

public class Tracer {

	private static final Logger logger = LoggerFactory.getLogger(Tracer.class);

	private Dictionnary dictionnary;
	
	private Tracer(Dictionnary dictionnary) {
		this.dictionnary = dictionnary;
	}
	

	public static Tracer getTracer() {
		Tracer tracer = new Tracer(null);
		return tracer;
	}

	public static Tracer getTracer(Dictionnary dictionnary) {
		Tracer tracer = new Tracer(dictionnary);
		return tracer;
	}

	public void run(String portName, String portsDescriptionMatchingString) {
		run(portName, portsDescriptionMatchingString, null);
	}
	
	
	public void run(String portName, String portsDescriptionMatchingString, List<MessageFormat> formats) {
		boolean isNotStopped = false;
		boolean traceBinaryToString = false;
		ByteArray messageByteArray = null;
		Queue<ByteArray> messageQueue = new ConcurrentLinkedQueue<ByteArray>();
		String lineToTrace = null;

		
		if ( (formats != null && formats.contains(MessageFormat.BINARY_TO_STRING) ) || dictionnary == null ) {
			traceBinaryToString = true;
		}
		
		SerialPort port = openSerialPort(portName, portsDescriptionMatchingString);
		
		isNotStopped = true;
		
		if ( traceBinaryToString ) {
			CarriageReturnEndedMessageListener carriageReturnEndedMessageListener = new CarriageReturnEndedMessageListener(messageQueue);
			port.addDataListener(carriageReturnEndedMessageListener);
		} else {
			FooterEndedMessageListener footerEndedMessageListener = new FooterEndedMessageListener(messageQueue);
			port.addDataListener(footerEndedMessageListener);
		}

		do {
			messageByteArray = messageQueue.poll();
			if ( messageByteArray != null ) {
				
				if ( traceBinaryToString ) {
					lineToTrace = MessagetBuilder.rawBinaryToString(messageByteArray.getBytes());
					logger.info("{}", lineToTrace);
				} else {
				
					Iterator<MessageFormat> it = formats.iterator();
					while (it.hasNext()) {
						MessageFormat type = (MessageFormat) it.next();
						
						lineToTrace = MessagetBuilder.buildString(type, this.dictionnary, messageByteArray.getBytes());
						if ( lineToTrace != null ) {
							logger.info("{}", lineToTrace);
						}
					}
				}
			}

		} while (isNotStopped);

		
		
//		try { Thread.sleep(5000); } catch (Exception e) { e.printStackTrace(); }
		port.removeDataListener();
		port.closePort();
		
		System.exit(0);
	}
	
	private SerialPort openSerialPort(String portName, String portsDescriptionMatchingString) {
		SerialPort ports[] = SerialPort.getCommPorts();
		SerialPort port = null;
		
		if (logger.isDebugEnabled()) {
			logger.info("Availables ports are:");
			for (SerialPort serialPort : ports) {
				logger.debug("  - {}: {}", serialPort.getSystemPortName(), serialPort.getPortDescription());
			}
		}

		if ( portName != null && portName.length() > 0 ) {
			for (SerialPort serialPort : ports) {
				if ( portName.toUpperCase().equals(serialPort.getSystemPortName()) ) {
					port = serialPort;
					logger.info("Selected port: {}", port.getSystemPortName());
					break;
				}
			}
		} else {
			for (SerialPort serialPort : ports) {
				if ( serialPort.getPortDescription().toLowerCase().contains(portsDescriptionMatchingString.toLowerCase())) {
					port = serialPort;
					logger.info("Selected port: {}", port.getSystemPortName());
					break;
				}
			}
		}

//		Force à attendre un buffer plein avant de sortir de read byte.
//		La taille du buffer est pilotée par le readByte()
		port.setComPortTimeouts(SerialPort.TIMEOUT_READ_BLOCKING, 1000, 0);
		port.setBaudRate(230400);
		boolean portOpened = port.openPort();
		if (portOpened) {
			logger.info("Port {} successfully opened", port.getSystemPortName());
		} else {
			logger.error("Port {} failed to open", port.getSystemPortName());
		}

		return port;
	}
	
	private void printRawData(ByteArray byteArray) {
		byte[] readBuffer;
		int nbBytesRead;

		readBuffer = byteArray.getBytes();
		nbBytesRead = readBuffer.length;

		byte[] hexStrFromBuffer = new byte[nbBytesRead * 2];
		byte[] hexFromBuffer = new byte[nbBytesRead];
		
		String readBufferAsString = null;
		String[] readBufferAsHex = new String[nbBytesRead];
		String readBufferAsHexStr = null;

		/* Convert bytes read to a string, directly from bytes */
		BinaryBufferParser.arrayCopyByteToByte(readBuffer, 0, hexStrFromBuffer, nbBytesRead);
		readBufferAsString = new String(hexStrFromBuffer, Charset.forName("ASCII"));

		/* Convert bytes read to an Hex string representation */
		BinaryBufferParser.arrayCopyByteToByte(readBuffer, 0, hexFromBuffer, nbBytesRead);
		BinaryBufferParser.arrayConvertByteToHex(hexFromBuffer, readBufferAsHex);
		readBufferAsHexStr = concatStrArray(readBufferAsHex);
		
		logger.info("Read {} bytes from serial port", nbBytesRead);
		logger.info(" Hex1: {}", readBufferAsString);
		logger.info(" Hex2: {}", readBufferAsHexStr);		
	}
	
	private static String concatStrArray(String [] array) {
		StringBuffer buffer = new StringBuffer();
		
		for (int i = 0; i < array.length; i++) {
			buffer.append(array[i]);
		}
		
		return buffer.toString();
	}

}
