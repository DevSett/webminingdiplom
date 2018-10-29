<#-- @ftlvariable name="cssResources" type="java.util.Map<java.lang.String, java.util.List<java.lang.String>>" -->
<#-- @ftlvariable name="jsResources" type="java.util.Map<java.lang.String, java.util.List<java.lang.String>>" -->
<#-- @ftlvariable name="isAjax" type="java.lang.Boolean" -->

<#include "settings.ftl" />
<#--<#import "/org/springframework/web/servlet/view/freemarker/spring.ftl" as spring />-->
<#import "/WEB-INF/pages/macro/messages.ftl" as messages />

<#macro page_html>
    <@compress single_line=true>
        <#escape x as x?html>
            <#if isAjax?? && isAjax>
                <@page_body/>
            <#else>
                <!DOCTYPE html>
                <html lang="en_GB">
                    <head>
                        <@page_head/>
                    </head>
                    <#flush>
                    <body onunload="">
                        <@page_body/>

                    </body>

                </html>
            </#if>
        </#escape>
    </@compress>
</#macro>

<#macro page_head>
    <@page_meta/>

    <title>Automining</title>


    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <link href="https://fonts.googleapis.com/css?family=Lato" rel="stylesheet">
    <style>
        body {
            background-color: #16193b; /* Цвет фона веб-страницы */
        }

        .alert-text{
            padding: 25px 0 25px 0;
        }
        .alert-text h6{
            color: #ffffff;
            font-size: 15px;
            font-family: 'Lato', sans-serif;
        }
        .mining-menu a {
            color: #7fb2f0;
            text-align: center;
            padding: 5% 0% 0% 0%;
            text-decoration: none;
            font-size: 12px;
            float: right;
            font-family: 'Lato', sans-serif;

        }

        .mining-menu a:hover {
            color: #add5f7;
        }

        .granica {
            padding-right: 10%;
            padding-left: 10%;
        }

        .container {
            position: relative;
            text-align: center;

        }

        .top-right10 {
            position: absolute;
            top: -15px;
            right: 10%;
        }

        .top-right15 {
            position: absolute;
            top: -15px;
            right: 18%;
        }
        .top-right20 {
            position: absolute;
            top: -15px;
            right: 25%;
        }
        .main-text {
            background: linear-gradient(45deg, #7588f7, #eeeeee, #7588f7);
            -webkit-background-clip: text;
            -webkit-text-fill-color: transparent;
            font-family: 'Lato', sans-serif;
            padding: 0% 0% 2% 0%;
        }

        /* Full-width input fields */
        input[type=text], input[type=password], select {
            width: 100%;
            padding: 12px 20px;
            margin: 8px 0;
            display: inline-block;
            border: 1px solid #5189c2;
            color: #5189c2;
            background-color: #35478c;
            box-sizing: border-box;
            font-family: 'Lato', sans-serif;
        }

        /* Set a style for all buttons */
        .button-login {
            background-color: #5189c2;
            color: black;
            padding: 14px 20px;
            margin: 8px 0;
            border: none;
            cursor: pointer;
            width: 100%;
        }

        .button-login:hover {
            opacity: 0.8;
        }

        /* Extra styles for the cancel button */
        .cancelbtn {
            width: auto;
            padding: 10px 18px;
            color: #5189c2;
            background-color: #16193b;
            font-family: 'Lato', sans-serif;;
        }

        /* Center the image and position the close button */
        .imgcontainer {
            text-align: center;
            margin: 24px 0 12px 0;
            position: relative;
        }

        img.avatar {
            width: 40%;
            border-radius: 50%;
        }

        span.psw {
            float: right;
            padding-top: 16px;
            font-family: 'Lato', sans-serif;;
        }

        /* The Modal (background) */
        .modal {
            display: none; /* Hidden by default */
            position: fixed; /* Stay in place */
            z-index: 1; /* Sit on top */
            left: 0;
            top: 0;
            width: 100%; /* Full width */
            height: 100%; /* Full height */
            overflow: auto; /* Enable scroll if needed */
            background-color: rgb(0, 0, 0); /* Fallback color */
            background-color: rgba(0, 0, 0, 0.4); /* Black w/ opacity */
            padding-top: 60px;
        }

        /* Modal Content/Box */
        .modal-content {
            background-color: #16193b;
            margin: 5% auto 15% auto; /* 5% from the top, 15% from the bottom and centered */
            border: 1px solid #888;
            width: 50%; /* Could be more or less, depending on screen size */
        }

        /* The Close Button (x) */
        .close {
            position: absolute;
            right: 25px;
            top: 0;
            color: #000;
            font-size: 35px;
            font-weight: bold;
        }

        .close:hover,
        .close:focus {
            color: #5189c2;
            cursor: pointer;
        }

        /* Add Zoom Animation */
        .animate {
            -webkit-animation: animatezoom 0.6s;
            animation: animatezoom 0.6s
        }

        @-webkit-keyframes animatezoom {
            from {
                -webkit-transform: scale(0)
            }
            to {
                -webkit-transform: scale(1)
            }
        }

        @keyframes animatezoom {
            from {
                transform: scale(0)
            }
            to {
                transform: scale(1)
            }
        }

        /* Change styles for span and cancel button on extra small screens */
        @media screen and (max-width: 300px) {
            span.psw {
                display: block;
                float: none;
            }

            .cancelbtn {
                width: 100%;
            }
        }

        /*end login*/

        .validation_error {
            color:#888;

        }
        .button-top{
            font-family: 'Lato', sans-serif;
            background-color: transparent;
            border: solid 1px #7fb2f0;
            color: #7fb2f0;
            width: 150px;
        }
        table {
            border-collapse: separate;
            border-spacing: 0;
            width:100%;
        }

        table tr th,
        table tr td {
            border-right: 2px solid #7fb2f0;
            border-bottom: 2px solid #7fb2f0;
            padding: 15px;
            text-align: center;
            color: #7fb2f0;
            font-family: 'Lato', sans-serif;
        }
        table tr th:first-child,
        table tr td:first-child {
            border-left: 2px solid #7fb2f0;
        }
        table tr th {
            background: transparent;
            border-top: 2px solid #7fb2f0;
            text-align: center;
        }

        /* top-left border-radius */
        table tr:first-child th:first-child {
            border-top-left-radius: 6px;
        }

        /* top-right border-radius */
        table tr:first-child th:last-child {
            border-top-right-radius: 6px;
        }

        /* bottom-left border-radius */
        table tr:last-child td:first-child {
            border-bottom-left-radius: 6px;
        }

        /* bottom-right border-radius */
        table tr:last-child td:last-child {
            border-bottom-right-radius: 6px;
        }
        td:hover{
            background-color: #427cae;
        }

        tr:nth-child(even){background-color: #334368}

        h2 {
            font-family: 'Lato', sans-serif;
            color: #7fb2f0;
        }
        .column {
            float: left;
            width: 50%;
            padding: 10px;
            height: 300px; /* Should be removed. Only for demonstration */
            box-sizing: border-box;
        }

        .row:after {
            content: "";
            display: table;
            clear: both;
        }
        .register-admin{
            border-radius: 5px;
            background-color: #232769;
            padding: 20px;
            width: 80%;
            margin: auto;
        }


        .register-admin input[type=submit] {
            width: 100%;
            background-color: #171a45;
            color: white;
            padding: 14px 20px;
            margin: 8px 0;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }
        .register-admin input[type=submit]:hover {
            background-color: #262b72;
        }
    </style>
</#macro>

<#macro page_meta>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=yes" />
    <meta name="format-detection" content="telephone=no" />
    <meta name="description" content="some silly description to have on this page" />
</#macro>

<#macro page_body>

</#macro>

