<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form>	
	<acme:button code="administrator.system-configuration-sep.button.title.accepted-currencies" action="/administrator/system-configuration-sep/accepted-currencies"/>
	<acme:button code="administrator.system-configuration-sep.button.title.system-currency" action="/administrator/system-configuration-sep/system-currency"/>
	<acme:button code="administrator.system-configuration-sep.button.title.spam" action="/administrator/system-configuration-sep/spam"/>
	
	<h1>
	</h1>
	<jstl:choose>
	
		<jstl:when test="${command == 'accepted-currencies' }">
			<h2>
				<acme:message code="administrator.system-configuration.form.title.acceptedCurrencies"/>
			</h2>
			<acme:input-textbox code="administrator.system-configuration-sep.form.label.acceptedCurrencies" path="acceptedCurrencies"/>
			<acme:input-textbox code="administrator.system-configuration-sep.form.label.systemCurrency" path="systemCurrency" readonly="true"/>
			<acme:input-textbox code="administrator.system-configuration-sep.form.label.spamTuple" path="spamTuple" readonly="true"/>
			<acme:input-double code="administrator.system-configuration-sep.form.label.spamThreshold" path="spamThreshold" readonly="true"/>
			
		
		</jstl:when>
		
		<jstl:when test="${command == 'system-currency' }">
			<h2>
				<acme:message code="administrator.system-configuration.form.title.systemCurrency"/>
			</h2>
			<acme:input-textbox code="administrator.system-configuration-sep.form.label.acceptedCurrencies" path="acceptedCurrencies" readonly="true"/>
			<acme:input-select code="administrator.system-configuration-sep.form.label.systemCurrency" path="systemCurrency">
                <jstl:forEach items="${accepted}" var="acceptedCurr">
                    <acme:input-option code="${acceptedCurr}" value="${acceptedCurr}"/>
                </jstl:forEach>
			</acme:input-select>
			<acme:input-textbox code="administrator.system-configuration-sep.form.label.spamTuple" path="spamTuple" readonly="true"/>
			<acme:input-double code="administrator.system-configuration-sep.form.label.spamThreshold" path="spamThreshold" readonly="true"/>
		</jstl:when>
		
		<jstl:when test="${command == 'spam' }">
			<h2>
				<acme:message code="administrator.system-configuration.form.title.spam"/>
			</h2>
			<acme:input-textbox code="administrator.system-configuration-sep.form.label.acceptedCurrencies" path="acceptedCurrencies" readonly="true"/>
			<acme:input-textbox code="administrator.system-configuration-sep.form.label.systemCurrency" path="systemCurrency" readonly="true"/>
			<acme:input-textbox code="administrator.system-configuration-sep.form.label.spamTuple" path="spamTuple"/>
			<acme:input-double code="administrator.system-configuration-sep.form.label.spamThreshold" path="spamThreshold"/>
		</jstl:when>
	</jstl:choose>
	
	<br><br>
	
	
	<jstl:choose>
		<jstl:when test="${acme:anyOf(command, 'accepted-currencies, system-currency, spam, delete')}">
			<acme:submit code="administrator.system-configuration.form.button.update" action="/administrator/system-configuration-sep/update"/>
		</jstl:when>
	</jstl:choose>	
</acme:form>