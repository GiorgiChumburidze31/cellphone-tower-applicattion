<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Account Created</title>
    <link rel="stylesheet" href="/genesisportal/CSS/jspRegResponse.css">
</head>
<body>
    <div class="card">
        <h1>Account created</h1>
        <p>Welcome, <%= request.getAttribute("username") %>. Your account is ready.</p>
        <a href="LoginServlet">Go to login</a>
    </div>
</body>
</html>