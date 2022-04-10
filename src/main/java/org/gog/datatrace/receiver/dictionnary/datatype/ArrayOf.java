package org.gog.datatrace.receiver.dictionnary.datatype;

public class ArrayOf extends AbstractDataType {
	
	private AbstractFixedSizeDataType dataType;
	private int numberOfEntry;
	
	public ArrayOf(AbstractFixedSizeDataType dataType, int numberOfEntry) {
		this.numberOfEntry = numberOfEntry;
		this.dataType = dataType;
		
		int numberOfBytes = dataType.getNumberOfBytes() * numberOfEntry;
		DataType type = dataType.getType();
		StringBuffer displayFormats = new StringBuffer();
		
		boolean first = true;
		for (int i = 0; i < numberOfEntry; i++) {
			if ( first ) {
				displayFormats.append("[ ");
			} else {
				displayFormats.append(" ");
			}
			displayFormats.append("%s");
			first = false;
			
			if ( i == numberOfEntry-1 ) {
				displayFormats.append("]");
			}
		}

		this.setType(type);
		this.setNumberOfBytes(numberOfBytes);
		this.setDisplayFormat(displayFormats.toString());
	}
	
	@Override
	public Object getValue(byte[] bytes) {
		throw new UnsupportedOperationException("Method getValues is not supported for an ArrayOf");
	}

	public AbstractFixedSizeDataType getDataType() {
		return dataType;
	}

	public int getNumberOfEntry() {
		return numberOfEntry;
	}

}
