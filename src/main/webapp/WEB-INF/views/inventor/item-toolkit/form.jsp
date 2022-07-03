<%@page language="java"%>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>
<acme:form>

	<jstl:choose>
		<jstl:when test="${published == true}">
			<acme:input-integer readonly="true" code="inventor.item-toolkit.form.label.quantity" path="quantity"/>
		</jstl:when>
		<jstl:otherwise>
			<acme:input-integer code="inventor.item-toolkit.form.label.quantity" path="quantity"/>
		</jstl:otherwise>
	</jstl:choose>
	
	<jstl:choose>
		<jstl:when test="${command=='create'}">		 
			<acme:input-select code="inventor.item-toolkit.form.label.item" path="itemId">
				<jstl:forEach items="${items}" var="item">
					<acme:input-option code="${item.name}" value="${item.id}"/>
				</jstl:forEach>
			</acme:input-select>
			
			<acme:submit code="inventor.item-toolkit.form.button.add-item" action="/inventor/item-toolkit/create?toolkitId=${toolkit.id}"/>
		</jstl:when>
		
		<jstl:when test="${acme:anyOf(command, 'show, delete, update')}">
			<acme:input-textbox readonly="true" code="inventor.item-toolkit.form.label.item" path="item.name"/>
			<br>
			<br>
		</jstl:when>
	</jstl:choose>
		<jstl:if test="${published == false}">
			<acme:submit code="inventor.item-toolkit.form.button.update-item" action="/inventor/item-toolkit/update"/>
			<acme:submit code="inventor.item-toolkit.form.button.delete-item" action="/inventor/item-toolkit/delete"/>
		</jstl:if>



</acme:form>

