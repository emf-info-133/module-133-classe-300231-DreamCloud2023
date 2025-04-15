function login(loginInfo, successCallback, errorCallback) {
    $.ajax({
        url: 'http://localhost:8080/api/gateway/login',
        type: 'POST',
        contentType: 'application/json',
        data: JSON.stringify(loginInfo),
        xhrFields: { withCredentials: true }, 
        success: successCallback,
        error: errorCallback
    });
}

function logout(successCallback, errorCallback) {
    $.ajax({
        url: 'http://localhost:8080/api/gateway/logout',
        type: 'POST',
        xhrFields: { withCredentials: true }, 
        success: successCallback,
        error: errorCallback
    });
}

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

function addPost(post, successCallback, errorCallback) {
    $.ajax({
        url: 'http://localhost:8080/api/gateway/addPost',
        type: 'POST',
        contentType: 'application/json',
        data: JSON.stringify(post),
        xhrFields: { withCredentials: true }, 
        success: successCallback,
        error: errorCallback
    });
}

function getUserByUsername(username, successCallback, errorCallback) {
    $.ajax({
        url: `http://localhost:8080/api/gateway/getUser/${username}`,
        type: 'GET',
        xhrFields: { withCredentials: true },
        success: successCallback,
        error: errorCallback
    });
}

function getAllPosts(successCallback, errorCallback) {
    $.ajax({
        url: 'http://localhost:8080/api/gateway/getPosts',
        type: 'GET',
        xhrFields: { withCredentials: true },
        success: successCallback,
        error: errorCallback
    });
}

function getMessagesByPost(postId, successCallback, errorCallback) {
    $.ajax({
        url: `http://localhost:8080/api/gateway/getMessages/${postId}`,
        type: 'GET',
        xhrFields: { withCredentials: true },
        success: successCallback,
        error: errorCallback
    });
}

function addMsg(msg, successCallback, errorCallback) {
    $.ajax({
        url: 'http://localhost:8080/api/gateway/addMsg',
        type: 'POST',
        contentType: 'application/json',
        data: JSON.stringify(msg),
        xhrFields: { withCredentials: true },
        success: successCallback,
        error: errorCallback
    });
}

function deletePost(postId, successCallback, errorCallback) {
    $.ajax({
        url: '/api/gateway/deletePost',
        type: 'DELETE',
        contentType: 'application/json',
        data: JSON.stringify({ postId: postId }),
        xhrFields: { withCredentials: true },
        success: successCallback,
        error: errorCallback
    });
}

function deleteUser(user, successCallback, errorCallback) {
    $.ajax({
        url: 'http://localhost:8080/api/gateway/deleteUser',
        type: 'DELETE',
        contentType: 'application/json',
        data: JSON.stringify(user),
        xhrFields: { withCredentials: true },
        success: successCallback,
        error: errorCallback
    });
}

function getUsernameById(userId) {
    return new Promise((resolve, reject) => {
        $.ajax({
            url: `http://localhost:8080/api/gateway/getUserById/${userId}`,
            type: 'GET',
            xhrFields: { withCredentials: true },
            success: function (data) {
                resolve(data.username);
            },
            error: function (error) {
                reject(error);
            }
        });
    });
}

function getAllUsers(successCallback, errorCallback) {
    $.ajax({
        url: 'http://localhost:8080/api/gateway/getAllUsers',
        type: 'GET',
        xhrFields: { withCredentials: true },
        success: function(response) {
            successCallback(response);
        },
        error: function(xhr, status, error) {
            errorCallback(error);
        }
    });
}

function banUser(data, successCallback, errorCallback) {
    $.ajax({
        url: "http://localhost:8080/api/gateway/banUser",
        type: "POST",
        data: JSON.stringify(data),
        contentType: "application/json",
        xhrFields: { withCredentials: true },
        success: function(response) {
            successCallback(response);
        },
        error: function(xhr, status, error) {
            console.error("Erreur lors du bannissement de l'utilisateur:", error);
            errorCallback();
        }
    });
}
