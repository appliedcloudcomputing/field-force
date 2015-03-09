/* Revenue Chart - Donut */
Pizza.init();

jQuery(document).ready(function($){

  /*** Count To ***/
  jQuery('.count-to').each(function(i, item) {
    var end = $(this).html();
    jQuery(item).countTo({from: 0,
                          to: end,
                          speed: 6000,
                          refreshInterval: 50});
  });

});