<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form>

	<jstl:if test="${command!='create'}">
		<acme:input-textbox
			code="chef.kitchenwareRecipe.form.label.kitchenware.code"
			path="kitchenware.code" readonly="true"/>
		<acme:input-textbox
			code="chef.kitchenwareRecipe.form.label.kitchenware.name"
			path="kitchenware.name" readonly="true"/>
	</jstl:if>
	


	<acme:input-integer code="chef.kitchenware-recipe.form.label.quantity"
		path="quantity" />

	<jstl:if test="${wareType == 'INGREDIENT' or type == 'INGREDIENT'}">
		<acme:input-select code="chef.kitchenwareRecipe.form.label.unitType"
			path="unitType">
			<acme:input-option code="GRAM" value="${enumGRAM}"
				selected="${unitType == 'GRAM'}" />
			<acme:input-option code="KILO" value="${enumKILO }"
				selected="${unitType == 'KILO'}" />
			<acme:input-option code="CM3" value="${enumCM3}"
				selected="${unitType == 'CM3'}" />
			<acme:input-option code="LITER" value="${enumLITER}"
				selected="${unitType == 'LITER'}" />
			<acme:input-option code="SPOON" value="${enumSPOON}"
				selected="${unitType == 'SPOON'}" />
		</acme:input-select>
	</jstl:if>
	
	

	<jstl:if test="${command!='create'}">

		<acme:input-textarea
			code="chef.kitchenwareRecipe.form.label.kitchenware.description"
			path="kitchenware.description" readonly="true"/>
		<acme:input-textbox
			code="chef.kitchenwareRecipe.form.label.kitchenware.retailPrice"
			path="retailPrice" readonly="true"/>
		<acme:input-textbox
			code="chef.kitchenwareRecipe.form.label.kitchenware.retailPrice-original"
			path="kitchenware.retailPrice" readonly="true"/>

			<acme:input-textbox
				code="chef.kitchenwareRecipe.form.label.kitchenware.retailPrice-total"
				path="totalPrice" readonly="true"/>
		<acme:input-textbox
			code="chef.kitchenwareRecipe.form.label.kitchenware.info"
			path="kitchenware.info" readonly="true"/>


	</jstl:if>

	<jstl:if test="${command=='create'}">
	
		<acme:input-textbox code="chef.kitchenware-recipe.form.label.kitchenware" path="kitchenwareCode" placeholder="AB:CDE-123, ABC-123..."/>

		<acme:submit
			code="chef.kitchenware-recipe.form.button.add-kitchenware"
			action="/chef/kitchenware-recipe/create?recipeId=${recipe.id}&type=${type}" />
	</jstl:if>


	<jstl:if test="${command!='create' or published}">
		<acme:submit
		code="chef.kitchenwareRecipe.form.button.update-kitchenwareRecipe"
		action="/chef/kitchenware-recipe/update" />
	<acme:submit
		code="chef.kitchenwareRecipe.form.button.delete-kitchenwareRecipe"
		action="/chef/kitchenware-recipe/delete" />
	</jstl:if>
	


</acme:form>







