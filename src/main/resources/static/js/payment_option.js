 var initialTableHtml = null;

$(document).ready(function (e) {

      var activeID = $(".active").attr('id');
      $('#'+activeID).removeClass('active');
      $('#payment_option').addClass('active');

      initialTableHtml = $('#creditCardListTable').html();

      $("#AddNewCardBtn").click(()=>{
          $("#newCreditCardModal").show();
      })

      $('.close').click(function() {
          $('.modal').hide();
      });
});

$('#searchString').on('keyup', function() {
    var inputValue = $(this).val();
    $('#creditCardListTable').empty();

    if(inputValue.length > 0){
        $.ajax({
              url: 'http://localhost:8080/payment_option/search/'+inputValue,
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
        $('#creditCardListTable').append(initialTableHtml);
    }

});

function populateTable(data){
        $.each(data, function(index, item) {
                    var position = index+1;
                    var row = '<tr>' +
                                '<td><b>' + position + '.</b></td>' +
                                '<td>' + item.cardNumber + '</td>' +
                                '<td>' + item.issueName + '</td>' +
                                '<td>' + item.expiryDate + '</td>' +
                                '<td>' + (item.preferredCard ? 'Yes' : 'No') + '</td>' +
                                '<td><a href="#" onclick="loadCard('+item.cardID+')">Edit</a> | <a href="/payment_option/delete/'+item.cardID+'">Delete</a>'+ ( !item.preferredCard ? ('<a href="/payment_option/preferred/'+item.cardID+'"> | Make Preferred</a>')  : '')+'</td>'
                              '</tr>';
                   $('#creditCardListTable').append(row);
        });
}

function loadCard(cardID){
      $.ajax({
            url: 'http://localhost:8080/payment_option/edit/'+cardID,
            type: 'GET',
            dataType: 'json',
            success: function(data) {
              let card = data;
                 console.log(data);
                 $('#updateCreditCardId').val(data.cardID);
                 $('#updateCreditCardNumber').val(data.cardNumber);
                 $('#updateIssueName').val(data.issueName);
                 $('#updateExpiryDate').val(data.expiryDate)
                 $('#updateCreditCardPreferredCard').val(data.preferredCard)
                 $("#updateCreditCardModal").show();
            },
            error: function(xhr, status, error) {
              console.log(error);
            }
      });
}