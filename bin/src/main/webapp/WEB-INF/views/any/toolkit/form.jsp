<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form readonly="true">
<acme:input-textbox code="inventor.toolkit.form.label.code" path="code"/>
	<acme:input-textbox code="inventor.toolkit.form.label.title" path="title"/>
	<acme:input-textarea code="inventor.toolkit.form.label.description" path="description"/>
	<acme:input-textarea code="inventor.toolkit.form.label.assemblyNotes" path="assemblyNotes"/>
	<acme:input-textbox code="inventor.toolkit.form.label.inventor" path="inventor"/>
	<acme:input-money code="inventor.toolkit.form.label.price" path="price"/>
	<acme:input-url code="inventor.toolkit.form.label.info" path="info"/>
</acme:form>

<acme:button code="inventor.toolkit.form.label.content" action="/any/item-toolkit/list?toolkitId=${id}"/>

