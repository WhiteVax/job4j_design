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
BEGIN TRANSACTION;
DECLARE name_cursor CURSOR FOR SELECT * FROM products_;
FETCH 20 FROM name_cursor;
-- выгрузить 20 строк
MOVE LAST FROM NAME_cursor;
FETCH 0 FROM name_cursor;
-- посмотреть текущий элемент
MOVE BACKWARD 4 FROM name_cursor;
FETCH BACKWARD FROM name_cursor;
MOVE BACKWARD 7 FROM name_cursor;
FETCH BACKWARD FROM name_cursor;
MOVE BACKWARD 4 FROM name_cursor;
FETCH BACKWARD FROM name_cursor;
FETCH BACKWARD FROM name_cursor;
CLOSE name_cursor;
ROLLBACK;


--Кластерный индекс – это индекс, который физически упорядочивает данные (фактически биты на диске) определенным образом.
-- Когда в таблицу поступают новые данные, то они сохраняются в том же порядке. Ограничение в отношении кластерного индекса
-- заключается в том, что для таблицы БД можно создать только один кластерный индекс.
-- Это происходит из-за его природы – кластерные индексы обеспечивают соблюдение порядка данных. К тому же кластерные индексы
-- увеличивают время записи, поскольку при добавлении новых данных все данные необходимо переупорядочить.
-- Однако кластерные индексы могут значительно увеличить скорость чтения данных из таблицы.В большинстве систем управления
-- базами данных (далее – СУБД) кластерный индекс строится автоматически на основании первичного ключа. Если в таблице нет первичного ключа,
-- то для построения кластерного индекса будет использовано поле, объявленное как UNIQUE. Если же в таблице нет уникального поля,
-- то можем создать индекс на основании любого из полей, по которому мы желаем сортировать данные в таблице.
-- По умолчанию кластерный индекс создается на основе B-tree, т.е. самобалансирующееся дерево. B-деревья стараются оставаться сбалансированными.
-- При этом количество данных в каждой ветви дерева примерно одинаково. Следовательно, количество уровней, которые необходимо пройти,
-- чтобы найти строки, всегда примерно одинаково. Такие индексы можно эффективно использовать для запросов на равенство и диапазона.
-- Они могут работать со всеми типами данных, а также могут быть использованы для получения значений NULL.
-- Некластерные индексы – это индексы, которые хранят отдельный список упорядочивания, в котором есть указатели на физические строки.
-- В основном это похоже на указатель в книге, поскольку такой указатель знает, на какой странице начинается или заканчивается та или иная глава.
-- Таким образом, в отличие от кластерного индекса, таблица может иметь много некластерных индексов.
-- Но стоит понимать важный момент – каждый новый некластерный индекс увеличивает время, которое необходимо для записи новых строк в таблицу.
-- Таким образом, некластерные индексы не упорядочивают данные физически, они просто хранят список порядка данных.
-- Некластерные индексы используются для увеличения скорости запросов к таблице. Некластерные индексы указывают на адреса памяти вместо хранения самих данных.
-- Поэтому они медленнее для запросов, чем кластерные индексы. Однако обычно они намного быстрее, чем неиндексированный столбец.
-- CREATE INDEX имя_индекса ON имя_таблицы(имя_столбца);
-- - если необходимо создавать несколько индексов в БД, то выбираем некластерный индекс, поскольку может быть только один кластерный индекс;
-- - если при выборке будут использоваться только столбцы, задействованные при создании индекса, то некластерный индекс будет работать быстрее.
-- Однако если нас интересуют и остальные столбцы, которые связаны с индексом, то операции выборки будут выполняться медленнее поскольку для некластерного индекса будет
-- выполнен сначала поиск по индексу и только потом выполнено связывание с фактической записью таблицы. С другой стороны, операция SELECT выполняется быстрее с кластерными индексами,
-- если данные выбираются из столбцов, отличных от столбца в кластерном индексе. Поскольку все записи уже отсортированы.
-- - операции INSERT и UPDATE выполняются быстрее с некластерными индексами, поскольку фактические записи не требуется сортировать при вставке и обновлении.
-- - поскольку некластерные индексы хранятся отдельно от физических данных, то они занимают дополнительное пространство на диске. Если это критично,
-- то для использования некластерный индекс непригоден.


-- Нормализация БД – это процесс организации данных определенным образом и рекомендации по проектированию. Т.е. таблицы
-- и связи между ними (отношения) создаются в соответствии с правилами. В результате обеспечивается некоторый уровень безопасности данных.
-- К тому же устраняются несогласованные зависимости и избыточность.
-- Чтобы выполнить нормализацию, требуется выполнить следующее:
-- - объединить имеющиеся данные в группы;
-- - определить логические связи между группами. Для того, чтобы обеспечить правильность связей, связываемые поля должны иметь одинаковый тип.
-- Существует 7 нормальных форм, каждая последующая форма содержит набор правил предыдущей.

-- 1 Нормальная форма
-- Каждый атрибут  отношения атомарен(неделим) и система управления БД не должна операировать отдельной частью атрибута.
-- каждый многозначный атрибут обязательно необходимо убрать путем вынесения его в новое отношение;
-- каждый составной атрибут стоит тщательно исследовать, чтобы понять, действительно ли он составной (не атомарный) и, если это так,
-- разделить его на несколько новых отдельных атрибутов.

-- 2 Нормальная форма
-- Ни один атрибут, не входящий в состав потенциального ключа, не должен функционально зависеть от части какого бы то ни было из потенциальных ключей.
-- необходимо искать не только зависимости от первичного ключа, но и искать частичные зависимости неключевых атрибутов от потенциальных ключей;
-- каждый атрибут, который частично зависит от потенциального ключа, необходимо перенести в отдельное отношение
-- и сделать первичным ключом этого отношения ту часть потенциального ключа, от которого перенесенный атрибут зависел до выполнения нормализации.

-- 3 Нормальная форма
-- Более строгая форма в отличии от 2 формы и не допускает дублирований строк.
-- 3НФ не защищает отношение от возможности ошибочного указания каких-то локальных данных (например, ошибочно вписать номер паспорта человека),
-- но т.к. отношение уже находится во 2 НФ, то будут соблюдаться глобальные правила, которые распространяются на множество записей;
-- ярким признаком нарушения 3НФ является бессмысленное дублирование одних и тех же данных в множестве строк таблицы
-- (тогда атрибуты, значения которых бессмысленно дублируются, являются первыми кандидатами на перемещение в новое отдельное отношение).