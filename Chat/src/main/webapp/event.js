var index=index||{};
var firstLoad=function (){
    var isfirst=index.firstLoad===undefined;
    index.firstLoad=false;
    return isfirst;
}

/*
*   Instanciation des events
*/
$(function () {
    /**
    * Connexion et déconnexion ont besoin d'être
    * load qu'une seule fois
    */
    if(!firstLoad()){
        return;
    }

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
});

/*
*   Instanciation des events de la liste des groupes
*/
function setEventListeGroupes(){
    $(function () {
        /**
         * Gérer le submit du formulaire de création de groupe
         */
        $("#formGroupes").submit(function(event){
            event.preventDefault();
            var jsonToSend = {
                nom: $('#inputGroupe').val()
            };
            submitFetch(jsonToSend,URL_GROUPES,'POST','creationGroupe',$('#inputGroupe').val());
        });

        /**
         * Event du bouton de get à un groupe
         */

        $( "#groupesList" ).on( "click",".refGetGrp", function(event) {
            event.preventDefault();
            var idCliqued=event.target.name;
            var nomGrp = $("#groupesList li").get(idCliqued).id;
            var URL_MODIFIED = URL_GROUPES+"/"+nomGrp;
            getDatas(URL_MODIFIED,'GET','responseGetGrp','#groupe');
        });
        

        /**
         * Event du bouton de suppression d'un groupe
         */
        $("#groupesList").on("click",".refDeleteGrp",function(event) {
            event.preventDefault();
            var idCliqued=event.target.name;
            var jsonToSend = {};
            var nomGrp = $("#groupesList li").get(idCliqued).id;
            var URL_MODIFIED = URL_GROUPES+"/"+nomGrp;
            submitFetch(jsonToSend,URL_MODIFIED,'DELETE','responseDeleteGrp',nomGrp);
        });

    });
}

/*
*   Déinstanciation des events de la liste des groupes
*/
function deleteEventListeGroupes(){
    console.log("Event groupes DELETE");
    $(function () {
        /**
         * Gérer le submit du formulaire de création de groupe
         */
        $("#formGroupes").off("submit");

        /**
         * Event du bouton de get à un groupe
         */

        $( "#groupesList" ).off( "click",".refGetGrp");
        

        /**
         * Event du bouton de suppression d'un groupe
         */
        $("#groupesList").off("click",".refDeleteGrp");

    });
}
	

/*
*   Instanciation des events d'un groupe
*/
function setEventGroupe(){
    $(function () {
        /**
         * Event du bouton d'ajout d'un membre à un groupe
         */
        $("#groupesList").on("click",".refDesinGrp",function(event) {
            event.preventDefault();
            var idCliqued=event.target.name;
            var nomGrp = $("#groupesList li").get(idCliqued).id;
            var jsonToSend = {
                nom: nomGrp
            };
            var URL_MODIFIED = URL_GROUPES+"/"+nomGrp;
            submitFetch(jsonToSend,URL_MODIFIED,'PUT','responseDeleteGrp',nomGrp);
        });

        /**
         * Event du bouton de suppression d'un membre à un groupe
         */
        $("#groupesList").on("click",".refInsGrp",function(event) {
            event.preventDefault();
            var idCliqued=event.target.name;
            var nomGrp = $("#groupesList li").get(idCliqued).id;
            var pseudo = getCookie("pseudo");
            var jsonToSend = {
                nom: nomGrp,
                membres:pseudo
            };
            var URL_MODIFIED = URL_GROUPES+"/"+nomGrp;
            ubmitFetch(jsonToSend,URL_MODIFIED,'PUT','responsePutGrp',nomGrp);
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
            submitFetch(jsonToSend,URL_MODIFIED,'POST','creationBillet',$('#nombreBillet').html(),nomGrp);
        });

        /**
         * Event du bouton de get à un billet
         */
        $("#bltList").on("click",".refGetBlt",function(event) {
            event.preventDefault();
            var nomBlt=event.target.name;
            var nomGrp=$('#nomGroupeID').html();
            if (nomBlt != "" && nomBlt != null && nomGrp != "" && nomGrp != null){
                var URL_MODIFIED = URL_GROUPES+"/"+nomGrp+"/billets/"+nomBlt;
                getDatas(URL_MODIFIED,'GET','responseGetBlt','#billet',nomBlt);
                
            } else {
                $('#errMsgSpan').text("Un problème est survenu");
                $("#errMsgSpan").show();
            }
            
        });
    });

}

/*
*   Déinstanciation des events d'un groupe
*/
function deleteEventGroupe(){
    $(function () {
        /**
         * Event du bouton d'ajout d'un membre à un groupe
         */
        $("#groupesList").off("click",".refDesinGrp");

        /**
         * Event du bouton de suppression d'un membre à un groupe
         */
        $("#groupesList").off("click",".refInsGrp");

        /**
         * Gérer le submit du formulaire de création de billet
         */
        $("#formBillet").off("submit");

        /**
         * Event du bouton de get à un billet
         */
        $("#bltList").off("click",".refGetBlt");
    });

}

/*
*   Instanciation des events d'un billet
*/
function setEventBillet(){
    $(function () {
        /**
         * Event du bouton de suppression d'un commentaire
         */
        $("#commentList").on("click",".refDeleteCmt",function(event) {
            event.preventDefault();
            var nomCmt=event.target.name;
            var jsonToSend = {};
            var nomBlt = $('#IDBillet').attr('name');
            var nomGrp = $("#nomGroupeID").html();
            if (nomBlt != "" && nomBlt != null && nomGrp != "" && nomGrp != null && nomCmt != "" && nomCmt != null){
                var URL_MODIFIED = URL_GROUPES+"/"+nomGrp+"/billets/"+nomBlt+"/commentaires/"+nomCmt;
                submitFetch(jsonToSend,URL_MODIFIED,'DELETE','responseDeleteCmt',nomCmt);
            } else {
                $('#errMsgSpan').text("Un problème est survenu");
                $("#errMsgSpan").show();
            }
            
        });

        /**
         * Gérer le submit du formulaire de création de commentaire
         */
        $("#formCommentaire").submit(event =>{
            event.preventDefault();
            var pseudoCookie = getCookie("pseudo");
            var jsonToSend = {
                auteur: pseudoCookie,
                texte: $('#newComment').val()
            };
            var nomBlt = $('#IDBillet').attr('name');
            var nomGrp = $('#nomGroupeID').html();
            var URL_MODIFIED = URL_GROUPES+'/'+nomGrp+'/billets/'+nomBlt+'/commentaires';
            submitFetch(jsonToSend,URL_MODIFIED,'POST','creationCommentaire');
        });

        /**
         * Event du bouton de suppression d'un billet
         */
        $("#suppressionBlt").on("click",function(event) {
            event.preventDefault();
            console.log("clic suppression actif");
            var jsonToSend = {};
            var nomBlt = $('#IDBillet').attr('name');
            var nomGrp = $("#nomGroupeID").html();
            if (nomBlt != "" && nomBlt != null && nomGrp != "" && nomGrp != null){
                var URL_MODIFIED = URL_GROUPES+"/"+nomGrp+"/billets/"+nomBlt;
                submitFetch(jsonToSend,URL_MODIFIED,'DELETE','responseDeleteBlt',nomBlt);
            } else {
                $('#errMsgSpan').text("Un problème est survenu");
                $("#errMsgSpan").show();
            }
        });
    });
}

/*
*   Déinstanciation des events d'un bilet
*/
function deleteEventBillet(){
    $(function () {
        /**
         * Event du bouton de suppression d'un commentaire
         */
        $("#commentList").off("click",".refDeleteCmt");

        /**
         * Gérer le submit du formulaire de création de commentaire
         */
        $("#formCommentaire").off("submit");

        /**
         * Event du bouton de suppression d'un billet
         */
        $("#suppressionBlt").off("click");
    });

}