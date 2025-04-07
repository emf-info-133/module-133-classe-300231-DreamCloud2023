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
        url: '/api/gateway/logout',
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
        url: '/api/gateway/addUser',
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
        url: '/api/gateway/addPost',
        type: 'POST',
        contentType: 'application/json',
        data: JSON.stringify(post),
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
        url: '/api/gateway/addMsg',
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
        url: '/api/gateway/deleteUser',
        type: 'DELETE',
        contentType: 'application/json',
        data: JSON.stringify(user),
        success: successCallback,
        error: errorCallback
    });
}
