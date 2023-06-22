$(document).ready(function (e) {

      var activeID = $(".active").attr('id');
      $('#'+activeID).removeClass('active');
      $('#payment_option').addClass('active');

//      initialTableHtml = $('#studentListTable').html();

      $("#AddNewCardBtn").click(()=>{
          $("#newCreditCardModal").show();
      })

//      $('#internationalCheckbox').change(function(){
//          $('#isInternational').val($('#internationalCheckbox').is(':checked'))
//      })
//
//      $('#UpdateInternationalCheckbox').change(function(){
//          $('#updateIsInternational').val($('#UpdateInternationalCheckbox').is(':checked'))
//      })

      $('.close').click(function() {
          $('.modal').hide();
      });
});



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