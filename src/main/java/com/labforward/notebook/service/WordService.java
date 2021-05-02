package com.labforward.notebook.service;

import java.util.List;

import com.labforward.notebook.exception.ServiceException;

/**
 * @author apurva.patil
 * 
 * This interface includes services to find similar words 
 * and frequency of occurrence of word in the note entry.
 *
 */
public interface WordService {

	List<String> fetchAllSimilarWords(String id, String word) throws ServiceException;
	Integer fetchCountOfWord(String id,String word) throws ServiceException;

}
