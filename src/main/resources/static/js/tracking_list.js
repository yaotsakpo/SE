 var initialTableHtml = null;

$(document).ready(function (e) {
      initialTableHtml = $('#deliveryRequestListTable').html();
      var activeID = $(".active").attr('id');
      $('#'+activeID).removeClass('active');
      $('#tracking_list').addClass('active');

       $("#AddNewDeliveryRequestBtn").click(()=>{
            $("#newDeliveryRequestModal").show();
       })

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
           if(data.invoice.status == "Sent"){
               let actionArea = '<a href=/invoice/accept/'+data.requestID+ ' class="btn btn-success" style="color: white !important"> Accept Invoice </a> <a href=/invoice/cancel/'+data.requestID+' class="btn btn-danger" style="color: white !important"> Decline Invoice </a>';
               $("#actionArea").html(actionArea);
           }else{
                $("#actionArea").html("");
           }
            $("#invoiceModal").show();

      },
      error: function(xhr, status, error) {
        console.log(error);
      }
    });
}

$('#searchString').on('keyup', function() {
    var inputValue = $(this).val();
    $('#deliveryRequestListTable').empty();

    if(inputValue.length > 0){
        $.ajax({
              url: 'http://localhost:8080/request/search/'+inputValue,
              type: 'GET',
              dataType: 'json',
              success: function(data) {
                console.log(data);
                populateTable(data);
              },
              error: function(xhr, status, error) {
                console.log(error);
              }
        });
    }else{
        $('#deliveryRequestListTable').append(initialTableHtml);
    }

});

var options = {
  year: 'numeric',
  month: 'short',
  day: 'numeric'
};

function populateTable(data){
        $.each(data, function(index, item) {
                    var position = index+1;
                    var processingAgent = "";
                    var viewInvoice = "";
                    if(item.invoice != null){
                            let date = new Date(item.invoice.invoiceDate);
                            processingAgent = "<div class='contact-container'> <a href='#'>"+item.validator.firstName + ' ' +item.validator.lastName+"</a><p>On <span>"+date.toLocaleDateString('en-US', options)+"</span></p></div>"
                            viewInvoice = '<a href="#" onclick="loadInvoice(\'' + item.requestID + '\')"> View invoice </a>';
                    }
                    var row = '<tr>' +
                                '<td><b>' + position + '.</b></td>' +
                                '<td>' + (item.pickupAddress.street + ' ' + item.pickupAddress.zipcode + ' ' + item.pickupAddress.state) + '</td>' +
                                '<td>' + (item.requestPackage != null ? item.requestPackage.trackingID : '') + '</td>' +
                                '<td>' + (item.deliveryAddress.street + ' ' + item.deliveryAddress.zipcode + ' ' + item.deliveryAddress.state) + '</td>' +
                                '<td>' + item.weight + '</td>' +
                                '<td>' + item.height + '</td>' +
                                '<td>' + item.status + '</td>' +
                                '<td>' + processingAgent  + '</td>' +
                                '<td>'+  viewInvoice  + '</td>'+
                              '</tr>';
                   $('#deliveryRequestListTable').append(row);
        });
}