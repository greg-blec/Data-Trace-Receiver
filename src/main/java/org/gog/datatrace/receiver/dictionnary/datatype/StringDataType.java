package org.gog.datatrace.receiver.dictionnary.datatype;

import java.io.UnsupportedEncodingException;

public class StringDataType extends AbstractFreeSizeDataType {

	public StringDataType(int numberOfBytes) {
		super(DataType.STRING, numberOfBytes, "%s");
	}

	public StringDataType(int numberOfBytes, String displayFormat) {
		super(DataType.STRING, numberOfBytes, displayFormat);
	}

	@Override
	public Object getValue(byte[] bytes) {
		String result;
		try {
			result = new String(bytes, "ASCII");
		} catch (UnsupportedEncodingException e) {
			result = "N/A";
		}
				
		return result;
	}
}

