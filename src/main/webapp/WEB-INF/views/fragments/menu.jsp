<%@page language="java"
	import="acme.framework.helpers.PrincipalHelper,acme.roles.Patron,acme.roles.Inventor"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:menu-bar code="master.menu.home">
	<acme:menu-left>
	
		<acme:menu-option code="master.menu.anonymous" access="isAnonymous()">
      		<acme:menu-suboption code="master.menu.anonymous.toolkit.list" action="/any/toolkit/list"/>
			<acme:menu-suboption code="master.menu.anonymous.user-account.list" action="/any/user-account/list" />
			<acme:menu-suboption code="master.menu.anonymous.chirp.list" action="/any/chirp/list"/>
			<acme:menu-suboption code="master.menu.anonymous.component.list" action="/any/item/list-component"/>
			<acme:menu-suboption code="master.menu.anonymous.tool.list" action="/any/item/list-tool"/>
			
      		<acme:menu-separator/>
			<acme:menu-suboption code="master.menu.anonymous.manferrod6-favourite-link" action="http://www.gol.gg"/>
			<acme:menu-suboption code="master.menu.anonymous.diedialop-favourite-link" action="https://www.sanfransentinel.com/youtube998.html"/>
			<acme:menu-suboption code="master.menu.anonymous.mansanrod4-favourite-link" action="https://defonic.com"/>
			<acme:menu-suboption code="master.menu.anonymous.aisqazcev-favourite-link" action="https://www.netflix.com/watch/70151933?trackId=14277283&tctx=-97%2C-97%2C%2C%2C%2C%2C%2C"/>
			<acme:menu-suboption code="master.menu.anonymous.olisanrod-favourite-link" action="https://google.com"/>
		</acme:menu-option>
		
		<acme:menu-option code="master.menu.authenticated" access="isAuthenticated()">
		    <acme:menu-suboption code="master.menu.authenticated.toolkit.list" action="/any/toolkit/list"/>
			<acme:menu-suboption code="master.menu.authenticated.user-account.list" action="/any/user-account/list" />
			<acme:menu-suboption code="master.menu.authenticated.chirp.list" action="/any/chirp/list"/>
      		<acme:menu-suboption code="master.menu.authenticated.announcement.list" action="/authenticated/announcement/list" access="isAuthenticated()"/>
      		<acme:menu-suboption code="master.menu.authenticated.component.list" action="/any/item/list-component"/>
			<acme:menu-suboption code="master.menu.authenticated.tool.list" action="/any/item/list-tool"/>
			<acme:menu-suboption code="master.menu.authenticated.system-configuration.show" action="/authenticated/system-configuration/show"/>	
		</acme:menu-option>
		
		<acme:menu-option code="master.menu.administrator" access="hasRole('Administrator')">

			<acme:menu-suboption code="master.menu.administrator.user-accounts"	action="/administrator/user-account/list" />
			<acme:menu-suboption code="master.menu.administrator.system-configuration" action="/administrator/system-configuration/show"/>
			<acme:menu-suboption code="master.menu.administrator.dashboard" action="/administrator/admin-dashboard/show"/>
			<acme:menu-suboption code="master.menu.administrator.announcement.create" action="/administrator/announcement/create"/>
			<acme:menu-separator />
			<acme:menu-suboption code="master.menu.administrator.populate-initial" action="/administrator/populate-initial" />
			<acme:menu-suboption code="master.menu.administrator.populate-sample" action="/administrator/populate-sample" />
			<acme:menu-separator />
			<acme:menu-suboption code="master.menu.administrator.shut-down"	action="/administrator/shut-down" />

		</acme:menu-option>

		<acme:menu-option code="master.menu.patron" access="hasRole('Patron')">
			<acme:menu-suboption code="master.menu.patron.patronage.list" action="/patron/patronage/list"/>
			<acme:menu-suboption code="master.menu.patron.dashboard" action="/patron/patron-dashboard/show"/>
		</acme:menu-option>


		<acme:menu-option code="master.menu.inventor" access="hasRole('Inventor')">
			<acme:menu-suboption code="master.menu.inventor.toolkit.list" action="/inventor/toolkit/list"/>
			<acme:menu-suboption code="master.menu.inventor.component.list" action="/inventor/item/list-component"/>
			<acme:menu-suboption code="master.menu.inventor.tool.list" action="/inventor/item/list-tool"/>
			<acme:menu-suboption code="master.menu.inventor.patronage.list" action="/inventor/patronage/list"/>
		</acme:menu-option>
	</acme:menu-left>

	<acme:menu-right>
     	
     	
     	<acme:menu-option code="master.menu.user-account" access="isAuthenticated()">
		<acme:menu-suboption code="master.menu.user-account.general-data" action="/authenticated/user-account/update"/>
		<acme:menu-suboption code="master.menu.user-account.become-patron" action="/authenticated/patron/create" access="!hasRole('Patron')"/>
		<acme:menu-suboption code="master.menu.user-account.patron" action="/authenticated/patron/update" access="hasRole('Patron')"/>
		<acme:menu-suboption code="master.menu.user-account.become-inventor" action="/authenticated/inventor/create" access="!hasRole('Inventor')"/> 
		<acme:menu-suboption code="master.menu.user-account.inventor" action="/authenticated/inventor/update" access="hasRole('Inventor')"/>
		</acme:menu-option>
     	
     	<acme:menu-option code="master.menu.sign-up" action="/anonymous/user-account/create" access="isAnonymous()" />
		<acme:menu-option code="master.menu.sign-in" action="/master/sign-in"	access="isAnonymous()" />
     	
     	
		<acme:menu-option code="master.menu.sign-out"
			action="/master/sign-out" access="isAuthenticated()" />
	</acme:menu-right>
</acme:menu-bar>

