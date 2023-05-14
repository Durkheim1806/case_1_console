INSERT INTO Gebruiker (id, voornaam, achternaam, email, wachtwoord, favorieteProgrammeertaal) VALUES (1, 'Frits','Spits','frits@spits.nl','welkom','java')
INSERT INTO Gebruiker (id, voornaam, achternaam, email, wachtwoord, favorieteProgrammeertaal) VALUES (2, 'Ruud', 'Gullit', 'ruud@gullit.nl', 'welkom', 'python');
INSERT INTO Gebruiker (id, voornaam, achternaam, email, wachtwoord, favorieteProgrammeertaal) VALUES (3, 'Klaas', 'Knot', 'klaas@knot.nl', 'welkom', 'C++');
INSERT INTO Categorie (id, naam, parent_id) VALUES (1, 'kite surf', null);
INSERT INTO Categorie (id, naam, parent_id) VALUES (2, 'kites', 1);
INSERT INTO Categorie (id, naam, parent_id) VALUES (3, 'kite boards', 1);
INSERT INTO Categorie (id, naam, parent_id) VALUES (4, 'kites 7m', 2);
INSERT INTO Categorie (id, naam, parent_id) VALUES (5, 'kites 9m', 2);
INSERT INTO Categorie (id, naam, parent_id) VALUES (6, 'kites 11m', 2);
INSERT INTO Categorie (id, naam, parent_id) VALUES (7, 'gereedschap', null);
INSERT INTO Categorie (id, naam, parent_id) VALUES (8, 'schroevendraaiers', 7);
INSERT INTO Categorie (id, naam, parent_id) VALUES (9, 'kwasten', 7);
INSERT INTO Categorie (id, naam, parent_id) VALUES (10, 'doppenset', 7);
INSERT INTO Categorie (id, naam, parent_id) VALUES (11, 'boormachine', 7);
INSERT INTO Advertentie  (id, titel, omschrijving, categorie_id, aanbieder_id) VALUES (1, 'kite North 9m', 'north kite nooit gebruikt',1 , 1);
INSERT INTO Advertentie  (id, titel, omschrijving, categorie_id, aanbieder_id) VALUES (2, 'kite board','f1 kite board zo goed als nieuw',2, 2);
INSERT INTO Advertentie  (id, titel, omschrijving, categorie_id, aanbieder_id) VALUES (3, 'kite North 12m','north kite 12m geel',3, 3);