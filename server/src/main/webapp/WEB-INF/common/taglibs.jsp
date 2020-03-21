<%@page import="i.met.you.ApplicationConstants"%>
<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro"%>


<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.Iterator"%>
<%@ page import="org.apache.shiro.SecurityUtils"%>
<%@ page import="org.apache.shiro.subject.Subject"%>
<%@ page import="org.apache.commons.lang.StringUtils"%>
<%@ page import="i.met.you.bean.SessionParameterBean"%>
<%@ page import="i.met.you.bean.JsonResponse"%>
<%@ page import="i.met.you.validation.Errors"%>
<%@ page import="org.apache.shiro.SecurityUtils"%>
<%@ page import="org.apache.shiro.subject.Subject"%>


<%@ page import="i.met.you.util.ApplicationUtil"%>


<%@page import="org.apache.commons.lang.StringUtils"%>


<fmt:setBundle basename="i18n.ApplicationResources" var="msg" scope="session"/>
<fmt:setLocale value="en" scope="session" />

<c:set var="pleaseSelect">
	<fmt:message key='select.pleaseSelect' />
</c:set>
<c:set var="ctx" value="${pageContext.request.contextPath}" />


<%



%>


<%!



%>









