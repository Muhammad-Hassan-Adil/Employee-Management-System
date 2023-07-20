<%--<!DOCTYPE html>--%>
<%--<!-- Heading -->--%>
<%--<h1>Employee Management System</h1>--%>

<%--<style>--%>
<%--    input[type=text] {--%>
<%--        align : right;--%>
<%--        padding: 12px 20px;--%>
<%--        margin: 8px 5px;--%>
<%--        box-sizing: border-box;--%>
<%--    }--%>

<%--    /*#jsonDataSection {*/--%>
<%--    /*    margin-top: 20px;*/--%>
<%--    /*    border: 1px solid #ccc;*/--%>
<%--    /*    padding: 10px;*/--%>
<%--    /*}*/--%>
<%--</style>--%>
<%--<form method="post" id="employeeForm">--%>
<%--    <label>--%>
<%--        <input type="text" name="id" placeholder="ID">--%>
<%--    </label><br><br>--%>
<%--    <label>--%>
<%--        <input type="text" name="name" placeholder="Name">--%>
<%--    </label><br><br>--%>
<%--    <label>--%>
<%--        <input type="text" name="age" placeholder="Age">--%>
<%--    </label><br><br>--%>
<%--    <label>--%>
<%--        <input type="text" name="salary" placeholder="Salary">--%>
<%--    </label><br><br>--%>
<%--    <input type="submit" name="submit"><br><br>--%>
<%--</form>--%>

<%--<script>--%>
<%--    // function to add new employee--%>
<%--    function addEmployee(event) {--%>
<%--        event.preventDefault();--%>
<%--        const form = document.getElementById('employeeForm');--%>
<%--        const formData = new FormData(form);--%>
<%--        const data = {};--%>
<%--        for (let [key, value] of formData.entries()) {--%>
<%--            data[key] = value;--%>
<%--        }--%>
<%--        const url = '/restfullapp';--%>
<%--        const options = {--%>
<%--            method: 'POST',--%>
<%--            headers: {--%>
<%--                'Content-Type': 'application/json'--%>
<%--            },--%>
<%--            body: JSON.stringify(data),--%>
<%--        };--%>
<%--        fetch(url, options)--%>
<%--            .then(response => response.json())--%>
<%--            .then(data => {--%>
<%--                console.log(data);--%>
<%--                alert('Employee added successfully');--%>
<%--            })--%>
<%--            .catch(error => console.log(error));--%>
<%--    }--%>
<%--    const form = document.getElementById('employeeForm');--%>
<%--    form.addEventListener('submit', addEmployee);--%>


<%--</script>--%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Employee Management System</title>
</head>
<body>
<h1>Employee Management System</h1>
<h2>Total Employees</h2>
<div id="jsonDataSection"></div>
<form method="post" id="employeeForm">
    <h2>Add Employee</h2>
    <label for="ID">ID:</label>
    <input type="text" name="ID" id="ID"><br>
    <label for="name">Name:</label>
    <input type="text" name="name" id="name"><br>
    <label for="age">Age:</label>
    <input type="text" name="age" id="age"><br>
    <label for="salary">Salary:</label>
    <input type="text" name="salary" id="salary"><br>
    <button type="submit">Create</button>
</form>
<script>
    function getUsers(){
        fetch('api/restfullapp')
            .then(response => response.json())
            .then(data => {const employeeList = document.getElementById('jsonDataSection');
                employeeList.innerHTML = '';
                data.forEach(employee => {
                    const div = document.createElement('div');
                    div.textContent = employee.ID + ' ' + employee.name + ' ' + employee.age + ' ' + employee.salary;
                    employeeList.appendChild(div);
                });
            })
            .catch(error => console.error("Error fetching data: ", error));
    }

    function addEmployee(event) {
        event.preventDefault();
        const form = document.getElementById('employeeForm');
        const id = document.getElementById('ID').value;
        const name = document.getElementById('name').value;
        const age = document.getElementById('age').value;
        const salary = document.getElementById('salary').value;
        const data = {
            ID: id,
            name: name,
            age: age,
            salary: salary
        };
        fetch('api/restfullapp', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(data),
        })
            .then(response => response.json())
            .then(data => {
                console.log('Employee added successfully: ',data);
                form.reset();
                getUsers();
            })
            .catch(error => console.error('Error adding employee: ',error));
    }
    const form = document.getElementById('employeeForm');
    form.addEventListener('submit', addEmployee);
    getUsers();
</script>
</body>
</html>
