<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form>
	<acme:input-textbox code="administrator.announcement.form.label.title" path="title"/>
	<acme:input-textarea code="administrator.announcement.form.label.body" path="body"/>
	<acme:input-select code="administrator.announcement.form.label.critical" path="critical">
		<acme:input-option code="administrator.announcement.form.label.TRUE" value="TRUE"/>
		<acme:input-option code="administrator.announcement.form.label.FALSE" value="FALSE"/>
	</acme:input-select>
	<acme:input-url code="administrator.announcement.form.label.link" path="link"/>	
	
	 	<jstl:choose>
		<jstl:when test="${command == 'create'}">
			<acme:input-checkbox code="administrator.announcement.form.label.confirmation" path="confirmation"/>
			<acme:submit code="administrator.announcement.form.button.create" 
			action="/administrator/announcement/create"/>
		</jstl:when>		
	</jstl:choose>		
	
</acme:form>
