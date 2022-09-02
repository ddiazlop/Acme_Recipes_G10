<%@page language="java"%>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form readonly="${readonly}">
	<acme:input-textbox code="any.component.list.label.code" path="code"/>
	<acme:input-textbox code="any.component.list.label.name" path="name"/>
	<acme:input-textbox code="any.component.list.label.tech" path="technology"/>
	<acme:input-textbox code="any.component.list.label.description" path="description"/>
	<acme:input-money code="any.component.list.label.price" path="retailPrice"/>
	<acme:input-textarea code="any.component.list.label.info" path="info"/>
</acme:form>