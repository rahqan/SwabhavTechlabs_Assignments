<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Create Account</title>
    <link rel="stylesheet" href="css/bootstrap.min.css">
</head>
<body class="container mt-5">
    <h3>Request New Account</h3>

    <c:if test="${not empty success}">
        <div class="alert alert-success">${success}</div>
    </c:if>
    <c:if test="${not empty error}">
        <div class="alert alert-danger">${error}</div>
    </c:if>

    <form action="create-account" method="post">
        <div class="mb-3">
            <label>Select Account Type</label>
            <select name="accountType" class="form-control" required>
                <option value="SAVINGS">Savings</option>
                <option value="CURRENT">Current</option>
            </select>
        </div>
        <button type="submit" class="btn btn-primary">Submit Request</button>
    </form>
</body>
</html>
