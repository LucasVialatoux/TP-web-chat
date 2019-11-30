var URL_CONNEXION = "https://192.168.75.13/api/v2/users/login";
var URL_DECONNEXION = "https://192.168.75.13/api/v2/users/logout";
var URL_USERS = "https://192.168.75.13/api/v2/users";
var URL_GROUPES = "https://192.168.75.13/api/v2/groupes";
var isLoaded = false;

/*
*	Créer une liste
*/
function loadListe(mustMock){
	if (mustMock != null){
		var liste = Mustache.render("{{{.}}}", mustMock);
		liste = liste.split(",");
		var listHtml='';
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
	console.log(mustMock);
	if (mustMock != null){
		var liste = Mustache.render("{{{billets}}}", mustMock);
		console.log(liste.length);
		liste = liste.split(",");
		var listHtml='';
		var cpt=0;
		if (liste.length > 0){
			for (var elem in liste){
				var pieces = liste[elem].split("/");
				listHtml+=('<li><input type="button" class="btn btn-info" name="'+cpt+'" value="'+pieces[pieces.length-1]+'">'
					+'<input type="button" class="btn btn-warning" name="'+cpt+'" value="Modifier">'
					+'<input type="button" class="btn btn-danger" name="'+cpt+'" value="Supprimer"></li>');
				cpt++;
			}
		} else {
			listHtml+=('<h3>Aucun Billets</h3>');
		}
	}
	return listHtml;
}

/*
*	liste de commentaires
*/
function loadListeCmt(mustMock){
	if (mustMock != null){
		var liste = Mustache.render("{{{.}}}", mustMock);
		liste = liste.split(",");
		var listHtml='';
		var compt=0;
		for (var elem in liste){
			var pieces = liste[elem].split("/");
			listHtml+=('<li>'+pieces[pieces.length-1]
				+'<input type="button" class="btn btn-warning" name="'+compt+'" value="Modifier">'
				+'<input type="button" class="btn btn-danger" name="'+compt+'" value="Supprimer"></li>');
			compt++;
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
function afficher(idScript,donnees,idHtml){
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
			var listHtml = loadListeCmt(donnees,'commentaires');
			if (donneesScript!=null){
				var rendered = Mustache.render(donneesScript, {cList: listHtml}  );
			}
			break;
	}
	//if (idScript=='#grpList'){
		if (rendered!=null){
			$(idHtml).load(idScript,function(){
				$(idHtml).html(rendered);
				eventToLoad();
			});
		}
	/*} else {
		$(idHtml).empty().html(rendered);
	}
	if (!isLoaded){
		
	}*/
}


/*
*	Contrôleur de template
*/
function gerer(hash,donnees){
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
		afficher(idScript,donnees,idHtml);
		if (idScript=="#grpID"){
			afficher('#billetID',donnees,'#groupeSuite');
		}
		if (idScript=="#bltID"){
			afficher('#commentID',donnees,'#billetSuite');
		}
		
	} else {
		//window.location.hash="#index";
	}
	
}


/*
*	Plier / déplier menu et vues
*/
window.addEventListener(
	'hashchange',
	() => {
		show(window.location.hash);
		$("#errMsgSpan").hide();
	}
);

/*
*	Plier / déplier menu et vues
*/
function show(hash) {
	$('.active')
	.removeClass('active')
	.addClass('inactive');
	$(hash)
	.removeClass('inactive')
	.addClass('active');

	if(hash=="#users"){
		submitUsers();
	} else if (hash == '#groupes'){
		getDatas(URL_GROUPES,'GET','responseGetGroupes','#groupes');
	}
};



/*
*	Instanciation des events
*/
$(function () {
	/**
	 * Gérer le submit du formulaire de connexion
	 */
	$("#formInit").submit(event =>{
		event.preventDefault();
		var inputPseudo = $('#inputPseudo').val();
		var jsonToSend = {
			pseudo: inputPseudo
		};
		setCookie("pseudo", inputPseudo, 31);
		submitFetch(jsonToSend,URL_CONNEXION,'POST','connexion');
		$('#inputPseudo').val('');
	});

	/**
	 * Gérer le submit du formulaire de déconnexion
	 */
	$("#formDeco").submit(event =>{
		event.preventDefault();
		var isChecked = $('#checkUser').is(':checked');
		var jsonToSend = {};
		if (isChecked){
			var pseudoCookie = getCookie("pseudo");
			setCookie("pseudo", pseudoCookie, 0);
			submitFetch(jsonToSend,URL_DECONNEXION,'POST','deconnexion');
			emptyTemplates();
		} else {
			$('#errMsgSpan').text("Merci de confirmer pour vous déconnecter");
    		$("#errMsgSpan").show();
		}
		
		
	});

	/**
	 * Gérer le submit du formulaire de création de groupe
	 */
	$("#formGroupes").submit(event =>{
		event.preventDefault();
		var jsonToSend = {
			nom: $('#inputGroupe').val()
		};
		submitFetch(jsonToSend,URL_GROUPES,'POST','creationGroupe',$('#inputGroupe').val());
	});

	/**
	 * Gérer le submit du formulaire de création de billet
	 */
	$("#formBillet").submit(event =>{
		event.preventDefault();
		var pseudoCookie = getCookie("pseudo");
		var jsonToSend = {
			titre: $('#titreBillet').val(),
			contenu: $('#contenuBillet').val(),
			auteur: pseudoCookie
		};
		var nomGrp = $('#nomGroupeID').html();
		var URL_MODIFIED = URL_GROUPES+'/'+nomGrp+'/billets';
		submitFetch(jsonToSend,URL_MODIFIED,'POST','creationBillet',$('#titreBillet').val(),nomGrp);
	});
});

/**
* Afficher liste de user
*/
function submitUsers(){
	getDatas(URL_USERS, 'GET', 'responseGetUsers','#users');
}

/*
*	Fonction pour instancer certains events
*/
function eventToLoad(){
	isLoaded = true;
	/**
     * Event du bouton de get à un groupe
     */
    $("#groupesList li .refGetGrp").bind("click",function(event) {
    	var idCliqued=event.target.name;
    	var nomGrp = $("#groupesList li").get(idCliqued).id;
        var URL_MODIFIED = URL_GROUPES+"/"+nomGrp;
        getDatas(URL_MODIFIED,'GET','responseGetGrp','#groupe');
    });


	/**
     * Event du bouton d'inscription à un groupe
     */
    $("#groupesList li .refInsGrp").bind("click",function(event) {
    	var idCliqued=event.target.name;
    	var nomGrp = $("#groupesList li").get(idCliqued).id;
    	var pseudo = getCookie("pseudo");
    	var jsonToSend = {
    		nom: nomGrp,
    		membres:pseudo
    	};
        var URL_MODIFIED = URL_GROUPES+"/"+nomGrp;
        submitFetch(jsonToSend,URL_MODIFIED,'PUT','responsePutGrp',nomGrp);
    });


    /**
     * Event du bouton de désinscription à un groupe
     */
    $("#groupesList li .refDesinGrp").bind("click",function(event) {
    	var idCliqued=event.target.name;
    	var nomGrp = $("#groupesList li").get(idCliqued).id;
    	var jsonToSend = {
    		nom: nomGrp
    	};
        var URL_MODIFIED = URL_GROUPES+"/"+nomGrp;
        submitFetch(jsonToSend,URL_MODIFIED,'PUT','responseDeleteGrp',nomGrp);
    });

    /**
     * Event du bouton de suppression d'un groupe
     */
    $("#groupesList li .refDeleteGrp").bind('click',function(event) {
    	var idCliqued=event.target.name;
    	var jsonToSend = {};
    	var nomGrp = $("#groupesList li").get(idCliqued).id;
        var URL_MODIFIED = URL_GROUPES+"/"+nomGrp;
		submitFetch(jsonToSend,URL_MODIFIED,'DELETE','responseDeleteGrp',nomGrp);
    });


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
    		//console.log(response);
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
    		}
    	})
	.catch(function(error){
			//console.log(error);
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
    		}
		});
}

/**
* Fetch sans json
*/
function getDatas(URL, methodToUse, responseToUse,hash){
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
		}
	  	return response.json();
	}).then(function(parsedJson) {
		//console.log(parsedJson);
	  	gerer(hash,parsedJson);
	})
	.catch(function(error){
			if (responseToUse=='getGroupes'){
    			responseGetGroupes(error);
    		} else if (responseToUse == 'responseGetGrp'){
    			responseGetUnGroupe(error);
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
function responsePostBillet(response,idObjet,idGrp) {
	if(response.status == 201){
		$("#errMsgSpan").hide();
		$('#titreBillet').val("");
		$('#contenuBillet').val("");
		window.location.hash ='#billet';
		getDatas(URL_GROUPES+"/"+idGrp+"/"+idObjet,'GET','responseGetBlt','#billet');
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