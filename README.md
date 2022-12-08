# Coworking@Zurich Park Side

Coworking@zurich Park Side ist ein Prototyp für ein System, welches Buchungen und Mitglieder eines Coworkingspaces
verwaltet. Coworkin@zurich Park Side wurde mit Quarkus entwickelt.

## DB

Zum Persistieren der Daten wird eine PostgreSQL-Datenbank verwendet. Die Datenbank wird im Docker-Compose-File
konfiguriert. (./.devcontainer/docker-compose.yml)<br>
Für die automatischen E2E-Tests wird eine H2-Datenbank verwendet.

## Einrichten

1. Repository klonen
2. Projekt im VSCode öffnen
3. Dev Container Extension
   installieren (https://marketplace.visualstudio.com/items?itemName=ms-vscode-remote.remote-containers)
4. Mit "Dev Containers: Reopen in Container" wird das Projekt im Dev Container geöffnent.
5. Mit "Developer: Reload Window" werden die nötigen Dependencies geladen.

## Starten

Um das Projekt zu starten, gibt es 2 Möglichkeiten:

* ./mvnw quarkus:debug
* "Quarkus: Debug current Quarkus project"

## Testdaten

Werden die E2E-Tests ausgeführt, wird vor jedem Test die H2 Datenbank zurückgesetzt und mit den folgenden Daten befüllt.
Somit entstehen zwischen den
Tests keine Abhängigkeiten.
Die Gleichen Daten werden auch beim Start der Applikation automatisch geladen.
* 3 Member
* 1 Admin
* 2 Buchungen

## Änderungen

Aufgrund mangelnder Zeit wurde auf ein Salten der Passwörter verzichtet. Stattdessen wird den Passwörtern vor dem Hashen
ein Pepper hinzugefügt. Ebenfalls aus Zeitgründen wurde nur vereinzelt
implementiert, dass die Endpoints möglichst passende HTTP-Responses zurückgeben. 

## Weitere Informationen
 * Weitere Informationen zu Quarkus sind im ReadMe zu Quarkus zu finden. (./README.quarkus.md)
 * [Offizielle Quarkus Website](https://quarkus.io/)
