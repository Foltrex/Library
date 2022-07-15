# Library. Web Project

## Application functionality:
  - Авторизация (sign in) и выход (sign out) в/из системы.
  - Регистрация пользователя и/или добавление артефакта предметной области системы.
  - Просмотр информации (например: просмотр всех ставок тотализатора, статистики заказов, счетов и т.д.)
  - Удаление информации (например: отмена заказа, удаление сущности и т.д.)
  - Добавление и модификация информации (например: создать и отредактировать товар, создать и отредактировать заказ и т.д.)


## Application Description
  - Приложение реализовано с применением технологий Servlet и JSP.
  - Архитектура приложения соответствует шаблонам Layered architecture и MVC.
  - Информация о предметной области хранится в БД:
  - Технология доступа к БД JDBC
  - Для работы с БД в приложении реализован потокобезопасный пул соединений, без использования synchronized и volatile.
  - Работу с данными в приложении осуществляется посредством шаблона DAO.
  - Интерфейс приложения локализован; выбор из языков: EN|BE|RU.
  - Приложение корректно обрабатывает возникающие исключительные ситуации, в том числе ведет их логи. В качестве логгера используется Log4J2
  - Классы и другие сущности приложения грамотно структурированы по пакетам и и имеют отражающую их функциональность название.
  - При реализации бизнес-логики приложения используются шаблоны проектирования: Factory Method, Command, Singleton, Proxy.
  - Для хранения пользовательской информации между запросами используется сессия.
  - Для перехвата и корректировки объектов запроса (request) и ответа (response) применяются фильтры.
  - При реализации страниц JSP использются теги библиотеки JSTL(скриплетов в проекте нет).
  - При реализации пользовательского интерфейса использются технологии front-end разработки (JavaScript).
  - Реализован собственный тег(Pagination).
  - Просмотр “длинных списков” организовыван в постраничном режиме.
  - При развертывании приложения используется технология Maven.
  - Приложение содержит тесты JUnit, Mockito.


## Task Descrption
  Библиотека. Читатель имеет возможность осуществлять поиск и заказ Книг в Каталоге. Библиотекарь выдает Читателю Книгу на абонемент или в читальный зал. Книга может присутствовать в Библиотеке в одном или нескольких экземплярах. Администратор управляет Библиотекарями, Читателями и Книгами.

## Documentation
[JavaDoc](https://github.com/Foltrex/Library/blob/gh-pages/index.html)

## Source Code
[PullRequest](https://github.com/Foltrex/Library/pull/1)

## Images

### 1. Login Page
![Login](https://github.com/Foltrex/Library/blob/gh-pages/assets/Login%20page.png)

### 2. Registration Page
![Registration](https://github.com/Foltrex/Library/blob/gh-pages/assets/Registration%20page.png)

### 3. Books Page (Admin role, en locale)
![Books Page (Admin role, en locale)](https://github.com/Foltrex/Library/blob/gh-pages/assets/Books%20Page%20(Admin%20role%2C%20en%20locale).png)

### 4. Books Page (BE locale)
![Books Page (BE locale)](https://github.com/Foltrex/Library/blob/gh-pages/assets/Books%20Page(BE%20locale).png)

### 5. Books Page (RU locale)
![Books Page (RU locale)](https://github.com/Foltrex/Library/blob/gh-pages/assets/Books%20Page(RU%20locale).png)

### 6. Add book Page (for admin)
![Add book(for admin)](https://github.com/Foltrex/Library/blob/gh-pages/assets/Add%20book(for%20admin).png)


### 7. Edit Book Page (for admin)
![Edit Book Page(Admin Role)](https://github.com/Foltrex/Library/blob/gh-pages/assets/Edit%20Book%20Page(Admin%20Role).png)

### 8. Authors Page
![Authors Page](https://github.com/Foltrex/Library/blob/gh-pages/assets/Authors%20Page.png)

### 9. Book Rentals Page
![Book Rentals Page](https://github.com/Foltrex/Library/blob/gh-pages/assets/Book%20Rentals%20Page.png)

### 10. Genres Page
![Genres Page](https://github.com/Foltrex/Library/blob/gh-pages/assets/Genres%20Page.png)

### 11. Librarians Page
![Librarians Page](https://github.com/Foltrex/Library/blob/gh-pages/assets/User%20List%20Page(Librarians).png)

### 12. Sidebar
![Sidebar](https://github.com/Foltrex/Library/blob/gh-pages/assets/Sidebar.png)





