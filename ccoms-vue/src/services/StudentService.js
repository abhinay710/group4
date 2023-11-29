import axios from 'axios'

const STUDENT_API = 'http://localhost:8080/student/'

class StudentService {
    getStudent() {
        return axios.get(STUDENT_API + 'all');
    }
    getStudentById(id) {
        return axios.get(STUDENT_API + id);
      }
      updateStudent(studentData) {
        // Assuming you have an endpoint for updating a student
        const id = studentData.id; // Replace with the actual property name used for student ID
        return axios.put(STUDENT_API + id, studentData);
      }
}

export default new StudentService()