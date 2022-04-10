package org.gog.datatrace.receiver.formatting;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;

import org.gog.datatrace.receiver.BinaryBuffer;
import org.gog.datatrace.receiver.dictionnary.DataDefinition;
import org.gog.datatrace.receiver.dictionnary.Dictionnary;
import org.gog.datatrace.receiver.dictionnary.DictionnaryEntry;
import org.gog.datatrace.receiver.dictionnary.datatype.AbstractDataType;
import org.gog.datatrace.receiver.dictionnary.datatype.ArrayOf;
import org.gog.datatrace.receiver.dictionnary.datatype.StringDataType;
import org.gog.datatrace.receiver.tools.ByteArrayTool;

public class MessagetBuilder {

	private static StringDataType idDataType = new StringDataType(4);

	public static String buildString(MessageFormat format, Dictionnary dictionnary, byte[] dataBuffer) {
		String id = null;
		StringBuffer result = new StringBuffer();
		
		try {
			BinaryBuffer buffer = BinaryBuffer.getInstance(dataBuffer);
			id = getFomrattedValue(idDataType, buffer);
			
			result.append(System.currentTimeMillis()).append(" - ").append(id).append(" - ");
			
			DictionnaryEntry entry = dictionnary.getEntry(id);
			
			if ( entry == null ) {
				if ( dictionnary.isIgnoreUnknownEntryId() ) {
					return null;
				}

				return "Entry with id <" + id + "> does not exists";
			}
			
			if ( entry.isSkipDisplay() ) {
				return null;
			}

//			if ( entry.getNumberOfBytes() > buffer.getSize() ) {
//				result.append("The buffer size (").append(buffer.getSize()).append(") is smaller than the expected size of ").append(entry.getNumberOfBytes());
//				return result.toString();
//			}

			List<DataDefinition> dataDefinitions = entry.getDataDefinitions();
			Object formattedStrings[] = new String[dataDefinitions.size()];
			DataDefinition dataDefinition = null;
			
			for (int i = 0; i < dataDefinitions.size(); i++) {
				dataDefinition = dataDefinitions.get(i);
				
				if ( dataDefinition.getDataType() instanceof ArrayOf ) {
					formattedStrings[i] = getArrayFomrattedValue(format, (ArrayOf)dataDefinition.getDataType(), buffer);
				} else {
					formattedStrings[i] = getValueToDisplay(format, dataDefinition.getDataType(), buffer);
				}
			}
			
			switch ( format ) {
				case SPLITED_BINARY:
				case SPLITED_FORMATTED:
				case SPLITED_HEX:
					result.append(String.format(buildSplitFormatString(entry), formattedStrings));
	
				default:
					result.append(String.format(entry.getMessage(), formattedStrings));
			}
			
			
			
		} catch (Exception e) {
//			logger.error("Error : {}", e);
			e.printStackTrace(System.err);
			result.append("Could not build fomatted data: " + e.getMessage());	
		}

		return result.toString();

	}
	
	/**
	 * Convert a binary array of byte to a String.
	 * 
	 * @param dataBuffer
	 * @return
	 */
	public static String rawBinaryToString(byte[] dataBuffer) {
		String result;
		try {
			result = new String(dataBuffer,0, dataBuffer.length-2, "ASCII");
		} catch (UnsupportedEncodingException e) {
			result = "N/A";
		}

		return result;
	}

	private static String getValueToDisplay(MessageFormat format, AbstractDataType dataType, BinaryBuffer buffer) {
		String result = null;
			
		switch (format) {
			case FORMATTED:
			case SPLITED_FORMATTED:
				result = getFomrattedValue(dataType, buffer);
				break;

			case HEX:
			case SPLITED_HEX:
				result = getHexValue(dataType, buffer);
				break;

			case BINARY:
			case SPLITED_BINARY:
				result = getRawValue(dataType, buffer);

			default:
				result = "No format given";
				break;
		}

		return result;
	}
	
	private static String getFomrattedValue(AbstractDataType dataType , BinaryBuffer buffer) {
		String result = null;
		byte valueAsBytes[] = buffer.readNext(dataType.getNumberOfBytes());
		Object value = dataType.getValue(valueAsBytes);
		result = String.format(dataType.getDisplayFormat(), value);
		
		return result;
	}

	private static String getArrayFomrattedValue(MessageFormat format, ArrayOf arrayOf, BinaryBuffer buffer) {
		String result = null;
		Object formattedStrings[] = new String[arrayOf.getNumberOfEntry()];
		
		for (int i = 0; i < arrayOf.getNumberOfEntry(); i++) {
			formattedStrings[i] = getValueToDisplay(format, arrayOf.getDataType(), buffer);
		}
	
		result = String.format(arrayOf.getDisplayFormat(), formattedStrings);

		return result;
	}

	private static String getHexValue(AbstractDataType dataType , BinaryBuffer buffer) {
		String result = null;
		byte valueAsBytes[] = buffer.readNext(dataType.getNumberOfBytes());
		result = ByteArrayTool.arrayConvertByteToHex(valueAsBytes);;
		
		return result;
	}

	private static String getRawValue(AbstractDataType dataType , BinaryBuffer buffer) {
		StringBuffer result = new StringBuffer();
		byte valueAsBytes[] = buffer.readNext(dataType.getNumberOfBytes());
		byte byteValue;
		
		for (int i = 0; i < valueAsBytes.length; i++) {
			byteValue = valueAsBytes[i];
			result.append(String.format("%8s", Integer.toBinaryString(byteValue & 0xFF)).replace(' ', '0'));
		}
		
		return result.toString();
	}
	
	private static String buildSplitFormatString(DictionnaryEntry dictionnaryEntry) {
		StringBuffer result = new StringBuffer();

		int size = dictionnaryEntry.getDataDefinitions().size();
		for (int i = 0; i < size; i++) {
			result.append("%10s ");
		}

		return result.toString();
	}
	
	
}
