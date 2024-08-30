<%@ page import="com.damvih.dto.MatchResponseDto" %>
<%@ page import="com.damvih.dto.MatchesResponseDto" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Matches</title>
    <link rel="stylesheet" href="css/style.css">
    <script src="https://kit.fontawesome.com/7bf2a01fc3.js" crossorigin="anonymous"></script>
</head>
    <body>
        <nav>
            <ul>
            <li><a href="/">Home</a></li>
            <li><a href="matches">Matches</a></li>
            <li><a href="new-match">New match</a></li>
            </ul>
        </nav>
        <main>
            <div class="container">
                <h1>Matches</h1>
                <div class="content-input">
                    <form class="matches-form-box" action="matches" method="get">
                        <input type="text" placeholder="Name" name="filter_by_player_name" pattern="^[A-Z][a-zA-Z]{2,15}$">
                        <button class="btn" type="submit">
                            <i class="fa-solid fa-magnifying-glass"></i>
                        </button>
                        <a class="btn" href="/matches">
                            <i class="fa-solid fa-arrows-rotate"></i>
                        </a>
                    </form>
                </div>
                <div class="table-content">
                    <table>
                        <thead>
                            <tr>
                                <th>Player 1</th>
                                <th>Player 2</th>
                            </tr>
                        </thead>
                        <tbody>
                            <% MatchesResponseDto matchesResponse = (MatchesResponseDto) request.getAttribute("matchesResponse"); %>
                            <% for (MatchResponseDto match : matchesResponse.getMatches()) { %>
                            <tr>
                                <td>
                                    <%= match.getPlayerOne().getName() %>
                                    <% if (match.getWinner().getName().equals(match.getPlayerOne().getName())) { %>
                                        <i class="fa-solid fa-trophy"></i>
                                    <% } %>
                                </td>
                                <td>
                                    <% if (match.getWinner().getName().equals(match.getPlayerTwo().getName())) { %>
                                        <i class="fa-solid fa-trophy"></i>
                                    <% } %>
                                    <%= match.getPlayerTwo().getName() %>
                                </td>
                            </tr>
                            <% } %>
                        </tbody>
                    </table>
                </div>
                <div class="pagination">
                    <% int currentPage = (int) request.getAttribute("currentPage"); %>
                    <% int totalPages = matchesResponse.getTotalPages(); %>
                    <% if (totalPages == 0) totalPages = 1; %>

                    <% if (currentPage > 1) { %>
                    <a href="?page=${currentPage - 1}&filter_by_player_name=${playerName}">
                        <i class="fa-solid fa-arrow-left"></i>
                    </a>
                    <% } %>

                    <p><%= currentPage %> of <%= totalPages %></p>

                    <% if (currentPage < totalPages) { %>
                    <a href="?page=${currentPage + 1}&filter_by_player_name=${playerName}">
                        <i class="fa-solid fa-arrow-right"></i>
                    </a>
                    <% } %>
                </div>
            </div>
        </main>
    </body>
</html>