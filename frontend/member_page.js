var PAGE_DATA = new Object();
var MATCH_DATA = new Object();

function makePersonalInfo() {
    return (
        '<h1>' +
        PAGE_DATA.member.memberName +
        '</h1><h3>Age: ' +
        PAGE_DATA.member.age +
        '</h3><a href= ' +
        PAGE_DATA.member.githubLink +
        ' target="_blank">GitHub</a>' +
        '</span>'
    );
}

function findMatches() {
    matches =
        '<h1>You Can Thank Us With A Hug We Think You And These Matches   Will Not Have Bugs</h1> ';
    if (MATCH_DATA.length == 0) {
        return '<h1>NO MATCHES HAVE BEEN FOUND</h1><br><h1>WILL BE UPDATED ONCE WE FIND SOMEONE</h1><br><h1>WHO TALKS YOUR LOVE LANGUAGE</h1>';
    }

    for (var i = 0; i < MATCH_DATA.length; i++) {
        matches +=
            '<div class="card border-primary" style="max-width: 20rem;">' +
            '<div class="card-header">' +
            MATCH_DATA[i].member.memberName +
            '</div>' +
            '<div class="card-body">' +
            '<h4 class="card-title"><a href=' +
            MATCH_DATA[i].member.githubLink +
            ' target="_blank">Link To GitHub</a></h4>' +
            '<p class="card-text">Answer One:' +
            MATCH_DATA[i].preferences.answer1 +
            '</p>' +
            '<p class="card-text">Answer Two:' +
            MATCH_DATA[i].preferences.answer2 +
            '</p>' +
            '<p class="card-text">Answer Three:' +
            MATCH_DATA[i].preferences.answer3 +
            '</p>' +
            '<p class="card-text">Answer Four:' +
            MATCH_DATA[i].preferences.answer4 +
            '</p>' +
            '<p class="card-text">Answer Five:' +
            MATCH_DATA[i].preferences.answer5 +
            '</p>' +
            '<p class="card-text">Answer Six:' +
            MATCH_DATA[i].preferences.answer6 +
            '</p>' +
            '<p class="card-text">Answer Seven:' +
            MATCH_DATA[i].preferences.answer7 +
            '</p>' +
            '<p class="card-text">Answer Eight:' +
            MATCH_DATA[i].preferences.answer8 +
            '</p>' +
            '</div></div>';
    }
    return matches;
}
function setFeed(response) {
    var id = getParameterByID('id');
    PAGE_DATA = response;
    $('.jumbotron').html(makePersonalInfo());
}

function setMatches(response) {
    MATCH_DATA = response;
    console.log(MATCH_DATA);
    $('#matches').html(findMatches());
}

$(function() {
    var id = getParameterByID('id');
    console.log(id);
    $.get('http://localhost:8080/memID/' + id)
        .then(function handleResponse(response) {
            setFeed(response);
            $.get('http://localhost:8080/getMatches/' + id)
                .then(function handleResponse(response) {
                    setMatches(response);
                })
                .catch(function(err) {
                    // console.log(err);
                });
        })
        .catch(function(err) {
            // console.log(err);
        });
});

function getParameterByID(id) {
    var url = window.location.href;
    id = id.replace(/[\[\]]/g, '\\$&');
    var regex = new RegExp('[?&]' + id + '(=([^&#]*)|&|#|$)');
    results = regex.exec(url);
    if (!results) return null;
    if (!results[2]) return '';
    return decodeURIComponent(results[2].replace(/\+/g, ' '));
}

function logout() {
    var id = getParameterByID('id');
    $.ajax({
        url: 'http://localhost:8080/logout/' + id,
        method: 'POST',
        dataType: 'json',
        crossDomain: true,
        contentType: 'application/json',
        mimeType: 'application/json',
        error: function(data, status, er) {
            alert('status: ' + status);
        }
    })
        .then(function handleResponse(response) {
            window.location.replace('login.html');
        })
        .catch(function catchError(err) {
            console.log(err);
        });
}
