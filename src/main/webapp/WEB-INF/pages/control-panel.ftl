<#include "layout/default.ftl" />
<#macro page_body>
<header>
    <a href="">
        <img src="images/scroll.png" class="w3-display-right" style="position: fixed; right: 10%"/>
    </a>
</header>
<div class="granica">
    <a href="telegram">
        <img src="images/line.png" style="width:100%;height:100%; padding: 5% 0% 0% 0%;">
    </a>
    <div class="mining-menu">
        <a
                href="exit"
                class="top-right15"
                style="white-space: nowrap;  cursor: pointer;"
        >Sign Out</a>

        <a href="statistics" style="display: none" class="top-right20">Statistics</a>
        <a class="top-right10" style="color: #e2d200">Monets -  <#if money??>${money}<#else>Error</#if></a>
    </div>

    <div class="w3-container w3-center ">
        <button onclick="document.getElementById('id01').style.display='block';"
                class="w3-btn button-top w3-round-large ">how to connect
        </button>
        <a style="padding-right: 50px"></a>
        <button onclick="document.getElementById('id02').style.display='block';"
                class="w3-btn button-top w3-round-large">telegram
        </button>
        <a style="padding-right: 50px"></a>
        <a href="rebootAll">
            <button class="w3-btn button-top w3-round-large">reboot all</button>
        </a>
        <a style="padding-right: 50px"></a>
        <a href="offAll">
            <button class="w3-btn button-top w3-round-large">off all</button>
        </a>
        <a style="padding-right: 50px"></a>
        <a href=""
        <button class="w3-btn button-top w3-round-large" style="">refresh</button>
        </a>
    </div>
    <#if alert??>
    <div class="alert-text w3-center">
        <h6>
            ${alert}
        </h6>
    </div>
    </#if>
    <div class="alert-text">
        <h6>
            <img src="images/alert-ico.png" style="padding-bottom: 5px"/>
            If you do not get "ether", the statistics will
            not work.
        </h6>
        <h6>
            <img src="images/alert-ico.png" style="padding-bottom: 5px"/> Select the desired rig to manage it.
        </h6>
        <h6>
            <img src="images/alert-ico.png" style="padding-bottom: 5px"/>
            To update the table, click the "refresh" button.
        </h6>
    </div>
    <div class="w3-container w3-center">
        <table id="myTable">
            <tr>
                <th>Name <img style="cursor: pointer;" onclick="sortTable(0)" src="images/sort-table.png"/></th>
                <th>Average speed <img style="cursor: pointer;" onclick="sortTable(1)" src="images/sort-table.png"/>
                </th>
                <th>Average temperature <img style="cursor: pointer;" onclick="sortTable(2)"
                                             src="images/sort-table.png"/></th>
                <th>Number of video cards <img style="cursor: pointer;" onclick="sortTable(3)"
                                               src="images/sort-table.png"/></th>
                <th>Last update <img style="cursor: pointer;" onclick="sortTable(4)" src="images/sort-table.png"/></th>
            </tr>
            <#if rigs?? && (rigs?size > 0)>
                <#list rigs as statistic>
                   <tr style="cursor: pointer;" onclick="window.location='rig/${statistic.id}/';">
                       <td>${statistic.name}</td>
                       <td>${statistic.averageSpeed}</td>
                       <td>${statistic.averageTemperature}</td>
                       <td>${statistic.numberVideoCards}</td>
                       <td>${statistic.lastUpdate}</td>
                   </tr>
                </#list>
            </#if>
        </table>
    </div>
</div>
    <div id="id01" class="modal" style="display: none">


        <div id="form_how_to_connect" class="modal-content animate">
            <div class="w3-center">
                <h2>
                    How to connect?
                </h2>
            </div>
            <div class="alert-text" style="padding: 16px;">
                <h6>
                    1. Download lust java from oracle
                </h6>
                <h6>
                    2. Download and extract special application for rig <a href="" style="color: darkred">download</a>
                </h6>
                <h6>
                    3. Insert to <a style="color: darkred">configCl.properties</a> path to you log file to "PathToLog"
                </h6>
                <h6>
                    4. Insert to <a style="color: darkred"> configCl.properties</a> charset and prefixes to logs
                    information
                </h6>
                <h6>
                    5. Insert to <a style="color: darkred"> configCl.properties</a> you key: "<a
                        style="color: darkred">${keyUser}</a>"
                </h6>
                <h6>
                    6. Run application
                </h6>
            </div>
            <button class="w3-button w3-blue" style="width: 100%"
                    onclick="document.getElementById('id01').style.display='none';">Close
            </button>
        </div>
    </div>

    <div id="id02" class="modal" style="display: none">


        <div id="form_telegram" class="modal-content animate">
            <div class="w3-center">
                <h2>
                    Telegram
                </h2>
            </div>
            <div class="alert-text" style="padding: 16px;">
                <h6>
                    1. Download telegram
                </h6>
                <h6>
                    2. Find bot telegram: <a style="color: darkred">"easyrigsplus_bot"</a>
                </h6>
                <h6>
                    3. Insert you key: "<a style="color: darkred">${keyTelegram}</a>"
                </h6>
                <h6>
                    4. Working to you bot
                </h6>
            </div>
            <button class="w3-button w3-blue" style="width: 100%"
                    onclick="document.getElementById('id02').style.display='none';">Close
            </button>
        </div>
    </div>
    <script>
        function sortTable(n) {
            var table, rows, switching, i, x, y, shouldSwitch, dir, switchcount;
            switchcount = 0;
            table = document.getElementById("myTable");
            switching = true;

            dir = "asc";
            while (switching) {
                switching = false;
                rows = table.getElementsByTagName("TR");
                for (i = 1; i < (rows.length - 1); i++) {
                    shouldSwitch = false;
                    x = rows[i].getElementsByTagName("TD")[n];
                    y = rows[i + 1].getElementsByTagName("TD")[n];
                    if (dir == "asc") {
                        if (x.innerHTML.toLowerCase() > y.innerHTML.toLowerCase()) {
                            shouldSwitch = true;
                            break;
                        }
                    } else if (dir == "desc") {
                        if (x.innerHTML.toLowerCase() < y.innerHTML.toLowerCase()) {
                            shouldSwitch = true;
                            break;
                        }
                    }
                }
                if (shouldSwitch) {
                    rows[i].parentNode.insertBefore(rows[i + 1], rows[i]);
                    switching = true;
                    switchcount++;
                } else {
                    if (switchcount == 0 && dir == "asc") {
                        dir = "desc";
                        switching = true;
                    }
                }
            }
        }


    </script>
</#macro>
<@page_html/>