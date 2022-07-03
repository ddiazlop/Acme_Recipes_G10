<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<h3>
	<acme:message code="patron.dashboard.form.title.indicators"/>
</h3>

<table class="table table-sm">
	<caption> </caption>
	<tr>
		<th id="patronages"><acme:message code="patron.dashboard.form.title.patronages-type"/></th>
		<th id = "quantity"><acme:message code="patron.dashboard.form.label.num"/></th>
		<th id ="average"><acme:message code="patron.dashboard.form.label.avg"/></th>
		<th id = "desviation"><acme:message code="patron.dashboard.form.label.desv"/></th>
		<th id = "minimum"><acme:message code="patron.dashboard.form.label.min"/></th>
		<th id = "maximum"><acme:message code="patron.dashboard.form.label.max"/></th>
	</tr>
	<tr>
		<td style="color:#58D68D"><strong><acme:message code="patron.dashboard.form.title.accepted-patronages"/></strong></td>
		<td><acme:print value="${numPatronageAccepted}"/></td>
		
		<td>
		
		<jstl:forEach items="${accepteds}" var="accepted">
			<jstl:choose>
				<jstl:when test="${accepted.value.maximum.amount==0}">
					<em><acme:message code="patron.dashboard.form.no-data"/>${accepted.key}</em>/
				</jstl:when>
				<jstl:otherwise>
					<acme:print value="${accepted.value.average}"/>/ 
				</jstl:otherwise>
			</jstl:choose>
		</jstl:forEach>
		
		</td>
		<td>
		
		<jstl:forEach items="${accepteds}" var="accepted">
			<jstl:choose>
				<jstl:when test="${accepted.value.maximum.amount==0}">
					<em><acme:message code="patron.dashboard.form.no-data"/>${accepted.key}</em>/
				</jstl:when>
				<jstl:otherwise>
					<acme:print value="${accepted.value.desviation}"/>/ 
				</jstl:otherwise>
			</jstl:choose>
		</jstl:forEach>
		
		</td>
		<td>
		
		<jstl:forEach items="${accepteds}" var="accepted">
			<jstl:choose>
				<jstl:when test="${accepted.value.maximum.amount==0}">
					<em><acme:message code="patron.dashboard.form.no-data"/>${accepted.key}</em>/
				</jstl:when>
				<jstl:otherwise>
					<acme:print value="${accepted.value.minimum}"/>/ 
				</jstl:otherwise>
			</jstl:choose>
		</jstl:forEach>
		
		</td>
		<td>
		
		<jstl:forEach items="${accepteds}" var="accepted">
			<jstl:choose>
				<jstl:when test="${accepted.value.maximum.amount==0}">
					<em><acme:message code="patron.dashboard.form.no-data"/>${accepted.key}</em>/
				</jstl:when>
				<jstl:otherwise>
					<acme:print value="${accepted.value.maximum}"/>/ 
				</jstl:otherwise>
			</jstl:choose>
		</jstl:forEach>
		
		</td>
	</tr>
	
		<tr>
		<td style="color:#C0392B"><strong><acme:message code="patron.dashboard.form.title.denied-patronages"/></strong></td>
		<td><acme:print value="${numPatronageDenied}"/></td>
		
		<td>
		<jstl:forEach items="${denieds}" var="denied">
			<jstl:choose>
				<jstl:when test="${denied.value.maximum.amount==0}">
					<em><acme:message code="patron.dashboard.form.no-data"/>${denied.key}</em>/
				</jstl:when>
				<jstl:otherwise>
					<acme:print value="${denied.value.average}"/>/ 
				</jstl:otherwise>
			</jstl:choose>
		</jstl:forEach>
		</td>
		
		<td>
			<jstl:forEach items="${denieds}" var="denied">
				<jstl:choose>
					<jstl:when test="${denied.value.maximum.amount==0}">
						<em><acme:message code="patron.dashboard.form.no-data"/>${denied.key}</em>/
					</jstl:when>
					<jstl:otherwise>
						<acme:print value="${denied.value.desviation}"/>/ 
					</jstl:otherwise>
				</jstl:choose>
			</jstl:forEach>
		</td>
		
		<td>
			<jstl:forEach items="${denieds}" var="denied">
				<jstl:choose>
					<jstl:when test="${denied.value.maximum.amount==0}">
						<em><acme:message code="patron.dashboard.form.no-data"/>${denied.key}</em>/
					</jstl:when>
					<jstl:otherwise>
						<acme:print value="${denied.value.minimum}"/>/ 
					</jstl:otherwise>
				</jstl:choose>
			</jstl:forEach>
		</td>
		
		<td>
			<jstl:forEach items="${denieds}" var="denied">
				<jstl:choose>
					<jstl:when test="${denied.value.maximum.amount==0}">
						<em><acme:message code="patron.dashboard.form.no-data"/>${denied.key}</em>/
					</jstl:when>
					<jstl:otherwise>
						<acme:print value="${denied.value.maximum}"/>/ 
					</jstl:otherwise>
				</jstl:choose>
			</jstl:forEach>
		</td>
	</tr>
	
		<tr>
		<td style="color:#616A6B"><strong><acme:message code="patron.dashboard.form.title.pending-patronages"/></strong></td>
		<td><acme:print value="${numPatronageProposed}"/></td>
		
		<td>
			<jstl:forEach items="${proposeds}" var="proposed">
				<jstl:choose>
					<jstl:when test="${proposed.value.maximum.amount==0}">
						<em><acme:message code="patron.dashboard.form.no-data"/>${proposed.key}</em>/
					</jstl:when>
					<jstl:otherwise>
						<acme:print value="${proposed.value.average}"/>/ 
					</jstl:otherwise>
				</jstl:choose>
			</jstl:forEach>
		</td>
		
		<td>
			<jstl:forEach items="${proposeds}" var="proposed">
				<jstl:choose>
					<jstl:when test="${proposed.value.maximum.amount==0}">
						<em><acme:message code="patron.dashboard.form.no-data"/>${proposed.key}</em>/
					</jstl:when>
					<jstl:otherwise>
						<acme:print value="${proposed.value.desviation}"/>/ 
					</jstl:otherwise>
				</jstl:choose>
			</jstl:forEach>
		</td>
		
		<td>
			<jstl:forEach items="${proposeds}" var="proposed">
				<jstl:choose>
					<jstl:when test="${proposed.value.maximum.amount==0}">
						<em><acme:message code="patron.dashboard.form.no-data"/>${proposed.key}</em>/
					</jstl:when>
					<jstl:otherwise>
						<acme:print value="${proposed.value.minimum}"/>/ 
					</jstl:otherwise>
				</jstl:choose>
			</jstl:forEach>
		</td>
		
		<td>
			<jstl:forEach items="${proposeds}" var="proposed">
				<jstl:choose>
					<jstl:when test="${proposed.value.maximum.amount==0}">
						<em><acme:message code="patron.dashboard.form.no-data"/>${proposed.key}</em>/
					</jstl:when>
					<jstl:otherwise>
						<acme:print value="${proposed.value.maximum}"/>/ 
					</jstl:otherwise>
				</jstl:choose>
			</jstl:forEach>
		</td>
	</tr>
</table>
<acme:return/>