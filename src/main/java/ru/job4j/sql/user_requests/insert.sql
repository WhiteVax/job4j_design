INSERT INTO role(role_user)
VALUES  ('user_first'),
        ('user_second'),
        ('user_third');

INSERT INTO users(role, nickname)
VALUES  (1, 'user_first'),
        (2, 'user_second'),
        (3, 'user_third');

INSERT INTO rulers(rule)
VALUES  ('viewing'),
        ('viewing'),
        ('viewing');

INSERT INTO role_rulers(id_role, id_rule)
VALUES  (1, 1),
        (2, 2),
        (3, 3);

INSERT INTO category(category_user)
VALUES  ('smartphone'),
        ('desktop'),
        ('laptop');

INSERT INTO state_item(txt)
VALUES ('in work'),
       ('completed'),
       ('in work');

INSERT INTO item(id_user, category, id_state, user_adress)
VALUES (1, 1,  1, 'Demonstrations St., 27'),
       (2, 3,  2, 'Demonstrations St., 28'),
       (3, 2,  3, 'Demonstrations St., 23');

INSERT INTO commentars(id_item, txt)
VALUES  (1, 'display repair'),
        (2, 'HDD replacement'),
        (3, 'touchpad replacement');

INSERT INTO attachs(id_item, attach_file)
VALUES  (1, 'photo_1.png'),
        (2, 'photo_2.png'),
        (3, 'photo_3.png');