<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<%@ attribute name="totalPages" rtexprvalue="true" required="true"%>
<%@ attribute name="currentPage" rtexprvalue="true" required="true"%>
<%@ attribute name="baseurl" rtexprvalue="true" required="true"%>
<%@ attribute name="query" rtexprvalue="true" required="true"%>
<nav aria-label="Page navigation example">
	<ul class="pagination justify-content-center">

		<c:if test="${currentPage gt 1}">
			<li class="page-item"><a class="page-link"
				href="${pageContext.request.contextPath}${baseurl}?page=${currentPage - 1}&query=${query}"><i
					class="fas fa-arrow-circle-left"></i> </a></li>
		</c:if>
		<c:forEach begin="1" end="${totalPages}" var="current">
			<c:choose>
				<c:when test="${current eq currentPage}">
					<li class="page-item active"><a class="page-link"
						href="${pageContext.request.contextPath}${baseurl}?page=${current}&query=${query}">${current}</a></li>
				</c:when>
				<c:otherwise>
					<li class="page-item"><a class="page-link"
						href="${pageContext.request.contextPath}${baseurl}?page=${current}&query=${query}">${current}</a></li>
				</c:otherwise>
			</c:choose>
		</c:forEach>
		<c:if test="${currentPage lt totalPages }">
			<li class="page-item"><a class="page-link"
				href="${pageContext.request.contextPath}${baseurl}?page=${currentPage + 1}&query=${query}"><i
					class="fas fa-arrow-circle-right"></i></a></li>
		</c:if>
	</ul>
</nav>