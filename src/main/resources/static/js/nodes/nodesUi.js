/**
 * Created by vstalmakov on 14.10.16.
 */

var nodesUi = {
    init: function () {
        nodesUi.nodesSelect = $("#nodesAdress");
        nodesUi.nodesSelect.change(function () {
            $("select option:selected").each(function () {
                nodesUi.loadNode($(this).text());
            });
        }).change();

        nodesUi.loadListNodes();
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
                nodesUi.loadNode(name);
            }
        });
    },

    loadNode: function (nodeAdress) {
        var template = Handlebars.compile($("#node-info-blank").html());
        $.getJSON("/" + nodeAdress + "/info", function (value) {
            $("#node-info").html(template(value));
        });
    }

};