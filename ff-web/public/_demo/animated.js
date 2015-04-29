$(document).ready(function(){

  /*** Attach Effect on Button on click ***/
  $('.effect-button').on('click', function(){
    var button_effect = $(this).attr('data-effect');
    $(this).removeClass(button_effect).addClass(button_effect + ' animated').one('webkitAnimationEnd mozAnimationEnd MSAnimationEnd oanimationend animationend', function(){
      $(this).removeClass(button_effect);
      $(this).removeClass('animated');
    });
  });

});