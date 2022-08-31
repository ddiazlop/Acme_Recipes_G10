<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<h1>
	<acme:message code="administrator.dashboard.form.title.general-indicators"/>
</h1>

<%--PATRONAGE DATA --%>

<h3>
	<acme:message code="administrator.dashboard.form.title.FineDish-statuses"/>
</h3>

<table class="table table-sm">
	<caption> </caption>
	<tr>
		<th id="dishes"><acme:message code="administrator.dashboard.form.title.dishes-type"/></th>
		<th id = "quantity"><acme:message code="administrator.dashboard.form.label.num"/></th>
		<th id ="average"><acme:message code="administrator.dashboard.form.label.avg"/></th>
		<th id = "desviation"><acme:message code="administrator.dashboard.form.label.desv"/></th>
		<th id = "minimum"><acme:message code="administrator.dashboard.form.label.min"/></th>
		<th id = "maximum"><acme:message code="administrator.dashboard.form.label.max"/></th>
	</tr>
	<tr>
		<td style="color:#58D68D"><strong><acme:message code="administrator.dashboard.form.title.accepted-dishes"/></strong></td>
		<td><acme:print value="${numDishesAccepted}"/></td>
		
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
		<td style="color:#C0392B"><strong><acme:message code="administrator.dashboard.form.title.denied-dishes"/></strong></td>
		<td><acme:print value="${numDishesDenied}"/></td>
		
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
		<td style="color:#616A6B"><strong><acme:message code="administrator.dashboard.form.title.pending-dishes"/></strong></td>
		<td><acme:print value="${numDishesRequested}"/></td>
		
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

<%--INGREDIENTS AND KITCHEN_UTENSILS MEANS--%>

<h2>
	<acme:message code="administrator.dashboard.form.title.ingredients-kitchenUtensils-data"/>
</h2>

<table class="table table-sm">
	<caption> </caption>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.numOfIngredients"/>
		</th>
		<td>
			<acme:print value="${numOfIngredients}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.numOfKitchenUtensils"/>
		</th>
		<td>
			<acme:print value="${numOfKitchenUtensils}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.desviationIngredients"/>
		</th>

		<td>
			<jstl:forEach items="${ingredients}" var="ingredient">
				<jstl:choose>
					<jstl:when test="${ingredient.value.maximum.amount==0}">
						<em><acme:message code="administrator.dashboard.form.no-data"/> ${ingredient.key}</em>/
					</jstl:when>
					<jstl:otherwise>
						<acme:print value="${ingredient.value.desviation}"/>/
					</jstl:otherwise>
				</jstl:choose>
			</jstl:forEach>
		</td>
	</tr>
	
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.desviationKitchenUtensils"/>
		</th>
		<td>
			<jstl:forEach items="${kitchenUtensils}" var="kitchen">
				<jstl:choose>
					<jstl:when test="${kitchen.value.maximum.amount==0}">
						<em><acme:message code="administrator.dashboard.form.no-data"/> ${kitchen.key}</em>/
					</jstl:when>
					<jstl:otherwise>
						<acme:print value="${kitchen.value.desviation}"/>/
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
					<jstl:forEach items="${kitchenUtensils}" var="kitchen">
						"KITCHEN-"+"${kitchen.key}",
					</jstl:forEach>
					<jstl:forEach items="${ingredients}" var="ingredient">
						"INGREDIENT-"+"${ingredient.key}",
					</jstl:forEach>
			],
			datasets : [
				{	
					backgroundColor: color,
					data : [
						<jstl:forEach items="${kitchenUtensils}" var="kitchen">
							<jstl:out value="${kitchen.value.average.amount}"/>,
						</jstl:forEach>
						<jstl:forEach items="${ingredients}" var="ingredient">
							<jstl:out value="${ingredient.value.average.amount}"/>,
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

<%--KITCHEN_UTENSILS MAXS AND MINS --%>

<h3>
	<acme:message code="administrator.dashboard.form.title.min-max-dataKitchen"/>
</h3>

<div>
	<canvas id="canvas3"></canvas>
</div>

<script type="text/javascript">
	$(document).ready(function() {
		var color = ["#819EED", "#819EED","#91DC60","#91DC60","#F0EF85","#F0EF85"]
		var data = {
		
			labels : [
				<jstl:forEach items="${kitchenUtensils}" var="kitchen">
					"MIN-"+"${kitchen.key}"+"-KITCHEN",
					"MAX-"+"${kitchen.key}"+"-KITCHEN",
				</jstl:forEach>
					],
			datasets : [
				{	
					backgroundColor: color,
					data : [
						<jstl:forEach items="${kitchenUtensils}" var="kitchen">
							<jstl:out value="${kitchen.value.minimum.amount}"/>,
							<jstl:out value="${kitchen.value.maximum.amount}"/>,
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

<%--INGREDIENTS MAXS AND MINS --%>

<h3>
	<acme:message code="administrator.dashboard.form.title.min-max-dataIngredient"/>
</h3>

<div>
	<canvas id="canvas4"></canvas>
</div>

<script type="text/javascript">
	$(document).ready(function() {
		var color = ["#819EED", "#819EED","#91DC60","#91DC60","#F0EF85","#F0EF85","#F0EF85","#F0EF85"]
		var data = {
		
			labels : [
				
				<jstl:forEach items="${ingredients}" var="ingredient">
					"MIN-"+"${ingredient.key}"+"-INGREDIENT",
					"MAX-"+"${ingredient.key}"+"-INGREDIENT",
				</jstl:forEach>
			],
			datasets : [
				{	
					backgroundColor: color,
					data : [
						<jstl:forEach items="${ingredients}" var="ingredient">
							<jstl:out value="${ingredient.value.minimum.amount}"/>,
							<jstl:out value="${ingredient.value.maximum.amount}"/>,
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

<acme:return/>