package com.labforward.notebook.service;

import java.util.List;

import com.labforward.notebook.dto.EntryDto;
import com.labforward.notebook.exception.ServiceException;

/**
 * @author apurva.patil
 * 
 * This interface includes services to manage entries of notes.
 *
 */
public interface NotebookService {

	boolean addEntry(EntryDto entry) throws ServiceException;
	boolean removeEntry(Integer id) throws ServiceException;
	List<EntryDto> fetchAllEntries() throws ServiceException;
	EntryDto fetchEntryById(Integer word) throws ServiceException;
	
}
