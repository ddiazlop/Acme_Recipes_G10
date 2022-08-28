<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form>

	<acme:input-textbox code="any.kitchenware.list.label.code" path="kitchenware.code" />
	<acme:input-textbox code="any.kitchenware.list.label.name" path="kitchenware.name" />
	<acme:input-textbox code="any.kitchenware.list.label.quantity" path="quantity" />
	
	<jstl:if test="${wareType == 'INGREDIENT'}">
		<jstl:choose>
			<jstl:when test="${unitType == 'GRAM'}">
				<acme:input-textbox code="any.kitchenware.list.label.unitType" path="" placeholder="any.kitchenware_recipe.placeholder.type.gram" readonly="true"/>
			</jstl:when>
			<jstl:when test="${unitType == 'KILO'}">
				<acme:input-textbox code="any.kitchenware.list.label.unitType" path="" placeholder="any.kitchenware_recipe.placeholder.type.kilo" readonly="true"/>
			</jstl:when>
			<jstl:when test="${unitType == 'CM3'}">
				<acme:input-textbox code="any.kitchenware.list.label.unitType" path="" placeholder="any.kitchenware_recipe.placeholder.type.cm3" readonly="true"/>
			</jstl:when>
			<jstl:when test="${unitType == 'LITER'}">
				<acme:input-textbox code="any.kitchenware.list.label.unitType" path="" placeholder="any.kitchenware_recipe.placeholder.type.liter" readonly="true"/>
			</jstl:when>
			<jstl:otherwise>
				<acme:input-textbox code="any.kitchenware.list.label.unitType" path="" placeholder="any.kitchenware_recipe.placeholder.type.spoon" readonly="true"/>
			</jstl:otherwise>
		</jstl:choose>
	</jstl:if>
	
	<acme:input-textarea code="any.kitchenware.form.label.description" path="kitchenware.description" />
	<acme:input-money code="any.kitchenware.form.label.total-price" path="price" />
	<acme:input-url code="any.kitchenware.form.label.info" path="kitchenware.info"/>
	
	
	<jstl:choose>
		<jstl:when test="${kitchenware.wareType == 'INGREDIENT'}">
			<acme:input-textbox code="any.kitchenware.form.label.kitchenware-type" path="" placeholder="any.kitchenware.placeholder.type.ingredient" readonly="true"/>
		</jstl:when>
		<jstl:otherwise>
			<acme:input-textbox code="any.kitchenware.form.label.kitchenware-type" path="" placeholder="any.kitchenware.placeholder.type.kitchen-utensil" readonly="true"/>
		</jstl:otherwise>
	</jstl:choose>
	<jstl:choose>
		<jstl:when test="${kitchenware.published}">
			<acme:input-textbox code="any.kitchenware.list.label.published" path="" placeholder="any.kitchenware.placeholder.published" readonly="true"/>
		</jstl:when>
		<jstl:otherwise>
			<acme:input-textbox code="any.kitchenware.list.label.published" path="" placeholder="any.kitchenware.placeholder.notpublished" readonly="true"/>
		</jstl:otherwise>
	</jstl:choose>
	

</acme:form>