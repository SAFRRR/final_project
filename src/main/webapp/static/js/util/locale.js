 import { changeLocationIfUndefined } from "../modules/util.js"
function changeLocale(localeName){
    let url = "controller?command=change_locale&locale=" + `${localeName}`
        $.ajax({
            url: url,
            type: "POST",
            dataType: "json",
            success: function (data, textStatus, jqXHR) {
                window.location.reload()
            },
            error: function (jqXHR, textStatus, errorThrown) {
                    changeLocationIfUndefined(jqXHR);
            }
        });
}

export const navigation = {
    changeLocale: changeLocale
}


