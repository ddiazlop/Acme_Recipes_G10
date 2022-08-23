<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form>	

	<acme:input-textbox code="administrator.systemConfigurationSep.form.label.systemCurrency" placeholder="EUR" path="systemCurrency"/>
	<acme:input-textbox code="administrator.systemConfigurationSep.form.label.acceptedCurrencies" placeholder="DIA,EUR,PSG..." path="acceptedCurrencies"/>
	<acme:input-textbox code="administrator.systemConfigurationSep.form.label.spamTuple" placeholder="Pinga:1.0,Dick:1.0,SEX:0.69,..." path="spamTuple"/>
	<acme:input-double  code="administrator.systemConfigurationSep.form.label.spamThreshold" placeholder="0.666" path="spamThreshold"/>

	<acme:submit test="${acme:anyOf(command, 'show, update' )}" code="administrator.system-configuration-sep.form.button.update" action="/administrator/system-configuration-sep/update"/>
	
</acme:form>