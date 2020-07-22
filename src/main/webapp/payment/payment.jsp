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

    <title>Payment</title>

    <script>
        function validate() {
            var result = true;
            var fields = [document.getElementById("username"), document.getElementById("phoneNumber")];

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
            }
            return result;
        }

        $(document).ready(function () {
            $.ajax({
                type: "GET",
                url: "http://localhost:8080/index.do/boughtSeats.do",
                data: {seats: document.cookie.toString()}
            }).done(function(data) {
                var result = data.split(",");
                for (var i = 0; i < result.length; i++) {
                    $("#SelectedSeats").append("<div class='row align-items-start'><p>"+ result[i] +"</p></div>");
                    if (i === result.length - 1) {
                        $("#sum").attr("value", result[i].substring(7).trim());
                    }
                }
                $("#seats").attr("value", document.cookie.toString());
            }).fail(function(){
                alert("Cannot get data from DB!");
            });
        });
    </script>

</head>
<body>
<div class="container">
    <div class="grid align-items-start" id="SelectedSeats">
        <div class="row">
            <h4>Вы выбрали: </h4>
        </div>
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
                <input type="hidden" class="form-control" name="seat" id="seats">
            </div>
            <button type="submit" class="btn btn-success" onclick="return validate();">Оплатить</button>
        </form>
    </div>
</div>
</body>
</html>