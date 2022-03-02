var form = document.getElementById("list-lang-id");

window.onload = function() {
    document.getElementById('lang-en-id').onclick = function() {
        document.getElementById('lang-list-id').submit();
        return false;
    };

    document.getElementById('lang-ru-id').onclick = function() {
        document.getElementById('lang-list-id').submit();
        return false;
    };
};