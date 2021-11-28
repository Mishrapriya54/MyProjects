var selectedId = 0;
var startingValue = 0.00;
var itemCost = 0.00;
$(document).ready(function () {
    loadInventory();
    initialMoney();
    addDoller();
    addQuarter();
    addNickel();
    addDime();
    showItemIn();
    vendItem();
});

function initialMoney() {
    $('#addMoney').val(startingValue);
  
   
}

function addDoller() {
    var doller = 1;
    $('#addDollerButton').click(function (event) {
        $('#addMoneyPlace').val("$" + (parseFloat(doller) + parseFloat(startingValue)).toFixed(2));
        startingValue += 1;
    });
}
function addQuarter() {
    var quarter = .25;
    $('#addQuarterButton').click(function (event) {
        $('#addMoneyPlace').val("$" + (parseFloat(quarter) + parseFloat(startingValue)).toFixed(2));
        startingValue += .25;
    });
}

function addNickel() {
    var nickel = .05;
    $('#addNickelButton').click(function (event) {
        $('#addMoneyPlace').val("$" + (parseFloat(nickel) + parseFloat(startingValue)).toFixed(2));
        startingValue += .05;
    });

}

function addDime() {
    var dime = .1;
    $('#addDimeButton').click(function (event) {
        $('#addMoneyPlace').val("$" + (parseFloat(dime) + parseFloat(startingValue)).toFixed(2));
        startingValue += .1;
    });

}

function loadInventory() {
    var contentRows = $('#contentItems');
    $.ajax({
        type: 'GET',
        url: 'http://vending.us-east-1.elasticbeanstalk.com/items',
        success: function (itemArray) {
            $.each(itemArray, function (index, item) {
                var id = item.id;
                var name = item.name;
                var price = item.price;
                var quantity = item.quantity;
                var row = ' <div class="col-md-4">';
                row += ' <button type="button" class="btnItems"';
                row += ' id="' + item.id + '"';
                row += '<class="btn btn-primary" onclick="showItemIn(' + item.id + ',' + item.price + ') ">';
                row += id;
                row += '<br/>';
                row += name;
                row += '<br/>';
                row += price;
                row += '<br/>';
                row += '<label>Quantity Left: </label><label id="quantity">' + quantity + '</label>';
                row += quantity;
                row += '<br/>';
                row += '<br/>';
                row += ' </button><br/><br/>';
                row += '</div>';
                contentRows.append(row);
            })
        },
        headers: {
            'Accept': 'application/json',
        },
        error: function () {
            $('#errorMessages')
                .append($('<li>')
                    .attr({ class: 'list-group-item list-group-item-danger' })
                    .text('Error calling web service. Please try again later.'));
        }

    });
}
function showItemIn(id, price) {
    $('#showItem').val(id);
    //itemCost=price;

}
function vendItem() {
    //check
    $('#purchaseButton').click(function (event) {
        ($('#displayMessage').val() == "");
        if ($('#showItem').val() == "") {
            $('#displayMessage').val("Please Make A Selection.");
        }
        else {
            $('#displayMessage').val()=="";
            var selectedItem = $('#showItem').val();
            var money = $('#addMoneyPlace').val().replace('$','');
            $.ajax({
                type: 'POST',
                url: 'http://vending.us-east-1.elasticbeanstalk.com/money/' + money + '/item/' + selectedItem,
                success: function (change) {
                    $('#changeDisplay').val(change.quarters + ':' + change.dimes + ':' + change.nickels + ':' + change.pennies);
                    $('#showItem').val('');
                    $('#addMoneyPlace').val('');
                    $('#displayMessage').val("Thank You!!");
                    $('#contentItems').empty();
                    loadInventory();

                },


                error: function (message) {
                    json = JSON.parse(message.responseText);
                    $('#displayMessage').val(json.message);
                   
                    $('#contentItems').empty();
                    loadInventory();
                }

            });

        }
    });
}