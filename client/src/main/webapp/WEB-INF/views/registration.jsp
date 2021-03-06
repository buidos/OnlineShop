<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri ="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@page pageEncoding="UTF-8" %>
<fmt:requestEncoding value="UTF-8" />
<html>
<head>
	<meta charset="UTF-8"/>
	<title> Регистрация </title>
	<jsp:include page="headers/userHeader.jsp" flush="true"/>
	<link href = "css/common.css" rel="stylesheet" type ="text/css" />
<head>

<body>
	<div class="container">

		<form:form method="POST" modelAttribute="userForm"  class="form-signin">

			<h2 class="form-signin-heading">Регистрация</h2>

			<spring:bind path="login">
				<div class="form-group ${status.error ? 'has-error' : ''}">
					<form:input type="text" path="login" class="form-control" placeholder="Логин*"
								autofocus="true" id = "login"/>
					<form:errors path="login"/>
				</div>
			</spring:bind>

			<spring:bind path="password">
				<div class="form-group ${status.error ? 'has-error' : ''}">
					<form:input type="password" path="password" class="form-control"
								placeholder="Введите пароль*" id = "password"></form:input>
					<form:errors path="password"></form:errors>
				</div>
			</spring:bind>

			<spring:bind path="confirmPassword">
				<div class="form-group ${status.error ? 'has-error' : ''}">
					<form:input type="password" path="confirmPassword" class="form-control"
								placeholder="Введите пароль еще раз*"></form:input>
					<form:errors path="confirmPassword"></form:errors>
				</div>
			</spring:bind>

			<spring:bind path="email">
				<div class="form-group ${status.error ? 'has-error' : ''}">
					<form:input type="text" path="email" class="form-control" placeholder="Email*" id = "email"/>
					<form:errors path="email"/>
				</div>
			</spring:bind>

			<spring:bind path="surname">
				<div class="form-group ${status.error ? 'has-error' : ''}">
					<form:input type="text" path="surname" class="form-control" placeholder="Фамилия"
								autofocus="true"/>
					<form:errors path="surname"/>
				</div>
			</spring:bind>

			<spring:bind path="fname">
				<div class="form-group ${status.error ? 'has-error' : ''}">
					<form:input type="text" path="fname" class="form-control" placeholder="Имя"
								autofocus="true"/>
					<form:errors path="fname"/>
				</div>
			</spring:bind>

			<spring:bind path="lname">
				<div class="form-group ${status.error ? 'has-error' : ''}">
					<form:input type="text" path="lname" class="form-control" placeholder="Отчество"
								autofocus="true"/>
					<form:errors path="lname"/>
				</div>
			</spring:bind>

			<spring:bind path="phone">
				<div class="form-group ${status.error ? 'has-error' : ''}">
					<form:input type="text" path="phone" class="form-control" placeholder="Телефон"
								autofocus="true"/>
					<form:errors path="phone"/>
				</div>
			</spring:bind>

			<button class="btn btn-lg btn-primary btn-block" type="submit">Зарегистрироваться</button>
            <h4 class="text-center"><a href="<c:url value="/"/>">Главная страница</a></h4>
		</form:form>

	</div>
</body>
</html>