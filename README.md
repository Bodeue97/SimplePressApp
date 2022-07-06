Aby uruchomić aplikację należy po zaimportowaniu projektu wywołać metodę main znajdującą się w klasie ArticleApp.
Aplikacja opiera się na frameworku Spring Boot.
Warstwą persystencji jest zapis do bazy MySQL.

Endpointy: 

------------------------------------------------------------------------------------------------------------------------

/add 

metoda HTTP: POST

Endpoint pozwalający na zapis artykułu prasowego.

Parametry: 

String title - tytuł artykułu
String contents - treść artykułu
String paperName - nazwa czasopisma
String authorFirstname - imie autora
String authorLastname - nazwisko autora
String publicationDate - data publikacji w formacie yyyy-MM-dd

------------------------------------------------------------------------------------------------------------------------

/show

metoda HTTP: GET

Endpoint zwracający wszystkie artykuły prasowe posortowane malejąco po dacie publikacji.

------------------------------------------------------------------------------------------------------------------------

/byKeyword

metoda HTTP: GET

Endpoint zwracający listę wszystkich artykułów prasowych po słowie kluczowym zawartym w tytule lub treści publikacji.

Parametry:

String keyword - słowo kluczowe po którym wyszukiwane są wyniki

------------------------------------------------------------------------------------------------------------------------

/byId

metoda HTTP: GET

Endpoint zwracający pojedynczy artykuł prasowy po id.

Parametry:

Long id - id szukanego artykułu.


------------------------------------------------------------------------------------------------------------------------

/update

metoda HTTP: PUT

Endpoint do aktualizacji istniejącego artykułu prasowego.

Parametry: 

Jeśli pole pozostawione jest puste, wartość pola nie zostanie zaktualizowana i pozostanie taka jak przed wywołaniem metody.

String title - nowy tytuł artykułu
String contents - nowa treść artykułu
String paperName - nowa nazwa czasopisma
String authorFirstname - nowe imię autora
String authorLastname - nowe nazwisko autora
String publicationDate - nowa data publikacji
Long id - id artykułu, który chcemy zaktualizować

------------------------------------------------------------------------------------------------------------------------

/delete

metoda HTTP: DELETE

Endpoint do usuwania wybranego artykułu prasowego.

Parametry: 

Long id - id artykułu, który ma zostać usunięty

------------------------------------------------------------------------------------------------------------------------






