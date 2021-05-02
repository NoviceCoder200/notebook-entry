# notebook-entry
This is a Spring Boot application notebook-entry with features to add, delete ,retrives notes from the database and below functionalities
  - Find All Similar words.
  - Find frequency of word.
  - Save the note.
  - Delete the note.
  - List all notes.

# Scope
1. Find All Similar words
    The endpoint `fetch-all-similar-words` fetches all the similar words in a note saved in h2-database and sends the list to the calling application.
 
2. Find frequence of word.
    The endpoint `fetch-word-frequency` gives the count of the requested word in the note saved in h2-database.
    
3. Save the note
    A post endpoint is exposed to add an entry of note in the h2-database.
    
4. Delete the note.
    A delete enpoint is exposed with the id of note in the request parameters to delete the note from h2-database.
    
5. List all notes.
    A list of entries of all notes is retrieved from the h2-database to send to calling application (lab-notebook-app)
    
# Features

- Saved entries in file based h2-database
- correlationId is added in headers to track the request coming from calling application
- Logging around controller using Spring AOP.
- Loosely coupled application as Word related services are implemented in WordController,WordServiceImpl and Managing notes in NoteController.

# Future Scope

- JWT token based implementation for Security of application.
- Repository can be implemented as different microservice and different microservice for common logging.
- JUNIT test cases for to add, delete and retrieve note.
- Implementation of modification service.
- Implementation of API Gateway and service discovery.



   


