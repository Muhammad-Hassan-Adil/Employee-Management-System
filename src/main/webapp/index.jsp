<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Employee Management System</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f2f2f2;
            margin: 20px;
        }

        h1, h2 {
            text-align: center;
        }
        table {
            width: 50%;
            border-collapse: collapse;
            margin: 20px auto;
            background-color: #fff;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        th, td {
            padding: 12px;
            border: 1px solid #ddd;
            text-align: center;
        }
        th:nth-child(1), td:nth-child(1) { width: 10%; }
        th:nth-child(2), td:nth-child(2) { width: 30%; }
        th:nth-child(3), td:nth-child(3) { width: 15%; }
        th:nth-child(4), td:nth-child(4) { width: 25%; }
        form {
            margin: 20px auto;
            max-width: 400px;
            padding: 20px;
            background-color: #fff;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            text-align: center;
        }
        label {
            display: block;
            margin-bottom: 8px;
        }
        input[type="text"] {
            width: 95%;
            padding: 8px;
            margin-bottom: 16px;
            border: 1px solid #ddd;
            border-radius: 4px;
        }
        button[type="submit"] {
            padding: 8px 16px;
            background-color: #4CAF50;
            color: #fff;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            display: block;
            margin: 0 auto;
        }
        button[type="submit"]:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>
<h1>Employee Management System</h1>
<h2>Total Employees</h2>
<div id="jsonDataSection">
    <table>
        <thead>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Age</th>
            <th>Salary</th>
        </tr>
        </thead>
        <tbody id="employeeTableBody">
        <!-- Employee data will be added dynamically here -->
        </tbody>
    </table>
</div>
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
            .then(data => {
                const employeeTableBody = document.getElementById('employeeTableBody');
                employeeTableBody.innerHTML = '';

                data.forEach(employee => {
                    const row = document.createElement('tr');

                    const idCell = document.createElement('td');
                    idCell.textContent = employee.ID;
                    row.appendChild(idCell);

                    const nameCell = document.createElement('td');
                    nameCell.textContent = employee.name;
                    row.appendChild(nameCell);

                    const ageCell = document.createElement('td');
                    ageCell.textContent = employee.age;
                    row.appendChild(ageCell);

                    const salaryCell = document.createElement('td');
                    salaryCell.textContent = employee.salary;
                    row.appendChild(salaryCell);

                    employeeTableBody.appendChild(row);
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
