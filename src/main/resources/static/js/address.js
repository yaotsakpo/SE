$(document).ready(function (e) {

      var activeID = $(".active").attr('id');
      $('#'+activeID).removeClass('active');
      $('#address').addClass('active');

      initialTableHtml = $('#addressListTable').html();

      $("#AddNewAddressBtn").click(()=>{
          $("#newAddressModal").show();
      })

      $('.close').click(function() {
          $('.modal').hide();
      });
});


$('#searchString').on('keyup', function() {
    var inputValue = $(this).val();
    $('#addressListTable').empty();

    if(inputValue.length > 0){
        $.ajax({
              url: 'http://localhost:8080/address/search/'+inputValue,
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
        $('#addressListTable').append(initialTableHtml);
    }

});


function populateTable(data){
        $.each(data, function(index, item) {
                    var position = index+1;
                    var row = '<tr>' +
                                '<td><b>' + position + '.</b></td>' +
                                '<td>' + item.street + '</td>' +
                                '<td>' + item.zipcode + '</td>' +
                                '<td>' + item.state + '</td>' +
                                '<td><a href="#" onclick="loadAddress('+item.addressID+')">Edit</a> | <a href="/address/delete/'+item.addressID+'">Delete</a></td>'
                              '</tr>';
                   $('#addressListTable').append(row);
        });
}



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