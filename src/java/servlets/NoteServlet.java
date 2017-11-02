/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import businesslogic.NoteService;
import domainmodel.Note;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author 679810
 */
public class NoteServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String noteSelected = request.getParameter("noteSelection");
        if (noteSelected != null && !noteSelected.isEmpty()) {
            try {
                displayNoteInfo(request, response, noteSelected);
            } catch (Exception ex) {
                Logger.getLogger(NoteServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            try {
                displayAllNotes(request, response);
            } catch (Exception ex) {
                Logger.getLogger(NoteServlet.class.getName()).log(Level.SEVERE, null, ex);
               // request.setAttribute("errorMessage", "No notes to display.");
            }
        }
        
    }

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        NoteService ns = new NoteService();
        
        if (action != null) {
            String contents = request.getParameter("contents");
            if (action.equals("addNote")) {
                try {
                    ns.insertNote(0, contents);
                } catch (Exception ex) {
                    Logger.getLogger(NoteServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (action.equals("deleteNote")) {
                String strId = request.getParameter("id");
                int id = Integer.parseInt(strId);
                try {
                    ns.deleteNote(id);
                } catch (Exception ex) {
                    Logger.getLogger(NoteServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (action.equals("updateNote")) {
                String strId = request.getParameter("id");
                int id = Integer.parseInt(strId);
                try {
                    ns.updateNote(id, contents);
                } catch (Exception ex) {
                    Logger.getLogger(NoteServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        displayAllNotes(request, response);
        //getServletContext().getRequestDispatcher("/WEB-INF/notes.jsp").forward(request, response);
    }
    
    private void displayAllNotes(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ArrayList<Note> notes = null;
        //String selectedNote = null;
        
        NoteService noteService = new NoteService();
        try {
            notes = (ArrayList<Note>) noteService.getAllNotes();
        } catch (Exception ex) {
            Logger.getLogger(NoteServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        request.setAttribute("notes", notes);
        //request.setAttribute("selectedNote", selectedNote);
        
        getServletContext().getRequestDispatcher("/WEB-INF/notes.jsp").forward(request, response);
    }
    
    private void displayNoteInfo(HttpServletRequest request, HttpServletResponse response, String noteSelected)
            throws ServletException, IOException {
        NoteService noteService = new NoteService();
        int noteId = Integer.parseInt(noteSelected);
        
        Note note = null;
        try {
            note = noteService.getNote(noteId);
        } catch (Exception ex) {
            Logger.getLogger(NoteServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        request.setAttribute("note", note);
        request.setAttribute("selectedNote", noteId);
        
        displayAllNotes(request, response);
        //getServletContext().getRequestDispatcher("/WEB-INF/notes.jsp").forward(request, response);
    }

}
