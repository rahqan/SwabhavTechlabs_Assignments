<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.List, com.aurionpro.model.Account, com.aurionpro.model.Beneficiary" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    List<Account> accounts = (List<Account>) request.getAttribute("accounts");
    List<Beneficiary> beneficiaries = (List<Beneficiary>) request.getAttribute("beneficiaries");
%>
<!DOCTYPE html>
<html>
<head>
    <title>Transfer Money</title>
    <link rel="stylesheet" href="css/bootstrap.min.css">
</head>
<body class="container mt-5">
    <h3>Transfer Money</h3>

    <c:if test="${not empty success}">
        <div class="alert alert-success">${success}</div>
    </c:if>
    <c:if test="${not empty error}">
        <div class="alert alert-danger">${error}</div>
    </c:if>

    <form action="transfer-money" method="post">
        <!-- From Account Dropdown -->
        <div class="mb-3">
            <label>From Account</label>
            <select name="fromAccount" class="form-control" required>
                <c:forEach var="acc" items="${accounts}">
                    <option value="${acc.accountId}">
                        ${acc.accountNumber} - ${acc.accountType} (Balance: ${acc.balance})
                    </option>
                </c:forEach>
            </select>
        </div>

        <!-- To Beneficiary Dropdown -->
        <div class="mb-3">
            <label>To Beneficiary</label>
            <select name="toAccountNumber" class="form-control" required>
                <c:forEach var="b" items="${beneficiaries}">
                    <option value="${b.beneficiaryAccountNumber}">
                        ${b.beneficiaryName} - ${b.beneficiaryAccountNumber}
                    </option>
                </c:forEach>
            </select>
        </div>

        <div class="mb-3">
            <label>Amount</label>
            <input type="number" step="0.01" name="amount" class="form-control" required/>
        </div>

        <div class="mb-3">
            <label>Description</label>
            <input type="text" name="description" class="form-control"/>
        </div>

        <button type="submit" class="btn btn-primary">Transfer</button>
    </form>
</body>
</html>
