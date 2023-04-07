INSERT INTO pizzas (description, name, price)VALUES('La sua ricetta è a base di pomodoro, mozzarella, funghi, prosciutto cotto, olive e carciofi: è senza dubbio la pizza più ricca mai realizzata, in grado di soddisfare anche il palato più esigente!', 'Capricciosa', 4.50)
INSERT INTO pizzas (description, name, price)VALUES('La marinara è una pizza napoletana condita con pomodori, origano, aglio, olio extra vergine di oliva e talvolta basilico fresco.', 'Marinara', 3.50)
INSERT INTO pizzas (description, name, price)VALUES('Funghi shiitake, salsiccia, prezzemolo, aglio, erbe aromatiche e olio extra vergine di oliva.', 'Boscaiola', 6.50)

INSERT INTO db_pizzeria.discounts(expiring_date, starting_date, title, value, pizza_id)VALUES('2023-05-02', '2023-03-01', 'Easter Discount',20, 1);
INSERT INTO db_pizzeria.discounts(expiring_date, starting_date, title, value, pizza_id)VALUES('2023-04-02', '2023-04-01', 'Christmas Discount',15, 1);
INSERT INTO db_pizzeria.discounts(expiring_date, starting_date, title, value, pizza_id)VALUES('2023-05-02', '2023-03-01', 'Easter Discount',20, 2);
INSERT INTO db_pizzeria.discounts(expiring_date, starting_date, title, value, pizza_id)VALUES('2023-04-02', '2023-04-01', 'Christmas Discount',15, 2);
INSERT INTO db_pizzeria.discounts(expiring_date, starting_date, title, value, pizza_id)VALUES('2023-05-02', '2023-03-01', 'Easter Discount',20, 3);
INSERT INTO db_pizzeria.discounts(expiring_date, starting_date, title, value, pizza_id)VALUES('2023-04-02', '2023-04-01', 'Christmas Discount',15, 3);


INSERT INTO db_pizzeria.ingredients(description, name)VALUES('', 'tomato');
INSERT INTO db_pizzeria.ingredients(description, name)VALUES('', 'mozzarella');
INSERT INTO db_pizzeria.ingredients(description, name)VALUES('', 'olive');
INSERT INTO db_pizzeria.ingredients(description, name)VALUES('', 'basil');
INSERT INTO db_pizzeria.ingredients(description, name)VALUES('', 'potatoes');
INSERT INTO db_pizzeria.ingredients(description, name)VALUES('', 'garlic');


INSERT INTO db_pizzeria.ingredients_pizzas(pizza_id, ingredient_id)VALUES(1, 1);
INSERT INTO db_pizzeria.ingredients_pizzas(pizza_id, ingredient_id)VALUES(2, 1);
INSERT INTO db_pizzeria.ingredients_pizzas(pizza_id, ingredient_id)VALUES(3, 1);

INSERT INTO db_pizzeria.ingredients_pizzas(pizza_id, ingredient_id)VALUES(1, 2);
INSERT INTO db_pizzeria.ingredients_pizzas(pizza_id, ingredient_id)VALUES(2, 2);
INSERT INTO db_pizzeria.ingredients_pizzas(pizza_id, ingredient_id)VALUES(3, 2);

INSERT INTO db_pizzeria.ingredients_pizzas(pizza_id, ingredient_id)VALUES(1, 3);
INSERT INTO db_pizzeria.ingredients_pizzas(pizza_id, ingredient_id)VALUES(2, 3);
INSERT INTO db_pizzeria.ingredients_pizzas(pizza_id, ingredient_id)VALUES(3, 4);

INSERT INTO db_pizzeria.ingredients_pizzas(pizza_id, ingredient_id)VALUES(1, 4);
INSERT INTO db_pizzeria.ingredients_pizzas(pizza_id, ingredient_id)VALUES(2, 4);
INSERT INTO db_pizzeria.ingredients_pizzas(pizza_id, ingredient_id)VALUES(3, 5);

INSERT INTO db_pizzeria.ingredients_pizzas(pizza_id, ingredient_id)VALUES(1, 5);
INSERT INTO db_pizzeria.ingredients_pizzas(pizza_id, ingredient_id)VALUES(2, 5);
INSERT INTO db_pizzeria.ingredients_pizzas(pizza_id, ingredient_id)VALUES(3, 3);


INSERT INTO db_pizzeria.users(email, nickname, password)VALUES('john@email.com', 'john', '{noop}john');
INSERT INTO db_pizzeria.users(email, nickname, password)VALUES('anna@email.com', 'anna', '{noop}anna');
INSERT INTO db_pizzeria.users(email, nickname, password)VALUES('carlo@email.com', 'carlo', '{noop}carlo');

INSERT INTO db_pizzeria.roles(name)VALUES('ADMIN');
INSERT INTO db_pizzeria.roles(name)VALUES('USER');

INSERT INTO db_pizzeria.users_roles(user_id, roles_id)VALUES(1, 1);
INSERT INTO db_pizzeria.users_roles(user_id, roles_id)VALUES(2, 2);
INSERT INTO db_pizzeria.users_roles(user_id, roles_id)VALUES(3, 1);
INSERT INTO db_pizzeria.users_roles(user_id, roles_id)VALUES(3, 2);