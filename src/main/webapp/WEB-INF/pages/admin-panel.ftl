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
                class="top-right10"
                style="white-space: nowrap;  cursor: pointer;"
        >Sign Out</a>

        <a  style="display: none" href="statistics" class="top-right15">Statistics</a>
    </div>

    <div class="w3-center">
        <h2>
            Register new user
        </h2>
    </div>
    <div class="register-admin">
        <form action="addUser" method="post">
            <label for="login" style="color: #7fb2f0">Login</label>
            <input type="text"  path="login" id="login" name="login" placeholder="Your name.." required>

            <label for="password" style="color: #7fb2f0">Password</label>
            <input type="password"  path="password" id="password" name="password" placeholder="Your password.." required>

            <label for="email" style="color: #7fb2f0">Email</label>
            <input type="text"  path="email" id="email" name="email" placeholder="Your email.." required>

            <label for="account" style="color: #7fb2f0">Address account ether</label>
            <input type="text"  path="account" id="account" name="account" placeholder="Your address account ether.." required>

            <label for="right" style="color: #7fb2f0">Right</label>

            <select id=right" name="right" required>
                <option value="1">User</option>
                <option value="666">Admin</option>
            </select>

            <input type="submit" value="Register">
             <#if bindingResult?? && (bindingResult.getAllErrors()?size > 0)>
                 <#list bindingResult.getAllErrors() as error>
                <p class="validation_error"> ${error.defaultMessage}</p>
                 </#list>
             </#if>

        </form>
    </div>
    <div class="w3-center">
        <h2>
            Delete user
        </h2>
    </div>
    <div class="register-admin">
        <form action="delUser" method="post">
            <label for="login" style="color: #7fb2f0">Login</label>
            <input type="text" path="login" id="login" name="login" placeholder="Your name.." required>

            <input type="submit" value="Delete">
            <#if bindingResultDelUser?? && (bindingResultDelUser.getAllErrors()?size > 0)>
                <#list bindingResultDelUser.getAllErrors() as error>
                <p class="validation_error"> ${error.defaultMessage}</p>
                </#list>
            </#if>
        </form>
    </div>

    <div class="w3-center">
        <h2>
            Add monets
        </h2>
    </div>
    <div class="register-admin">
        <form action="addMonets" method="post">
            <label for="login" style="color: #7fb2f0">Login</label>
            <input type="text" path="login" id="login" name="login" placeholder="Your name.." required>
            <label for="monets" style="color: #7fb2f0">monets</label>
            <input type="text" path="password" id="password" name="password" placeholder="monets.." required>
            <input type="submit" value="Add monets">
             <#if bindingResultAddMonets?? && (bindingResultAddMonets.getAllErrors()?size > 0)>
                 <#list bindingResultAddMonets.getAllErrors() as error>
                <p class="validation_error"> ${error.defaultMessage}</p>
                 </#list>
             </#if>
        </form>
    </div>

</#macro>
<@page_html/>