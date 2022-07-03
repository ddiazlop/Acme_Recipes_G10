<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form>	
	<acme:input-select code="administrator.system-configuration.form.label.systemCurrency" path="systemCurrency">
                <jstl:forEach items="${accepted}" var="acceptedCurr">
                    <acme:input-option code="${acceptedCurr}" value="${acceptedCurr}"/>
                </jstl:forEach>
</acme:input-select>
	
	<acme:input-textbox code="administrator.system-configuration.form.label.acceptedCurrencies" path="acceptedCurrencies"/>
	
	<br><br>
	<h2>
	<acme:message code="administrator.system-configuration.form.title.spam"/>
	</h2>
	<acme:input-textbox code="administrator.system-configuration.form.label.strongSpamTerms" path="strongSpamTerms"/>
	<acme:input-double code="administrator.system-configuration.form.label.strongSpamThreshold" path="strongSpamThreshold"/>
	<acme:input-textbox code="administrator.system-configuration.form.label.weakSpamTerms" path="weakSpamTerms"/>
	<acme:input-double code="administrator.system-configuration.form.label.weakSpamThreshold" path="weakSpamThreshold"/>
	
	<jstl:choose>
		<jstl:when test="${acme:anyOf(command, 'show, update, delete')}">
			<acme:submit code="administrator.system-configuration.form.button.update" action="/administrator/system-configuration/update"/>
		</jstl:when>
	</jstl:choose>	
</acme:form>