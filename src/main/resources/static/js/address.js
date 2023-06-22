$(document).ready(function (e) {

      var activeID = $(".active").attr('id');
      $('#'+activeID).removeClass('active');
      $('#address').addClass('active');

//      initialTableHtml = $('#studentListTable').html();

      $("#AddNewAddressBtn").click(()=>{
          $("#newAddressModal").show();
      })


      $('.close').click(function() {
          $('.modal').hide();
      });
});



      function loadAddress(addressID){
              $.ajax({
                    url: 'http://localhost:8080/address/edit/'+addressID,
                    type: 'GET',
                    dataType: 'json',
                    success: function(data) {
                      let card = data;
                         console.log(data);
                         $('#updateAddressId').val(data.addressID);
                         $('#updateAddressStreet').val(data.street);
                         $('#updateZipcode').val(data.zipcode);
                         $('#updateState').val(data.state);
                         $("#updateAddressModal").show();
                    },
                    error: function(xhr, status, error) {
                      console.log(error);
                    }
              });
      }