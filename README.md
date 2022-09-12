# My Personal Project

## Project Proposal

- What will the application do?
The application will be similar to a fantasy sports team application. The original program will include several NBA
players and teams. However, the users can add new players to each team or even build their own team. The users can
update each team and player's stats with the current real stats in NBA. The users are also allowed to trade players
with other teams if certain rules matched. 
- Who will use it?
The users can be anyone who is passionated about basketball or NBA. It gives them an opportunity to be like an NBA team
manager where they can create their dream teams and even their dream players. 
- Why is this project of interest to you?
I am a huge basketball fan, and I love to watch NBA. It was one of my dream to an NBA player or an NBA team manager that
can trade players to form a best team in NBA and win a championship. Since I could not make these dream come true in
real life, making this application will be another way to fulfill them. 


## User Stories
- As a user, I want to select a team and view all the players in the team.
- As a user, I want to be able to add a new players and his relevant stats in a team.
- As a user, I want to be able to reset an existing player's information in a team. 
- As a user, I want to be able to view all the teams in the league(the list of teams).
- As a user, I want to be able to add a new team to the league.
- As a user, I want to be able to save all the new teams and new players that 
  I might have added to the league and a team.
- As a user, when I start the application, I want to be given an option to load all the teams and players'
  stats that I saved last time. 


## Phase 4: Task 2
Tue Nov 23 21:00:29 PST 2021
Lebron James has been added to Los Angles Lakers

Tue Nov 23 21:00:29 PST 2021
Anthony Davis has been added to Los Angles Lakers

Tue Nov 23 21:00:29 PST 2021
Russell Westbrook has been added to Los Angles Lakers

Tue Nov 23 21:00:29 PST 2021
Giannis Antetokounmpo has been added to Milwaukee Bucks

Tue Nov 23 21:00:29 PST 2021
Kris Middleton has been added to Milwaukee Bucks

Tue Nov 23 21:00:29 PST 2021
True Holiday has been added to Milwaukee Bucks

Tue Nov 23 21:00:29 PST 2021
Los Angles Lakers has been added to National Basketball Association

Tue Nov 23 21:00:29 PST 2021
Milwaukee Bucks has been added to National Basketball Association

Tue Nov 23 21:01:09 PST 2021
The PPG of Russell Westbrook has been changed to 19.8

Tue Nov 23 21:01:09 PST 2021
The RPG of Russell Westbrook has been changed to 10.7


## Phase 4: Task 3
- Let Player, Team, and League classes extends Java Observable class and 
EventLog class implement Java Observer interface, so the logEvent method can be replaced by the update method. 
- Let MainView and Team view extend a super class named ChoicePanels and implement ActionListener. The super class will
extend JFrame, and the fields and the methods that are same and exists in both classes can be extracted to ChoicePanel. 
- Let AddPlayerView and ResetPlayerView only extend a super abstract class named ModifyPlayerPanels. ModifyPlayerPanels 
will extend JFrame and implement ActionListener.