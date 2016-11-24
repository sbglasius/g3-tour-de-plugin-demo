<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main"/>
    <title></title>
</head>

<body>
<div class="container">
    <g:form action="sendgrid" method="POST">
        <fieldset>
            <g:if test="${flash.message}">
                <div class="alert alert-info">
                    ${flash.message}
                </div>
            </g:if>
            <div class="row">
                <div class="col-sm-6">

                    <f:with bean="mailCommand">
                        <f:field property="to"/>
                        <f:field property="subject"/>
                        <f:field property="text"/>
                    </f:with>
                </div>

            </div>

            <div class="row">
                <div class="col-sm-12">
                    <g:submitButton name="send" value="Send"/>
                    <button type="reset">Clear</button>
                </div>
            </div>
        </fieldset>
    </g:form>
</div>

</body>
</html>