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
        error: function(data, status, er) {
            alert('status: ' + status);
        }
    })
        .then(function handleFeedResponse(response) {
            var data = response;
            $('#preferencesDiv').removeAttr('hidden');
            $('#signUpDiv').attr('hidden', true);
            $('body').prepend(
                "<div class='jumbotron'><center><h3>" +
                    response.memberName +
                    '</h3><h5>Answer Following Questions!!</h5></center></div>'
            );
        })
        .catch(function handleFeedError(response) {
            console.log(response);
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
}
