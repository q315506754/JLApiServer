$(function () {
    console.log("loaded..");

    var obj = GetRequest();
    for (var key in obj) {

        $(".container").append(`
            <div class="row">   
                <label>${key}</label>
                <div>${obj[key]}</div>
            </div>
        `);
    }
});