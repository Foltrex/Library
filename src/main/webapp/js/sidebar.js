
var mySidebar = document.getElementById("mySidebar");

var overlayBg = document.getElementById("myOverlay");

function openNav() {
  if (mySidebar.style.display === 'block') {
    mySidebar.style.display = 'none';
    overlayBg.style.display = "none";
  } else {
    mySidebar.style.display = 'block';
    overlayBg.style.display = "block";
  }
}

// Close the sidebar with the close button
function closeNav() {
  mySidebar.style.display = "none";
  overlayBg.style.display = "none";
}