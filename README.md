## Serverkomponenten:
- Servern ska använda sig av Spring-ramverket och det är i servern som alla
blogginlägg sparas
- Servern ska svara på API-förfrågningar för att lista inlägg, redigera inlägg, ta bort
inlägg och visa specifikt inlägg.
- Adresserna till dessa API-förfrågningar ska vara följande:

  ###/api/v1/blog/list – Lista alla inlägg (TODO)
- **Given**: Att användaren vill visa alla inlägg och valt det alternativet
- **When**: När "/api/v1/blog/list" anropas ska klienten skicka en förfrågan till servern om att Lista alla inlägg (GET)
- **Then**: Då ska servern svara med en lista på alla inlägg
***
  ###/api/v1/blog/view/<id> - Visa ett specifikt inlägg (TODO)
- **Given**: Att användaren vill visa ett specifikt inlägg och valt det alternativet
- **When**: När /api/v1/blog/view/<id> anropas ska klienten skicka en förfrågan till servern om att lista ett specifikt inlägg (GET)
- **Then**: Då svarar servern med information om det specifika inlägget 
***

  ###/api/v1/blog/update/<id> - Uppdatera ett specifikt inlägg (TODO)
- **Given**: Att användaren vill uppdatera ett specifikt inlägg och valt det alternativet
- **When**: När /api/v1/blog/update/<id> anropas ska klienten skicka en förfrågan till servern om att uppdatera ett specifikt inlägg (POST/PATCH)
- **Then**: Då ska servern uppdatera inlägget och skriva ut godkänt meddelande 
***

  ###/api/v1/blog/delete/<id> - Ta bort ett specifikt inlägg (TODO)
- **Given**: Att användaren vill ta bort ett specifikt inlägg och valt det alternativet
- **When**: När /api/v1/blog/delete/<id> anropas ska klienten skicka en förfrågan till servern om att ta bort ett specifikt inlägg (DELETE)
- **Then**: Då ska servern ta bort inlägget och skriva ut godkänt meddelande
***

  ###/api/v1/blog/create – Lägg till ett nytt inlägg (TODO)
- **Given**: Att användaren vill skapa nytt inlägg och valt det alternativet
- **When**: När /api/v1/blog/create anropas ska klienten skicka en förfrågan om att skapa ett nytt inlägg (POST)
- **Then**: Då ska servern skapa ett nytt inlägg och svara med godkänt meddelande
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