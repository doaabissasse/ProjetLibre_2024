// Select the dark mode toggle
const darkModeToggle = document.getElementById("darkModeToggle");

// Add event listener for toggling dark mode
darkModeToggle.addEventListener("change", () => {
  document.body.classList.toggle("dark-mode", darkModeToggle.checked);
});
