jQuery('document').ready(function() {
    jQuery(".deleteButton").on("click", function () {
        var id = $(this).data("id");
        var csrfValue = $(this).data("csrf-value");
        var csrfName = $(this).data("csrf-name");

        var data = {'id': id};
        data[csrfName] = csrfValue;

        jQuery.ajax({
            url: "/security/material/delete",
            headers: {'X-Csrf-Token': csrfValue},
            data: data,
            method: "post",
            success: function (response, textStatus, xhr) {
                $("body").html(response);
            },
            error: function (response) {
                alert("Какой-то продукт имеет в своем составе на данный материал, " +
                    "чтобы удалить материал вместе с продуктами," +
                    "нажмите кнопку \"удалить каскадно\"");
            }
        });
    });

    jQuery(".deleteCascadeButton").on("click", function () {
        var id = $(this).data("id");
        var csrfValue = $(this).data("csrf-value");
        var csrfName = $(this).data("csrf-name");

        var data = {'id': id};
        data[csrfName] = csrfValue;

        jQuery.ajax({
            url: "/security/material/deleteCascade",
            headers: {'X-Csrf-Token': csrfValue},
            data: data,
            method: "post",
            success: function (response, textStatus, xhr) {
                $("body").html(response);
            },
            error: function (response) {
                alert("ошибка");
            }
        });
    });

    jQuery(".editButton").on("click", function () {
        var id = $(this).data("id");
        var data = {'id': id};

        jQuery.ajax({
            url: "/security/material/edit",
            data: data,
            method: "get",
            success: function (response, textStatus, xhr) {
                $('body').html(response);
            },
            error: function (response) {
                alert("что-то пошло не так");
            }
        });
    });
});