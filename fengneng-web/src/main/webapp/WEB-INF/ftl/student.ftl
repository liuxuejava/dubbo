<html>
<head>
    <title>学生信息</title>
</head>
<body>
学号:${student.id}<br>
姓名:${student.name}<br>
年龄:${student.age}<br>
地址:${student.address}<br>
学生列表信息:<br>
<table border="2">
    <tr>
        <th>序号</th>
        <th>学号</th>
        <th>姓名</th>
        <th>年龄</th>
        <th>家庭住址</th>
    </tr>
<#list stuList as stu>
    <#if stu_index%2==0>
    <tr bgcolor="#7fff00">
    <#else>
    <tr bgcolor="red">
    </#if>
    <td>${stu_index}</td>
    <td>${stu.id}</td>
    <td>${stu.name}</td>
    <td>${stu.age}</td>
    <td>${stu.address}</td>
</tr>
</#list>
</table>
</body>
</html>