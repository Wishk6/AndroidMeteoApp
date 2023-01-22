## Notes
Nous avons eu un problème avec l'api météo que nous utilisions et le systeme de coordonnées d'android
<br></br>
Les 2 systèmes n'utilisent apparement pas le même systeme pour les coordoonées.
<br></br>
J'obtiens par exemple '37.4219983, -122.084' avec LocationManager et  l'api météo me renvoie :
<br></br>
```text
Please specify a latitude between 41.3 and 51.9 and a longitude between -5.2 and 10.7
```
je n'ai pas réussi à trouver une solution pour convertir les coordonnées. 
<br></br>
On ne peut donc pas afficher la position actuelle de l'utilisateur
<br></br>
(Tout le fonctionnement est codé, il suffit de trouver une solution pour convertir les coordonnées,
<br></br>
on pourrait également essayer de trouver comment convertir les coordonnées de notre api en une ville mais je pense que c'est impossible avec des coordoonéees.. )
<br></br>
<br></br>
## Fonctionnalités
```text
[X] Utilisation de l'Api Preference
<br>
[ ] Ecriture/lecture dans un Fichier
<br>
[ ] Utilisation de SQLite
<br>
[ ] Utilisation de Room
<br>
[X] Utilisation de Firebase
<br>
[X] Nombre d'activités ou fragment supérieur ou égal à 3
<br>
[X] Gestion du bouton Back (message pour confirmer que l'on veut réellement quitter s'application)
<br>
[ ] L'affichage d'une liste avec son adapter
<br>
[ ] L'affichage d'une liste avec un custom adapter (avec gestion d’événement)
<br>
[X] La pertinence d'utilisation des layouts (L'application doit être responsive et supporter: portrait/paysage et tablette)
<br>
[ ] L'utilisation de d’événement améliorant l'ux (pex: swipe). Préciser :
<br>
[ ] La réalisation de composant graphique custom (Paint 2D, Calendrier,...) Préciser :
<br>
[ ] Les taches en background (codage du démarrage d'un thread)
<br>
[ ] Le codage d'un menu (contextuel ou non, utilisation d'un menu en resource XML)
<br>
[X] L'application de pattern (Reactive programming, singleton, MVC,...) Liste :

MVC (Les fragments sont les Vues, l'activité est le Controller unique et les modèles sont nos différents services) 
```
