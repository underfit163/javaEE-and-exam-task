package com.tay.lab8jee.servlets;

import com.tay.lab8jee.dao.AlbumDao;
import com.tay.lab8jee.dao.CompositionDao;
import com.tay.lab8jee.entities.Album;
import com.tay.lab8jee.entities.Composition;
import com.tay.lab8jee.entities.Musician;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet(name = "compositionServlet", value = "/composition")
public class CompositionServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AlbumDao albumDao = new AlbumDao();
        CompositionDao compositionDao = new CompositionDao();
        request.setAttribute("compositions", compositionDao.selectAll());
        request.setAttribute("albums", albumDao.selectAll());
        request.getRequestDispatcher("composition.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        CompositionDao compositionDao = new CompositionDao();
        AlbumDao albumDao = new AlbumDao();
        String action = request.getParameter("action");
        switch (action) {
            case "add": {
                Composition composition = new Composition(request.getParameter("name"), Double.parseDouble(request.getParameter("duration")));
                compositionDao.insertCompInAlbum(Integer.parseInt(request.getParameter("album")), composition);
                ArrayList<Album> albums = (ArrayList<Album>) albumDao.selectAll();
                try (PrintWriter pw = response.getWriter()) {
                    pw.print("<tr id=\"tr" + composition.getIdComposition() + "\">");
                    pw.print("<form id=" + composition.getIdComposition() + " name=\"udForm\" action=\"album\" method=\"POST\" onsubmit=\"udAjaxFunction(event); return false\">");
                    pw.print("<td><input type=\"text\" name=\"id\" size=\"3\" value=\"" + composition.getIdComposition() +
                            "\" title=\"" + composition.getIdComposition() + "\" form=\"" + composition.getIdComposition() + "\" readonly></td>");
                    pw.print("<td><input type=\"text\" name=\"name\" value=\"" + composition.getNameComposition() +
                            "\" title=\"" + composition.getNameComposition() + "\"  form=\"" + composition.getIdComposition() + "\" list=\"nameDL\" autocomplete=\"off\"></td>");
                    pw.print("<td><input type=\"text\" name=\"duration\" value=\"" + composition.getDurationComposition() + "\" title=\"" + composition.getDurationComposition() +
                            "\" size=\"10\" pattern=\"[+]?\\d+(\\.\\d+)?\" form=\"" + composition.getIdComposition() + "\" list=\"durationDL\" autocomplete=\"off\"></td>");
                    pw.print("<td>");
                    pw.print("<label>");
                    pw.print("<select name=\"album\" form=\"" + composition.getIdComposition() + "\">");
                    for (Album album : albums) {
                        if (album.getIdAlbum() == Integer.parseInt(request.getParameter("album"))) {
                            pw.print("<option value=\"" + album.getIdAlbum() + "\" title=\"" + album.getIdAlbum() + "\" selected>" + album.getNameAlbum() +"</option>");
                        } else {
                            pw.print("<option value=\"" + album.getIdAlbum() + "\" title=\"" + album.getIdAlbum() + "\">" + album.getNameAlbum() + "</option>");
                        }
                    }
                    pw.print("</select>");
                    pw.print("</label>");
                    pw.print("</td>");
                    pw.print("<td><button type=\"submit\" id=\"uB" + composition.getIdComposition() + "\" name=\"action\" value=\"edit\" class=\"shine-button\" form=\"" + composition.getIdComposition() + "\"><i>Обновить</i></button></td>");
                    pw.print("<td><button type=\"submit\" id=\"dB" + composition.getIdComposition() + "\" name=\"action\" value=\"delete\" class=\"shine-button\" form=\"" + composition.getIdComposition() + "\"><i>Удалить</i></button></td>");
                    pw.print("</form>");
                    pw.print("</tr>");

                    pw.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            }
            case "edit": {
                Composition composition = compositionDao.findByID(Integer.parseInt(request.getParameter("id")));
                composition.setNameComposition(request.getParameter("name"));
                composition.setDurationComposition(Double.parseDouble(request.getParameter("duration")));
                composition.setAlbum(albumDao.findByID(Integer.parseInt(request.getParameter("album"))));
                compositionDao.update(composition);
                try {
                    PrintWriter pw = response.getWriter();
                    pw.print("id=" + composition.getIdComposition() +
                            "&name=" + composition.getNameComposition() +
                            "&duration=" + composition.getDurationComposition() +
                            "&album=" + request.getParameter("album"));
                    pw.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                //response.sendRedirect(request.getContextPath() + "/composition");
                break;
            }
            case "delete": {
                compositionDao.deleteByID(Integer.parseInt(request.getParameter("id")));
                try {
                    PrintWriter pw = response.getWriter();
                    pw.print(request.getParameter("id"));
                    pw.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                //response.sendRedirect(request.getContextPath() + "/composition");
                break;
            }
            case "datalist1": {
                try (PrintWriter pw = response.getWriter()) {
                    List<Composition> compositions = compositionDao.selectAll();
                    List<String> names = compositions.stream().map(Composition::getNameComposition).distinct().collect(Collectors.toList());
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
            case "datalist2": {
                try (PrintWriter pw = response.getWriter()) {
                    List<Composition> compositions = compositionDao.selectAll();
                    List<Double> durations = compositions.stream().map(Composition::getDurationComposition).distinct().collect(Collectors.toList());
                    pw.write("duration=" + durations.get(0));
                    for (int i = 1; i < durations.size(); i++) {
                        pw.write("&duration=" + durations.get(i));
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
