$(document).ready(function (e) {
      var activeID = $(".active").attr('id');
      $('#'+activeID).removeClass('active');
      $('#pending_delivery').addClass('active');

//       $("#AddNewDeliveryRequestBtn").click(()=>{
//            $("#newDeliveryRequestModal").show();
//       })

       $('.close').click(function() {
                 $('.modal').hide();
       });
});
