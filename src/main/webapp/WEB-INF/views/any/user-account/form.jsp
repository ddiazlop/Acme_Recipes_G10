
<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form>
	<acme:input-textbox code="any.user-account.form.label.name" path="identity.name" readonly="true"/>
	<acme:input-textbox code="any.user-account.form.label.surname" path="identity.surname" readonly="true"/>
	<acme:input-email code="any.user-account.form.label.email" path="identity.email" readonly="true"/>
	<acme:input-textbox code="any.user-account.form.label.role-list" path="roleList" readonly="true"/>
	
	<jstl:if test="${canUpdate}">
		<acme:input-select code="any.user-account.form.label.new-status" path="newStatus">
			<acme:input-option code="KEEP" value="KEEP" selected="true"/>
			<acme:input-option code="ENABLED" value="ENABLED"/>
			<acme:input-option code="DISABLED" value="DISABLED"/>		
		</acme:input-select>		
		<acme:submit code="administrator.user-account.form.button.update" action="/administrator/user-account/update"/>
	</jstl:if>
</acme:form>
