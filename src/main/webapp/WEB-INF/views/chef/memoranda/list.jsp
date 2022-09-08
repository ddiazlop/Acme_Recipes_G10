<%@page language="java"%>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

	<acme:message code="chef.memoranda.list.tip.creation"/>

<acme:list>
	
	<acme:list-column code="chef.memoranda.list.label.sequenceNumber" path="sequenceNumber"/>
	<jstl:if test="${command == 'list'}">
		<acme:list-column code="chef.memoranda.list.label.fineDishCode" path="fineDishCode"/>
	</jstl:if>
	<acme:list-column code="chef.memoranda.list.label.moment" path="moment"/>
	<acme:list-column code="chef.memoranda.list.label.epicureUsername" path="epicureUsername"/>
	
</acme:list>
