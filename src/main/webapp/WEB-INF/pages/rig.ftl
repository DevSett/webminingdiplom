<#include "layout/default.ftl" />
<#macro page_body>
<header>
    <a href="">
        <img src="/images/scroll.png" class="w3-display-right" style="position: fixed; right: 10%"/>
    </a>
</header>
    <div class="granica">
        <a href="telegram">
            <img src="/images/line.png" style="width:100%;height:100%; padding: 5% 0% 0% 0%;">
        </a>
    <div class="mining-menu">
        <a
                href="/exit"
                class="top-right10"
                style="white-space: nowrap;  cursor: pointer;"
        >Sign Out</a>

        <a style="display: none" href="statistics" class="top-right15">Statistics</a>
    </div>

    <div class="w3-container w3-center ">
        <button style="display: none" class="w3-btn button-top w3-round-large">save conf</button>
        <a style="padding-right: 50px"></a>
        <a href="reboot">
            <button class="w3-btn button-top w3-round-large">reboot</button>
        </a>

        <a style="padding-right: 50px"></a>
        <a href="off">
            <button class="w3-btn button-top w3-round-large">off</button>
        </a>
        <a style="padding-right: 50px"></a>
        <a href="">
            <button class="w3-btn button-top w3-round-large">refresh</button>
        </a>
        <a style="padding-right: 50px"></a>
        <button onclick="location.href = '/web';" class="w3-btn button-top w3-round-large">back</button>
    </div>
     <#if alert??>
    <div class="alert-text w3-center">
        <h6>
            ${alert}
        </h6>
    </div>
     </#if>

    <div class="w3-center">
        <h2>
            ${name}
        </h2>
    </div>

    <div class="row">
        <div class="column">
            <div class="w3-center">
                <table id="myTable">
                    <tr>
                        <th>Speed <img style="cursor: pointer;" onclick="sortTable(0)"
                                       src="/images/sort-table.png"/></th>
                        <th>Temperature <img style="cursor: pointer;" onclick="sortTable(1)"
                                             src="/images/sort-table.png"/>
                        </th>
                        <th>Fan speed <img style="cursor: pointer;" onclick="sortTable(2)"
                                           src="/images/sort-table.png"/></th>

                    </tr>
            <#if cards?? && (cards?size > 0)>
                <#list cards as card>
                   <tr>
                       <td>${card.speed}</td>
                       <td>${card.temperature}</td>
                       <td>${card.fanSpeed}</td>
                   </tr>
                </#list>
            </#if>
                </table>
            </div>
        </div>
        <div class="column">
            <div class="register-admin">
                <form action="updateConf" method="post">
                    <div class="w3-center">
                        <h2>
                           Configurator
                        </h2>
                    </div>
                    <label for="ip" style="color: #7fb2f0">ip</label>
                    <input type="text"  path="ip" id="ip" name="ip" placeholder="ip.." value="${conf.ip}" required>
                    <label for="port" style="color: #7fb2f0">port</label>
                    <input type="text"  path="port" id="port" name="port" placeholder="port.." value="${conf.port}" required>
                    <label for="pathToLog" style="color: #7fb2f0">pathToLog</label>
                    <input type="text"  path="pathToLog" id="pathToLog" name="pathToLog" placeholder="pathToLog.." value="${conf.pathToLog}" required>
                    <label for="rigName" style="color: #7fb2f0">rigName</label>
                    <input type="text"  path="rigName" id="rigName" name="rigName" placeholder="rigName.."  value="${conf.rigName}" required>
                    <label for="charset" style="color: #7fb2f0">charset</label>
                    <input type="text"  path="charset" id="charset" name="charset" placeholder="charset.." value="${conf.charset}" required>
                    <label for="temperatureArg" style="color: #7fb2f0">temperatureArg</label>
                    <input type="text"  path="temperatureArg" id="temperatureArg" name="temperatureArg" placeholder="temperatureArg.." value="${conf.temperatureArg}" required>
                    <label for="totalArg" style="color: #7fb2f0">totalArg</label>
                    <input type="text"  path="totalArg" id="totalArg" name="totalArg" placeholder="totalArg.." value="${conf.totalArg}" required>
                    <label for="mhsArg" style="color: #7fb2f0">mhsArg</label>
                    <input type="text"  path="mhsArg" id="mhsArg" name="mhsArg" placeholder="mhsArg.." value="${conf.mhsArg}" required>
                    <label for="type" style="color: #7fb2f0">type</label>
                    <input type="text"  path="type" id="type" name="type" placeholder="type.." value="${conf.type}" required>
                    <label for="key" style="color: #7fb2f0">key</label>
                    <input type="text"  path="key" id="key" name="key" placeholder="key.." value="${conf.key}" required>

                    <input type="submit" value="Save">

             <#if bindingResult?? && (bindingResult.getAllErrors()?size > 0)>
                 <#list bindingResult.getAllErrors() as error>
                <p class="validation_error"> ${error.defaultMessage}</p>
                 </#list>
             </#if>

                </form>
            </div>
        </div>
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