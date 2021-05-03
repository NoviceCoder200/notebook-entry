package com.labforward.notebook.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.labforward.notebook.dto.EntryDto;
import com.labforward.notebook.exception.ServiceException;
import com.labforward.notebook.service.NotebookService;

/**
 * @author apurva.patil
 * 
 * This class exposes endpoints to manage entries in the notebook.
 *
 */

@CrossOrigin
@RestController
@RequestMapping("entry")
public class NotebookController {
	
	@Autowired
	private NotebookService notebookService;


	/**
	 * 
	 * This endpoint adds an entry of note in the data.
	 * 
	 * @param correlationId
	 * @param entryDto
	 * @return
	 * @throws ServiceException
	 */
	@PostMapping("/")
	public ResponseEntity<String> addEntry(@RequestHeader("correlationId") String correlationId,
			@RequestBody EntryDto entryDto) throws ServiceException {
		try {
			if(null!=entryDto){
				notebookService.addEntry(entryDto);
				return new ResponseEntity<>(HttpStatus.OK);
			}
			
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	

	/**
	 * 
	 * This endpoint deletes an entry of a note from database.
	 * 
	 * @param correlationId
	 * @param id
	 * @return
	 * @throws ServiceException
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<Boolean> removeEntry(@RequestHeader("correlationId") String correlationId,
			@PathVariable("id") Integer id) throws ServiceException {
		boolean status=false;
		
		try {
			if(null!=id){
				status=notebookService.removeEntry(id);
				return new ResponseEntity<>(status,HttpStatus.OK);
			}

			return new ResponseEntity<>(status,HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			return new ResponseEntity<>(status,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	/**
	 * This endpoint retrieves all the entries of notes from the database.
	 * 
	 * @return
	 * @throws ServiceException
	 */
	@GetMapping("/fetch-all-entries")
	public ResponseEntity<List<EntryDto>> fetchAllEntries() throws ServiceException {
		List<EntryDto> entries=notebookService.fetchAllEntries();
		if(null!=entries && !entries.isEmpty()){
			return new ResponseEntity<>(entries, HttpStatus.OK);
		}
			
		return new ResponseEntity<>(new ArrayList<>(), HttpStatus.NO_CONTENT);
	}
	
	
}
