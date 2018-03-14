function signUp() {
    $.post(
        'http://localhost:8080/SignUp',
        console.log(
            JSON.stringify({
                memberName: $('#memberName').val(),
                age: $('#age option:selected').text(),
                email: $('#email').val(),
                password: $('#pwd').val()
            })
        )
    );

    // .then(function handleFeedResponse(response) {
    //     window.location.replace(
    //         'feed.html?username=' + $('#username').val()
    //     );
    // })
    // .catch(function handleFeedError(response) {
    //     console.log(response);
    // });
}
