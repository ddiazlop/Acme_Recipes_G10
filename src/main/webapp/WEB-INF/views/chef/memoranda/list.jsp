<%@page language="java"%>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:list>
	
	<acme:list-column code="chef.memoranda.list.label.sequenceNumber" path="sequenceNumber"/>
	<acme:list-column code="chef.memoranda.list.label.fineDishCode" path="fineDishCode"/>
	<acme:list-column code="chef.memoranda.list.label.moment" path="moment"/>
	<acme:list-column code="chef.memoranda.list.label.epicureUsername" path="epicureUsername"/>
	
</acme:list>