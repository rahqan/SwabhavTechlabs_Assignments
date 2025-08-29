<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.List, com.aurionpro.model.Transaction, com.aurionpro.model.Account" %>
<%
    List<Account> accounts = (List<Account>) request.getAttribute("accounts");
    List<Transaction> transactions = (List<Transaction>) request.getAttribute("transactions");
%>
<!DOCTYPE html>
<html>
<head>
    <title>Account Statement</title>
    <link rel="stylesheet" href="css/bootstrap.min.css">
</head>
<body class="container mt-5">
    <h3>Account Statement</h3>

    <form action="statement" method="post" class="mb-4">
        <div class="mb-3">
            <label>Select Account</label>
            <select name="accountId" class="form-control" required>
                <c:forEach var="acc" items="${accounts}">
                    <option value="${acc.accountId}" <c:if test="${selectedAccountId == acc.accountId}">selected</c:if>>
                        ${acc.accountNumber} - ${acc.type}
                    </option>
                </c:forEach>
            </select>
        </div>
        <div class="mb-3">
            <label>From Date</label>
            <input type="date" name="fromDate" value="${fromDate}" class="form-control" required/>
        </div>
        <div class="mb-3">
            <label>To Date</label>
            <input type="date" name="toDate" value="${toDate}" class="form-control" required/>
        </div>
        <button type="submit" class="btn btn-primary">Generate Statement</button>
    </form>

    <c:if test="${not empty transactions}">
        <h4>Transactions</h4>
        <table class="table table-bordered">
            <tr>
                <th>Date</th>
                <th>Description</th>
                <th>Type</th>
                <th>Amount</th>
            </tr>
            <c:forEach var="txn" items="${transactions}">
                <tr>
                    <td>${txn.txnDate}</td>
                    <td>${txn.description}</td>
                    <td>${txn.txnType}</td>
                    <td>${txn.amount}</td>
                </tr>
            </c:forEach>
        </table>
    </c:if>
</body>
</html>
