package com.labforward.notebook;



import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.labforward.notebook.model.Entry;
import com.labforward.notebook.repository.NotebookRepository;
import com.labforward.notebook.service.impl.WordServiceImpl;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class NotebookEntryApplicationTests {

	
	
	@Autowired
	private WordServiceImpl wordServiceImpl;
	
	@MockBean
	private NotebookRepository notebookRepository;
	
	
	@Test
	public void testFetchAllSimilarWordsSuccess() throws Exception{
		Entry entry=new Entry();
		entry.setId(2);
		entry.setWords("Word Words Wor word");
		Mockito.when(notebookRepository.findById(Integer.valueOf("2"))).thenReturn(Optional.of(entry)); 
		List<String> similarWords=wordServiceImpl.fetchAllSimilarWords("2", "Words");
//		assertNotNull(similarWords);
		similarWords.forEach((word)->{
			assertEquals("Word", word);
		});
		
		
		
	}

	@Test
	public void testFetchAllSimilarWordsNotFound() throws Exception{
		Entry entry=new Entry();
		entry.setId(2);
		entry.setWords("Word Words Wor word");
		Mockito.when(notebookRepository.findById(Integer.valueOf("2"))).thenReturn(Optional.of(entry)); 
		List<String> similarWords=wordServiceImpl.fetchAllSimilarWords("2", "worte");
		similarWords.forEach((word)->{
			assertEquals("", word);
		});
		
	}
	@Test
	public void testFetchCountOfWordSuccess() throws Exception{
		Entry entry=new Entry();
		entry.setId(2);
		entry.setWords("Word Word Word word");
		Mockito.when(notebookRepository.findById(Integer.valueOf("2"))).thenReturn(Optional.of(entry)); 
		int count =wordServiceImpl.fetchCountOfWord("2", "Word");
		assertEquals(3, count);		
	}
	@Test
	public void testFetchCountOfWordNotFound() throws Exception{
		Entry entry=new Entry();
		entry.setId(2);
		entry.setWords("Word Words Wor word");
		Mockito.when(notebookRepository.findById(Integer.valueOf("2"))).thenReturn(Optional.of(entry)); 
		int count =wordServiceImpl.fetchCountOfWord("2", "worte");
		assertEquals(0, count);		
	}

}
