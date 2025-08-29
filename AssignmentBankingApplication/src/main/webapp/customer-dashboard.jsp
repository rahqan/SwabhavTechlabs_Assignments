<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.List, com.aurionpro.model.Account" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%
    List<Account> accounts = (List<Account>) request.getAttribute("accounts");
%>
<!DOCTYPE html>
<html>
<head>
    <title>Customer Dashboard</title>
    <link rel="stylesheet" href="css/bootstrap.min.css">
</head>
<body class="container mt-5">
    <h2>Welcome, Customer</h2>
    <a href="create-account" class="btn btn-success mb-3">Request New Account</a>
    <a href="manage-beneficiaries" class="btn btn-info mb-3">Manage Beneficiaries</a>
    <a href="transfer-money" class="btn btn-warning mb-3">Transfer Money</a>
    <a href="statement" class="btn btn-secondary mb-3">Get Statement</a>

    <h4>Your Accounts</h4>
<table class="table table-bordered">
    <tr>
        <th>Account No</th>
        <th>Type</th>
        <th>Status</th>
        <th>Balance</th>
        <th>Cash Credit Allowed</th>
        <th>Cash Credit Remaining</th>
    </tr>
    <c:forEach var="acc" items="${accounts}">
        <tr>
            <td>${acc.accountNumber}</td>
            <td>${acc.accountType}</td>
            <td>${acc.status}</td>
            <td>${acc.balance}</td>
            <td><c:out value="${acc.cashCreditAllowed != null ? acc.cashCreditAllowed : '-'}"/></td>
            <td><c:out value="${acc.cashCreditRemaining != null ? acc.cashCreditRemaining : '-'}"/></td>
        </tr>
    </c:forEach>
</table>

</body>
</html>
