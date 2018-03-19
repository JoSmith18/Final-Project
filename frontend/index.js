function signUp() {
    var file = $('input:file')[0].files[0];
    if (file) {
        var url = window.URL.createObjectURL(file);
    } else {
        var url = '';
    }
    console.log(
        JSON.stringify({
            memberName: $('#memberName').val(),
            age: $('#age option:selected').text(),
            email: $('#email').val(),
            password: $('#pwd').val(),
            profilePicUrl: url
        })
    );
    $.ajax({
        url: 'http://localhost:8080/SignUp',
        method: 'POST',
        dataType: 'json',
        crossDomain: true,
        data: JSON.stringify({
            memberName: $('#memberName').val(),
            age: $('#age option:selected').text(),
            email: $('#email').val(),
            password: $('#pwd').val(),
            profilePicUrl: url
        }),
        contentType: 'application/json',
        mimeType: 'application/json',
        error: function(data, status, er) {
            alert('status: ' + status);
        }
    })
        .then(function handleFeedResponse(response) {
            console.log(response);
            var data = response;
            $('#preferencesDiv').removeAttr('hidden');
            $('#signUpDiv').attr('hidden', true);
            $('#submitPrefsButton').click(function() {
                submitPrefs(
                    data.id,
                    data.profileURL,
                    data.memberName,
                    data.email
                );
            });
            $('body').prepend(
                "<div class='jumbotron'><center><h3>" +
                    response.memberName +
                    '</h3><h5>Answer Following Questions!!</h5></center></div>'
            );
        })
        .catch(function handleFeedError(response) {
            console.log(response);
        });
}

function submitPrefs(id, profileURL, memberName, email) {
    console.log(profileURL);
    $.ajax({
        url: 'http://localhost:8080/submitPrefs',
        method: 'POST',
        dataType: 'json',
        crossDomain: true,
        data: JSON.stringify({
            ID: id,
            answer1: $('#answer1 option:selected').text(),
            answer2: $('#answer2 option:selected').text(),
            answer3: $('#answer3 option:selected').text(),
            answer4: $('#answer4 option:selected').text(),
            answer5: $('#answer5 option:selected').text(),
            answer6: $('#answer6 option:selected').text(),
            answer7: $('#answer7 option:selected').text(),
            answer8: $('#answer8 option:selected').text()
        }),
        contentType: 'application/json',
        mimeType: 'application/json',
        error: function(data, status, er) {
            alert('status: ' + status);
        }
    })
        .then(function handleFeedResponse() {
            $('#body').html(
                '<div><img src=' +
                    profileURL +
                    ' class="img"><h3>' +
                    memberName +
                    '</h3><span>@' +
                    email +
                    '</span><br></div>'
            );
        })
        .catch(function handleFeedError(response) {
            console.log(response);
        });
}
