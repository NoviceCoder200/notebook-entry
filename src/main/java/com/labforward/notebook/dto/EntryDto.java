package com.labforward.notebook.dto;

import java.io.Serializable;

/**
 * @author apurva.patil
 * 
 * This class includes fields, getter and setter methods for entry.
 *
 */


public class EntryDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String words;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getWords() {
		return words;
	}
	public void setWords(String words) {
		this.words = words;
	}
	

}
