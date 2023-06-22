$(document).ready(function (e) {
      var activeID = $(".active").attr('id');
      $('#'+activeID).removeClass('active');
      $('#pending_delivery').addClass('active');

       $('.close').click(function() {
                 $('.modal').hide();
       });
});


function generateInvoice(requestID){
    $.ajax({
      url: 'http://localhost:8080/request/details/'+requestID,
      type: 'GET',
      dataType: 'json',
      success: function(data) {
        let deliveryRequest = data;
           console.log(data);
            $('#requestID').val(data.requestID);
            $("#newInvoiceModal").show();

      },
      error: function(xhr, status, error) {
        console.log(error);
      }
    });
}