<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>number guess game</title>
</head>
<body>
	I'm sorry you were wrong.  Try <b> ${sessionScope.numguess.getTip()} </b>.

	You have tried ${sessionScope.numguess.getGuessNum()}  times.<p>

	The Target number is between 1 and 100.<p>

  	<form method="get" action="/six">
  		What's your guess? <input type=text name=guess>
  		<input type=submit value="Submit">
  	</form>
</body>
</html>