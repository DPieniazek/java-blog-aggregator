<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<%@ include file="../layout/taglib.jsp" %>

	
	
	<table class="table table-bordered table-hover table-striped">
			<thead>
				<tr>
					<th>Title</th>
					<th>Description</th>
				</tr>
				<tbody>
					<c:forEach items="${feeds}" var="feed"> 
						<tr>
							<td>
								<a href="feed/${feed.id}.html">
									<c:out value="${feed.title}" />
								</a>
							
							</td>
							<td>
 								<c:out value="${feed.description}" />
						</td>
						</tr>				
					</c:forEach>
				</tbody>
		</table>
	
	
	
	<security:authorize ifAnyGranted="ROLE_ADMIN">
        <b>Create new event:</b>
<FORM METHOD="POST" ACTION="feeds/addFeed.html">
<TABLE BORDER="1">
  <TR>
    <TD>Event name:</TD>
    <TD>
      <INPUT TYPE="TEXT" NAME="ename" SIZE="25">
    </TD>
  </TR>
  <TR>
    <TD>Event description:</TD>
    <TD><INPUT TYPE="TEXT" NAME="edescription" SIZE="25"></TD>
  </TR>
</TABLE>
<P><INPUT TYPE="SUBMIT" VALUE="Submit" NAME="B1"></P>
</FORM>
    </security:authorize>
    
    
    
    