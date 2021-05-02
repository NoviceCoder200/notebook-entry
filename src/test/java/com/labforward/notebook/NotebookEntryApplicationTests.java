package com.labforward.notebook;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.labforward.notebook.model.Entry;
import com.labforward.notebook.repository.NotebookRepository;
import com.labforward.notebook.service.impl.WordServiceImpl;

@SpringBootTest
@RunWith(SpringRunner.class)
class NotebookEntryApplicationTests {

	@Test
	void contextLoads() {
	}
	
	@InjectMocks
	private WordServiceImpl wordServiceImpl;
	
	@Mock
	private NotebookRepository notebookRepository;
	private Entry entry;
	
	
	@Test
	public void testFetchAllSimilarWordsSuccess() throws Exception{
		Entry entry=new Entry();
		entry.setId(2);
		entry.setWords("Word Words Wor word");
		Mockito.when(notebookRepository.findById(Integer.valueOf("2"))).thenReturn(Optional.of(entry)); 
		List<String> similarWords=wordServiceImpl.fetchAllSimilarWords("2", "Words");
		assertNotNull(similarWords);
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
		assertNotNull(similarWords);
		similarWords.forEach((word)->{
			assertEquals("", word);
		});
		
	}
	@Test
	public void testFetchCountOfWordSuccess() throws Exception{
		Entry entry=new Entry();
		entry.setId(2);
		entry.setWords("Word Words Wor word");
		Mockito.when(notebookRepository.findById(Integer.valueOf("2"))).thenReturn(Optional.of(entry)); 
		int count =wordServiceImpl.fetchCountOfWord("2", "Words");
		assertEquals(1, count);		
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
