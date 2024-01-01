# RoomMate Raum Reservierungssystem

## Beschreibung

Das RoomMate Raum Reservierungssystem ermöglicht es Benutzern, Arbeitsplätze in Räumen für bestimmte Zeiträume zu buchen. Jeder Arbeitsplatz verfügt über verschiedene Ausstattungen wie Monitor, Dockingstation, Maus, usw. Bei der Suchanfrage können Benutzer die gewünschte Ausstattung angeben, und das System zeigt verfügbare Arbeitsplätze an.

Administratoren haben erweiterte Funktionen wie das Hinzufügen oder Entfernen von Ausstattungen für Arbeitsplätze und Räume. Sie können auch Arbeitsplätze und Räume erstellen oder löschen. Zusätzlich können Administratoren Buchungen löschen und Arbeitsplätze sperren.

## Rollen

### User
![User Ansicht](/gifs/userRoomMate.gif)

### Admin
![Admin Ansicht](/gifs/adminRoomMate.gif)


## Status: ONGOING
- Folgende Features kommen noch:
  - Admin sollen Arbeitsplätze sperren
  - Buchunungsdarstellung von allen Nutzern
    - Buchungen stornieren, ändern
  - Weitere Tests schreiben.

## Technologie Stack

- Java Version 21
- Gradle 8.5
- Spring Boot 3.1
- Docker
- PostgreSQL
- OAuthApp in Github für die Anmeldung mit Github
- Flyway für die Datenbankintegration
- Thymeleaf, CSS Bootstrap, HTML

## Installation
Achtung: Vorher eigenen Oauth App in Github erstellen um CLIENT_ID und CLIENT_SECRET in die Umgebung einzutragen
rstelle im Projektverzeichnis eine .env Datei mit folgenden Inhalt:

    
    CLIENT_ID= `deine CLient ID`
    CLIENT_SECRET= `deine Client Secret`
    POSTGRES_DB=roommate
    POSTGRES_USER=roommate
    POSTGRES_PASSWORD=roommate123

1. Klone das Repository: `git clone https://github.com/dein-username/roommate.git`
2. Wechsle in das Projektverzeichnis: `cd roommate`
3. Starten Sie die `docker-compose.yaml` damit der Container geöffnet ist.
4. Verbinden Sie sich mit der Datenbank und melden sie sich mit der `application.yaml` an um Datenbankzugriff zu haben

5. Führe die Anwendung mit Gradle aus: `./gradlew bootRun`
