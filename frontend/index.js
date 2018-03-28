function signUp() {
    $.ajax({
        url: 'http://localhost:8080/SignUp',
        method: 'POST',
        dataType: 'json',
        crossDomain: true,
        data: JSON.stringify({
            memberName: $('#memberName').val(),
            gender: $('#gender option:selected').text(),
            age: $('#age').val(),
            githubLink: $('#github').val(),
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
            $('#submitPrefsButton').click(function() {
                submitPrefs(
                    data.id,
                    data.age,
                    data.memberName,
                    data.githubLink
                );
            });
            $('body').prepend(
                "<div class='jumbotron'><center><h3>" +
                    response.memberName +
                    '</h3><h5>Answer Following Questions!!</h5></center></div>'
            );
        })
        .catch(function handleFeedError(response) {});
}

function submitPrefs(id, age, memberName, githubLink) {
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
        .then(function handleFeedResponse(response) {
            window.location.replace('member_page.html?id=' + response.memID);
        })
        .catch(function handleFeedError(response) {});
}
