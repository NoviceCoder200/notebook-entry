package com.labforward.notebook.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.labforward.notebook.model.Entry;


/**
 * @author apurva.patil
 * 
 * This is a repository class to process data from h2 database.
 *
 */
@Repository
public interface NotebookRepository extends JpaRepository<Entry, Integer>{

}
