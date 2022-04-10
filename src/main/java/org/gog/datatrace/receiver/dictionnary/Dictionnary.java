package org.gog.datatrace.receiver.dictionnary;

import java.util.HashMap;
import java.util.Map;

public class Dictionnary {
	private Map<String, DictionnaryEntry> entries = new HashMap<String, DictionnaryEntry>();
	private boolean ignoreUnknownEntryId = false;
	
	public DictionnaryEntry createEntry(String id, String message) {
		DictionnaryEntry entry = new DictionnaryEntry(id, message);
		
		this.entries.put(id, entry);

		return entry;
	}

	public DictionnaryEntry createEntry(String id) {
		DictionnaryEntry entry = new DictionnaryEntry(id, null);
		
		this.entries.put(id, entry);

		return entry;
	}

	public DictionnaryEntry getEntry(String id) {
		return this.entries.get(id);
	}
	
	public void setIgnoreUnknownEntryId(boolean ignoreUnknownEntryId) {
		this.ignoreUnknownEntryId = ignoreUnknownEntryId;
	}
	
	public boolean isIgnoreUnknownEntryId() {
		return ignoreUnknownEntryId;
	}
}
