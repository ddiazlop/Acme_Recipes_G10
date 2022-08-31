<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:list readonly="false">


 	<acme:list-column code="epicure.fine-dish.list.label.code" path="code" width="20%"/>
 	<acme:list-column code="epicure.fine-dish.list.label.request" path="request" width="20%"/>
 	<acme:list-column code="epicure.fine-dish.list.label.status" path="status" width="20%"/>
 
 	
			
</acme:list>
