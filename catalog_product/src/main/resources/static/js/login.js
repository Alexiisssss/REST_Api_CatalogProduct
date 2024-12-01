const params = new URLSearchParams(window.location.search);
if (params.has('error')) {
    document.getElementById('error-message').style.display = 'block';
}
if (params.has('logout')) {
    document.getElementById('logout-message').style.display = 'block';
}
