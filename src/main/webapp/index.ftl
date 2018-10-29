<#include "WEB-INF/pages/layout/default.ftl" />

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
    <div class="mining-menu ">
        <a
                class="top-right10"
                style="white-space: nowrap;  cursor: pointer;"
                onclick="document.getElementById('id01').style.display='block'">Sign Up </a>

        <a href="statistics" style="display: none" class="top-right15">Statistics</a>
    </div>


    <div class="w3-container w3-center">
        <h1 class="main-text ">Convenient and smart control of rigs</h1>
        <img src="images/main.png" style="width:85%;height:85%;">
        <img src="images/info.png" style="width:85%;height:85%; padding: 2% 0% 0% 0%">

    </div>
</div>

<div id="id01" class="modal"
     style=" <#if bindingResult?? && (bindingResult.getAllErrors()?size > 0)> display: block; </#if>">



    <form id="form_login" class="modal-content animate" action="login" method="post">

        <div class="imgcontainer">
        <span onclick="document.getElementById('id01').style.display='none'" class="close"
              title="Close Modal">&times;</span>
            <img src="images/avatar.png" alt="Avatar" class="avatar">
        </div>
        <div style="padding: 16px;">

            <label style="color: #7fb2f0;font-family: 'Lato', sans-serif;" for="login"><b>Username</b></label>
            <input id="loginfield1" path="login" type="text" placeholder="Enter Username" name="login" required>

            <label style="color:#7fb2f0; font-family: 'Lato', sans-serif;" for="password"><b>Password</b></label>
            <input type="password" placeholder="Enter Password" name="password" required>
            <button class="button-login" id="submitbtn" type="submit" style="font-family: 'Lato', sans-serif;">Login
            </button>
               <#if bindingResult?? && (bindingResult.getAllErrors()?size > 0)>
                   <#list bindingResult.getAllErrors() as error>
                <p class="validation_error"> ${error.defaultMessage}</p>
                   </#list>
               </#if>

            <label style="color: #7fb2f0; font-family: 'Lato', sans-serif;">
                <input type="checkbox" checked="checked" name="remember"> Remember me
            </label>
        </div>

        <div style="background-color:#35478c;  padding: 16px;">
            <button class="w3-button w3-blue" style="width: 100%" onclick="document.getElementById('form_login').style.display='none'; document.getElementById('form_register').style.display='block'">Register</button>

            <button type="button" onclick="document.getElementById('id01').style.display='none'"
                    class="button-login cancelbtn">
                Cancel
            </button>
            <span class="psw">Forgot <a href="#">password?</a></span>
        </div>
    </form>
    <form id="form_register" style="display: none" class="modal-content animate" action="addUser" method="post">
        <div class="imgcontainer">
        <span onclick="document.getElementById('id01').style.display='none'" class="close"
              title="Close Modal">&times;</span>
            <img src="images/avatar.png" alt="Avatar" class="avatar">
        </div>
        <div style="padding: 16px;">
            <label for="login" style="color: #7fb2f0">Login</label>
            <input type="text" path="login" id="login" name="login" placeholder="Your name.." required>

            <label for="password" style="color: #7fb2f0">Password</label>
            <input type="password" path="password" id="password" name="password" placeholder="Your password.." required>

            <label for="email" style="color: #7fb2f0">Email</label>
            <input type="text" path="email" id="email" name="email" placeholder="Your email.." required>

            <label for="account" style="color: #7fb2f0">Address account ether</label>
            <input type="text" path="account" id="account" name="account" placeholder="Your address account ether.."
                   required>

            <button class="button-login" id="submitbtn" type="submit" style="font-family: 'Lato', sans-serif;">Register
            </button>
             <#if bindingResult?? && (bindingResult.getAllErrors()?size > 0)>
                 <#list bindingResult.getAllErrors() as error>
                <p class="validation_error"> ${error.defaultMessage}</p>
                 </#list>
             </#if>

        </div>
        <div style="background-color:#35478c;  padding: 16px;">
            <button class="w3-button w3-blue" style="width: 100%" onclick="document.getElementById('form_login').style.display='block'; document.getElementById('form_register').style.display='none'">Login</button>

            <button type="button" onclick="document.getElementById('id01').style.display='none'"
                    class="button-login cancelbtn">
                Cancel
            </button>
            <span class="psw">Forgot <a href="#">password?</a></span>
        </div>
    </form>

</div>

<script>
    // Get the modal
    var modal = document.getElementById('id01');

    // When the user clicks anywhere outside of the modal, close it
    window.onclick = function (event) {
        if (event.target == modal) {
            modal.style.display = "none";
        }
    }

</script>
</#macro>

<@page_html/>