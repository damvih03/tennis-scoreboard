package com.damvih.servlets;

import com.damvih.dto.MatchesResponseDto;
import com.damvih.services.FinishedMatchesPersistenceService;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/matches")
public class MatchesServlet extends HttpServlet {

    private FinishedMatchesPersistenceService finishedMatchesPersistenceService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        finishedMatchesPersistenceService = (FinishedMatchesPersistenceService) config.getServletContext().getAttribute("FinishedMatchesPersistenceService");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pageValue = request.getParameter("page");
        String playerName = request.getParameter("filter_by_player_name");

        int page = getPage(pageValue);

        MatchesResponseDto matches = finishedMatchesPersistenceService.get(page, playerName);
        request.setAttribute("playerName", playerName);
        request.setAttribute("matchesResponse", matches);
        request.setAttribute("currentPage", page);
        request.getRequestDispatcher("matches.jsp").forward(request, response);
    }

    private int getPage(String pageValue) {
        int page;
        try {
            page = Integer.parseInt(pageValue);
        } catch (NumberFormatException exception) {
            page = 1;
        }
        return page;
    }

}
