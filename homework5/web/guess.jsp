<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="mytag" uri="/mytag" %>
<jsp:useBean id="guessnum" class="guess.NumberGuessBean" scope="session"/>
<jsp:setProperty name="guessnum" property="*"/>

<html>
<head>
	<title>Number Guess</title>
</head>

<c:choose>
	<c:when test="${guessnum.correct}">
		Your guess is right. Congratulations!

		And after just ${guessnum.getGuessNum()} tries.<p>

		<mytag:reset bean="${guessnum}"/>

		Care to <a href="guess.jsp">try again</a>?

	</c:when>
	
	<c:otherwise>
	
		<c:if test="${guessnum.getGuessNum() eq 0}" >

			This is a Number Guess game.<p>

			The Target number is between 1 and 100.<p>

			<form method=get>
				Please type in your number? <input type=text name=guess>
				<input type=submit value="Submit">
			</form>
  
		</c:if>

		<c:if test="${guessnum.getGuessNum() ne 0}" >

			I'm sorry you were wrong.  Try <b><%= guessnum.getTip() %></b>.

			You have tried <%= guessnum.getGuessNum() %> times.<p>

			The Target number is between 1 and 100.<p>

			<form method=get>
				Please type in your number? <input type=text name=guess>
				<input type=submit value="Submit">
			</form>

		</c:if>
	
	</c:otherwise>

</c:choose>

</html>
