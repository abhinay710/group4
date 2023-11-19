import axios from 'axios';

const EMPLOYEE_API = 'http://localhost:8080/employee/';

class EmployeeService {
  getEmployees() {
    return axios.get(EMPLOYEE_API + 'all');
  }

  addEmployee(employee) {
    return axios.post(EMPLOYEE_API + 'add', employee);
  }

  updateEmployee(id, employee) {
    return axios.put(EMPLOYEE_API + 'update/' + id, employee);
  }
}

export default new EmployeeService();
