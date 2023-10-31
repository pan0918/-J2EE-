<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>number guess game</title>
</head>
<body>
	Your guess is right. Congratulations!

    And after just ${sessionScope.numguess.getGuessNum()}  tries.<p>

  	Care to <a href="/six">try again</a>?
</body>
</html>