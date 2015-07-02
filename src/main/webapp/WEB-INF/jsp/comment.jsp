<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<%@ include file="../layout/taglib.jsp" %>
    
${comment.comment }

<br>
<br>
    
<security:authentication var="user" property="principal.username" />
<c:if test="${(user==\"admin\") || user == comment.user.name}">
	<b>Edit comment:</b>
	<FORM METHOD="POST" ACTION="/java-blog-aggregator/feeds/changeComment.html">
		<TABLE BORDER="1">
  			<TR>
    		<TD>New comment:</TD>
    		<TD>
      			<INPUT TYPE="TEXT" NAME="newComment" SIZE="25">
      			<INPUT TYPE="HIDDEN" NAME="id" VALUE="${comment.id }">
    		</TD>
  			</TR>
  		</TABLE>
  		<P><INPUT TYPE="SUBMIT" VALUE="Edit"></P></FORM>
</c:if>


<security:authorize ifAnyGranted="ROLE_ADMIN">
        <b>Delete comment:</b>
		<FORM METHOD="POST" ACTION="/java-blog-aggregator/feeds/deleteComment.html">
			<INPUT TYPE="HIDDEN" NAME="id" VALUE="${comment.id }">
			<P><INPUT TYPE="SUBMIT" VALUE="Delete"></P>
		</FORM>
</security:authorize>