Préambule :
DULHOSTE Antoine 11606751
VIALATOUX Lucas 11600354


Section TP2 & TP3 :
Déploiement : https://192.168.75.26/api/v1/
Description : 
TP2 : Tout à été réalisé
TP3 : Il manque la gestion du cache


Section TP4 :
Déploiement : https://192.168.75.26/api/v2/
Description : 
TP4 : Il manque la modification d'un groupe (requête PUT)


Section TP5 & TP7 :
Déploiement : https://192.168.75.26
Description :
TP5 : Nous avons requêté votre API, 
toutes les requêtes ont été implémentées sauf la modification (requête PUT) d'un groupe et d'un billet (requête PUT).
TP7 : 
Mesures : déploiement sur Tomcat :
le temps de chargement de la page HTML initiale : 14.64ms
le temps d'affichage de l'app shell : 356.64ms
le temps d'affichage du chemin critique de rendu (CRP) : 359.64ms

Mesures : déploiement sur nginx
le temps de chargement de la page HTML initiale : 12.48ms | 14.75%
le temps d'affichage de l'app shell : 218.48ms | 38,74%
le temps d'affichage du chemin critique de rendu (CRP) : 221.48ms | 38.41%

Mesures : après modification : 
le temps de chargement de la page HTML initiale : 10.71ms | 14.18%
le temps d'affichage de l'app shell : 175.71ms | 80.42%
le temps d'affichage du chemin critique de rendu (CRP) : 178.71ms | 19.31%

Modification apportée :
Modification version jQuery, insertion CSS (car petit) dans notre SPA.