$(document).ready(function (e) {
      var activeID = $(".active").attr('id');
      $('#'+activeID).removeClass('active');
      $('#processed_delivery').addClass('active');

       $('.close').click(function() {
                 $('.modal').hide();
       });
});

function loadInvoice(requestID){
    $.ajax({
      url: 'http://localhost:8080/request/details/'+requestID,
      type: 'GET',
      dataType: 'json',
      success: function(data) {
        let deliveryRequest = data;
           console.log(data);
           $('#price').val(data.invoice.price);
           $('#description').val(data.invoice.description);
            $("#invoiceModal").show();

      },
      error: function(xhr, status, error) {
        console.log(error);
      }
    });
}