/**
 * Created by vstalmakov on 14.10.16.
 */

var nodesUi = {
    init: function () {
        nodesUi.nodesSelect = $("#nodesAdress");
        nodesUi.nodesSelect.change(function () {
            nodesUi.loadNode();
        }).change();

        nodesUi.loadListNodes();
        nodesUi.refreshInfo();
    },

    refreshInfo: function () {
        nodesUi.loadNode();
        setTimeout(nodesUi.refreshInfo, 1000);
    },

    getSelectNode: function () {
        return $(nodesUi.nodesSelect).find("option:selected").text()
    },

    loadListNodes: function () {
        $.getJSON("/cluster/nodes", function (data) {
            nodesUi.nodesSelect.empty();
            $.each(data.nodes, function (index, item) {

                var name = item.host + ":" + item.port;
                nodesUi.nodesSelect
                    .append($("<option></option>")
                        .attr("value", name)
                        .text(name));
            });

            if (data.nodes.length > 0) {
                var name = data.nodes[0].host + ":" + data.nodes[0].port;
                nodesUi.loadNode();
                // nodesUi.getSelectNode()

            }
        });
    },

    loadNode: function () {
        var selectNode = nodesUi.getSelectNode();
        if (selectNode != "") {
            var template = Handlebars.compile($("#node-info-blank").html());
            $.getJSON("/" + selectNode + "/info", function (value) {
                $("#node-info").html(template(value));
            });
        }
    }
};