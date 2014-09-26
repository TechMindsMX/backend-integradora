<%@ page import="com.tim.one.integradora.Project" %>



<div class="fieldcontain ${hasErrors(bean: projectInstance, field: 'integradoId', 'error')} required">
	<label for="integradoId">
		<g:message code="project.integradoId.label" default="Integrado Id" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="integradoId" type="number" min="0" value="${projectInstance.integradoId}" required=""/>

</div>

<div class="fieldcontain ${hasErrors(bean: projectInstance, field: 'parentId', 'error')} required">
	<label for="parentId">
		<g:message code="project.parentId.label" default="Parent Id" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="parentId" type="number" min="0" value="${projectInstance.parentId}" required=""/>

</div>

<div class="fieldcontain ${hasErrors(bean: projectInstance, field: 'status', 'error')} required">
	<label for="status">
		<g:message code="project.status.label" default="Status" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="status" type="number" min="0" max="1" value="${projectInstance.status}" required=""/>

</div>

<div class="fieldcontain ${hasErrors(bean: projectInstance, field: 'name', 'error')} required">
	<label for="name">
		<g:message code="project.name.label" default="Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textArea name="name" cols="40" rows="5" maxlength="255" required="" value="${projectInstance?.name}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: projectInstance, field: 'description', 'error')} ">
	<label for="description">
		<g:message code="project.description.label" default="Description" />
		
	</label>
	<g:textArea name="description" cols="40" rows="5" maxlength="255" value="${projectInstance?.description}"/>

</div>

