import axios from 'axios'

const STUDENT_API = 'http://localhost:8080/student/'

class StudentService {
    getStudent() {
        return axios.get(STUDENT_API + 'all');
    }
}

export default new StudentService()