/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Note;
import services.NoteService;

/**
 *
 * @author 792807
 */
public class NoteServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        NoteService service = new NoteService();
        List<Note> notesList = service.getAll();
        request.setAttribute("notesList", notesList);
        getServletContext().getRequestDispatcher("/WEB-INF/notes.jsp").forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("mode", "view");
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         NoteService service = new NoteService();
        List<Note> notesList = service.getAll();
        request.setAttribute("notesList", notesList);
        request.setAttribute("mode", "edit");
       
        //Edit
        if(request.getParameter("edit") != null) {
            int editNoteID = Integer.parseInt(request.getParameter("noteID"));
            Note note = service.get(editNoteID);
            request.setAttribute("selectedNoteId", note.getNoteid());
            request.setAttribute("noteTitle", note.getTitle());
            request.setAttribute("noteContents", note.getContents()); 
        }
        
        //save
        if(request.getParameter("save") != null) {
            int saveNoteID = Integer.parseInt(request.getParameter("selectedNoteId"));
            
            String saveTitle = request.getParameter("noteTitle");
            String saveContent = request.getParameter("noteContent");
            
            service.update(saveNoteID, saveTitle, saveContent);
            
            request.setAttribute("mode", "view");
        }
        
        //add
        if(request.getParameter("add") != null) {
            
            String addTitle = request.getParameter("noteTitle");
            String addContent = request.getParameter("noteContent");
            
            service.insert(addContent, addTitle);
            
            request.setAttribute("mode", "view");
        }
        
        //delete
        if(request.getParameter("delete") != null) {
            int deleteNoteID = Integer.parseInt(request.getParameter("selectedNoteId"));
            
            service.delete(deleteNoteID);  
            request.setAttribute("mode", "view");
        }
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
