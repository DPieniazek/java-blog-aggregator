<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<%@ include file="../layout/taglib.jsp" %>


<h1><b>${feed.title }:</b></h1>
${feed.description }
<br>
<br><br>

<table class="table table-bordered table-hover table-striped">
			<thead>
				<tr>
					<th>User</th>
					<th>Comment</th>
					<ht></ht>
				</tr>
				<tbody>
					<c:forEach items="${comments}" var="comment"> 
						<tr>
							<td>
								<c:out value="${comment.user.name}" />
								<br />							
							</td>
							<td>
								<c:out value="${comment.comment}" />
							</td>
							<td>
								<security:authentication var="user" property="principal.username" />
								<c:if test="${(user==\"admin\") || user == comment.user.name}">
									<a href="/java-blog-aggregator/feeds/editComment.html?id=${comment.id}">Edit</a>	
								</c:if>
								
								
								
							</td>			
					</c:forEach>
				</tbody>
		</table>


	<security:authorize ifAnyGranted="ROLE_USER">
        <b>Add comment:</b>
<FORM METHOD="POST" ACTION="/java-blog-aggregator/feeds/comment.html">
<TABLE BORDER="1">
  <TR>
    <TD>Comment:</TD>
    <TD>
      <INPUT TYPE="TEXT" NAME="comment" SIZE="25">
      <INPUT TYPE="HIDDEN" NAME="id" VALUE="${feed.id }">
    </TD>
  </TR>
</TABLE>
<P><INPUT TYPE="SUBMIT" VALUE="Post" NAME="B1"></P>
</FORM>

</security:authorize>


<br>

	<security:authorize ifAnyGranted="ROLE_ADMIN">
        <b>Edit event data:</b>
<FORM METHOD="POST" ACTION="/java-blog-aggregator/feeds/editFeed.html">
<TABLE BORDER="1">
  <TR>
    <TD>New event name:</TD>
    <TD>
      <INPUT TYPE="TEXT" NAME="ename" SIZE="25">
      <INPUT TYPE="HIDDEN" NAME="id" VALUE="${feed.id }">
    </TD>
  </TR>
  <TR>
    <TD>New event description:</TD>
    <TD><INPUT TYPE="TEXT" NAME="edescription" SIZE="25"></TD>
  </TR>
</TABLE>
<P><INPUT TYPE="SUBMIT" VALUE="Submit" NAME="B1"></P>
</FORM>


<br>

        <b>Delete event:</b>
<FORM METHOD="POST" ACTION="/java-blog-aggregator/feeds/deleteFeed.html">
<INPUT TYPE="HIDDEN" NAME="id" VALUE="${feed.id }">
<P><INPUT TYPE="SUBMIT" VALUE="Delete"></P>
</FORM>

    </security:authorize>