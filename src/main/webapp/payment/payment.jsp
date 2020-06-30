<%@ page contentType="text/html; charset=UTF-8" %>
<!doctype html>
<html lang="en">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
            integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
            integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>

    <title>Payment</title>

    <script>
        function validate() {
            var result = true;
            var fields = [document.getElementById("username"), document.getElementById("phoneNumber")];
            var seats = "";

            for (var i = 0; i < fields.length; i++) {
                if (fields[i].value === "") {
                    result = false;
                    break;
                }
            }

            if (!result) {
                for (var j = 0; j < fields.length; j++) {
                    if (fields[j].value === "") {
                        alert("Please fill this field: " + $(fields[j]).attr('name'));
                    }
                }
            } else {
                document.getElementById("sum").setAttribute("value", "123");
                location.href = "index.do";
            }
            return result;
        }

        $(document).ready(function () {
            var selectedSeats = document.cookie.split(",");

            for (var i = 0; i < selectedSeats.length; i++) {
                $("#SelectedSeats").append("<h3>"+ selectedSeats[i] +"</h3><br>");
            }
        });

        function updateSeats() {
            $.ajax({
                type: "GET",
                url: "http://localhost:8080/index.do/seats.do"
            }).done(function(data) {
                var resp = $.parseJSON(data);
                $.each(resp, function (i, seat) {
                    var row = seat["row"];
                    var place = seat["seat"];
                    var occupied = seat["occupied"];
                    if (occupied === true) {
                        $("#"+row+""+place).attr("disabled", true);
                    } else {
                        $("#"+row+""+place).removeAttr("disabled");
                    }
                })
            }).fail(function(){
                alert("Cannot get data from DB!");
            });
        }

        $(document).ready(function () {
            updateSeats();
        });

    </script>
</head>
<body>
<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B,+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>

<div class="container">
    <div class="row pt-3" id="SelectedSeats">
    </div>
    <div class="row">
        <form action="<%=request.getContextPath()%>/payment.do" method="post">
            <div class="form-group">
                <label for="username">ФИО</label>
                <input type="text" class="form-control" name="userName" id="username"
                       placeholder="ФИО">
            </div>
            <div class="form-group">
                <label for="phoneNumber">Номер телефона</label>
                <input type="text" class="form-control" name="phoneNumber" id="phoneNumber"
                       placeholder="Номер телефона">
            </div>
            <div class="form-group">
                <input type="hidden" class="form-control" name="sum" id="sum">
            </div>
            <div class="form-group">
                <input type="hidden" class="form-control" name="seat" id="seat">
            </div>
            <button type="submit" class="btn btn-success" onclick="return validate();">Оплатить</button>
        </form>
    </div>
</div>
</body>
</html>