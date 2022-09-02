<%@page language="java"%>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>


<acme:form>
	<acme:input-textbox code="inventor.component.list.label.code" path="code" placeholder="XXX-123, XXX-235-X"/>
	<acme:input-textbox code="inventor.component.list.label.name" path="name"/>
	<acme:input-textbox code="inventor.component.list.label.tech" path="technology"/>
	<acme:input-textbox code="inventor.component.list.label.description" path="description"/>
	<acme:input-money code="inventor.component.list.label.price" path="retailPrice"/>
	<jstl:choose>
		<jstl:when test="${command == 'show' && !retailPriceIsInSystemCurrency}">
			<acme:input-money code="inventor.component.list.label.price-system" path="systemMoney" readonly="true"/>
		</jstl:when>
	</jstl:choose>
	
	<acme:input-textarea code="inventor.component.list.label.info" path="info" placeholder="URL"/>
	
	<jstl:choose>
		<jstl:when test="${published}">
			<acme:input-textbox code="inventor.component.list.label.published" path="" placeholder="inventor.component.published" readonly="true"/>
		</jstl:when>
		<jstl:otherwise>
			<acme:input-textbox code="inventor.component.list.label.published" path="" placeholder="inventor.component.notpublished" readonly="true"/>
		</jstl:otherwise>
	</jstl:choose>
	
	
	
	<jstl:choose>
		<jstl:when test="${acme:anyOf(command, 'create-component')}">
			<acme:submit code="inventor.item.form.button.create" action="/inventor/item/create-component"/>
		</jstl:when>
		<jstl:when test="${acme:anyOf(command, 'create-tool')}">
			<acme:submit code="inventor.item.form.button.create" action="/inventor/item/create-tool"/>
		</jstl:when>
		<jstl:when test="${published == false}">
			<acme:submit code="inventor.item.form.button.update" action="/inventor/item/update"/>
			<acme:submit code="inventor.item.form.button.publish" action="/inventor/item/publish"/>
			<acme:submit code="inventor.item.form.button.delete" action="/inventor/item/delete"/>
		</jstl:when>
	</jstl:choose>
		
</acme:form> 	

<br><br><br>
