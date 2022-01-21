###Skapa ett paket med tre klasser

#### Paket com.example.blogapiserver;
#### Klass: BlogApiServerApplication.java 
Innehåller main-metoden för att kunna starta programmet.

#### Klass: BlogPost.java (Objekt)
Innehåller attribut för ett blogginlägg (Id, Titel & Body)
Innehåller ToString metod och Enkapsulering av attributen

#### Klass: BlogController.java ("Server")
Innehåller metoder som styrs av annoteringar med HTTP-adresser
Arraylist av klassen BlogPost för att spara blogginlägg


***
## Serverkomponenten:
- Servern ska använda sig av Spring-ramverket och det är i servern som alla
blogginlägg sparas
- Servern ska svara på API-förfrågningar för att lista inlägg, redigera inlägg, ta bort
inlägg och visa specifikt inlägg.
- Adresserna till dessa API-förfrågningar ska vara följande:

  ###/api/v1/blog/list – Lista alla inlägg (DONE, DEV)
- **Given**: Att användaren har menyn framför sig
- **When**: Användaren väljer alternativet att lista blogginlägg
- **Then**: Ska klienten visa en lista med alla blogginlägg
***
  ###/api/v1/blog/view/<id> - Visa ett specifikt inlägg (DONE, DEV)
- **Given**: Att användaren har menyn framför sig
- **When**: Användaren väljer alternativet att lista specifikt blogginlägg med ett ID-nummer
- **Then**: Ska klienten visa det specifikt blogginlägget om det finns annars svara med en felkod
***
  ###/api/v1/blog/update/<id> - Uppdatera ett specifikt inlägg (DONE, DEV)
- **Given**: Att användaren har menyn framför sig
- **When**: Användaren väljer alternativet att uppdatera ett specifikt blogginlägg med ID-nummer
- **Then**: Ska klienten fråga efter ny titel eller body som ska ändras och skicka tillbaka uppdatering till servern
***
  ###/api/v1/blog/delete/<id> - Ta bort ett specifikt inlägg (TODO)
- **Given**: Att användaren har menyn framför sig 
- **When**: Användaren väljer att ta bort ett specifikt blogginlägg 
- **Then**: Om ID-numret finns ska klienten skicka förfrågan till servern annars svara med felkod
***
  ###/api/v1/blog/create – Lägg till ett nytt inlägg (DONE, DEV)
- **Given**: Att användaren har menyn framför sig
- **When**: Användaren väljer att skapa ett nytt blogginlägg 
- **Then**: Ska klienten skicka förfrågan om att skapa ett nytt blogginlägg till servern
***

- Fler får läggas till om du känner ett behov av det
- Varje förfrågan måste använda en lämplig HTTP-metod (GET, POST, PATCH et cetera)
- Din kod ska sparas i versionhantering med Git

***

## Klientkomponenten:

- Klientkomponenten ska vara ett textbaserat gränssnitt (CLI)
- Användaren ska kunna lista/lägga till/redigera/ta bort blogginlägg via API- förfrågningar till serverkomponenten
- Användaren ska kunna lista alla inlägg (ID och titel visas)
- Användaren ska kunna efterfråga ett specifikt inlägg och läsa innehållet Ingen information ska sparas i klienten
- Om användaren försöker visa, ta bort, redigera eller ändra ett inlägg som inte finns ska det visas ett tydligt felmeddelande
- Din kod ska sparas i versionshantering med Git