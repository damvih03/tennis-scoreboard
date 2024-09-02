<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Match score</title>
    <link rel="stylesheet" href="css/style.css">
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
            <h1>Match score</h1>
            <div class="table-content">
                <table>
                    <thead>
                        <tr>
                            <th>Player</th>
                            <th>Sets</th>
                            <th>Games</th>
                            <th>Points</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>${currentMatch.playerOne}</td>
                            <td>${currentMatch.fullScore.matchScore.getPlayerPoints(0)}</td>
                            <td>${currentMatch.fullScore.setScore.getPlayerPoints(0)}</td>
                            <td>${currentMatch.fullScore.gameScore.getPlayerPoints(0)}</td>
                            <td>
                                <form class="match-score-box" action="match-score?uuid=${uuid}" method="post">
                                    <button class="btn" name="playerNumber" value="0">Point</button>
                                </form>
                            </td>
                        </tr>
                        <tr>
                            <td>${currentMatch.playerTwo}</td>
                            <td>${currentMatch.fullScore.matchScore.getPlayerPoints(1)}</td>
                            <td>${currentMatch.fullScore.setScore.getPlayerPoints(1)}</td>
                            <td>${currentMatch.fullScore.gameScore.getPlayerPoints(1)}</td>
                            <td>
                                <form class="match-score-box" action="match-score?uuid=${uuid}" method="post">
                                    <button class="btn" name="playerNumber" value="1">Point</button>
                                </form>
                            </td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </main>
</body>
</html>