<%@ taglib tagdir="/WEB-INF/tags/import" prefix="import"%>
<%@ attribute name="pagetitle" required="true" rtexprvalue="true"%>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${pagetitle}</title>
    <link rel="stylesheet" href="bootstrap.min.css">
    <script src="jquery-3.4.1.js"></script>
    <script src="bootstrap.min.js"></script>
    <style>
        #navbar-logo {
            width: 50px;
            height: 60px;
        }

        #landing {
            background: url('black-ipad-on-black-table-3751404.jpg');
            background-size: cover;
            background-repeat: no-repeat;
            background-origin: border-box;
        }
    </style>
</head>