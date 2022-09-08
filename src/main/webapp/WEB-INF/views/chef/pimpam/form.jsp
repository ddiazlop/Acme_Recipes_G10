<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form readonly="true">

	<acme:input-textbox code="chef.pimpam.form.label.code" path="code"/>	
	<acme:input-textbox code="chef.pimpam.form.label.title" path="title" />
	<acme:input-textarea code="chef.pimpam.form.label.description" path="description"/>
	<acme:input-textbox code="chef.fineDish.label.form.instantiationMoment" path="instantiationMoment"/>
	<acme:input-textbox code="chef.fineDish.label.form.startDate" path="startDate"/>
	<acme:input-textbox code="chef.fineDish.label.form.endDate" path="endDate"/>
	<acme:input-money code="chef.pimpam.form.label.budget" path="budget" /> 
	<acme:input-url code="chef.pimpam.form.label.info" path="info"/>
	

</acme:form>