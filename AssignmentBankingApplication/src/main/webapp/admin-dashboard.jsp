<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.List, com.aurionpro.model.UnApprovedAccount" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <title>Admin Dashboard</title>
    <link rel="stylesheet" href="css/bootstrap.min.css">
</head>
<body class="container mt-5">
    <h2>Welcome, Admin</h2>

    <h4>Pending Account Requests</h4>
   <table class="table table-bordered">
    <tr>
        <th>Account ID</th>
        <th>User ID</th>
        <th>Account Number</th>
        <th>Type</th>
        <th>Customer Name</th>
        <th>Action</th>
    </tr>
    <c:forEach var="acc" items="${pendingAccounts}">
        <tr>
            <td>${acc.accountId}</td>
            <td>${acc.userId}</td>
            <td>${acc.accountNumber}</td>
            <td>${acc.accountType}</td>
            <td>${acc.customerName}</td>
            <td>
                <form action="approve-account" method="post" style="display:inline;">
                    <input type="hidden" name="accountId" value="${acc.accountId}" />
                    <input type="hidden" name="action" value="approve" />
                    <button type="submit" class="btn btn-success btn-sm">Approve</button>
                </form>
                <form action="approve-account" method="post" style="display:inline;">
                    <input type="hidden" name="accountId" value="${acc.accountId}" />
                    <input type="hidden" name="action" value="reject" />
                    <button type="submit" class="btn btn-danger btn-sm">Reject</button>
                </form>
            </td>
        </tr>
    </c:forEach>
</table>

</body>
</html>
