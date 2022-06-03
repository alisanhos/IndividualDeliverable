<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form>
	<jstl:choose>
		<jstl:when test="${command == 'create'}">
			<acme:input-textbox code="inventor.talmonu.form.label.code" path="code"/>
			<acme:input-select code="inventor.talmonu.form.label.item" path="items">
            	<jstl:forEach items="${items}" var="items">
                	<acme:input-option code="${items}" value="${items}" selected="${items}"/>
            	</jstl:forEach>
        	</acme:input-select>
		</jstl:when>
		<jstl:when test="${acme:anyOf(command, 'show, update, delete')}">
			<acme:input-textbox code="inventor.talmonu.form.label.code" path="code" readonly="true"/>
			<acme:input-textbox code="inventor.talmonu.form.label.item" path="item" readonly="true"/>
			<acme:input-moment code="inventor.talmonu.form.label.creationMoment" path="creationMoment" readonly="true"/>
		</jstl:when>
	</jstl:choose>
    <acme:input-textbox code="inventor.talmonu.form.label.theme" path="theme"/>
    <acme:input-textarea code="inventor.talmonu.form.label.explanation" path="explanation"/>
    <acme:input-moment code="inventor.talmonu.form.label.startPeriod" path="startPeriod"/>
    <acme:input-moment code="inventor.talmonu.form.label.endPeriod" path="endPeriod"/>
    <acme:input-money code="inventor.talmonu.form.label.expenditure" path="expenditure"/>
    <acme:input-url code="inventor.talmonu.form.label.furtherInfo" path="furtherInfo"/>
    
    	<jstl:choose>
			<jstl:when test="${acme:anyOf(command, 'show, update, delete')}">
				<acme:submit code="inventor.talmonu.form.button.update" action="/inventor/talmonu/update"/>
				<acme:submit code="inventor.talmonu.form.button.delete" action="/inventor/talmonu/delete"/>
			</jstl:when>	
		
			<jstl:when test="${command == 'create'}">
				<acme:submit code="inventor.talmonu.form.button.create" action="/inventor/talmonu/create"/>
			</jstl:when>
		</jstl:choose>
    
</acme:form>