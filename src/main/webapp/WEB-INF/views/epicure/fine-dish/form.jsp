<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form readonly="true">

	<acme:input-textbox code="epicure.fine-dish.form.label.fineDishId" path="fineDishId"/> 
	<acme:input-textbox code="epicure.fine-dish.form.label.status" path="status"/>
	<acme:input-textbox code="epicure.fine-dish.form.label.code" path="code"/>
	<acme:input-textbox code="epicure.fine-dish.form.label.request" path="request"/>
	<acme:input-textbox code="epicure.fine-dish.form.label.budget" path="budget"/>
	<acme:input-textbox code="epicure.fine-dish.form.label.creationDate" path="creationDate"/>	
	<acme:input-textbox code="epicure.fine-dish.form.label.startDate" path="startDate"/>
	<acme:input-textbox code="epicure.fine-dish.form.label.endDate" path="endDate"/>
	<acme:input-textbox code="epicure.fine-dish.form.label.info" path="info"/>

</acme:form>
