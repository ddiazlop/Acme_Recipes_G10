<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form>
	<acme:input-textbox code="authenticated.bulletin.form.label.heading" path="heading"/>
	<acme:input-moment code="authenticated.bulletin.form.label.moment" path="moment"/>
	<acme:input-textarea code="authenticated.bulletin.form.label.text" path="text"/>
	<acme:input-select code="authenticated.bulletin.form.label.critical" path="critical">
		<acme:input-option code="authenticated.bulletin.form.label.is-critical" value="TRUE" selected="${critical}"/>
		<acme:input-option code="authenticated.bulletin.form.label.not-critical" value="FALSE" selected="${!critical}"/>
	</acme:input-select>
	<acme:input-url code="authenticated.bulletin.form.label.link" path="link"/>		
	
</acme:form>
