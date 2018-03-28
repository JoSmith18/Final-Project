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

function returnCheck(user, matches) {
    if (user == matches) {
        return '  <i class="fas fa-check" style="color:#B58900"/>';
    } else {
        return '';
    }
}

function findPercent(i) {
    var count = 0;

    if (PAGE_DATA.preferences.answer1 == MATCH_DATA[i].preferences.answer1) {
        count += 20;
    }

    if (PAGE_DATA.preferences.answer2 != MATCH_DATA[i].preferences.answer2) {
        count += 20;
    }

    if (PAGE_DATA.preferences.answer3 == MATCH_DATA[i].preferences.answer3) {
        count += 10;
    }

    if (PAGE_DATA.preferences.answer4 == MATCH_DATA[i].preferences.answer4) {
        count += 10;
    }
    if (PAGE_DATA.preferences.answer5 == MATCH_DATA[i].preferences.answer5) {
        count += 10;
    }
    if (PAGE_DATA.preferences.answer6 == MATCH_DATA[i].preferences.answer6) {
        count += 10;
    }
    if (PAGE_DATA.preferences.answer7 == MATCH_DATA[i].preferences.answer7) {
        count += 10;
    }
    if (PAGE_DATA.preferences.answer8 == MATCH_DATA[i].preferences.answer8) {
        count += 10;
    }
    return count;
}

function findMatches() {
    matches =
        '<h1>You Can Thank Us With A Hug We Think You And These Matches   Will Not Have Bugs</h1> ';
    if (MATCH_DATA.length == 0) {
        return '<h1>NO MATCHES HAVE BEEN FOUND</h1><br><h1>WILL BE UPDATED ONCE WE FIND SOMEONE</h1><br><h1>WHO TALKS YOUR LOVE LANGUAGE</h1>';
    }

    for (var i = 0; i < MATCH_DATA.length; i++) {
        var percent = findPercent(i);
        matches +=
            '<div class="col-lg-4"><div class="card border-primary" style="max-width: 20rem;">' +
            '<div class="card-header"><h2>' +
            MATCH_DATA[i].member.memberName +
            '&nbsp' +
            MATCH_DATA[i].member.age +
            ' </h2> Match Percent: ' +
            percent +
            '% ' +
            '</div>' +
            '<div class="card-body">' +
            '<h4 class="card-title"><a href=' +
            MATCH_DATA[i].member.githubLink +
            ' target="_blank">Link To GitHub</a></h4>' +
            '<p class="card-text">Employment Status: ' +
            MATCH_DATA[i].preferences.answer1 +
            '&nbsp' +
            returnCheck(
                MATCH_DATA[i].preferences.answer1,
                MY_DATA.preferences.answer1
            ) +
            '</p>' +
            '<p class="card-text">Prefered Gender: ' +
            MATCH_DATA[i].preferences.answer2 +
            '&nbsp' +
            returnCheck(
                MATCH_DATA[i].preferences.answer2,
                MY_DATA.member.gender
            ) +
            '</p>' +
            '<p class="card-text">Prefered Language: ' +
            MATCH_DATA[i].preferences.answer3 +
            '&nbsp' +
            returnCheck(
                MATCH_DATA[i].preferences.answer3,
                MY_DATA.preferences.answer3
            ) +
            '</p>' +
            '<p class="card-text">Developer Type: ' +
            MATCH_DATA[i].preferences.answer4 +
            '&nbsp' +
            returnCheck(
                MATCH_DATA[i].preferences.answer4,
                MY_DATA.preferences.answer4
            ) +
            '</p>' +
            '<p class="card-text">Started Coding: ' +
            MATCH_DATA[i].preferences.answer5 +
            '&nbsp' +
            returnCheck(
                MATCH_DATA[i].preferences.answer5,
                MY_DATA.preferences.answer5
            ) +
            '</p>' +
            '<p class="card-text">Learned Code Through: ' +
            MATCH_DATA[i].preferences.answer6 +
            '&nbsp' +
            returnCheck(
                MATCH_DATA[i].preferences.answer6,
                MY_DATA.preferences.answer6
            ) +
            '</p>' +
            '<p class="card-text">Thoughts on Testing: ' +
            MATCH_DATA[i].preferences.answer7 +
            '&nbsp' +
            returnCheck(
                MATCH_DATA[i].preferences.answer7,
                MY_DATA.preferences.answer7
            ) +
            '</p>' +
            '<p class="card-text">Years Since Working in Industry:' +
            MATCH_DATA[i].preferences.answer8 +
            '&nbsp' +
            returnCheck(
                MATCH_DATA[i].preferences.answer8,
                MY_DATA.preferences.answer8
            ) +
            '</p>' +
            '</div></div></div';
    }
    return matches;
}
function setFeed(response) {
    var id = getParameterByID('id');
    PAGE_DATA = response;
    updatePreferences(PAGE_DATA);
    $('.jumbotron').html(makePersonalInfo());
}

function setMatches(myResponse, response) {
    MY_DATA = myResponse;
    MATCH_DATA = response;
    console.log(MY_DATA);
    $('#matches').html(findMatches());
}

$(function() {
    var id = getParameterByID('id');
    console.log(id);
    $.get('http://localhost:8080/memID/' + id)
        .then(function handleResponse(response) {
            var myResponse = response;
            setFeed(response);
            $.get('http://localhost:8080/getMatches/' + id)
                .then(function handleResponse(response) {
                    setMatches(myResponse, response);
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

function deleteUser() {
    var id = getParameterByID('id');
    $.ajax({
        async: true,
        data: { _method: 'delete' },
        url: 'http://localhost:8080/delete/' + id,
        method: 'POST',
        dataType: 'json',
        crossDomain: true,
        contentType: 'application/json',
        mimeType: 'application/json'
    })
        .then(function handleResponse(response) {
            if (response) {
                alert('You Have Been Deleted!!!');
                window.location.replace('index.html');
            } else {
                alert('status: Error');
            }
        })
        .catch(function catchError(err) {
            console.log(err);
        });
}

function updatePreferences(PAGE_DATA) {
    $('#answer1').val(PAGE_DATA.preferences.answer1);
    $('#answer2').val(PAGE_DATA.preferences.answer2);
    $('#answer3').val(PAGE_DATA.preferences.answer3);
    $('#answer4').val(PAGE_DATA.preferences.answer4);
    $('#answer5').val(PAGE_DATA.preferences.answer5);
    $('#answer6').val(PAGE_DATA.preferences.answer6);
    $('#answer7').val(PAGE_DATA.preferences.answer7);
    $('#answer8').val(PAGE_DATA.preferences.answer8);
}

function newPrefs() {
    $('#feedPage').attr('hidden', true);
    $('#updatePrefs').attr('hidden', false);
}

function submitUpdatedPrefsButton(event) {
    var id = getParameterByID('id');
    $.ajax({
        async: true,
        url: 'http://localhost:8080/update/' + id,
        method: 'POST',
        dataType: 'json',
        crossDomain: true,
        data: JSON.stringify({
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
        mimeType: 'application/json'
    })
        .then(function handleResponse(response) {
            if (response) {
                window.location.replace('member_page.html?id=' + id);
            } else {
                alert('status: Error');
            }
        })
        .catch(function catchError(err) {
            console.log(err);
        });
}
