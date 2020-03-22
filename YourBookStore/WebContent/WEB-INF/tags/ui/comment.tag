<%@ attribute name="username" rtexprvalue="true" required="true" %>
<%@ attribute name="dateposted" rtexprvalue="true" required="true" %>
<%@ attribute name="content" rtexprvalue="true" required="true" %>

<div class="border-bottom p-3">
	<strong>${username}</strong> <span class="text-muted text-indent">${dateposted}</span>
	<p class="text-dark">${content}</p>
</div>