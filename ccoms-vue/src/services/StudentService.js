import axios from 'axios'

const STUDENT_API = 'http://localhost:8080/student/'

class StudentService {
  getStudent() {
    return axios.get(STUDENT_API + 'all');
  }
  getStudentById(id) {
    return axios.get(STUDENT_API + id);
  }
  saveStudent(student) {
    return axios.post(STUDENT_API + 'save', student);
}
}

export default new StudentService()