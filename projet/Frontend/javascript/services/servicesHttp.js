// serviceHttp.js

/**
 * Méthode permettant de se connecter
 * 
 * @param {*} loginInfo Informations de connexion
 * @param {*} successCallback 
 * @param {*} errorCallback 
 */
function login(loginInfo, successCallback, errorCallback) {
    $.ajax({
        url: 'http://localhost:8080/api/gateway/login',
        type: 'POST',
        contentType: 'application/json',
        data: JSON.stringify(loginInfo),
        success: successCallback,
        error: errorCallback
    });
}


/**
 * Méthode permettant de se déconnecter
 * 
 * @param {*} successCallback 
 * @param {*} errorCallback 
 */
function logout(successCallback, errorCallback) {
    $.ajax({
        url: 'http://localhost:8080/api/gateway/logout',
        type: 'POST',
        success: successCallback,
        error: errorCallback
    });
}

/**
 * Méthode permettant d'ajouter un utilisateur
 * 
 * @param {*} user Informations de l'utilisateur
 * @param {*} successCallback 
 * @param {*} errorCallback 
 */
function addUser(user, successCallback, errorCallback) {
    $.ajax({
        url: 'http://localhost:8080/api/gateway/addUser',
        type: 'POST',
        contentType: 'application/json',
        data: JSON.stringify(user),
        success: successCallback,
        error: errorCallback
    });
}

/**
 * Méthode permettant d'ajouter un post
 * 
 * @param {*} post Informations du post
 * @param {*} successCallback 
 * @param {*} errorCallback 
 */
function addPost(post, successCallback, errorCallback) {
    $.ajax({
        url: 'http://localhost:8080/api/gateway/addPost',
        type: 'POST',
        contentType: 'application/json',
        data: JSON.stringify(post),
        success: successCallback,
        error: errorCallback
    });
}

/**
 * Récupère un utilisateur par son username
 * 
 * @param {*} username 
 * @param {*} successCallback 
 * @param {*} errorCallback 
 */
function getUserByUsername(username, successCallback, errorCallback) {
    $.ajax({
        url: `http://localhost:8080/api/gateway/getUser/${username}`,
        type: 'GET',
        success: successCallback,
        error: errorCallback
    });
}

function getAllPosts(successCallback, errorCallback) {
    $.ajax({
        url: 'http://localhost:8080/api/gateway/getPosts',
        type: 'GET',
        success: successCallback,
        error: errorCallback
    });
}

/**
 * Récupère tous les messages d’un post
 * 
 * @param {*} postId identifiant du post
 * @param {*} successCallback 
 * @param {*} errorCallback 
 */
function getMessagesByPost(postId, successCallback, errorCallback) {
    $.ajax({
        url: `http://localhost:8080/api/gateway/getMessages/${postId}`,
        type: 'GET',
        success: successCallback,
        error: errorCallback
    });
}



/**
 * Méthode permettant d'ajouter un message
 * 
 * @param {*} msg Informations du message
 * @param {*} successCallback 
 * @param {*} errorCallback 
 */
function addMsg(msg, successCallback, errorCallback) {
    $.ajax({
        url: 'http://localhost:8080/api/gateway/addMsg',
        type: 'POST',
        contentType: 'application/json',
        data: JSON.stringify(msg),
        success: successCallback,
        error: errorCallback
    });
}

/**
 * Méthode permettant de supprimer un post
 * 
 * @param {*} postId identifiant du post à supprimer
 * @param {*} successCallback 
 * @param {*} errorCallback 
 */
function deletePost(postId, successCallback, errorCallback) {
    $.ajax({
        url: '/api/gateway/deletePost',
        type: 'DELETE',
        contentType: 'application/json',
        data: JSON.stringify({ postId: postId }),
        success: successCallback,
        error: errorCallback
    });
}

/**
 * Méthode permettant de supprimer un utilisateur
 * 
 * @param {*} user Informations de l'utilisateur à supprimer
 * @param {*} successCallback 
 * @param {*} errorCallback 
 */
function deleteUser(user, successCallback, errorCallback) {
    $.ajax({
        url: 'http://localhost:8080/api/gateway/deleteUser',
        type: 'DELETE',
        contentType: 'application/json',
        data: JSON.stringify(user),
        success: successCallback,
        error: errorCallback
    });
}

function getUsernameById(userId) {
    console.log(userId);
    return new Promise((resolve, reject) => {
        $.ajax({
            url: `http://localhost:8080/api/gateway/getUserById/${userId}`,
            type: 'GET',
            success: function (data) {
                console.log(data); // Afficher la réponse pour vérifier sa structure
                resolve(data.username); // On retourne le nom d'utilisateur
            },
            error: function (error) {
                reject(error); // En cas d'erreur
            }
        });
    });
}

function getAllUsers(successCallback, errorCallback) {
    $.ajax({
        url: 'http://localhost:8080/api/gateway/getAllUsers', // Remplace par l'URL correcte
        type: 'GET',
        success: function(response) {
            // Si la requête réussit, on appelle le callback de succès
            successCallback(response);
        },
        error: function(xhr, status, error) {
            // En cas d'erreur, on appelle le callback d'erreur
            errorCallback(error);
        }
    });
}

function banUser(data, successCallback, errorCallback) {
    $.ajax({
        url: "http://localhost:8080/api/gateway/banUser",  // Remplace par l'URL correcte pour bannir un utilisateur
        type: "POST",
        data: JSON.stringify(data),  // Envoi des données (nom d'utilisateur et raison)
        contentType: "application/json",  // Assurez-vous que l'API accepte le format JSON
        success: function(response) {
            successCallback(response);  // Appeler la fonction de succès avec la réponse
        },
        error: function(xhr, status, error) {
            console.error("Erreur lors du bannissement de l'utilisateur:", error);
            errorCallback();  // Appeler la fonction d'erreur
        }
    });
}

/**
 * Méthode permettant de bannir un utilisateur
 * 
 * @param {*} data Données contenant le nom d'utilisateur et la raison du bannissement
 * @param {*} successCallback 
 * @param {*} errorCallback 
 */
function banUser(data, successCallback, errorCallback) {
    $.ajax({
        url: "http://localhost:8080/api/gateway/banUser",  // Remplace par l'URL correcte pour bannir un utilisateur
        type: "POST",
        data: JSON.stringify(data),  // Envoi des données (nom d'utilisateur et raison)
        contentType: "application/json",  // Assurez-vous que l'API accepte le format JSON
        success: function(response) {
            successCallback(response);  // Appeler la fonction de succès avec la réponse
        },
        error: function(xhr, status, error) {
            console.error("Erreur lors du bannissement de l'utilisateur:", error);
            errorCallback();  // Appeler la fonction d'erreur
        }
    });
}

