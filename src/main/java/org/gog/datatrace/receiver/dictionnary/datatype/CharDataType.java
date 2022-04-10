package org.gog.datatrace.receiver.dictionnary.datatype;

public class CharDataType extends AbstractFixedSizeDataType {

	public CharDataType(String displayFormat) {
		super(DataType.CHAR, 1, displayFormat);
	}
	
	@Override
	public Object getValue(byte[] bytes) {
		char result = 
				(char)bytes[0];
		return result;
	}
}
