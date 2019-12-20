var URL_CONNEXION = "https://192.168.75.13/api/v2/users/login";
var URL_DECONNEXION = "https://192.168.75.13/api/v2/users/logout";
var URL_USERS = "https://192.168.75.13/api/v2/users";
var URL_GROUPES = "https://192.168.75.13/api/v2/groupes";



/*
*	Créer une liste
*/
function loadListe(mustMock){
	var listHtml='';
	if (mustMock != null){
		var liste = Mustache.render("{{{.}}}", mustMock);
		liste = liste.split(",");
		for (var elem in liste){
			var pieces = liste[elem].split("/");
			listHtml+=('<li><p>'+pieces[pieces.length-1]+'</p></li>');
		}
	}
	return listHtml;
}

/*
*	liste de billets
*/
function loadListeBlt(mustMock){
	var listHtml='';
	if (mustMock != null){
		var liste = Mustache.render("{{{billets}}}", mustMock);
		liste = liste.split(",");
		var cpt=0;
		var noBillet = true;
		if (liste.length > 0){
			for (var elem in liste){
				var pieces = liste[elem].split("/");
				if (pieces[pieces.length-1]!=""){
					noBillet = false;
				}
				listHtml+=('<li><input type="button" class="refGetBlt btn btn-info" name="'+cpt+'" value="Billet n°'+pieces[pieces.length-1]+'"></li>');
				cpt++;
			}
			listHtml+=('<p>Nombre de billets : <span id="nombreBillet">'+cpt+'</span></p>');
			if (noBillet==true){
				listHtml=('<h3>Aucun Billets</h3><p>Nombre de billes : <span id="nombreBillet">0</span></p>');
			}
		} else {
			listHtml=('<h3>Aucun Billets</h3>');
		}
	}
	return listHtml;
}

/*
*	liste de commentaires
*/
function loadListeCmt(mustMock,idDuBlt){
	var listHtml='<p name="'+idDuBlt+'" id="IDBillet">ID Billet actuel :'+idDuBlt+'</p>';
	if (mustMock != null){
		var liste = Mustache.render("{{{commentaires}}}", mustMock);
		liste = liste.split(",");
		var compt=0;
		var noComment = true;
		if (liste.length > 0){
			for (var elem in liste){
				var pieces = liste[elem].split("/");
				if (pieces[pieces.length-1]!=""){
					noComment = false;
				}
				listHtml+=('<li id="'+pieces[pieces.length-1]+'">'+pieces[pieces.length-1]
					+'<input type="button" class="refDeleteCmt btn btn-danger" name="'+pieces[pieces.length-1]+'" value="Supprimer"></li>');
				compt++;
			}
			listHtml+=('<p>Nombre de commentaires : <span id="nombreCommentaire">'+compt+'</span></p>');
			if (noComment==true){
				listHtml=('<h3>Aucun commentaires</h3><p name="'+idDuBlt+'" id="IDBillet">ID Billet actuel :'+idDuBlt+' </p>'
					+'<p>Nombre de commentaires : <span id="nombreCommentaire">0</span></p>');
			}
		} else {
			listHtml=('<h3>Aucun commentaires</h3><p name="'+idDuBlt+'" id="IDBillet">ID Billet actuel :'+idDuBlt+' </p>');
		}
	}
	return listHtml;
}

/*
*	liste de groupes
*/
function loadListeGrp(mustMock){
	if (mustMock != null){
		var liste = Mustache.render("{{{.}}}", mustMock);
		liste = liste.split(",");
		var listHtml='';
		var compteur=0;
		for (var elem in liste){
			var pieces = liste[elem].split("/");
			listHtml+=('<li id="'+pieces[pieces.length-1]+'">'
				+'<input type="button" class="refGetGrp btn btn-info" name="'+compteur+'" value="'+pieces[pieces.length-1]+'">'
				+'<input type="button" class="refDeleteGrp btn btn-danger" name="'+compteur+'" value="Supprimer"></li>');
			compteur++;
		}
	}
	return listHtml;
}


/*
*	Contrôleur d'affichage
*/
function afficher(idScript,donnees,idHtml,idObjet){
	var donneesScript = $(idScript).html();
	//Mustache.parse(donneesScript);
	switch(idScript){
		case '#grpList':
			var listHtml = loadListeGrp(donnees);
			if (donneesScript!=null){
				var rendered = Mustache.render(donneesScript, {listGroupe: listHtml} );
			}
			break;
		case '#usrList':
			var listHtml = loadListe(donnees);
			if (donneesScript!=null){
				var rendered = Mustache.render(donneesScript, {listUser: listHtml} );
			}
			break;
		case '#grpID':
			if (donneesScript!=null){
				var rendered = Mustache.render(donneesScript,donnees);
			}
			break;
		case '#billetID':
			var listHtml = loadListeBlt(donnees);
			if (donneesScript!=null){
				var rendered = Mustache.render(donneesScript, {bList: listHtml}  );
			}
			break;
		case '#bltID':
			if (donneesScript!=null){
				var rendered = Mustache.render(donneesScript,donnees);
			}
			break;
		case '#commentID':
			var listHtml = loadListeCmt(donnees,idObjet);
			if (donneesScript!=null){
				var rendered = Mustache.render(donneesScript, {cList: listHtml}  );
			}
			break;
	}

	if (rendered!=null){
		$(idHtml).load(idScript,function(){
			$(idHtml).html(rendered);
			if (idScript == '#billetID'){
				setEventGroupe();
			}else {
				deleteEventGroupe();
			}
			if (idScript == '#commentID'){
				setEventBillet();
			} else {
				deleteEventBillet();
			}
			if (idScript == '#grpList'){
				setEventListeGroupes();
			} else {

			}
			
		});
	}
	
}


/*
*	Contrôleur de template
*/
function gerer(hash,donnees,idObjet){
	var idScript="";
	var idHtml="";
	switch(hash){
		case '#groupes':
			idScript = '#grpList';
			idHtml = '#groupesList';
			break;
		case '#users':
			idScript = '#usrList';
			idHtml = '#usersList';
			break;
		case '#groupe':
			idScript = '#grpID';
			idHtml = '#divGrp';
			break;
		case '#billet':
			idScript = '#bltID';
			idHtml = '#divBlt';
			break;
	}
	if (idScript != "" && donnees != "" && idHtml != ""){
		afficher(idScript,donnees,idHtml,idObjet);
		if (idScript=="#grpID"){
			afficher('#billetID',donnees,'#groupeSuite',idObjet);
		}
		if (idScript=="#bltID"){
			afficher('#commentID',donnees,'#billetSuite',idObjet);
		}
		
	}
	
}


/*
*	Plier / déplier menu et vues
*/
window.addEventListener(
	'hashchange',
	() => {
		show(window.location.hash);
	}
);

/*
*	Plier / déplier menu et vues
*/
function show(hash) {

	$("#errMsgSpan").hide();

	$('.active')
	.removeClass('active')
	.addClass('inactive');
	$(hash)
	.removeClass('inactive')
	.addClass('active');
	
	if (hash != '#index'){
		var pseudoActuel = getCookie("pseudo");
		if (pseudoActuel == "" || pseudoActuel == null){
			window.location.hash="#index";
		}
	}
	
	if(hash=="#users"){
		submitUsers();
	} else if (hash == '#groupes'){
		getDatas(URL_GROUPES,'GET','responseGetGroupes','#groupes');
	}
	

	
	
};



/**
* Afficher liste de user
*/
function submitUsers(){
	getDatas(URL_USERS, 'GET', 'responseGetUsers','#users');
}


/**
* Fetch avec json
*/
function submitFetch(jsonToSend, URL, methodToUse, responseToUse,idObjet,idGrp){

	var options = {
		method: methodToUse,
		body: JSON.stringify(jsonToSend),
		credentials: 'include',
		headers: {
			'Content-Type': 'application/json',
			'Accept': 'application/json'
		}
	};

    fetch(URL,options)
    .then(function (response){
    		if (responseToUse=='connexion'){
    			responsePostLogin(response);
    		} else if(responseToUse=='deconnexion'){
    			responsePostLogout(response);
    		} else if(responseToUse=='responseDeleteGrp'){
    			responseDeleteGrp(response,idObjet);
    		} else if(responseToUse == 'responsePutGrp'){
    			responsePutGroupes(response);
    		} else if(responseToUse=='creationGroupe'){
    			responsePostGroupe(response,idObjet);
    		} else if(responseToUse=='creationBillet'){
    			responsePostBillet(response,idObjet,idGrp);
    		} else if(responseToUse=='responseDeleteCmt'){
    			responseDeleteCmt(response,idObjet);
    		} else if (responseToUse=='creationCommentaire'){
    			responsePostCommentaire(response);
    		} else if (responseToUse=='responseDeleteBlt'){
    			responseDeleteBlt(response,idObjet);
    		}
    	})
	.catch(function(error){
			if (responseToUse=='connexion'){
    			responsePostLogin(error);
    		} else if(responseToUse=='deconnexion'){
    			responsePostLogout(error);
    		} else if(responseToUse=='responseDeleteGrp'){
    			responseDeleteGrp(error,idObjet);
    		} else if(responseToUse == 'responsePutGrp'){
    			responsePutGroupes(error)
    		} else if(responseToUse=='creationGroupe'){
    			responsePostGroupe(error);
    		} else if(responseToUse=='creationBillet'){
    			responsePostBillet(error);
    		} else if(responseToUse=='responseDeleteCmt'){
    			responseDeleteCmt(error);
    		} else if (responseToUse=='creationCommentaire'){
    			responsePostCommentaire(error);
    		} else if (responseToUse=='responseDeleteBlt'){
    			responseDeleteBlt(error);
    		}
		});
}

/**
* Fetch sans json
*/
function getDatas(URL, methodToUse, responseToUse,hash,idObjet){
	var options = {
		method: methodToUse,
		credentials: 'include',
		headers: {
			'Content-Type': 'application/json',
			'Accept': 'application/json',
		}
	};
	
    fetch(URL,options)
    .then(function(response) {
    	if (responseToUse=='getGroupes'){
			responseGetGroupes(response);
		} else if (responseToUse == 'responseGetGrp'){
			responseGetUnGroupe(response);
		} else if (responseToUse == 'responseGetBlt'){
			responseGetUnBillet(response);
		}
	  	return response.json();
	}).then(function(parsedJson) {
		//console.log(parsedJson);
	  	gerer(hash,parsedJson,idObjet);
	})
	.catch(function(error){
			if (responseToUse=='getGroupes'){
    			responseGetGroupes(error);
    		} else if (responseToUse == 'responseGetGrp'){
    			responseGetUnGroupe(error);
    		} else if (responseToUse == 'responseGetBlt'){
				responseGetUnBillet(error);
			}
		});
}

/**
* Empty every template
*/
function emptyTemplates(){
	//liste groupes
	$('#groupesList').empty();
	//un groupe
	$('#divGrp').empty();
	$('#groupeSuite').empty();
	//un billet
	$('#divBlt').empty();
	$('#billetSuite').empty();
	//liste de users
	$('#usersList').empty();
}

/**
* Connexion
*/
function responsePostLogin(response) {
	if (response.status == 201){
		$("#errMsgSpan").hide();
		window.location.hash ='#groupes';
	}else if (response.status == 400){
		$('#errMsgSpan').text("Pas de paramètres acceptables dans la requête");
    	$("#errMsgSpan").show();
	}else if (response.status == 415){
		$('#errMsgSpan').text("Format de requête incorrect ou non implémenté");
    	$("#errMsgSpan").show();
	}
}

/**
* Déconnexion
*/
function responsePostLogout(response) {
	if (response.status == 204){
		$("#errMsgSpan").hide();
		location.reload();
	}else if (response.status == 401){
		$('#errMsgSpan').text("Veuillez vous authentifier");
    	$("#errMsgSpan").show();
    	location.reload();
	}
}

/**
* Création groupe
*/
function responsePostGroupe(response,idObjet) {
	if(response.status == 201){
		$("#errMsgSpan").hide();
		$('#inputGroupe').val("");
		window.location.hash ='#groupe';
		getDatas(URL_GROUPES+"/"+idObjet,'GET','responseGetGrp','#groupe');
	}else if (response.status == 400){
		$('#errMsgSpan').text("Pas de paramètres acceptables dans la requête");
    	$("#errMsgSpan").show();
	}else if (response.status == 401){
		location.reload();
		$('#errMsgSpan').text("Veuillez vous authentifier");
    	$("#errMsgSpan").show();
    }else if (response.status == 415){
		$('#errMsgSpan').text("Format de requête incorrect ou non implémenté");
    	$("#errMsgSpan").show();
	}
}

/**
* Création billet
*/
function responsePostBillet(response,nbBillet,idGrp) {
	if(response.status == 201){
		$("#errMsgSpan").hide();
		$('#titreBillet').val("");
		$('#contenuBillet').val("");
		URL_MODIFIED=URL_GROUPES+"/"+idGrp+"/billets/"+parseInt(nbBillet);
		getDatas(URL_MODIFIED,'GET','responseGetBlt','#billet',nbBillet);
		nbBillet+=1;
	}else if (response.status == 400){
		$('#errMsgSpan').text("Pas de paramètres acceptables dans la requête");
    	$("#errMsgSpan").show();
	}else if (response.status == 401){
		location.reload();
		$('#errMsgSpan').text("Veuillez vous authentifier");
    	$("#errMsgSpan").show();
    }else if (response.status == 403){
		$('#errMsgSpan').text("Utilisateur non membre du groupe");
    	$("#errMsgSpan").show();
	}else if (response.status == 415){
		$('#errMsgSpan').text("Format de requête incorrect ou non implémenté");
    	$("#errMsgSpan").show();
	}
}

/**
* Création commentaire
*/
function responsePostCommentaire(response) {
	if(response.status == 201){
		$('#newComment').val("");
		$('#errMsgSpan').text("Commentaire créé avec succès");
    	$("#errMsgSpan").show();
	}else if (response.status == 400){
		$('#errMsgSpan').text("Pas de paramètres acceptables dans la requête");
    	$("#errMsgSpan").show();
	}else if (response.status == 401){
		location.reload();
		$('#errMsgSpan').text("Veuillez vous authentifier");
    	$("#errMsgSpan").show();
    }else if (response.status == 403){
		$('#errMsgSpan').text("Utilisateur non membre du groupe");
    	$("#errMsgSpan").show();
	}else if (response.status == 415){
		$('#errMsgSpan').text("Format de requête incorrect ou non implémenté");
    	$("#errMsgSpan").show();
	}
}


/**
* Delete groupe
*/
function responseDeleteGrp(response,idGrp){
	if (response.status==204){
		$("#errMsgSpan").hide();
		$("#"+idGrp).hide();
		window.location.hash ='#groupes';
	}else if (response.status == 401){
		location.reload();
		$('#errMsgSpan').text("Veuillez vous authentifier");
    	$("#errMsgSpan").show();
    }else if (response.status == 403){
		$('#errMsgSpan').text("Utilisateur non membre du groupe");
    	$("#errMsgSpan").show();
	} else if (response.status == 404){
		$('#errMsgSpan').text("Groupe non trouvé");
    	$("#errMsgSpan").show();
	}
}

/**
* Delete comentaire
*/
function responseDeleteCmt(response,idCmt){
	if (response.status==204){
		$("#errMsgSpan").hide();
		$("#"+idCmt).hide();
		window.location.hash ='#billet';
	}else if (response.status == 401){
		location.reload();
		$('#errMsgSpan').text("Veuillez vous authentifier");
    	$("#errMsgSpan").show();
    }else if (response.status == 403){
		$('#errMsgSpan').text("Utilisateur non membre du groupe");
    	$("#errMsgSpan").show();
	} else if (response.status == 404){
		$('#errMsgSpan').text("Commentaire non trouvé");
    	$("#errMsgSpan").show();
	}
}

/**
* Delete billet
*/
function responseDeleteBlt(response,idBlt){
	if (response.status==204){
		$("#errMsgSpan").hide();
		document.getElementById('bltList').children[idBlt].style.display="none";
		window.location.hash ='#groupe';
	}else if (response.status == 401){
		location.reload();
		$('#errMsgSpan').text("Veuillez vous authentifier");
    	$("#errMsgSpan").show();
    }else if (response.status == 403){
		$('#errMsgSpan').text("Utilisateur non membre du groupe");
    	$("#errMsgSpan").show();
	} else if (response.status == 404){
		$('#errMsgSpan').text("Commentaire non trouvé");
    	$("#errMsgSpan").show();
	}
}


/**
* Get listes groupe
*/
function responseGetGroupes(response) {
	if (response.status == 200){
		$("#errMsgSpan").hide();
	}else if (response.status == 401){
		location.reload();
		$('#errMsgSpan').text("Veuillez vous authentifier");
    	$("#errMsgSpan").show();
	} else if (response.status == 406){
		$('#errMsgSpan').text("Format de réponse demandé incorrect ou indisponible");
    	$("#errMsgSpan").show();
	}
}

/**
* Get un groupe
*/
function responseGetUnGroupe(response){
	if (response.status == 200){
		$("#errMsgSpan").hide();
		window.location.hash ='#groupe';
	}else if (response.status == 401){
		location.reload();
		$('#errMsgSpan').text("Veuillez vous authentifier");
    	$("#errMsgSpan").show();
	}else if (response.status == 403){
		$('#errMsgSpan').text("Vous n'êtes pas membre du groupe");
    	$("#errMsgSpan").show();
	} else if (response.status == 404){
		$('#errMsgSpan').text("Groupe non trouvé");
    	$("#errMsgSpan").show();
	} else if (response.status == 406){
		$('#errMsgSpan').text("Format de réponse demandé incorrect ou indisponible");
    	$("#errMsgSpan").show();
	}
}

/**
* Get un billet
*/
function responseGetUnBillet(response){
	if (response.status == 200){
		$("#errMsgSpan").hide();
		window.location.hash ='#billet';
	}else if (response.status == 401){
		location.reload();
		$('#errMsgSpan').text("Veuillez vous authentifier");
    	$("#errMsgSpan").show();
	}else if (response.status == 403){
		$('#errMsgSpan').text("Vous n'êtes pas membre du groupe");
    	$("#errMsgSpan").show();
	} else if (response.status == 404){
		$('#errMsgSpan').text("Billet non trouvé");
    	$("#errMsgSpan").show();
	} else if (response.status == 406){
		$('#errMsgSpan').text("Format de réponse demandé incorrect ou indisponible");
    	$("#errMsgSpan").show();
	}
}

/**
* Put sur un groupe
*/
function responsePutGroupes(response) {
	if (response.status == 204){
		$('#errMsgSpan').text("Modification prise en compte");
    	$("#errMsgSpan").show();
	}else if (response.status == 400){
		$('#errMsgSpan').text("Pas de paramètres acceptables dans la requête");
    	$("#errMsgSpan").show();
	}else if (response.status == 401){
		location.reload();
		$('#errMsgSpan').text("Veuillez vous authentifier");
    	$("#errMsgSpan").show();
	} else if (response.status == 404){
		$('#errMsgSpan').text("Groupe non trouvé");
    	$("#errMsgSpan").show();
	} else if (response.status == 415){
		$('#errMsgSpan').text("Format de requête incorrect ou non implémenté");
    	$("#errMsgSpan").show();
	}
}

/**
* Get listes users
*/
function responseGetUsers(response) {
	if (response.status == 200){
		$("#errMsgSpan").hide();
	}else if (response.status == 401){
		location.reload();
		$('#errMsgSpan').text("Veuillez vous authentifier");
    	$("#errMsgSpan").show();
	} else if (response.status == 403){
		$('#errMsgSpan').text("Utilisateur non membre du groupe");
    	$("#errMsgSpan").show();
	} else if (response.status == 406){
		$('#errMsgSpan').text("Format de réponse demandé incorrect ou indisponible");
    	$("#errMsgSpan").show();
	}
}

/*
*	Pouvoir set un cookie
*/
function setCookie(cname, cvalue, exdays) {
  var d = new Date();
  d.setTime(d.getTime() + (exdays*24*60*60*1000));
  var expires = "expires="+ d.toUTCString();
  document.cookie = cname + "=" + cvalue + ";" + expires + ";path=/";
}


/*
*	Récupérer un cookie
*/
function getCookie(cname) {
  var name = cname + "=";
  var decodedCookie = decodeURIComponent(document.cookie);
  var ca = decodedCookie.split(';');
  for(var i = 0; i <ca.length; i++) {
    var c = ca[i];
    while (c.charAt(0) == ' ') {
      c = c.substring(1);
    }
    if (c.indexOf(name) == 0) {
      return c.substring(name.length, c.length);
    }
  }
  return "";
}