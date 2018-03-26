function Login() {
    $.ajax({
        url: 'http://localhost:8080/Login',
        method: 'POST',
        dataType: 'json',
        crossDomain: true,
        data: JSON.stringify({
            memberName: $('#memberName').val(),
            password: $('#pwd').val()
        }),
        contentType: 'application/json',
        mimeType: 'application/json',
        error: function(data, status, er) {
            alert('status: ' + status);
        }
    })
        .then(function handleFeedResponse(response) {
            window.location.replace('member_page.html?id=' + response.id);
        })
        .catch(function handleFeedError(response) {
            console.log(response);
        });
}
