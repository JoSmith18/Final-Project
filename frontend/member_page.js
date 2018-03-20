var PAGE_DATA = new Object();

function makePersonalInfo() {
    return (
        '<h1>' +
        PAGE_DATA.member.memberName +
        '</h1><h3>Age: ' +
        PAGE_DATA.member.age +
        '</h3><span>Email: ' +
        PAGE_DATA.member.email +
        '</span><br><span>Matching Points: ' +
        PAGE_DATA.member.matchingPoints +
        ' </span><span>'
    );
}
function setFeed(response) {
    PAGE_DATA = response;
    $('.jumbotron').html(makePersonalInfo());
}

$(function() {
    var id = getParameterByID('id');
    console.log(id);
    $.get('http://localhost:8080/memID/' + id)
        .then(function handleResponse(response) {
            console.log(response);
            setFeed(response);
        })
        .catch(function(err) {
            console.log(err);
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
