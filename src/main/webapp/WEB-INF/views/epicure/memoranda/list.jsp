<%@page language="java"%>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="epicure.memorandum.list.label.moment" path="moment"
		width="20%" />
	<acme:list-column code="epicure.memorandum.list.label.sequenceNumber" path="sequenceNumber"
		width="20%" />
	<acme:list-column code="epicure.memorandum.list.label.chef" path="chef.userAccount.username"
		width="20%" />
		
</acme:list>
<acme:button code="epicure.memorandum.list.button.create" action="/epicure/memoranda/create"/>