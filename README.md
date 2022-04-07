# Library. Final Task. Web Project

###### Application functionality:
  - Авторизация (sign in) и выход (sign out) в/из системы.
  - Регистрация пользователя и/или добавление артефакта предметной области системы.
  - Просмотр информации (например: просмотр всех ставок тотализатора, статистики заказов, счетов и т.д.)
  - Удаление информации (например: отмена заказа, удаление сущности и т.д.)
  - Добавление и модификация информации (например: создать и отредактировать товар, создать и отредактировать заказ и т.д.)


###### Application Description
  - Приложение реализовано применяя технологии Servlet и JSP.
  - Архитектура приложения соответствовует шаблонам Layered architecture и MVC.
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


###### Task Descrption
  Библиотека. Читатель имеет возможность осуществлять поиск и заказ Книг в Каталоге. Библиотекарь выдает Читателю Книгу на абонемент или в читальный зал. Книга может присутствовать в Библиотеке в одном или нескольких экземплярах. Администратор управляет Библиотекарями, Читателями и Книгами.


