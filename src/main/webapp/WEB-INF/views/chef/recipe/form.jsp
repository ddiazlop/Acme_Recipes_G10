<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form>
	<acme:input-textbox code="chef.recipes.form.label.code" path="code"/>
	<acme:input-textbox code="chef.recipes.form.label.heading" path="heading"/>
	<acme:input-textarea code="chef.recipes.form.label.description" path="description"/>
	<acme:input-textarea code="chef.recipes.form.label.preparationNotes" path="preparationNotes"/>
	<acme:input-textbox code="chef.recipes.form.label.info" path="info"/>
	<acme:input-textbox code="chef.recipes.form.label.totalPrice" path="totalPrice"/>
	<acme:input-textbox code="chef.recipes.form.label.state" path="state" readonly='true'/>

</acme:form>