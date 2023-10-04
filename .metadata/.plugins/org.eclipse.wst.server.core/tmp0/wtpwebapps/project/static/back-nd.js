    //NavBar..

    (function($) { // Begin jQuery
        $(function() { // DOM ready
          // If a link has a dropdown, add sub menu toggle.
          $('nav ul li a:not(:only-child)').click(function(e) {
            $(this).siblings('.nav-dropdown').toggle();
            // Close one dropdown when selecting another
            $('.nav-dropdown').not($(this).siblings()).hide();
            e.stopPropagation();
          });
          // Clicking away from dropdown will remove the dropdown class
          $('html').click(function() {
            $('.nav-dropdown').hide();
          });
          // Toggle open and close nav styles on click
          $('#nav-toggle').click(function() {
            $('nav ul').slideToggle();
          });
          // Hamburger to X toggle
          $('#nav-toggle').on('click', function() {
            this.classList.toggle('active');
          });
        }); // end DOM ready
      })(jQuery); // end jQuery
    
    // Wrap every letter in a span
    var textWrapper = document.querySelector('.ml2');
    textWrapper.innerHTML = textWrapper.textContent.replace(/\S/g, "<span class='letter'>$&</span>");
    
    anime.timeline({loop: true})
      .add({
        targets: '.ml2 .letter',
        scale: [4,1],
        opacity: [0,1],
        translateZ: 0,
        easing: "easeOutExpo",
        duration: 950,
        delay: (el, i) => 70*i
      }).add({
        targets: '.ml2',
        opacity: 0,
        duration: 1000,
        easing: "easeOutExpo",
        delay: 1000
      });

      // card style 

let card_animate = document.querySelectorAll('.subject .col-md-4  ')   




 





