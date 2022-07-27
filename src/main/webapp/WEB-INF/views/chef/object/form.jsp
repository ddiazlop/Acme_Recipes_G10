<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form>
	
	<jstl:if test="${command == 'create-ingredient'}">
		<acme:input-textbox code="chef.object.form.label.code" path="code" />
	</jstl:if>
	<jstl:if test="${command == 'show'}">
		<acme:input-textbox code="chef.object.form.label.code" path="code" readonly="true" />
	</jstl:if>
	<acme:input-textbox code="chef.object.form.label.name" path="name" />
	<acme:input-textarea code="chef.object.form.label.description" path="description" />
	<acme:input-money code="chef.object.form.label.retail-price" path="retailPrice" />
	<acme:input-url code="chef.object.form.label.info" path="info"/>
	<acme:input-textbox code="chef.object.form.label.object-type" path="objectType" readonly="true" />
	<acme:input-textbox code="chef.object.form.label.published" path="published" readonly="true"/>
	<jstl:if test="${published == false}">
			<acme:submit code="chef.object.form.submit.update" action="/chef/object/update"/>
			<acme:submit code="chef.object.form.submit.publish" action="/chef/object/publish"/>
			<acme:submit code="chef.object.form.submit.delete" action="/chef/object/delete"/>
	</jstl:if>
	<jstl:if test="${command == 'create-ingredient'}">
		<acme:submit code="chef.object.form.submit.create" action="/chef/object/create-ingredient"/>
	</jstl:if>
	
</acme:form>
