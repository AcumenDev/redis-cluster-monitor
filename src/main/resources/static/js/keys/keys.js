/**
 * Created by vstalmakov on 16.11.16.
 */

var keysUi = {
    keysTemplate: null,

    loadTemplates: function () {
        $.get('keyList.html', function (data) {
            keysUi.keysTemplate = Handlebars.compile(data);
        }, 'html')
    },

    init: function () {
        keysUi.loadTemplates();
        keysUi.getKeys();
    },

    refreshInfo: function () {
        nodesUi.loadNode();
        setTimeout(nodesUi.refreshInfo, 20000);
    },

    getKeys: function () {
        $.getJSON("/operation/keys/get", function (data) {
            $("#keys-list").html(keysUi.keysTemplate(data));
        });
    }
};


$(function () {
    keysUi.init();
});