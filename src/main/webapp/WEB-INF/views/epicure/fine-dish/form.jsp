<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form>
	<acme:input-textbox code="epicure.fine-dish.form.label.code" path="code" readonly="${readOnly}"/>
	<acme:input-textbox code="epicure.fine-dish.form.label.request" path="request" />
	<acme:input-textbox code="epicure.fine-dish.form.label.status" path="status" readonly="true"/>
	<acme:input-textbox code="epicure.fine-dish.form.label.budget" path="budget"/>
	<acme:input-textbox code="epicure.fine-dish.form.label.creationDate" path="creationDate" readonly="true" />	
	<acme:input-textbox code="epicure.fine-dish.form.label.startDate" path="startDate"/>
	<acme:input-textbox code="epicure.fine-dish.form.label.endDate" path="endDate"/>
	<acme:input-textbox code="epicure.fine-dish.form.label.info" path="info" />
	<jstl:if test="${command == 'create'}">
		<acme:input-select code="epicure.fine-dish.form.label.chef.username" path="chefUsername" >
			<jstl:forEach items = "${chefs}" var= "chef">
				<acme:input-option code="${chef.userAccount.username}" value="${chef.userAccount.username}" />
			</jstl:forEach>
		</acme:input-select>
	</jstl:if>
	<jstl:if test="${command != 'create'}">
		<acme:input-textbox code="epicure.fine-dish.form.label.chef.username" path="chef.username" readonly="true"/>
		<acme:input-textbox code="epicure.fine-dish.form.label.chef.organisation" path="chef.organisation" readonly="true"/>
		<acme:input-textbox code="epicure.fine-dish.form.label.chef.assertion" path="chef.assertion" readonly="true"/>
		<acme:input-textbox code="epicure.fine-dish.form.label.chef.link" path="chef.link" readonly="true"/>
	</jstl:if>
	
	<jstl:if test="${command == 'create'}">
		<acme:submit code="epicure.fine-dish.form.submit.create" action="/epicure/fine-dish/create"/>
	</jstl:if>
</acme:form>


	