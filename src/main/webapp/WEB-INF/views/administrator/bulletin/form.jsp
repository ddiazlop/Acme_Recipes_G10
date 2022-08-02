<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form>
	<acme:input-textbox code="administrator.bulletin.form.label.heading" path="heading"/>
	<acme:input-textarea code="administrator.bulletin.form.label.text" path="text"/>
	<acme:input-select code="administrator.bulletin.form.label.critical" path="critical">
		<acme:input-option code="administrator.bulletin.form.label.TRUE" value="TRUE"/>
		<acme:input-option code="administrator.bulletin.form.label.FALSE" value="FALSE"/>
	</acme:input-select>
	<acme:input-url code="administrator.bulletin.form.label.link" path="link"/>	
	<acme:input-checkbox code="administrator.bulletin.form.label.confirmation" path="confirmation"/>
	
	<acme:submit code="administrator.bulletin.form.button.create" action="/administrator/bulletin/create"/>	
	
</acme:form>
