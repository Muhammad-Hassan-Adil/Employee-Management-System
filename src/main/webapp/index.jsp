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
<form id="employeeForm">
    <h2>Add Employee</h2>
    <label for="ID">ID:</label>
    <input type="text" name="ID" id="ID"><br>
    <label for="name">Name:</label>
    <input type="text" name="name" id="name"><br>
    <label for="age">Age:</label>
    <input type="text" name="age" id="age"><br>
    <label for="salary">Salary:</label>
    <input type="text" name="salary" id="salary"><br>
    <button type="submit">Create</button><br>
</form>
<form id="updateForm">
    <h2>Update Employee</h2>
    <label for="ID">ID:</label>
    <input type="text" name="ID" id="PID"><br>
    <label for="name">Name:</label>
    <input type="text" name="name" id="Pname"><br>
    <label for="age">Age:</label>
    <input type="text" name="age" id="Page"><br>
    <label for="salary">Salary:</label>
    <input type="text" name="salary" id="Psalary"><br>
    <button type="submit">Change</button>
</form>
<form id="deleteForm">
    <h2>Delete Employee</h2>
    <label for="ID">ID:</label>
    <input type="text" name="ID" id="DID"><br>
    <button type="submit">Delete</button>
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

    function updateEmployee(event) {
        event.preventDefault();
        const form = document.getElementById('updateForm');
        const id = document.getElementById('PID').value;
        const name = document.getElementById('Pname').value;
        const age = document.getElementById('Page').value;
        const salary = document.getElementById('Psalary').value;
        const data = {
            ID: id,
            name: name,
            age: age,
            salary: salary
        };
        fetch('api/restfullapp', {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(data),
        })
            .then(response => response.json())
            .then(data => {
                console.log('Employee updated successfully: ',data);
                form.reset();
                getUsers();
            })
            .catch(error => console.error('Error updating employee: ',error));
    }

    function deleteEmployee(event) {
        event.preventDefault();
        const form = document.getElementById('deleteForm');
        const id = document.getElementById('DID').value;
        const data = {
            ID: id
        };
        fetch('api/restfullapp', {
            method: 'DELETE',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(data),
        })
            .then(response => response.json())
            .then(data => {
                console.log('Employee deleted successfully: ',data);
                form.reset();
                getUsers();
            })
            .catch(error => console.error('Error deleting employee: ',error));
    }

    const createform = document.getElementById('employeeForm');
    const updateForm = document.getElementById('updateForm');
    const deleteForm = document.getElementById('deleteForm');
    deleteForm.addEventListener('submit', deleteEmployee);
    createform.addEventListener('submit', addEmployee);
    updateForm.addEventListener('submit', updateEmployee);
    getUsers();
</script>
</body>
</html>
