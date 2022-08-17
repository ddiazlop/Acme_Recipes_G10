<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form>

	<acme:input-textbox code="any.kitchenware.form.label.code" path="code" />
	<acme:input-textbox code="any.kitchenware.form.label.name" path="name" />
	<acme:input-textarea code="any.kitchenware.form.label.description" path="description" />
	<acme:input-money code="any.kitchenware.form.label.retail-price" path="retailPrice" />
	<acme:input-url code="any.kitchenware.form.label.info" path="info"/>
	
	
	<jstl:choose>
		<jstl:when test="${wareType == 'INGREDIENT'}">
			<acme:input-textbox code="any.kitchenware.form.label.kitchenware-type" path="" placeholder="any.kitchenware.placeholder.type.ingredient" readonly="true"/>
		</jstl:when>
		<jstl:otherwise>
			<acme:input-textbox code="any.kitchenware.form.label.kitchenware-type" path="" placeholder="any.kitchenware.placeholder.type.kitchen-utensil" readonly="true"/>
		</jstl:otherwise>
	</jstl:choose>
	<jstl:choose>
		<jstl:when test="${published}">
			<acme:input-textbox code="any.kitchenware.list.label.published" path="" placeholder="any.kitchenware.placeholder.published" readonly="true"/>
		</jstl:when>
		<jstl:otherwise>
			<acme:input-textbox code="any.kitchenware.list.label.published" path="" placeholder="any.kitchenware.placeholder.notpublished" readonly="true"/>
		</jstl:otherwise>
	</jstl:choose>
	

</acme:form>