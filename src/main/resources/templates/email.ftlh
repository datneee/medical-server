<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Spring Boot Email using FreeMarker</title>
</head>
<body>
<div style="margin-top: 10px;display: flex;flex-direction: column;padding: 24px;background: #f5f5f5;color: #000;border-radius: 24px;">
    <h1 style="width: 100%">
        Xin chào, ${name}
    </h1>
    <p style="width: 100%;">Cảm ơn, bạn đã thực hiện thành công các bước để đăng ký tài khoản medical shop. Hãy click vào nút dưới đây để thực hiện kích hoạt tài khoản nhé !</p>
    <a style="border: solid 1px #3498db;border-radius: 5px;box-sizing: border-box;cursor: pointer;display: inline-block;font-size: 14px;font-weight: bold;margin: 0;padding: 12px 25px;text-decoration: none;text-transform: capitalize;background-color: #3498db;border-color: #3498db;color: #ffffff;width: 164px;" href=http://localhost:8080/api/v1/auth/active/${token} >
        Active account
    </a>
</div>

<br/>
<div> Have a nice day..!</div>
</body>
</html>