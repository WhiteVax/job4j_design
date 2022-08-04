INSERT INTO role(role_user)
VALUES  ('user_first'),
        ('user_second'),
        ('user_third');

INSERT INTO users(role, nickname)
VALUES  (1, 'user_first'),
        (2, 'user_second'),
        (3, 'user_third');

INSERT INTO rulers(role_user, rule)
VALUES  (1, 'viewing'),
        (2, 'viewing'),
        (3, 'viewing');

INSERT INTO commentars(txt)
VALUES  ('display repair'),
        ('HDD replacement'),
        ('touchpad replacement');

INSERT INTO attachs(attach_file)
VALUES  ('photo_1.png'),
        ('photo_2.png'),
        ('photo_3.png');

INSERT INTO category(category_user)
VALUES  ('laptop'),
        ('desktop'),
        ('smartphone');

INSERT INTO state_item(txt)
VALUES ('in work'),
        ('completed'),
        ('in work');

INSERT INTO item(id_user, category, id_state, commentar, id_attachs, user_adress)
VALUES (1, 1, 1, 1, 1, 'Demonstrations St., 27'),
        (2, 3, 2, 2, 2, 'Demonstrations St., 28'),
        (3, 2, 3, 3, 3, 'Demonstrations St., 23');
