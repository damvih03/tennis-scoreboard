package com.damvih.servlets;

import com.damvih.dto.NewMatchRequestDto;
import com.damvih.services.OngoingMatchesService;
import com.damvih.utils.ValidationUtils;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.UUID;

@WebServlet("/new-match")
public class NewMatchServlet extends HttpServlet {

    private OngoingMatchesService ongoingMatchesService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        ongoingMatchesService = (OngoingMatchesService) config.getServletContext().getAttribute("OngoingMatchesService");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("new-match.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String playerOneName = request.getParameter("playerOne");
        String playerTwoName = request.getParameter("playerTwo");

        NewMatchRequestDto newMatchRequestDto = new NewMatchRequestDto(playerOneName, playerTwoName);

        ValidationUtils.validateNewMatch(newMatchRequestDto);

        UUID uuid = ongoingMatchesService.create(newMatchRequestDto);
        response.sendRedirect("match-score?uuid=" + uuid);
    }

}
