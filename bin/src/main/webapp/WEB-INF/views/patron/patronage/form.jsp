<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form>
	<acme:hidden-data path="patronageId" />

	<jstl:choose>
		<jstl:when test="${command == 'create'}">
			<acme:input-textbox code="patron.patronage.label.code" path="code" />
			<acme:input-select code="patron.patronage.label.inventor" path="inventorId">
				<jstl:forEach items="${inventors}" var="inventor">
					<acme:input-option code="${inventor.identity.fullName}"	value="${inventor.id}" selected="false" />
				</jstl:forEach>
			</acme:input-select>
		</jstl:when>
					<jstl:when test="${acme:anyOf(command, 'show, update')}">
			<acme:input-textbox code="patron.patronage.label.code" path="code" readonly="true" />			
			<acme:input-textbox code="patron.patronage.label.creationDate" path="creationDate" readonly="true" />
		</jstl:when>
	</jstl:choose>

	<acme:input-textbox code="patron.patronage.label.startDate" path="startDate" />
	<acme:input-textbox code="patron.patronage.label.endDate" path="endDate" />
	<acme:input-textarea code="patron.patronage.label.legalStuff" path="legalStuff" />
	<acme:input-money code="patron.patronage.label.budget" path="budget" />
	<jstl:choose>
		<jstl:when test="${command == 'show' && !budgetIsInSystemCurrency}">
			<acme:input-money code="patron.patronage.label.budgetChanged" path="budgetChanged" readonly="true" />		
		</jstl:when>
	</jstl:choose>
	<acme:input-textarea code="patron.patronage.label.info" path="info" />
	
	<jstl:choose>
			<jstl:when test="${acme:anyOf(command, 'show, update')}">

			<acme:input-textbox code="patron.patronage.label.inventorFullName" path="inventorFullName" readonly="true" />
			<acme:input-textbox code="patron.patronage.label.inventorEmail" path="inventorEmail" readonly="true" />
			<acme:input-textbox code="patron.patronage.label.inventorCompany" path="inventorCompany" readonly="true" />
			<acme:input-textbox code="patron.patronage.label.inventorStatement" path="inventorStatement" readonly="true" />
			<acme:input-textbox code="patron.patronage.label.inventorInfo" path="inventorInfo" readonly="true" />						
		</jstl:when>

	</jstl:choose>
	
	<jstl:choose>
		<jstl:when test="${published}">
			<acme:input-textbox code="patron.patronage.label.status" path="status" readonly="true" />		
		</jstl:when>
	</jstl:choose>

	<jstl:choose>
		<jstl:when test="${command == 'create'}">
			<acme:submit code="patron.patronage.form.button.create"	action="/patron/patronage/create?patronId=${patron.id}" />
		</jstl:when>
		<jstl:when test="${command == 'show' && published == true}">
			<acme:button code="patron.patronage.form.button.patronage-reports"
				action="/patron/patronage-report/list?patronageId=${id}" />
		</jstl:when>
		<jstl:when
			test="${acme:anyOf(command, 'show, update, delete, publish') && published == false}">
			<acme:submit code="patron.patronage.form.button.update"	action="/patron/patronage/update" />
			<acme:submit code="patron.patronage.form.button.delete" action="/patron/patronage/delete" />
			<acme:submit code="patron.patronage.form.button.publish" action="/patron/patronage/publish" />
		</jstl:when>
	</jstl:choose>

</acme:form>
