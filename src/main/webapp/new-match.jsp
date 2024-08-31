<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Start a new match</title>
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
            <h1>New match</h1>
            <div class="content-input">
                <form action="new-match" method="post">
                    <div class="input-box">
                        <input type="text" placeholder="Input name" name="playerOne" pattern="^[A-Z][a-zA-Z]{2,15}$" required>
                        <input type="text" placeholder="Input name" name="playerTwo" pattern="^[A-Z][a-zA-Z]{2,15}$" required>
                        <i class="fa-solid fa-circle-info" title="Use only english letters, name length must be between 3 and 16"></i>
                    </div>
                    <div class="btn-box">
                        <button class="start-btn">Start a match</button>
                    </div>
                </form>
            </div>
        </div>
    </main>
</body>
</html>