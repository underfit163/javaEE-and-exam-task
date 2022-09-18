package com.tay.lab8jee.servlets;

import com.tay.lab8jee.dao.AlbumDao;
import com.tay.lab8jee.dao.MusicianDao;
import com.tay.lab8jee.entities.Album;
import com.tay.lab8jee.entities.Composition;
import com.tay.lab8jee.entities.Musician;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet(name = "albumServlet", value = "/album")
public class AlbumServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AlbumDao albumDao = new AlbumDao();
        MusicianDao musicianDao = new MusicianDao();
        request.setAttribute("albums", albumDao.selectAll());
        request.setAttribute("musicians", musicianDao.selectAll());
        request.getRequestDispatcher("album.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        AlbumDao albumDao = new AlbumDao();
        MusicianDao musicianDao = new MusicianDao();
        String action = request.getParameter("action");
        switch (action) {
            case "add": {
                Album album = new Album(request.getParameter("name"), request.getParameter("genre"));
                albumDao.insertAlbumInMusician(Integer.parseInt(request.getParameter("musician")), album);
                ArrayList<Musician> musicianList = (ArrayList<Musician>) musicianDao.selectAll();
                try (PrintWriter pw = response.getWriter()) {
                    pw.print("<tr id=\"tr" + album.getIdAlbum() + "\">");
                    pw.print("<form id=" + album.getIdAlbum() + " name=\"udForm\" action=\"album\" method=\"POST\" onsubmit=\"udAjaxFunction(event); return false\">");
                    pw.print("<td><input type=\"text\" name=\"id\" size=\"3\" value=\"" + album.getIdAlbum() + "\" title=\"" + album.getIdAlbum() + "\" form=\"" + album.getIdAlbum() + "\" readonly></td>");
                    pw.print("<td><input type=\"text\" name=\"name\" value=\"" + album.getNameAlbum() + "\" title=\"" + album.getNameAlbum() + "\"  form=\"" + album.getIdAlbum() + "\" list=\"nameDL\" autocomplete=\"off\"></td>");
                    pw.print("<td><input type=\"text\" name=\"genre\" value=\"" + album.getGenreAlbum() + "\" title=\"" + album.getGenreAlbum() + "\" form=\"" + album.getIdAlbum() + "\" list=\"genreDL\" autocomplete=\"off\"></td>");
                    pw.print("<td>");
                    pw.print("<label>");
                    pw.print("<select name=\"musician\" form=\"" + album.getIdAlbum() + "\">");
                    for (Musician musician : musicianList) {
                        if (musician.getIdMusician() == Integer.parseInt(request.getParameter("musician"))) {
                            pw.print("<option value=\"" + musician.getIdMusician() + "\" title=\"" + musician.getIdMusician() + "\" selected>" + musician.getNameMusician() + "</option>");
                        } else {
                            pw.print("<option value=\"" + musician.getIdMusician() + "\" title=\"" + musician.getIdMusician() + "\">" + musician.getNameMusician() + "</option>");
                        }
                    }
                    pw.print("</select>");
                    pw.print("</label>");
                    pw.print("</td>");
                    pw.print("<td><button type=\"submit\" id=\"uB" + album.getIdAlbum() + "\" name=\"action\" value=\"edit\" class=\"shine-button\" form=\"" + album.getIdAlbum() + "\"><i>Обновить</i></button></td>");
                    pw.print("<td><button type=\"submit\" id=\"dB" + album.getIdAlbum() + "\" name=\"action\" value=\"delete\" class=\"shine-button\" form=\"" + album.getIdAlbum() + "\"><i>Удалить</i></button></td>");
                    pw.print("</form>");
                    pw.print("</tr>");

                    pw.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                //response.sendRedirect(request.getContextPath() + "/album");
                break;
            }
            case "edit": {
                Album album = albumDao.findByID(Integer.parseInt(request.getParameter("id")));
                album.setNameAlbum(request.getParameter("name"));
                album.setGenreAlbum(request.getParameter("genre"));
                album.setMusician(musicianDao.findByID(Integer.parseInt(request.getParameter("musician"))));
                albumDao.update(album);
                try {
                    PrintWriter pw = response.getWriter();
                    pw.print("id=" + album.getIdAlbum() +
                            "&name=" + album.getNameAlbum() +
                            "&genre=" + album.getGenreAlbum() +
                            "&musician=" + request.getParameter("musician"));
                    pw.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                //response.sendRedirect(request.getContextPath() + "/album");
                break;
            }
            case "delete": {
                albumDao.deleteByID(Integer.parseInt(request.getParameter("id")));
                try {
                    PrintWriter pw = response.getWriter();
                    pw.print(request.getParameter("id"));
                    pw.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                //response.sendRedirect(request.getContextPath() + "/album");
                break;
            }
            case "select": {
                List<Composition> compositions = albumDao.selectComposition(Integer.parseInt(request.getParameter("album")),
                        Double.parseDouble(request.getParameter("duration")));
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
                    List<Album> albums = albumDao.selectAll();
                    pw.write("id=" + albums.get(0).getIdAlbum() +
                            "&name=" + albums.get(0).getNameAlbum());
                    for (int i = 1; i < albums.size(); i++) {
                        pw.write("&id=" + albums.get(i).getIdAlbum() +
                                "&name=" + albums.get(i).getNameAlbum());
                    }
                    pw.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            }
            case "datalist1": {
                try (PrintWriter pw = response.getWriter()) {
                    List<Album> albums = albumDao.selectAll();
                    List<String> names = albums.stream().map(Album::getNameAlbum).distinct().collect(Collectors.toList());
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
                    List<Album> compositions = albumDao.selectAll();
                    List<String> genre = compositions.stream().map(Album::getGenreAlbum).distinct().collect(Collectors.toList());
                    pw.write("genre=" + genre.get(0));
                    for (int i = 1; i < genre.size(); i++) {
                        pw.write("&genre=" + genre.get(i));
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
