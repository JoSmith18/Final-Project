function signUp() {
    $.ajax({
        url: 'http://localhost:8080/SignUp',
        method: 'POST',
        dataType: 'json',
        crossDomain: true,
        data: JSON.stringify({
            memberName: $('#memberName').val(),
            age: $('#age option:selected').text(),
            email: $('#email').val(),
            password: $('#pwd').val()
        }),
        contentType: 'application/json',
        mimeType: 'application/json',
        success: function(data) {
            alert(data.success + ' ' + data.message);
            //commit(true);
        },
        error: function(data, status, er) {
            alert('status: ' + status);
        }
    });
    // $.post(
    //     'http://localhost:8080/SignUp',

    //     JSON.stringify({
    //         memberName: $('#memberName').val(),
    //         age: $('#age option:selected').text(),
    //         email: $('#email').val(),
    //         password: $('#pwd').val()
    //     })
    // )
    //     .then(function handleFeedResponse(response) {
    //         // window.location.replace(
    //         //     'feed.html?username=' + $('#username').val()
    //         // );
    //         console.log('ITS BEEN A LONG TIME COMING!!!!!!!!!');
    //     })
    //     .catch(function handleFeedError(response) {
    //         console.log(response);
    //     });
}
