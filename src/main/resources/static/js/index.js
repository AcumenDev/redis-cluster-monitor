/**
 * Created by vstalmakov on 10.10.16.
 */
$(function () {
    //  $("#content").load("cluster.html");
    //   loadCluster();
    
    refreshInfo();

    function refreshInfo() {
        loadInfo();
        loadNodes();
        setTimeout(loadInfo, 10000);
    }

    $('header a').click(function (index, value) {
        var id = $(this).attr('id');
        var showElement = id.replace("Nav", ".html");
        includeHtml(showElement);
    });
    function includeHtml(showElement) {
        $("#content").load(showElement);
        //load data
        if (showElement.indexOf("node") > -1)
            loadNode();
        else if (showElement.indexOf("cluster") > -1)
            loadCluster();
        else if (showElement.indexOf("chart") > -1)
            loadChart();
        else
            loadCmd();
    }

    function loadInfo() {
        var template = Handlebars.compile($("#info-blank").html());
        $.getJSON("/cluster/info", function (value) {
            $("#info").html(template(value));  //fill single table
        });

    }

    function loadNodes() {
        var template = Handlebars.compile($("#nodes-blank").html());
        $.getJSON("/cluster/nodes", function (value) {
            $("#nodes").html(template(value));
        });


    }

    function loadNode() {
        $.getJSON("/cluster/master", function (data) {
            var options = "";
            $.each(data, function (index, value) {
                options += '<option>' + value.host + ":" + value.port + '</option>';
            });
            $('.nodeSelect').append(options);
            fillNodeData($(".nodeSelect option:selected").text());

            $(".nodeSelect").change(function () {
                fillNodeData($(".nodeSelect option:selected").text());
            });
        });
    }

    function fillNodeData(hostPort) {
        var uri = "/" + hostPort + "/info";
        $.getJSON(uri, function (data) {
            $("#memory").fillTable(data.memory);  //fill memory
            $("#cpu").fillTable(data.cpu);  //fill cpu
            $("#cpu").fillTable(data.cluster);  //fill cluster
            $("#cpu").fillTable(data.keyspace);  //fill keyspace

            $("#stats").fillTable(data.stats);  //fill stats
            $("#persistence").fillTable(data.persistence);  //fill persistence

            $("#server").fillTable(data.server);  //fill server
            $("#clients-replication").fillTable(data.clients);  //fill clients
            $("#clients-replication").fillTable(data.replication);  //fill replication
            var slaveSize = data.replication.connected_slaves;  //slaves
            for (var i = 0; i < slaveSize; i++) {

            }
        });
    }
});