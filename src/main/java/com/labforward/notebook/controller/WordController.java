package com.labforward.notebook.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.labforward.notebook.exception.ServiceException;
import com.labforward.notebook.service.WordService;

/**
 * @author apurva.patil
 * 
 * This class exposes endpoints to search similar words and frequency for requested word.
 *
 */

@CrossOrigin
@RestController
@RequestMapping("/word")
public class WordController {

	@Autowired
	private WordService wordService;
	
	@GetMapping("/fetch-all-similar-words")
	public ResponseEntity<List<String>> findAllSimilarWords(@RequestHeader("correlationId") String correlationId,
			@RequestParam String id, @RequestParam String word)  {

		try {
			if (null != word && !word.isEmpty() ) {
				List<String> words = wordService.fetchAllSimilarWords(id, word);
				return new ResponseEntity<>(words, HttpStatus.OK);
			}
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/fetch-word-frequency")
	public ResponseEntity<Integer> getFrequencyOfWords(@RequestHeader("correlationId") String correlationId,
			@RequestParam String id, @RequestParam String word) throws ServiceException {

		try {
			if (null != word && !word.isEmpty()) {

				Integer words = wordService.fetchCountOfWord(id, word);

				return new ResponseEntity<>(words, HttpStatus.OK);

			} 
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);	
		}
	}

}
