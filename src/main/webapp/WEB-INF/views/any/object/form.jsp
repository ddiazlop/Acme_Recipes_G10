<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form>

	<acme:input-textbox code="any.object.form.label.code" path="code" />
	<acme:input-textbox code="any.object.form.label.name" path="name" />
	<acme:input-textarea code="any.object.form.label.description" path="description" />
	<acme:input-money code="any.object.form.label.retail-price" path="retailPrice" />
	<acme:input-url code="any.object.form.label.info" path="info"/>
	<acme:input-textbox code="any.object.form.label.object-type" path="objectType"/>
	<acme:input-textbox code="any.object.form.label.published" path="published"/>
	

</acme:form>