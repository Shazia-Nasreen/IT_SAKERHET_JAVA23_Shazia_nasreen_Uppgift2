
Tidskapsel-applikation


Det här är en säker app där användare kan spara och hämta krypterade meddelanden. Bara registrerade användare kan logga in och läsa sina meddelanden. Appen använder JWT för inloggning och AES för att kryptera meddelanden så att de lagras säkert i databasen.

Funktioner

Användare kan skapa ett konto med e-post och lösenord. Lösenorden krypteras innan de sparas.
Registrerade användare kan logga in och få en säker kod (JWT-token) för att bevisa att de är inloggade.
Inloggade användare kan skriva ett meddelande i tidskapseln. Meddelandet krypteras innan det sparas.
Efter inloggning kan användaren läsa och dekryptera sina sparade meddelanden.
Tekniker som används

Java och Spring Boot för backend (den delen av appen som körs på servern)
MySQL för att spara meddelanden och användarkonton
AES för att kryptera och skydda meddelanden
JWT för att hantera inloggning
HTML, CSS och JavaScript för frontend (det användaren ser och använder)
