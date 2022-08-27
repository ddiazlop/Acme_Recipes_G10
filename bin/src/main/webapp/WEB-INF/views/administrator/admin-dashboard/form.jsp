<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<h1>
	<acme:message code="administrator.dashboard.form.title.general-indicators"/>
</h1>

<%--PATRONAGE DATA --%>

<h3>
	<acme:message code="administrator.dashboard.form.title.patronage-statuses"/>
</h3>

<table class="table table-sm">
	<caption> </caption>
	<tr>
		<th id="patronages"><acme:message code="administrator.dashboard.form.title.patronages-type"/></th>
		<th id = "quantity"><acme:message code="administrator.dashboard.form.label.num"/></th>
		<th id ="average"><acme:message code="administrator.dashboard.form.label.avg"/></th>
		<th id = "desviation"><acme:message code="administrator.dashboard.form.label.desv"/></th>
		<th id = "minimum"><acme:message code="administrator.dashboard.form.label.min"/></th>
		<th id = "maximum"><acme:message code="administrator.dashboard.form.label.max"/></th>
	</tr>
	<tr>
		<td style="color:#58D68D"><strong><acme:message code="administrator.dashboard.form.title.accepted-patronages"/></strong></td>
		<td><acme:print value="${numPatronageAccepted}"/></td>
		
		<td>
		
		<jstl:forEach items="${accepteds}" var="accepted">
			<jstl:choose>
				<jstl:when test="${accepted.value.maximum.amount==0}">
					<em><acme:message code="administrator.dashboard.form.no-data"/> ${accepted.key}</em>/
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
					<em><acme:message code="administrator.dashboard.form.no-data"/> ${accepted.key}</em>/
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
					<em><acme:message code="administrator.dashboard.form.no-data"/> ${accepted.key}</em>/
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
					<em><acme:message code="administrator.dashboard.form.no-data"/> ${accepted.key}</em>/
				</jstl:when>
				<jstl:otherwise>
					<acme:print value="${accepted.value.maximum}"/>/ 
				</jstl:otherwise>
			</jstl:choose>
		</jstl:forEach>
		
		</td>
	</tr>
	
		<tr>
		<td style="color:#C0392B"><strong><acme:message code="administrator.dashboard.form.title.denied-patronages"/></strong></td>
		<td><acme:print value="${numPatronageDenied}"/></td>
		
		<td>
		<jstl:forEach items="${denieds}" var="denied">
			<jstl:choose>
				<jstl:when test="${denied.value.maximum.amount==0}">
					<em><acme:message code="administrator.dashboard.form.no-data"/> ${denied.key}</em>/
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
						<em><acme:message code="administrator.dashboard.form.no-data"/> ${denied.key}</em>/
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
						<em><acme:message code="administrator.dashboard.form.no-data"/> ${denied.key}</em>/
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
						<em><acme:message code="administrator.dashboard.form.no-data"/> ${denied.key}</em>/
					</jstl:when>
					<jstl:otherwise>
						<acme:print value="${denied.value.maximum}"/>/ 
					</jstl:otherwise>
				</jstl:choose>
			</jstl:forEach>
		</td>
	</tr>
	
		<tr>
		<td style="color:#616A6B"><strong><acme:message code="administrator.dashboard.form.title.pending-patronages"/></strong></td>
		<td><acme:print value="${numPatronageRequested}"/></td>
		
		<td>
			<jstl:forEach items="${pendings}" var="pending">
				<jstl:choose>
					<jstl:when test="${pending.value.maximum.amount==0}">
						<em><acme:message code="administrator.dashboard.form.no-data"/> ${pending.key}</em>/
					</jstl:when>
					<jstl:otherwise>
						<acme:print value="${pending.value.average}"/>/ 
					</jstl:otherwise>
				</jstl:choose>
			</jstl:forEach>
		</td>
		
		<td>
			<jstl:forEach items="${pendings}" var="pending">
				<jstl:choose>
					<jstl:when test="${pending.value.maximum.amount==0}">
						<em><acme:message code="administrator.dashboard.form.no-data"/> ${pending.key}</em>/
					</jstl:when>
					<jstl:otherwise>
						<acme:print value="${pending.value.desviation}"/>/ 
					</jstl:otherwise>
				</jstl:choose>
			</jstl:forEach>
		</td>
		
		<td>
			<jstl:forEach items="${pendings}" var="pending">
				<jstl:choose>
					<jstl:when test="${pending.value.maximum.amount==0}">
						<em><acme:message code="administrator.dashboard.form.no-data"/> ${pending.key}</em>/
					</jstl:when>
					<jstl:otherwise>
						<acme:print value="${pending.value.minimum}"/>/ 
					</jstl:otherwise>
				</jstl:choose>
			</jstl:forEach>
		</td>
		
		<td>
			<jstl:forEach items="${pendings}" var="pending">
				<jstl:choose>
					<jstl:when test="${pending.value.maximum.amount==0}">
						<em><acme:message code="administrator.dashboard.form.no-data"/> ${pending.key}</em>/
					</jstl:when>
					<jstl:otherwise>
						<acme:print value="${pending.value.maximum}"/>/ 
					</jstl:otherwise>
				</jstl:choose>
			</jstl:forEach>
		</td>
	</tr>
</table>

<br><br><br>

<%--COMPS AND TOOLS MEANS--%>

<h2>
	<acme:message code="administrator.dashboard.form.title.comps-tools-data"/>
</h2>

<table class="table table-sm">
	<caption> </caption>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.numComponents"/>
		</th>
		<td>
			<acme:print value="${numComponents}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.numTools"/>
		</th>
		<td>
			<acme:print value="${numTools}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.desviationComp"/>
		</th>

		<td>
			<jstl:forEach items="${comps}" var="comp">
				<jstl:choose>
					<jstl:when test="${comp.value.maximum.amount==0}">
						<em><acme:message code="administrator.dashboard.form.no-data"/> ${comp.key}</em>/
					</jstl:when>
					<jstl:otherwise>
						<acme:print value="${comp.value.desviation}"/>/
					</jstl:otherwise>
				</jstl:choose>
			</jstl:forEach>
		</td>
	</tr>
	
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.desviationTool"/>
		</th>
		<td>
			<jstl:forEach items="${tools}" var="tool">
				<jstl:choose>
					<jstl:when test="${tool.value.maximum.amount==0}">
						<em><acme:message code="administrator.dashboard.form.no-data"/> ${tool.key}</em>/
					</jstl:when>
					<jstl:otherwise>
						<acme:print value="${tool.value.desviation}"/>/
					</jstl:otherwise>
				</jstl:choose>
			</jstl:forEach>
		</td>
	</tr>
</table>
<br><br>
<h3>
	<acme:message code="administrator.dashboard.form.title.avg-data"/>
</h3>
<br>

<div>
	<canvas id="canvas2"></canvas>
</div>

<script type="text/javascript">
	$(document).ready(function() {
		var color = ["#819EED", "#91DC60","#F0EF85","#819EED","#91DC60","#F0EF85"]
		var data = {
		
			labels : [
					<jstl:forEach items="${comps}" var="comp">
						"COMP-"+"${comp.key}",
					</jstl:forEach>
					<jstl:forEach items="${tools}" var="tool">
						"TOOL-"+"${tool.key}",
					</jstl:forEach>
			],
			datasets : [
				{	
					backgroundColor: color,
					data : [
						<jstl:forEach items="${comps}" var="comp">
							<jstl:out value="${comp.value.average.amount}"/>,
						</jstl:forEach>
						<jstl:forEach items="${tools}" var="tool">
							<jstl:out value="${tool.value.average.amount}"/>,
						</jstl:forEach>
						
					]
				}
			]
		};
		var options = {
			scales : {
				yAxes : [
					{
						ticks : {
							suggestedMin : 0.0,
							suggestedMax : 50.0
						}
					}
				]
			},
			legend : {
				display : false
			}
		};
	
		var canvas2, context;
	
		canvas2 = document.getElementById("canvas2");
		context = canvas2.getContext("2d");
		new Chart(context, {
			type : "bar",
			data : data,
			options : options
		});
	});
</script>
<br><br><br>

<%--COMPS MAXS AND MINS --%>

<h3>
	<acme:message code="administrator.dashboard.form.title.min-max-dataComp"/>
</h3>

<div>
	<canvas id="canvas3"></canvas>
</div>

<script type="text/javascript">
	$(document).ready(function() {
		var color = ["#819EED", "#819EED","#91DC60","#91DC60","#F0EF85","#F0EF85"]
		var data = {
		
			labels : [
				<jstl:forEach items="${comps}" var="comp">
					"MIN-"+"${comp.key}"+"-COMP",
					"MAX-"+"${comp.key}"+"-COMP",
				</jstl:forEach>
					],
			datasets : [
				{	
					backgroundColor: color,
					data : [
						<jstl:forEach items="${comps}" var="comp">
							<jstl:out value="${comp.value.minimum.amount}"/>,
							<jstl:out value="${comp.value.maximum.amount}"/>,
						</jstl:forEach>
					]
				}
			]
		};
		var options = {
			scales : {
				yAxes : [
					{
						ticks : {
							suggestedMin : 0.0,
							suggestedMax : 7.0
						}
					}
				]
			},
			legend : {
				display : false
			}
		};
	
		var canvas3, context;
	
		canvas3 = document.getElementById("canvas3");
		context = canvas3.getContext("2d");
		new Chart(context, {
			type : "bar",
			data : data,
			options : options
		});
	});
</script>
<br>

<%--TOOLS MAXS AND MINS --%>

<h3>
	<acme:message code="administrator.dashboard.form.title.min-max-dataTool"/>
</h3>

<div>
	<canvas id="canvas4"></canvas>
</div>

<script type="text/javascript">
	$(document).ready(function() {
		var color = ["#819EED", "#819EED","#91DC60","#91DC60","#F0EF85","#F0EF85","#F0EF85","#F0EF85"]
		var data = {
		
			labels : [
				
				<jstl:forEach items="${tools}" var="tool">
					"MIN-"+"${tool.key}"+"-TOOL",
					"MAX-"+"${tool.key}"+"-TOOL",
				</jstl:forEach>
			],
			datasets : [
				{	
					backgroundColor: color,
					data : [
						<jstl:forEach items="${tools}" var="tool">
							<jstl:out value="${tool.value.minimum.amount}"/>,
							<jstl:out value="${tool.value.maximum.amount}"/>,
						</jstl:forEach>
					]
				}
			]
		};
		var options = {
			scales : {
				yAxes : [
					{
						ticks : {
							suggestedMin : 0.0,
							suggestedMax : 100.0
						}
					}
				]
			},
			legend : {
				display : false
			}
		};
	
		var canvas4, context;
	
		canvas4 = document.getElementById("canvas4");
		context = canvas4.getContext("2d");
		new Chart(context, {
			type : "bar",
			data : data,
			options : options
		});
	});
</script>

<h3>
	<acme:message code="administrator.dashboard.form.title.comps-data-tech"/>
</h3>

<table class="table table-sm">
	<caption> </caption>
	<tr>
		<th id="tech"><acme:message code="administrator.dashboard.form.title.tech"/></th>
		<th id= "average"><acme:message code="administrator.dashboard.form.label.avg"/></th>
		<th id="desviation"><acme:message code="administrator.dashboard.form.label.desv"/></th>
		<th id="minimum"><acme:message code="administrator.dashboard.form.label.min"/></th>
		<th id="maximum"><acme:message code="administrator.dashboard.form.label.max"/></th>
	</tr>
	
	<jstl:forEach items="${componentsDataByTechnology}" var="data">
			<tr>
			<td><acme:print value="${data['key'].getFirst()} (${data['key'].getSecond() })"></acme:print></td>
				<jstl:choose>
					<jstl:when test="${data['value'].maximum.amount==0}">
						<td><em><acme:message code="administrator.dashboard.form.no-data"/></em></td>
						<td><em><acme:message code="administrator.dashboard.form.no-data"/></em></td>
						<td><em><acme:message code="administrator.dashboard.form.no-data"/></em></td>
						<td><em><acme:message code="administrator.dashboard.form.no-data"/></em></td>					
					</jstl:when>
					<jstl:otherwise>
						<td><acme:print value="${data['value'].average}"></acme:print></td>
						<td><acme:print value="${data['value'].desviation}"></acme:print></td>
						<td><acme:print value="${data['value'].minimum}"></acme:print></td>
						<td><acme:print value="${data['value'].maximum}"></acme:print></td>
					</jstl:otherwise>
				</jstl:choose>
			</tr>
	</jstl:forEach>

</table>

<h3>
	<acme:message code="administrator.dashboard.form.title.tools-data-tech"/>
</h3>

<table class="table table-sm">
	<caption> </caption>
	<tr>
		<th id="tech"><acme:message code="administrator.dashboard.form.title.tech"/></th>
		<th id= "average"><acme:message code="administrator.dashboard.form.label.avg"/></th>
		<th id="desviation"><acme:message code="administrator.dashboard.form.label.desv"/></th>
		<th id="minimum"><acme:message code="administrator.dashboard.form.label.min"/></th>
		<th id="maximum"><acme:message code="administrator.dashboard.form.label.max"/></th>
	</tr>
	
	<jstl:forEach items="${toolsDataByTechnology}" var="data">
			<tr>
				<td><acme:print value="${data['key'].getFirst()} (${data['key'].getSecond() })"></acme:print></td>
				<jstl:choose>
					<jstl:when test="${data['value'].maximum.amount==0}">
						<td><em><acme:message code="administrator.dashboard.form.no-data"/></em></td>	
						<td><em><acme:message code="administrator.dashboard.form.no-data"/></em></td>	
						<td><em><acme:message code="administrator.dashboard.form.no-data"/></em></td>	
						<td><em><acme:message code="administrator.dashboard.form.no-data"/></em></td>					
					</jstl:when>
					<jstl:otherwise>
						<td><acme:print value="${data['value'].average}"></acme:print></td>
						<td><acme:print value="${data['value'].desviation}"></acme:print></td>
						<td><acme:print value="${data['value'].minimum}"></acme:print></td>
						<td><acme:print value="${data['value'].maximum}"></acme:print></td>
					</jstl:otherwise>
				</jstl:choose>

			</tr>
			

	</jstl:forEach>

</table>


<acme:return/>