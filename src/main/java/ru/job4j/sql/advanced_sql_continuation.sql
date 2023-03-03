CREATE TABLE products_
(
    id    SERIAL PRIMARY KEY,
    name  VARCHAR(50),
    count INTEGER DEFAULT 0,
    price INTEGER
);

INSERT INTO products_ (name, count, price)
VALUES ('product_1', 3, 50);
INSERT INTO products_ (name, count, price)
VALUES ('product_2', 15, 32);
INSERT INTO products_ (name, count, price)
VALUES ('product_3', 8, 115);

SET SESSION TRANSACTION ISOLATION LEVEL READ UNCOMMITTED;
-- изменения доступны в параллельных транзакциях, не все СУБД поддерживают.
SET SESSION TRANSACTION ISOLATION LEVEL READ COMMITTED;
-- доступные только подтвержденные данные параллельных транзакциях.
-- Этот уровень изолированности позволяет обеспечить отсутствие потерянных обновлений и грязного чтения.
-- Однако допускается неповторяющееся чтение и фантомное чтение.
-- Физически данный уровень реализуется с использованием блокировки или введения версий данных:
-- при блокировке транзакция, которая изменяет данные, блокирует чтение данных для параллельных транзакций,
-- которые выполняются на уровне read committed и выше;
-- при введении версий данных СУБД создает копию изменяемых данных для той транзакции, которая эти данные изменяет,
-- а все остальные параллельные транзакции получают доступ к старой (неизмененной) версии.
SET SESSION TRANSACTION ISOLATION LEVEL REPEATABLE READ;
-- begin transaction isolation level repeatable read;
-- при доступе параллельных транзакций, полученные данные блокируются. Вставка не блокируется.
-- Физически этот уровень изолированности транзакций реализуется через блокировку прочитанных данных, что запрещает параллельным
-- транзакциям изменять строки таблиц. Однако, параллельные транзакции могут вставлять новые строки, что может породить
-- проблему фантомного чтения.
SET SESSION TRANSACTION ISOLATION LEVEL SERIALIZABLE;
-- Сериализация - все транзакции выполняются не парарельно, а последовательно. Самый надежный уровень.
-- Этот уровень обеспечивает отсутствие всех проблем, т.е. потерянные обновления, грязное чтение, неповторяющееся чтение и фантомное чтение.
-- Физически это достигается за счет управления очередью транзакций и сложной системой блокировок.
-- Данный уровень изолированности самый надежный по части точности работы с данными, однако он же и самый медленный с точки зрения производительности.
-- start transaction; begin transaction; set transaction режим_транзакции; set session characteristics as transaction isolation level serializable; - все транзакции в сеансе
-- start transaction isolation level read committed; установка уровня изоляции во время начала транзакции
-- commit; commit transaction; - полностью аналогичные команды rollback; rollback transaction;
-- savepoint имя_точки_сохранения; release savepoint имя_точки_сохранения; - для создания и уничтожения точки сохранения, а так же ВСЕ до неё.
-- rollback to savepoint имя_точки_сохранения; откат до точки сохранения.
START TRANSACTION ISOLATION LEVEL SERIALIZABLE;
SAVEPOINT first;
INSERT INTO products_ (name, count, price)
VALUES ('product_11', 3, 50);
INSERT INTO products_ (name, count, price)
VALUES ('product_12', 15, 32);
SAVEPOINT second;
DROP TABLE products_;
SELECT *
FROM products_;
ROLLBACK TO first;
-- откат до первой точки сохранения
SELECT *
FROM products_;
ROLLBACK TRANSACTION;
-- Курсор - наподобии итератора в коллекциях для перебора елементов.
-- Для работы с курсором необходимо выполнить следующую последовательность операций:
-- 1. Объявить курсор – используется команда DECLARE имя_курсора;
-- 2. Открыть курсор – используется команда OPEN имя_курсора;
-- 3. Чтение следующей строки из курсора – используется команда FETCH имя_курсора;
-- 4. Закрыть курсор – используется команда CLOSE имя_курсора;
-- 5. Удалить курсор из памяти – используется команда DEALLOCATE имя_курсора.
-- DECLARE [cursor_name] [[NO] SCROLL] CURSOR FOR [query];
-- - используется DECLARE, чтобы объявить курсор;
-- - [cursor_name] – присваивается любое имя курсору;
-- - [[NO] SCROLL] – указание того, что курсор можно прокручивать или нет назад. Если указано NO SCROLL,
-- то прокрутка назад невозможна. Если ничего не указано, то возможность прокрутки зависит от запроса.
-- [query] – запрос на основании которого будет работать курсор.
-- FETCH [FORWARD | BACKWARD] [direction (rows)] FROM [cursor_name];
-- MOVE [FORWARD | BACKWARD] [direction (rows)] FROM [cursor_name];
-- NEXT (следующий);
-- PRIOR (предыдущий);
-- FIRST (первый);
-- LAST (последний);
-- ABSOLUTE count (абсолютное количество);
-- RELATIVE count (относительное количество);
-- FORWARD (вперед);
-- BACKWARD (назад).
INSERT INTO products_ (name, count, price)
VALUES ('product_1', 1, 5),
       ('product_2', 2, 10),
       ('product_3', 3, 15),
       ('product_4', 4, 20),
       ('product_5', 5, 25),
       ('product_6', 6, 30),
       ('product_7', 7, 35),
       ('product_8', 8, 40),
       ('product_9', 9, 45),
       ('product_10', 10, 50),
       ('product_11', 11, 55),
       ('product_12', 12, 60),
       ('product_13', 13, 65),
       ('product_14', 14, 70),
       ('product_15', 15, 75),
       ('product_16', 16, 80),
       ('product_17', 17, 85),
       ('product_18', 18, 90),
       ('product_19', 19, 95),
       ('product_20', 20, 100);
-- MOVE [FORWARD | BACKWARD] [direction (rows)] FROM [cursor_name]; CLOSE cursor_name;
BEGIN TRANSACTION;
DECLARE name_cursor CURSOR FOR SELECT * FROM products_;
FETCH 20 FROM name_cursor;
-- выгрузить 20 строк
MOVE LAST FROM NAME_cursor;
MOVE BACKWARD 4 FROM name_cursor;
FETCH BACKWARD FROM name_cursor;
MOVE BACKWARD 7 FROM name_cursor;
FETCH BACKWARD FROM name_cursor;
MOVE BACKWARD 4 FROM name_cursor;
FETCH BACKWARD FROM name_cursor;
FETCH BACKWARD FROM name_cursor;
CLOSE name_cursor;
ROLLBACK;