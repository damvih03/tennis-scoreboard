# Tennis scoreboard

Created according to the [technical specifications](https://zhukovsd.github.io/java-backend-learning-course/projects/tennis-scoreboard/) presented in this [course](https://zhukovsd.github.io/java-backend-learning-course/).

# Overview

Web app for tracking tennis matches. Allows you to create, manage matches and save results.

## Assumptions in the game rules

* Tiebreak starts at 6:6 and ends when minimal difference equals two points
* Playing best of 3 (2 sets winning)

## Technologies/tools used

### Backend:
* Java Core
* Java Servlets
* JSP
* Hibernate
* H2
* MapStruct
* Lombok
* Maven
* JUnit5

### Frontend:
* HTML/CSS

## Requirements:

* Java 21+
* Maven
* Tomcat 10
* Intellij IDEA

## Installation:

1. Clone repository

```
https://github.com/damvih03/tennis-scoreboard.git
```

2. Open Intellij IDEA and select "Run -> Edit configuration -> Add new configuration -> Tomcat".
3. Run the project

## Application interface

### Home page

![Home page](https://raw.githubusercontent.com/damvih03/tennis-scoreboard/e692e896981e07d11052b46c5538d12de3eada8d/home_page.png)

### New match page

Allows you to start a new match by creating or using existing players.

[❗️] Only **english** letters in name, length must be between **3 and 16** symbols. The **first letter** must be **capital** (ex. Rublev, Medvedev).

![New match page](https://raw.githubusercontent.com/damvih03/tennis-scoreboard/e692e896981e07d11052b46c5538d12de3eada8d/new_match_page.png)

### Match score page

Allows you to manage match and save the result (without score) to database. When match is over you will be forwarded tou finished matches page.

![Match score page](https://raw.githubusercontent.com/damvih03/tennis-scoreboard/e692e896981e07d11052b46c5538d12de3eada8d/match_score_page.png)

### Finished matches page

Allows you to track finish matches and find them by player name (same validation while creating the match).

![Finished matches page](https://raw.githubusercontent.com/damvih03/tennis-scoreboard/e692e896981e07d11052b46c5538d12de3eada8d/finished_matches_page.png)

