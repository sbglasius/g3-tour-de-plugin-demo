<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main"/>
    <title></title>
</head>

<body>
<div class="container">
    <g:form action="search" method="POST">
        <fieldset>
            <g:if test="${flash.message}">
                <div class="alert alert-info">
                    ${flash.message}
                </div>
            </g:if>
            <div class="form-group">
                <label for="query" class="control-label">Query:</label>
                <g:textField class="form-control" name="query" value="${query}"/>
            </div>

                <div>
                    <g:submitButton name="submit" value="Query"/>
                    <g:actionSubmit action="evict" name="evict" value="Evict"/>
                    <g:link action="evictAll">Evict all</g:link>
                </div>
        </fieldset>
    </g:form>
</div>
</body>
</html>