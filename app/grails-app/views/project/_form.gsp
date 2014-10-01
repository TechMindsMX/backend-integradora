<%@ page import="com.tim.one.integradora.Project" %>



<div class="fieldcontain ${hasErrors(bean: projectInstance, field: 'parent', 'error')} ">
	<label for="parent">
		<g:message code="project.parent.label" default="Parent" />
		
	</label>
	<g:select id="parent" name="parent.id" from="${com.tim.one.integradora.Project.list()}" optionKey="id" value="${projectInstance?.parent?.id}" class="many-to-one" noSelection="['null': '']"/>

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

<div class="fieldcontain ${hasErrors(bean: projectInstance, field: 'status', 'error')} required">
	<label for="status">
		<g:message code="project.status.label" default="Status" />
		<span class="required-indicator">*</span>
	</label>
	<g:select name="status" from="${com.tim.one.integradora.ProjectStatus?.values()}" keys="${com.tim.one.integradora.ProjectStatus.values()*.name()}" required="" value="${projectInstance?.status?.name()}" />

</div>

<div class="fieldcontain ${hasErrors(bean: projectInstance, field: 'subProjects', 'error')} ">
	<label for="subProjects">
		<g:message code="project.subProjects.label" default="Sub Projects" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${projectInstance?.subProjects?}" var="s">
    <li><g:link controller="project" action="show" id="${s.id}">${s?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="project" action="create" params="['project.id': projectInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'project.label', default: 'Project')])}</g:link>
</li>
</ul>


</div>

<div class="fieldcontain ${hasErrors(bean: projectInstance, field: 'user', 'error')} required">
	<label for="user">
		<g:message code="project.user.label" default="User" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="user" name="user.id" from="${com.tim.one.integradora.User.list()}" optionKey="id" required="" value="${projectInstance?.user?.id}" class="many-to-one"/>

</div>

