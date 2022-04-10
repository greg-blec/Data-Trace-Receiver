package org.gog.datatrace.receiver.dictionnary;

import org.gog.datatrace.receiver.dictionnary.datatype.AbstractDataType;

public class DataDefinition {

	private AbstractDataType dataType;
	private String shortName;
	private String longName;
	
	public DataDefinition(String name, AbstractDataType dataType) {
		this(name, name, dataType);
	}

	public DataDefinition(String shortName, String longName, AbstractDataType dataType) {
		this.dataType = dataType;
		this.shortName = shortName;
		this.longName = longName;
	}

	public AbstractDataType getDataType() {
		return dataType;
	}
	
	public String getShortName() {
		return shortName;
	}
	
	public String getLongName() {
		return longName;
	}
}
