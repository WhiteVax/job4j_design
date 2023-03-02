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
    INSERT INTO products_ (name, count, price) VALUES ('product_11', 3, 50);
    INSERT INTO products_ (name, count, price) VALUES ('product_12', 15, 32);
    SAVEPOINT second;
    DROP TABLE products_;
    SELECT * FROM products_;
    ROLLBACK TO first;
-- откат до первой точки сохранения
    SELECT * FROM products_;
    ROLLBACK TRANSACTION ;
