--Insérer un client en retard de 1 jours (sachant que date courante = '2016-05-11')
/*INSERT INTO emprunts(id_client,id_exemplaire,id_document,date_emprunt,date_rendu,nb_renouvellements,nb_renouvellements) VALUES
(8,82,4,'2016-04-19',null,0);*/

/*--Pour tester que le client 5 est en retard
INSERT INTO emprunts(id_client,id_exemplaire,id_document,date_emprunt,date_rendu,nb_renouvellements) VALUES 
(5,75,2,'2016-04-18',null,0);
INSERT INTO emprunts(id_client,id_exemplaire,id_document,date_emprunt,date_rendu,nb_renouvellements) VALUES 
(5,75,2,(select date from date_courante),null,0);*/

--Pour tester le fait que le client 5 peut pas emprunter un DVD comme il n'a pas l'abo CD/DVD
/*INSERT INTO emprunts(id_client,id_exemplaire,id_document,date_emprunt,date_rendu,nb_renouvellements) VALUES 
(5,33,6,(select date from date_courante),null,0);*/

/*--Pour tester le fait que le client 6 veut emprunter plus de 5 DVD
INSERT INTO emprunts(id_client,id_exemplaire,id_document,date_emprunt,date_rendu,nb_renouvellements) VALUES
(6,33,6,'2016-05-05',null,0);
INSERT INTO emprunts(id_client,id_exemplaire,id_document,date_emprunt,date_rendu,nb_renouvellements) VALUES
(6,34,6,'2016-05-05',null,0);
INSERT INTO emprunts(id_client,id_exemplaire,id_document,date_emprunt,date_rendu,nb_renouvellements) VALUES
(6,39,7,'2016-05-05',null,0);
INSERT INTO emprunts(id_client,id_exemplaire,id_document,date_emprunt,date_rendu,nb_renouvellements) VALUES
(6,43,7,'2016-05-05',null,0);
INSERT INTO emprunts(id_client,id_exemplaire,id_document,date_emprunt,date_rendu,nb_renouvellements) VALUES
(6,45,8,'2016-05-05',null,0);
INSERT INTO emprunts(id_client,id_exemplaire,id_document,date_emprunt,date_rendu,nb_renouvellements) VALUES
(6,46,8,'2016-05-05',null,0);*/

/*--Pour tester le fait que le client 6 veut emprunter plus de 3 nouveautés
INSERT INTO emprunts(id_client,id_exemplaire,id_document,date_emprunt,date_rendu,nb_renouvellements) VALUES
(6,3,1,'2016-05-05',null,0);
INSERT INTO emprunts(id_client,id_exemplaire,id_document,date_emprunt,date_rendu,nb_renouvellements) VALUES
(6,33,6,'2016-05-05',null,0);
INSERT INTO emprunts(id_client,id_exemplaire,id_document,date_emprunt,date_rendu,nb_renouvellements) VALUES
(6,34,6,'2016-05-05',null,0);
INSERT INTO emprunts(id_client,id_exemplaire,id_document,date_emprunt,date_rendu,nb_renouvellements) VALUES
(6,39,7,'2016-05-05',null,0);*/

/*--Pour tester le fait que le client 6 veut emprunter plus de 20 exemplaires dans la même bibliotheque
INSERT INTO emprunts(id_client,id_exemplaire,id_document,date_emprunt,date_rendu,nb_renouvellements) VALUES
(6,48,1,'2016-05-05',null,0);
INSERT INTO emprunts(id_client,id_exemplaire,id_document,date_emprunt,date_rendu,nb_renouvellements) VALUES
(6,49,1,'2016-05-05',null,0);
INSERT INTO emprunts(id_client,id_exemplaire,id_document,date_emprunt,date_rendu,nb_renouvellements) VALUES
(6,50,1,'2016-05-05',null,0);
INSERT INTO emprunts(id_client,id_exemplaire,id_document,date_emprunt,date_rendu,nb_renouvellements) VALUES
(6,51,1,'2016-05-05',null,0);
INSERT INTO emprunts(id_client,id_exemplaire,id_document,date_emprunt,date_rendu,nb_renouvellements) VALUES
(6,52,1,'2016-05-05',null,0);
INSERT INTO emprunts(id_client,id_exemplaire,id_document,date_emprunt,date_rendu,nb_renouvellements) VALUES
(6,53,2,'2016-05-05',null,0);
INSERT INTO emprunts(id_client,id_exemplaire,id_document,date_emprunt,date_rendu,nb_renouvellements) VALUES
(6,54,2,'2016-05-05',null,0);
INSERT INTO emprunts(id_client,id_exemplaire,id_document,date_emprunt,date_rendu,nb_renouvellements) VALUES
(6,55,2,'2016-05-05',null,0);
INSERT INTO emprunts(id_client,id_exemplaire,id_document,date_emprunt,date_rendu,nb_renouvellements) VALUES
(6,56,2,'2016-05-05',null,0);
INSERT INTO emprunts(id_client,id_exemplaire,id_document,date_emprunt,date_rendu,nb_renouvellements) VALUES
(6,57,2,'2016-05-05',null,0);
INSERT INTO emprunts(id_client,id_exemplaire,id_document,date_emprunt,date_rendu,nb_renouvellements) VALUES
(6,58,3,'2016-05-05',null,0);
INSERT INTO emprunts(id_client,id_exemplaire,id_document,date_emprunt,date_rendu,nb_renouvellements) VALUES
(6,59,3,'2016-05-05',null,0);
INSERT INTO emprunts(id_client,id_exemplaire,id_document,date_emprunt,date_rendu,nb_renouvellements) VALUES
(6,60,3,'2016-05-05',null,0);
INSERT INTO emprunts(id_client,id_exemplaire,id_document,date_emprunt,date_rendu,nb_renouvellements) VALUES
(6,61,3,'2016-05-05',null,0);
INSERT INTO emprunts(id_client,id_exemplaire,id_document,date_emprunt,date_rendu,nb_renouvellements) VALUES
(6,62,3,'2016-05-05',null,0);
INSERT INTO emprunts(id_client,id_exemplaire,id_document,date_emprunt,date_rendu,nb_renouvellements) VALUES
(6,63,4,'2016-05-05',null,0);
INSERT INTO emprunts(id_client,id_exemplaire,id_document,date_emprunt,date_rendu,nb_renouvellements) VALUES
(6,64,4,'2016-05-05',null,0);
INSERT INTO emprunts(id_client,id_exemplaire,id_document,date_emprunt,date_rendu,nb_renouvellements) VALUES
(6,65,4,'2016-05-05',null,0);
INSERT INTO emprunts(id_client,id_exemplaire,id_document,date_emprunt,date_rendu,nb_renouvellements) VALUES
(6,66,5,'2016-05-05',null,0);
INSERT INTO emprunts(id_client,id_exemplaire,id_document,date_emprunt,date_rendu,nb_renouvellements) VALUES
(6,67,5,'2016-05-05',null,0);
INSERT INTO emprunts(id_client,id_exemplaire,id_document,date_emprunt,date_rendu,nb_renouvellements) VALUES
(6,68,5,'2016-05-05',null,0);*/

/*--Pour tester le fait que le client 6 veut emprunter plus de 40 exemplaires
INSERT INTO emprunts(id_client,id_exemplaire,id_document,date_emprunt,date_rendu,nb_renouvellements) VALUES
(6,3,1,'2016-05-05',null,0);
INSERT INTO emprunts(id_client,id_exemplaire,id_document,date_emprunt,date_rendu,nb_renouvellements) VALUES
(6,4,1,'2016-05-05',null,0);
INSERT INTO emprunts(id_client,id_exemplaire,id_document,date_emprunt,date_rendu,nb_renouvellements) VALUES
(6,6,1,'2016-05-05',null,0);
INSERT INTO emprunts(id_client,id_exemplaire,id_document,date_emprunt,date_rendu,nb_renouvellements) VALUES
(6,7,1,'2016-05-05',null,0);
INSERT INTO emprunts(id_client,id_exemplaire,id_document,date_emprunt,date_rendu,nb_renouvellements) VALUES
(6,8,1,'2016-05-05',null,0);
INSERT INTO emprunts(id_client,id_exemplaire,id_document,date_emprunt,date_rendu,nb_renouvellements) VALUES
(6,9,1,'2016-05-05',null,0);
INSERT INTO emprunts(id_client,id_exemplaire,id_document,date_emprunt,date_rendu,nb_renouvellements) VALUES
(6,10,1,'2016-05-05',null,0);
INSERT INTO emprunts(id_client,id_exemplaire,id_document,date_emprunt,date_rendu,nb_renouvellements) VALUES
(6,11,1,'2016-05-05',null,0);
INSERT INTO emprunts(id_client,id_exemplaire,id_document,date_emprunt,date_rendu,nb_renouvellements) VALUES
(6,12,2,'2016-05-05',null,0);
INSERT INTO emprunts(id_client,id_exemplaire,id_document,date_emprunt,date_rendu,nb_renouvellements) VALUES
(6,13,2,'2016-05-05',null,0);
INSERT INTO emprunts(id_client,id_exemplaire,id_document,date_emprunt,date_rendu,nb_renouvellements) VALUES
(6,14,2,'2016-05-05',null,0);
INSERT INTO emprunts(id_client,id_exemplaire,id_document,date_emprunt,date_rendu,nb_renouvellements) VALUES
(6,15,2,'2016-05-05',null,0);
INSERT INTO emprunts(id_client,id_exemplaire,id_document,date_emprunt,date_rendu,nb_renouvellements) VALUES
(6,16,2,'2016-05-05',null,0);
INSERT INTO emprunts(id_client,id_exemplaire,id_document,date_emprunt,date_rendu,nb_renouvellements) VALUES
(6,17,2,'2016-05-05',null,0);
INSERT INTO emprunts(id_client,id_exemplaire,id_document,date_emprunt,date_rendu,nb_renouvellements) VALUES
(6,18,3,'2016-05-05',null,0);
INSERT INTO emprunts(id_client,id_exemplaire,id_document,date_emprunt,date_rendu,nb_renouvellements) VALUES
(6,19,3,'2016-05-05',null,0);
INSERT INTO emprunts(id_client,id_exemplaire,id_document,date_emprunt,date_rendu,nb_renouvellements) VALUES
(6,20,3,'2016-05-05',null,0);
INSERT INTO emprunts(id_client,id_exemplaire,id_document,date_emprunt,date_rendu,nb_renouvellements) VALUES
(6,21,3,'2016-05-05',null,0);
INSERT INTO emprunts(id_client,id_exemplaire,id_document,date_emprunt,date_rendu,nb_renouvellements) VALUES
(6,22,3,'2016-05-05',null,0);
INSERT INTO emprunts(id_client,id_exemplaire,id_document,date_emprunt,date_rendu,nb_renouvellements) VALUES
(6,23,4,'2016-05-05',null,0);
INSERT INTO emprunts(id_client,id_exemplaire,id_document,date_emprunt,date_rendu,nb_renouvellements) VALUES
(6,24,4,'2016-05-05',null,0);
INSERT INTO emprunts(id_client,id_exemplaire,id_document,date_emprunt,date_rendu,nb_renouvellements) VALUES
(6,25,4,'2016-05-05',null,0);
INSERT INTO emprunts(id_client,id_exemplaire,id_document,date_emprunt,date_rendu,nb_renouvellements) VALUES
(6,26,4,'2016-05-05',null,0);
INSERT INTO emprunts(id_client,id_exemplaire,id_document,date_emprunt,date_rendu,nb_renouvellements) VALUES
(6,27,4,'2016-05-05',null,0);
INSERT INTO emprunts(id_client,id_exemplaire,id_document,date_emprunt,date_rendu,nb_renouvellements) VALUES
(6,28,5,'2016-05-05',null,0);
INSERT INTO emprunts(id_client,id_exemplaire,id_document,date_emprunt,date_rendu,nb_renouvellements) VALUES
(6,29,5,'2016-05-05',null,0);
INSERT INTO emprunts(id_client,id_exemplaire,id_document,date_emprunt,date_rendu,nb_renouvellements) VALUES
(6,30,5,'2016-05-05',null,0);
INSERT INTO emprunts(id_client,id_exemplaire,id_document,date_emprunt,date_rendu,nb_renouvellements) VALUES
(6,31,5,'2016-05-05',null,0);
INSERT INTO emprunts(id_client,id_exemplaire,id_document,date_emprunt,date_rendu,nb_renouvellements) VALUES
(6,32,5,'2016-05-05',null,0);
INSERT INTO emprunts(id_client,id_exemplaire,id_document,date_emprunt,date_rendu,nb_renouvellements) VALUES
(6,33,6,'2016-05-05',null,0);
INSERT INTO emprunts(id_client,id_exemplaire,id_document,date_emprunt,date_rendu,nb_renouvellements) VALUES
(6,34,6,'2016-05-05',null,0);
INSERT INTO emprunts(id_client,id_exemplaire,id_document,date_emprunt,date_rendu,nb_renouvellements) VALUES
(6,41,7,'2016-05-05',null,0);
INSERT INTO emprunts(id_client,id_exemplaire,id_document,date_emprunt,date_rendu,nb_renouvellements) VALUES
(6,43,7,'2016-05-05',null,0);
INSERT INTO emprunts(id_client,id_exemplaire,id_document,date_emprunt,date_rendu,nb_renouvellements) VALUES
(6,44,8,'2016-05-05',null,0);
INSERT INTO emprunts(id_client,id_exemplaire,id_document,date_emprunt,date_rendu,nb_renouvellements) VALUES
(6,45,8,'2016-05-05',null,0);
INSERT INTO emprunts(id_client,id_exemplaire,id_document,date_emprunt,date_rendu,nb_renouvellements) VALUES
(6,69,5,'2016-05-05',null,0);
INSERT INTO emprunts(id_client,id_exemplaire,id_document,date_emprunt,date_rendu,nb_renouvellements) VALUES
(6,70,1,'2016-05-05',null,0);
INSERT INTO emprunts(id_client,id_exemplaire,id_document,date_emprunt,date_rendu,nb_renouvellements) VALUES
(6,71,1,'2016-05-05',null,0);
INSERT INTO emprunts(id_client,id_exemplaire,id_document,date_emprunt,date_rendu,nb_renouvellements) VALUES
(6,72,1,'2016-05-05',null,0);
INSERT INTO emprunts(id_client,id_exemplaire,id_document,date_emprunt,date_rendu,nb_renouvellements) VALUES
(6,73,1,'2016-05-05',null,0);
INSERT INTO emprunts(id_client,id_exemplaire,id_document,date_emprunt,date_rendu,nb_renouvellements) VALUES
(6,74,2,'2016-05-05',null,0);*/

--Pour tester le fait que le client 2 ne peux pas réserver plus de 5 exemplaires(qui sont déja empruntés par le client 3)
/*INSERT INTO reserve(id_client,id_exemplaire,id_document) VALUES
(2,75,2);
INSERT INTO reserve(id_client,id_exemplaire,id_document) VALUES
(2,76,2);
INSERT INTO reserve(id_client,id_exemplaire,id_document) VALUES
(2,77,3);
INSERT INTO reserve(id_client,id_exemplaire,id_document) VALUES
(2,78,3);
INSERT INTO reserve(id_client,id_exemplaire,id_document) VALUES
(2,79,3);
INSERT INTO reserve(id_client,id_exemplaire,id_document) VALUES
(2,80,4);*/

--Pour tester la mise a jour des pénalités en modifiant la date courante et afficher une alerte lorsque les client a atteind au minimum 15 euros
--UPDATE date_courante SET date = '2016-08-31' WHERE date_courante.date = (select date from date_courante);

--Alerte quand le client 7 devra rendre dans 2 jours le livre
--UPDATE date_courante SET date = '2016-05-07' WHERE date_courante.date = (select date from date_courante); 

--Tester le client 8 après avoir rendu son exemplaire si son emprunt à été supprimé après 2 mois
--update date_courante set date='2016-07-15' where date=(select date from date_courante );
