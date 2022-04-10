package org.gog.datatrace.receiver.dictionnary;

import java.util.LinkedList;
import java.util.List;

import org.gog.datatrace.receiver.dictionnary.datatype.AbstractDataType;

public class DictionnaryEntry {
	
	private String id;
	private String message;
	private List<DataDefinition> dataDefinitions = null;
	private long numberOfBytes;
	private boolean skipDisplay;
	
	public DictionnaryEntry(String id, String message) {
		if ( id == null || id.length() == 0 ) {
			throw new IllegalArgumentException("id can not be null or empty. Actual value is " + id);
		}
		
		if ( id.length() > 4 ) {
			throw new IllegalArgumentException("id cannot be more than 4 character. Actual value is " + id);
		}

		this.id = id;
		this.message= message;
		this.dataDefinitions = new LinkedList<DataDefinition>();
		this.numberOfBytes = 0;
	}

	public void addDataDefinition(String name, AbstractDataType dataType) {
		dataDefinitions.add(new DataDefinition(name, dataType));
		numberOfBytes += dataType.getNumberOfBytes();
	}

	public void addDataDefinition(String shortName, String longName, AbstractDataType dataType) {
		dataDefinitions.add(new DataDefinition(shortName, longName, dataType));
		numberOfBytes += dataType.getNumberOfBytes();
	}

	public String getId() {
		return id;
	}

	public String getMessage() {
		if ( message == null ) {
			message = generateMessageFormat();
		}

		return message;
	}
	
	public void setSkipDisplay(boolean skipDisplay) {
		this.skipDisplay = skipDisplay;
	}
	
	public boolean isSkipDisplay() {
		return skipDisplay;
	}

	public List<DataDefinition> getDataDefinitions() {
		return dataDefinitions;
	}
	
	public long getNumberOfBytes() {
		return numberOfBytes;
	}
	
	private String generateMessageFormat() {
		StringBuffer messageFormatBuffer = new StringBuffer();
		boolean isFirstDataDefinition = true;
		// t1ofd: %s - t1ofc: %s
		
		for (DataDefinition dataDefinition : dataDefinitions) {
			if ( isFirstDataDefinition ) {
				isFirstDataDefinition = false;
			} else {
				messageFormatBuffer.append(" - ");				
			}
			
			messageFormatBuffer.append(dataDefinition.getShortName()).append(": %s");
		}
		
		return messageFormatBuffer.toString();
	}

}
