<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form>

	<acme:input-textbox code="chef.kitchenware.form.label.code" path="code" />
	<acme:input-textbox code="chef.kitchenware.form.label.name" path="name" />
	<acme:input-textbox code="chef.kitchenware.form.label.wareType" path="wareType"/>
	<acme:input-textarea code="chef.kitchenware.form.label.description" path="description" />
	<acme:input-money code="chef.kitchenware.form.label.retailPrice" path="retailPrice" />
	<acme:input-url code="chef.kitchenware.form.label.info" path="info"/>
	<acme:input-select code="chef.kitchenware.form.label.status" path="published">
		<acme:input-option code="chef.kitchenware.form.label.published" value="TRUE"/>
		<acme:input-option code="chef.kitchenware.form.label.not-published" value="FALSE"/>
	</acme:input-select>
	

</acme:form>