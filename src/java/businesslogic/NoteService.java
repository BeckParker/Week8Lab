/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package businesslogic;

import dataaccess.NoteDB;
import domainmodel.Note;
import java.util.Date;
import java.util.List;

/**
 *
 * @author 679810
 */
public class NoteService {
    
    private NoteDB noteDB;

    public NoteService() {
        noteDB = new NoteDB();
    }

    public Note getNote(int noteId) throws Exception {
        return noteDB.getNote(noteId);
    }

    public List<Note> getAllNotes() throws Exception {
        return noteDB.getAllNotes();
    }

    public int updateNote(int noteId, String contents) throws Exception {
        
        Note note = new Note(noteId, new Date(), contents);
        return noteDB.updateNote(note);
    }

    public int deleteNote(int noteId) throws Exception {
        Note deletedNote = noteDB.getNote(noteId);
        return noteDB.deleteNote(deletedNote);
    }

    public int insertNote(int noteId, String contents) throws Exception {
        Note note = new Note(noteId, new Date(), contents);
        return noteDB.insertNote(note);
    }
    
}
