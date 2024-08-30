package com.damvih.servlets;

import com.damvih.services.CurrentMatch;
import com.damvih.services.FinishedMatchesPersistenceService;
import com.damvih.services.MatchScoreCalculationService;
import com.damvih.services.OngoingMatchesService;
import com.damvih.utils.ValidationUtils;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.UUID;

@WebServlet("/match-score")
public class MatchScoreServlet extends HttpServlet {

    private OngoingMatchesService ongoingMatchesService;
    private MatchScoreCalculationService matchScoreCalculationService;
    private FinishedMatchesPersistenceService finishedMatchesPersistenceService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        ongoingMatchesService = (OngoingMatchesService) config.getServletContext().getAttribute("OngoingMatchesService");
        matchScoreCalculationService = (MatchScoreCalculationService) config.getServletContext().getAttribute("MatchScoreCalculationService");
        finishedMatchesPersistenceService = (FinishedMatchesPersistenceService) config.getServletContext().getAttribute("FinishedMatchesPersistenceService");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uuidValue = request.getParameter("uuid");

        UUID uuid = UUID.fromString(uuidValue);
        CurrentMatch currentMatch = ongoingMatchesService.getMatch(uuid).orElseThrow(NoSuchElementException::new);

        request.setAttribute("currentMatch", currentMatch);
        request.setAttribute("uuid", uuid);

        request.getRequestDispatcher("match-score.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int playerNumber = Integer.parseInt(request.getParameter("playerNumber"));
        ValidationUtils.validatePlayerNumber(playerNumber);

        String uuidValue = request.getParameter("uuid");
        UUID uuid = UUID.fromString(uuidValue);

        CurrentMatch currentMatch = ongoingMatchesService.getMatch(uuid).orElseThrow(NoSuchElementException::new);

        if (matchScoreCalculationService.isMatchOverAfterWinningPoint(currentMatch.getFullScore(), playerNumber)) {
            response.sendRedirect("matches");
            matchScoreCalculationService.setWinner(currentMatch, playerNumber);
            finishedMatchesPersistenceService.save(currentMatch);
            ongoingMatchesService.remove(uuid);
        } else {
            response.sendRedirect("match-score?uuid=" + uuid);
        }

    }

}
