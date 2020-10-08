<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Sign Up Customer</title>
</head>
<body>
<div class="container">
    <form class="form-signup" method="post" action="/auth/signUp">
        <h2 class="form-signup-heading">Sign up</h2>
        <p>
            <label for="email">Email</label>
            <input type="text" id="email" name="email" class="form-control" placeholder="Email" required>
        </p>
        <p>
            <label for="password">Password</label>
            <input type="password" id="password" name="password" class="form-control" placeholder="Password" required>
        </p>
        <p>
            <label for="firstName">First Name</label>
            <input type="text" id="firstName" name="firstName" class="form-control" placeholder="First Name" required>
        </p>
        <p>
            <label for="secondName">Second Name</label>
            <input type="text" id="secondName" name="secondName" class="form-control" placeholder="Second Name" required>
        </p>
        <p>
            <label for="dateOfBirth">Date Of Birth</label>
            <input type="date" id="dateOfBirth" name="dateOfBirth" class="form-control" placeholder="Date Of Birth" required>
        </p>
        <button class="btn btn-lg btn-primary btn-block" type="submit">Sign up</button>
    </form>
</div>
</body>
</html>