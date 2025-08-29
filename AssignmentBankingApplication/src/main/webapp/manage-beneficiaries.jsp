<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.List, com.aurionpro.model.Beneficiary" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Manage Beneficiaries</title>
    <link rel="stylesheet" href="css/bootstrap.min.css">
</head>
<body class="container mt-5">
    <h3>Manage Beneficiaries</h3>

    <!-- ✅ Message alerts -->
    <c:if test="${not empty successMessage}">
        <div class="alert alert-success">${successMessage}</div>
    </c:if>

    <c:if test="${not empty errorMessage}">
        <div class="alert alert-danger">${errorMessage}</div>
    </c:if>
    <!-- ✅ End messages -->

    <form action="add-beneficiary" method="post" class="mb-4">
        <div class="mb-3">
            <label>Beneficiary Account Number</label>
            <input type="text" name="accountNumber" class="form-control" required/>
        </div>
        <div class="mb-3">
            <label>Beneficiary Name</label>
            <input type="text" name="beneficiaryName" class="form-control" required/>
        </div>
        <button type="submit" class="btn btn-success">Add Beneficiary</button>
    </form>

    <h4>Your Beneficiaries</h4>
    <table class="table table-bordered">
        <tr>
            <th>ID</th>
            <th>Account Number</th>
            <th>Name</th>
            <th>Action</th>
        </tr>
        <c:forEach var="b" items="${beneficiaries}">
            <tr>
                <td>${b.beneficiaryId}</td>
                <td>${b.beneficiaryAccountNumber}</td>
                <td>${b.beneficiaryName}</td>
                <td>
                    <form action="remove-beneficiary" method="post">
                        <input type="hidden" name="beneficiaryId" value="${b.beneficiaryId}" />
                        <button type="submit" class="btn btn-danger btn-sm">Remove</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
