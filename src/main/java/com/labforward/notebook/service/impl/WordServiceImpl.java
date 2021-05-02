package com.labforward.notebook.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.labforward.notebook.exception.ServiceException;
import com.labforward.notebook.model.Entry;
import com.labforward.notebook.repository.NotebookRepository;
import com.labforward.notebook.service.WordService;
import com.labforward.notebook.utils.ServiceUtils;

/**
 * @author apurva.patil
 * 
 * This class is an implementation of services to 
 * search similar words and frequency of given word.
 *
 */ 

@Service
public class WordServiceImpl implements WordService {
	
	@Autowired
	NotebookRepository notebookRepository;

	@Override
	public List<String> fetchAllSimilarWords(String id,String word) throws ServiceException {
		Entry entry=null;
		String words=null;
		if(null!=id){
			Optional<Entry> optionalOfEntry=notebookRepository.findById(Integer.valueOf(id));
			if (optionalOfEntry.isPresent()) {
				entry = optionalOfEntry.get();
				words=entry.getWords();
				return ServiceUtils.findSimilarWords(words,word);
			}
		}
		
		return new ArrayList<>();
	}

	@Override
	public Integer fetchCountOfWord(String id,String word) throws ServiceException {
		Entry entry=null;
		String words=null;
		if(null!=id){
			Optional<Entry> optionalOfEntry=notebookRepository.findById(Integer.valueOf(id));
			if (optionalOfEntry.isPresent()) {
				entry = optionalOfEntry.get();
				words=entry.getWords();
				return ServiceUtils.frequencyOfWord(words,word);
			}
		}
		
		return 0;
	}

}
