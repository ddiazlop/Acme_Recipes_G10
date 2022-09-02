<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form readonly="true">
	<acme:input-textbox code="authenticated.system-configuration.form.label.systemCurrency" path="systemCurrency"/>
	<acme:input-textbox code="authenticated.system-configuration.form.label.acceptedCurrencies" path="acceptedCurrencies"/>
	
	<acme:button code="authenticated.money-exchange.form.button.perform" action="/authenticated/money-exchange/perform"/>
</acme:form>