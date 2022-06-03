<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:list>
    <acme:list-column code="inventor.talmonu.list.label.code" path="code" width="35%"/>
    <acme:list-column code="inventor.talmonu.list.label.theme" path="theme" width="30%"/>
    <acme:list-column code="inventor.talmonu.list.label.item" path="item" width="35%"/>
</acme:list>

<acme:button code="inventor.talmonu.list.button.create" action="/inventor/talmonu/create"/>