package com.io.serialization;

public class Editor extends Person{
	
	/**
	 *  serialver com.io.serialization.Editor
	 */
	static final long serialVersionUID = 2663151858712284454L;
	private int editorId=0;
	private String EditingOffice="";
	
	public Editor(int editorId, String editingOffice) {
		this.editorId = editorId;
		EditingOffice = editingOffice;
	}

	public int getEditorId() {
		return editorId;
	}

	public void setEditorId(int editorId) {
		this.editorId = editorId;
	}

	public String getEditingOffice() {
		return EditingOffice;
	}

	public void setEditingOffice(String editingOffice) {
		EditingOffice = editingOffice;
	}

	@Override
	public String toString() {
		return "Editor [editorId=" + editorId + ", EditingOffice=" + EditingOffice + "]";
	}
	
}
