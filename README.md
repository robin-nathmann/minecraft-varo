# Minecraft Varo System (Spigot 1.8.8)

Ein Minecraft Varo System für Minecraft 1.8-Server, entwickelt auf Basis der Spigot API 1.8.8. 

> [!WARNING]
> **Archiv- & Legacy-Code (Stand: 2016)**  
> Dieses Plugin habe ich 2016 im Alter von 13 Jahren geschrieben. Der Code spiegelt diesen Stand wider: Er funktioniert im Kern, ist aber unstrukturiert, fehleranfällig und entspricht keinen modernen Software-Design-Standards.  
> 
> Es handelt sich um **keine Plug-and-Play-Lösung**! Um das Plugin zu kompilieren, einzurichten und fehlerfrei zu Verwenden, ist ein solides Verständnis von Java und der Spigot-1.8-API erforderlich.

---

## 📦 Abhängigkeiten (Dependencies)

Für die Ausführung auf dem Server werden folgende Komponenten in den exakten (oder kompatiblen) Versionen benötigt:

| Komponente           | Version      |
|----------------------|--------------|
 | **Java**             | Java 8       |
| **Minecraft Server** | Spigot 1.8.8 |
| **ProtocolLib**      | `3.6.4`      |
| **DiscordSRV**       | `1.30.1`     |
| **NoteBlockAPI**     | `1.1.0`      |

---

## 🛠️ Entwicklung & Build-Setup

Das Projekt wird am besten direkt in IntelliJ IDEA importiert:

1. **Repository klonen:**
   File -> New -> Project from Version Control -> Repository URL -> Clone
2. **Kompilieren:** Maven -> Lifecycle -> package
3. **Server:** `/target/minecraft-varo-x.x.x-SNAPSHOT.jar` in `/plugins` Ordner eines Spigot 1.8.8 Server kopieren
4. **Dependencies:** Alle von Entwicklerseite downloaden und in `/plugins` Ordner des Spigot 1.8.8 Server
5. **Config:** Die `/plugins/Varo/config.yml` kann momentan nicht komplett eigenständig generiert werden! Eine Beispieldatei befindet sich im Repository unter `/src/main/resources/`.
6. **Song:** Ein Song `/plugins/Varo/Win.nbs` wird für das Gewinnerteam abgespielt. Die Datei sollte vorhanden sein! Eine Beispieldatei befindet sich im Repository unter `/src/main/resources/`.
7. **Discord:** Konfiguration über `/DiscordSRV/config.yml`, weitere Informationen auf Seite des Entwicklers
8. **Funktionalität:** Es gibt keine Dokumentation, Code durchschauen und analysieren ist notwendig!