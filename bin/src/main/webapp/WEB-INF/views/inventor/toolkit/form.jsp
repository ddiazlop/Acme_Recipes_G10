<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form>
	<acme:input-textbox code="inventor.toolkit.form.label.code" placeholder="AAA-123 o AAA-123-A" path="code"/>
	<acme:input-textbox code="inventor.toolkit.form.label.title" path="title"/>
	<acme:input-textarea code="inventor.toolkit.form.label.description" path="description"/>
	<acme:input-textarea code="inventor.toolkit.form.label.assemblyNotes" path="assemblyNotes"/>
		<jstl:choose>	 
		<jstl:when test="${acme:anyOf(command, 'show, delete, update, publish')}">
			<acme:input-textbox readonly="true" code="inventor.toolkit.form.label.inventor" path="inventor"/>
			<acme:input-money readonly="true" code="inventor.toolkit.form.label.price" path="price"/>
		</jstl:when>
	</jstl:choose>
	<acme:input-url code="inventor.toolkit.form.label.info" path="info"/>
	<jstl:choose>	 
		<jstl:when test="${acme:anyOf(command, 'show, update, delete, publish') && published == false}">
			<acme:submit code="inventor.toolkit.form.button.delete" action="/inventor/toolkit/delete"/>
			<jstl:if test="${ableToPublish==true}">
				<acme:submit code="inventor.toolkit.form.button.publish" action="/inventor/toolkit/publish"/>
			</jstl:if>
			<acme:submit code="inventor.toolkit.form.button.update" action="/inventor/toolkit/update"/>
			<acme:button code="inventor.toolkit.form.button.add-content" action="/inventor/item-toolkit/create?toolkitId=${id}"/>
		</jstl:when>
		<jstl:when test="${command=='create'}">
			<acme:submit code="inventor.toolkit.form.button.create" action="/inventor/toolkit/create"/>
		</jstl:when>
	</jstl:choose>
</acme:form>
<br>
<br>

<jstl:if test = "${command != 'create'}">
	<acme:button code="inventor.toolkit.form.label.content" action="/inventor/item-toolkit/list?toolkitId=${id}"/>
</jstl:if>
