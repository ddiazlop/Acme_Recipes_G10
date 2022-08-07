<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form>

	<acme:input-textbox code="any.kitchenware.form.label.code" path="code" />
	<acme:input-textbox code="any.kitchenware.form.label.name" path="name" />
	<acme:input-textarea code="any.kitchenware.form.label.description" path="description" />
	<acme:input-money code="any.kitchenware.form.label.retail-price" path="retailPrice" />
	<acme:input-url code="any.kitchenware.form.label.info" path="info"/>
	<acme:input-textbox code="any.kitchenware.form.label.kitchenware-type" path="wareType"/>
	<acme:input-textbox code="any.kitchenware.form.label.published" path="published"/>
	

</acme:form>