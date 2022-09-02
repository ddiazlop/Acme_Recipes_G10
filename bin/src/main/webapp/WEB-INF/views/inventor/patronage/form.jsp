

<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form>
	<acme:hidden-data path="patronageId"/>

	<acme:input-textbox code="inventor.patronage.label.code" path="code" readonly="true"/>
	<jstl:if test="${command == 'show'}">
		<acme:input-textbox code="inventor.patronage.label.creationDate" path="creationDate" readonly="true"/>
		<acme:input-textbox code="inventor.patronage.label.startDate" path="startDate" readonly="true"/>
		<acme:input-textbox code="inventor.patronage.label.endDate" path="endDate" readonly="true"/>
		<acme:input-textbox code="inventor.patronage.label.status" path="status" readonly="true"/>
	</jstl:if>	
	<acme:input-textarea code="inventor.patronage.label.legalStuff" path="legalStuff" readonly="true"/>
	<acme:input-money code="inventor.patronage.label.budget" path="budget" readonly="true"/>
	<jstl:choose>
		<jstl:when test="${command == 'show' && !budgetIsInSystemCurrency}">
			<acme:input-money code="inventor.patronage.label.budgetChanged" path="budgetChanged" readonly="true" />		
		</jstl:when>
	</jstl:choose>
	
	<acme:input-textarea code="inventor.patronage.label.info" path="info" readonly="true"/>			
	
	<acme:input-textbox code="inventor.patronage.label.patronFullName" path="patronFullName" readonly="true"/>
	<acme:input-textbox code="inventor.patronage.label.patronEmail" path="patronEmail" readonly="true"/>
	<acme:input-textbox code="inventor.patronage.label.patronCompany" path="patronCompany" readonly="true"/>
	<acme:input-textbox code="inventor.patronage.label.patronStatement" path="patronStatement" readonly="true"/>
	<acme:input-textbox code="inventor.patronage.label.patronInfo" path="patronInfo" readonly="true"/>
	
	<jstl:if test="${status.name() == 'PROPOSED'}">
		<acme:submit code="inventor.patronage.form.button.accept" action="/inventor/patronage/change-status?operation=accept"/>		
		<acme:submit code="inventor.patronage.form.button.deny" action="/inventor/patronage/change-status?operation=deny"/>		
	</jstl:if>
	
	<acme:button code="inventor.patronage.form.button.patronage-reports" action="/inventor/patronage-report/list?masterId=${id}"/>			

	
	<acme:button test="${showCreate}" code="inventor.patronage-report.list.button.create" action="/inventor/patronage-report/create?patronageId=${id}"/>	
</acme:form>

