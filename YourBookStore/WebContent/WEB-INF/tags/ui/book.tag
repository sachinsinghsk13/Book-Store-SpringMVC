<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ attribute name="id" rtexprvalue="true" required="true"%>
<%@ attribute name="title" rtexprvalue="true" required="true"%>
<%@ attribute name="thumb" rtexprvalue="true" required="true"%>
<%@ attribute name="description" rtexprvalue="true" required="true"%>
<%@ attribute name="author" rtexprvalue="true" required="true"%>
<div class="row border-bottom p-2">
	<div class="col-2">
		<a href='<spring:url value="/book/${id}"></spring:url>'>
		<img
			src='<spring:url value="/thumbs?id=${thumb}"></spring:url>'
			class="img-fluid min-vh-25 thumb-img">
		</a>
	</div>
	<div class="col-9">
		<a href='<spring:url value="/book/${id}"></spring:url>' class="lead text-dark text-decoration-none">${title}</a>
		<p class="text-muted">By: ${author}</p>
		<p class="text-dark d-none d-lg-block">
			<c:choose>
				<c:when test="${fn:length(description) gt 300}">
					${fn:substring(description,0,300)}...
				</c:when>
				<c:otherwise>
					${description}
				</c:otherwise>
			</c:choose>
		
		</p>
	</div>
</div>