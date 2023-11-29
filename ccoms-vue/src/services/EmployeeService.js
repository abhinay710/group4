import axios from 'axios';

const EMPLOYEE_API = 'http://localhost:8080/employee/';

class EmployeeService {
  getEmployees() {
    return axios.get(EMPLOYEE_API + 'all');
  }

  saveEmployee(employee) {
    return axios.post(EMPLOYEE_API + 'save', employee);
  }
}

export default new EmployeeService();
