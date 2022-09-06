<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:list>

	<acme:list-column code="chef.fineDish.list.label.code" path="code"/>
	<acme:list-column code="chef.fineDish.list.label.budget" path="budget"/>
	<acme:list-column code="chef.fineDish.list.label.startDate" path="startDate"/>
	<acme:list-column code="chef.fineDish.list.label.endDate" path="endDate"/>
	<acme:list-column code="chef.fineDish.list.label.status" path="status"/>
	<acme:list-column code="chef.fineDish.list.label.epicureUserName" path="epicureUserName"/>

			
</acme:list>