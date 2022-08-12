<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form>	
	<acme:button code="administrator.system-configuration.button.title.accepted-currencies" action="/administrator/system-configuration-sep/accepted-currencies"/>
	<acme:button code="administrator.system-configuration.button.title.system-currency" action="/administrator/system-configuration-sep/system-currency"/>
	<acme:button code="administrator.system-configuration.button.title.spam" action="/administrator/system-configuration-sep/spam"/>
	
	<h1>
	</h1>
	<jstl:choose>
	
		<jstl:when test="${command == 'accepted-currencies' }">
			<acme:input-textbox code="administrator.system-configuration.form.label.acceptedCurrencies" path="acceptedCurrencies"/>
		</jstl:when>
		
		<jstl:when test="${command == 'system-currency' }">
			<acme:input-select code="administrator.system-configuration.form.label.systemCurrency" path="systemCurrency">
                <jstl:forEach items="${accepted}" var="acceptedCurr">
                    <acme:input-option code="${acceptedCurr}" value="${acceptedCurr}"/>
                </jstl:forEach>
			</acme:input-select>
		</jstl:when>
		
		<jstl:when test="${command == 'spam' }">
			<h2>
				<acme:message code="administrator.system-configuration.form.title.spam"/>
			</h2>
			<acme:input-textbox code="administrator.system-configuration.form.label.spamTuple" path="spamTerms"/>
			<acme:input-double code="administrator.system-configuration.form.label.spamThreshold" path="spamThreshold"/>
		</jstl:when>
	</jstl:choose>
	
	<br><br>
	
	
	<jstl:choose>
		<jstl:when test="${acme:anyOf(command, 'accepted-currencies, system-currency, spam, update, delete')}">
			<acme:submit code="administrator.system-configuration.form.button.update" action="/administrator/system-configuration-sep/update"/>
		</jstl:when>
	</jstl:choose>	
</acme:form>