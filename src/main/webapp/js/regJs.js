$(document).ready(function() {

    $("#emailInput").focus(function () {
        $(this).keyup(function () {
            $('#checkemail').addClass("bg-warning my-5");
            $('#checkemail').text("Loading...");
            $("#checkemail").removeClass('bg-danger');
            $("#checkemail").removeClass('bg-success');
        })
    });
    ////////////////////////////////////////////////////////////
    $("#regbtn").prop('disabled', true);
    $("#emailInput").blur(function () {
        event.preventDefault();
        $.ajax({
            url: "http://localhost:8080/sdpproject_war_exploded/register?&action=check&em=" + $("#emailInput").val(),
            type: 'GET',
            success: function (data) {
                if (data.message == "empty") {
                    $("#checkemail").removeClass('bg-success');
                    $("#checkemail").removeClass('bg-warning');
                    $("#checkemail").removeClass('bg-danger');
                    $("#checkemail").removeClass('bg-info');
                    $("#checkemail").addClass('bg-secondary p-2 text-white');
                    $("#checkemail").text("Fill email input");
                    $("#regbtn").prop('disabled', true);
                } else if (data.message == "success") {
                    $("#checkemail").removeClass('bg-warning');
                    $("#checkemail").removeClass('bg-danger');
                    $("#checkemail").removeClass('bg-info');
                    $("#checkemail").addClass('bg-success p-2 text-white');
                    $("#checkemail").text("This email is free");
                    $("#regbtn").prop('disabled', false);
                } else {
                    $("#checkemail").removeClass('bg-warning');
                    $("#checkemail").removeClass('bg-success');
                    $("#checkemail").removeClass('bg-info');
                    $("#checkemail").text("Error: " + data.message);
                    $("#checkemail").addClass("bg-danger p-2 text-white");
                    $("#regbtn").prop('disabled', true);
                }
            }
        });
    });

    $(".buy").on("click", function (){
        event.preventDefault();
        var a = $( this ).attr("id");
        var url = "http://localhost:8080/sdpproject_war_exploded/cart?&action=buy&id=" + a;
        var msgg = "msg" + a;

        $.ajax({
            url: url,
            type: 'GET',  // http method
            accepts: 'application/json; charset=utf-8',
            //contentType: "application/json",
            success: function (data) {
                alert("Added");
            },
            error: function (errorData, textStatus, errorMessage) {
                var msg = (errorData.responseJSON != null) ? errorData.responseJSON.errorMessage : '';
                $(msgg).text('Error: ' + msg + ', ' + errorData.status);
            }
        });
    });

});