package com.tay.lab8jee.servlets;

import com.tay.lab8jee.dao.MusicianDao;
import com.tay.lab8jee.entities.Composition;
import com.tay.lab8jee.entities.Musician;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@WebServlet(name = "musicianServlet", value = "/musician")
public class MusicianServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        MusicianDao musicianDao = new MusicianDao();
        request.setAttribute("musicians", musicianDao.selectAll());
        request.getRequestDispatcher("musician.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        MusicianDao musicianDao = new MusicianDao();
        String action = request.getParameter("action");
        switch (action) {
            case "add": {
                Musician musician = new Musician(request.getParameter("name"));
                musicianDao.save(musician);
                try (PrintWriter pw = response.getWriter()) {
                    pw.print("<tr id=\"tr" + musician.getIdMusician() + "\">");
                    pw.print("<form id=" + musician.getIdMusician() + " name=\"udForm\" action=\"musician\" method=\"POST\" onsubmit=\"udAjaxFunction(event); return false\">");
                    pw.print("<td><input type=\"text\" name=\"id\" size=\"3\" value=\"" + musician.getIdMusician() + "\" title=\"" + musician.getIdMusician() + "\" form=\"" + musician.getIdMusician() + "\" readonly></td>");
                    pw.print("<td><input type=\"text\" name=\"name\" value=\"" + musician.getNameMusician() + "\" title=\"" + musician.getNameMusician() + "\" form=\"" + musician.getIdMusician() + "\" list=\"nameDL\" autocomplete=\"off\"></td>");
                    pw.print("<td><button type=\"submit\" id=\"uB" + musician.getIdMusician() + "\" name=\"action\" value=\"edit\" class=\"shine-button\" form=\"" + musician.getIdMusician() + "\"><i>Обновить</i></button></td>");
                    pw.print("<td><button type=\"submit\" id=\"dB" + musician.getIdMusician() + "\" name=\"action\" value=\"delete\" class=\"shine-button\" form=\"" + musician.getIdMusician() + "\"><i>Удалить</i></button></td>");
                    pw.print("</form>");
                    pw.print("</tr>");

                    pw.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                //response.getWriter().println("id=" + musician.getIdMusician() +
                //        "&name="+ musician.getNameMusician());
                //response.sendRedirect(request.getContextPath() + "/musician");
                break;
            }
            case "edit": {
                Musician musician = musicianDao.findByID(Integer.parseInt(request.getParameter("id")));
                musician.setNameMusician(request.getParameter("name"));
                musicianDao.update(musician);
                try {
                    PrintWriter pw = response.getWriter();
                    pw.print("id=" + musician.getIdMusician() +
                            "&name=" + request.getParameter("name"));
                    pw.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                //response.sendRedirect(request.getContextPath() + "/musician");
                break;
            }
            case "delete": {
                musicianDao.deleteByID(Integer.parseInt(request.getParameter("id")));
                try {
                    PrintWriter pw = response.getWriter();
                    pw.print(request.getParameter("id"));
                    pw.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                //response.sendRedirect(request.getContextPath() + "/musician");
                break;
            }
            case "select": {
                List<Composition> compositions = musicianDao.selectComposition(Integer.parseInt(request.getParameter("musician")));
                try (PrintWriter pw = response.getWriter()) {
                    pw.print("<table id=\"selectTable\">");
                    pw.print("<tbody>");
                    pw.print("<tr>");
                    pw.print("<th>Название композиции</th>");
                    pw.print("<th>Длительность</th>");
                    pw.print("</tr>");

                    for (Composition composition : compositions) {
                        System.out.println(composition.getNameComposition());
                        System.out.println(composition.getDurationComposition());
                        pw.print("<tr>");
                        pw.print("<td class=\"tdSelect\">" + composition.getNameComposition() + "</td>");
                        pw.print("<td class=\"tdSelect\">" + composition.getDurationComposition() + "</td>");
                        pw.print("</tr>");
                    }
                    pw.print("</tbody>");
                    pw.print("</table>");
                    pw.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                //response.sendRedirect(request.getContextPath() + "/musician");
                break;
            }
            case "option": {
                try (PrintWriter pw = response.getWriter()) {
                    List<Musician> musicians = musicianDao.selectAll();
                    pw.write("id="+ musicians.get(0).getIdMusician()+
                            "&name=" + musicians.get(0).getNameMusician());
                    for (int i = 1; i < musicians.size(); i++) {
                        pw.write("&id="+ musicians.get(i).getIdMusician()+
                                "&name=" + musicians.get(i).getNameMusician());
                    }
                    pw.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            }
            case "datalist": {
                try (PrintWriter pw = response.getWriter()) {
                    List<Musician> musicians = musicianDao.selectAll();
                    List<String> names = musicians.stream().map(Musician::getNameMusician).distinct().collect(Collectors.toList());
                    pw.write("name=" + names.get(0));
                    for (int i = 1; i < names.size(); i++) {
                        pw.write("&name=" + names.get(i));
                    }
                    pw.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            }
        }
    }
}
