package com.labforward.notebook.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.labforward.notebook.dto.EntryDto;
import com.labforward.notebook.exception.ServiceException;
import com.labforward.notebook.model.Entry;
import com.labforward.notebook.repository.NotebookRepository;
import com.labforward.notebook.service.NotebookService;

/**
 * @author apurva.patil
 * 
 * This class is an implementation of Notebook services to manage entries.
 *
 */


@Service
public class NotebookServiceImpl implements NotebookService {
	
	@Autowired
	NotebookRepository notebookRepository;

	@Override
	public boolean addEntry(EntryDto entryDto) throws ServiceException {
		Entry entry=new Entry();
		entry.setWords(entryDto.getWords());
		entry=notebookRepository.save(entry);
		return null!=entry ? true : false;
	}

	@Override
	public boolean removeEntry(Integer id) throws ServiceException {
		notebookRepository.deleteById(id);
		return true;
	}

	@Override
	public List<EntryDto> fetchAllEntries() throws ServiceException {
		List<Entry> entries=notebookRepository.findAll();
		if(null!=entries && !entries.isEmpty()){
			List<EntryDto> entryList = new ArrayList<>();
			entries.forEach((entry)->{
				EntryDto entryDto=new EntryDto();
				entryDto.setId(entry.getId());
				entryDto.setWords(entry.getWords());
				entryList.add(entryDto);
			});
			return entryList;
			
		}
		return new ArrayList<>();
		
	}

	@Override
	public EntryDto fetchEntryById(Integer id) throws ServiceException {
		EntryDto entryDto=null;
		Optional<Entry> entry= notebookRepository.findById(id);
		if(entry.isPresent()){
			entryDto=new EntryDto();
			entryDto.setId(entry.get().getId());
			entryDto.setWords(entry.get().getWords());
		}
		return entryDto;
	}

}
