<fieldset>
    <g:each in="${domainClass.persistentProperties}" var="p">
        <div class="form-group">
            <label id="${p.name}-label" class="col-sm-2 control-label"><g:message code="${domainClass.propertyName}.${p.name}.label" default="${p.naturalName}"/></label>
            ${body(p)}
        </div>
    </g:each>
</fieldset>
