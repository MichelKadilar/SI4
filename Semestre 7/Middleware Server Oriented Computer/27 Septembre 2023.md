Slide 40+ :

Dynamic Class Loading : la JVM reçoit les octets de la classe compilée au fur et à mesure

Une JVM peut générer le .class d'un stub à la volée, mais uniquement si le client connait les interfaces RMI et les .class de ces interfaces

Slide 49 :

le registre RMI peut récupérer les .class des interfaces RMI depuis le serveur HTTP si le registre RMI ne les connait pas 

Slide 50 :

Pour protéger la JVM d'exécuter du code malicieux via les .class des stubs, on peut mettre en place un système de permission (première chose à exécuter dans le main()).

Mais RMI a déjà mis en place des mécanismes de sécurité à ce niveau là.

Slide 51 :


