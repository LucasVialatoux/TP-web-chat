
/*
*	Mock-objects MUSTACHE
*/
var users = {
	liens : [
	"http://localhost:8080/users/toto",
	"http://localhost:8080/users/titi"
	]
};

var groupes ={
	liens : [
	"http://localhost:8080/groupes/M1IF03",
	"http://localhost:8080/groupes/M1IF04"
	]
};

var groupeid ={
	nom: "M1IF03",
	description: "UneDescription",
	proprietaire: "UnProprio",
	membres: [
	"membre1"
	],
	billets: [
	"http://localhost:8080/groupes/M1IF03/billets/billet1",
	"http://localhost:8080/groupes/M1IF03/billets/billet2"
	]
};

var billets ={
	liens : [
	"http://localhost:8080/groupes/M1IF03/billets/billet1",
	"http://localhost:8080/groupes/M1IF04/billets/billet1",
	"http://localhost:8080/groupes/M1IF04/billets/billet3"
	]
};

var billetid = {
  titre: "billet1",
  contenu: "ContenuBillet",
  auteur: "UnProprio",
  commentaires: [
    "http://localhost:8080/groupes/M1IF03/billets/0/commentaires/0",
    "http://localhost:8080/groupes/M1IF03/billets/0/commentaires/1"
  ]
};

var commentaires = {
	liens : [
	"http://localhost:8080/groupes/M1IF03/billets/0/commentaires/0",
	"http://localhost:8080/groupes/M1IF03/billets/0/commentaires/1"
	]
};

var commentaireid = {
  auteur: "UnProprio",
  texte: "texteArallonge"
};

