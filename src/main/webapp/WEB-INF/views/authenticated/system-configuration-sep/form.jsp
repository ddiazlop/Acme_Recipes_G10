<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form readonly="true">
	<acme:input-textbox code="authenticated.system-configuration-sep.form.label.systemCurrency" path="systemCurrency"/>
	<acme:input-textbox code="authenticated.system-configuration-sep.form.label.acceptedCurrencies" path="acceptedCurrencies"/>
	
	<acme:button code="authenticated.money-exchange-sep.form.button.perform" action="/authenticated/money-exchange-sep/perform"/>
</acme:form>