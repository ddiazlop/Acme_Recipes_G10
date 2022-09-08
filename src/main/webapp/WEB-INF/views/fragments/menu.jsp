<%@page language="java"
	import="acme.framework.helpers.PrincipalHelper"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:menu-bar code="master.menu.home">
	<acme:menu-left>
	
		<acme:menu-option code="master.menu.anonymous" access="isAnonymous()">
			<acme:menu-suboption code="master.menu.anonymous.user-account.list" action="/any/user-account/list" />
			<acme:menu-suboption code="master.menu.anonymous.ingredient.list" action="/any/kitchenware/list-ingredient"/>
			<acme:menu-suboption code="master.menu.anonymous.kitchen-utensils.list" action="/any/kitchenware/list-utensils"/>
			<acme:menu-suboption code="master.menu.anonymous.peep.list" action="/any/peep/list"/>
			<acme:menu-suboption code="master.menu.anonymous.recipe.list" action="/any/recipe/list"/>
			
      		<acme:menu-separator/>
			<acme:menu-suboption code="master.menu.anonymous.diedialop-favourite-link" action="http://www.stealthboats.com"/>
			<acme:menu-suboption code="master.menu.anonymous.salparram-favourite-link" action="https://sevillafc.es"/>
			<acme:menu-suboption code="master.menu.anonymous.carcardia-favourite-link" action="https://www.casadellibro.com/"/>	 
            		<acme:menu-suboption code="master.menu.anonymous.marpedrod3-favourite-link" action="https://www.instagram.com/"/>
            		<acme:menu-suboption code="master.menu.anonymous.julnavrod-favourite-link" action="https://es.linkedin.com/"/>
			<acme:menu-suboption code="master.menu.anonymous.alvsevcab-favourite-link" action="https://www.instagram.com"/>
		</acme:menu-option>
		
		<acme:menu-option code="master.menu.authenticated" access="isAuthenticated()">
			<acme:menu-suboption code="master.menu.anonymous.user-account.list" action="/any/user-account/list" />
			<acme:menu-suboption code="master.menu.authenticated.bulletin.list" action="/authenticated/bulletin/list" access="isAuthenticated()"/>
			<acme:menu-suboption code="master.menu.anonymous.ingredient.list" action="/any/kitchenware/list-ingredient"/>
			<acme:menu-suboption code="master.menu.anonymous.kitchen-utensils.list" action="/any/kitchenware/list-utensils"/>
			<acme:menu-suboption code="master.menu.anonymous.peep.list" action="/any/peep/list"/>
			<acme:menu-suboption code="master.menu.anonymous.recipe.list" action="/any/recipe/list"/>
			<acme:menu-suboption code="master.menu.authenticated.system-configuration-sep.show" action="/authenticated/system-configuration-sep/show"/>	
		
		</acme:menu-option>
		
		<acme:menu-option code="master.menu.administrator" access="hasRole('Administrator')">

			<acme:menu-suboption code="master.menu.administrator.user-accounts"	action="/administrator/user-account/list" />
			<acme:menu-suboption code="master.menu.administrator.system-configuration-sep" action="/administrator/system-configuration-sep/show"/>
			<acme:menu-suboption code="master.menu.administrator.dashboard.sep" action="/administrator/administrator-dashboard/show"/>
			<acme:menu-suboption code="master.menu.administrator.bulletin.create" action="/administrator/bulletin/create"/>
			<acme:menu-separator />
			<acme:menu-suboption code="master.menu.administrator.populate-initial" action="/administrator/populate-initial" />
			<acme:menu-suboption code="master.menu.administrator.populate-sample" action="/administrator/populate-sample" />
			<acme:menu-separator />
			<acme:menu-suboption code="master.menu.administrator.shut-down"	action="/administrator/shut-down" />

		</acme:menu-option>

		
		<acme:menu-option code="master.menu.chef" access="hasRole('Chef')">
			<acme:menu-suboption code="master.menu.chef.memoranda.list" action="/chef/memoranda/list"/>
			<acme:menu-suboption code="master.menu.chef.fineDish.list" action="/chef/fine-dish/list"/>
			<acme:menu-suboption code="master.menu.chef.kitchenware.list" action="/chef/kitchenware/list"/>
			<acme:menu-suboption code="master.menu.chef.recipe.list" action="/chef/recipe/list"/>
		</acme:menu-option>
		
		<acme:menu-option code="master.menu.epicure" access="hasRole('Epicure')">
			<acme:menu-suboption code="master.menu.epicure.fine-dish.list" action="/epicure/fine-dish/list"/>
			<acme:menu-suboption code="master.menu.epicure.memorandum.list" action="/epicure/memoranda/list"/>
			<acme:menu-suboption code="master.menu.epicure.dashboard" action="/epicure/epicure-dashboard/show"/>
		</acme:menu-option>
		
	
	</acme:menu-left>

	<acme:menu-right>
     	
     	
     	<acme:menu-option code="master.menu.user-account" access="isAuthenticated()">
		<acme:menu-suboption code="master.menu.user-account.general-data" action="/authenticated/user-account/update"/>
		<acme:menu-suboption code="master.menu.user-account.become-chef" action="/authenticated/chef/create" access="!hasRole('Chef')"/> 
		<acme:menu-suboption code="master.menu.user-account.chef" action="/authenticated/chef/update" access="hasRole('Chef')"/>
		<acme:menu-suboption code="master.menu.user-account.become-epicure" action="/authenticated/epicure/create" access="!hasRole('Epicure')"/> 
		<acme:menu-suboption code="master.menu.user-account.epicure" action="/authenticated/epicure/update" access="hasRole('Epicure')"/>
		</acme:menu-option>
     	
     	<acme:menu-option code="master.menu.sign-up" action="/anonymous/user-account/create" access="isAnonymous()" />
		<acme:menu-option code="master.menu.sign-in" action="/master/sign-in"	access="isAnonymous()" />
     	
     	
		<acme:menu-option code="master.menu.sign-out"
			action="/master/sign-out" access="isAuthenticated()" />
	</acme:menu-right>
</acme:menu-bar>

