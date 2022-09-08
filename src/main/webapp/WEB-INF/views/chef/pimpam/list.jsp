<%@page language="java"%>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:list>
	
	<acme:list-column code="chef.PIMPAM.list.label.title" path="title"/>
	<acme:list-column code="chef.PIMPAM.list.label.code" path="code"/>
	<acme:list-column code="chef.PIMPAM.list.label.kitchenwareCode" path="kitchenwareCode"/>
	<acme:list-column code="chef.PIMPAM.list.label.instantiationMoment" path="instantiationMoment"/>
	<acme:list-column code="chef.PIMPAM.list.label.budget" path="budget"/>
	
</acme:list>
