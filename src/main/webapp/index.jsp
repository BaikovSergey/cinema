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
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
            integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
            integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>

    <title>Tickets sale</title>

    <script>
        function validate() {
            var result = false;
            var selectedSeats = [];
            var places = document.getElementsByName("place");
            var index = 0;
            for (var j = 0; j < places.length; j++) {
                if (places[j].checked === true) {
                    selectedSeats[index] = "seat=" + places[j].value;
                    index++;
                }
            }

            for (var i = 0; i < places.length; i++) {
                if (places[i].checked === true) {
                    result = true;
                    break;
                }
            }

            if (!result) {
                alert("Необходимо выбрать одно или более мест!");
            } else {
                document.cookie = selectedSeats.toString();
                window.location.href = "payment.do";
            }
            return result;
        }

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
            setInterval(updateSeats, 1000);
         });

    </script>

</head>
<body>

<div class="container">
    <div class="row pt-3">
        <h4>
            Бронирование месте на сеанс
        </h4>
        <table class="table table-bordered">
            <thead>
            <tr>
                <th style="width: 120px;">Ряд / Место</th>
                <th>1</th>
                <th>2</th>
                <th>3</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <th>1</th>
                <td><label for="11"></label>
                    <input type="checkbox" name="place" value="11" id="11"> Ряд 1, Место 1</td>
                <td><label for="12"></label>
                    <input type="checkbox" name="place" value="12" id="12"> Ряд 1, Место 2</td>
                <td><label for="13"></label>
                    <input type="checkbox" name="place" value="13" id="13"> Ряд 1, Место 3</td>
            </tr>
            <tr>
                <th>2</th>
                <td><label for="21"></label>
                    <input type="checkbox" name="place" value="21" id="21"> Ряд 2, Место 1</td>
                <td><label for="22"></label>
                    <input type="checkbox" name="place" value="22" id="22"> Ряд 2, Место 2</td>
                <td><label for="23"></label>
                    <input type="checkbox" name="place" value="23" id="23"> Ряд 2, Место 3</td>
            </tr>
            <tr>
                <th>3</th>
                <td><label for="31"></label>
                    <input type="checkbox" name="place" value="31" id="31"> Ряд 3, Место 1</td>
                <td><label for="32"></label>
                    <input type="checkbox" name="place" value="32" id="32"> Ряд 3, Место 2</td>
                <td><label for="33"></label>
                    <input type="checkbox" name="place" value="33" id="33"> Ряд 3, Место 3</td>
            </tr>
            </tbody>
        </table>
    </div>
    <div class="row float-right">
        <button type="button" class="btn btn-success" onclick="return validate();">Оплатить
        </button>
    </div>
</div>
</body>
</html>
