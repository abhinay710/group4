<template>
  <div>
    <h2><strong>STUDENT PROFILE</strong></h2>

    <div v-if="studentData">
      <form @submit.prevent="saveChanges">
        <table class="table">
          
          <tbody>
            <tr>
              <td>Student ID</td>
              <td>{{ studentData.id }}</td>
            </tr>
            <tr>
              <td>First Name</td>
              <td><input type="text" v-model="studentData.firstName" /></td>
            </tr>
            <tr>
              <td>Last Name</td>
              <td><input type="text" v-model="studentData.lastName" /></td>
            </tr>
            <tr>
              <td>Global ID</td>
              <td><input type="text" v-model="studentData.globalID" /></td>
            </tr>
            <tr>
              <td>Password</td>
              <td><input type="text" v-model="studentData.password" /></td>
            </tr>
            
            <tr>
              <td>Phone Number</td>
              <td><input type="text" v-model="studentData.phoneNum" /></td>
            </tr>
            <tr>
              <td>Flex Amount Balance</td>
              <td>{{ studentData.flexAmountBalance }}</td>
            </tr>
            <tr>
              <td>Address</td>
              <td>
                <input type="text" v-model="studentData.aptNo" />,
                <input type="text" v-model="studentData.street" />,
                <input type="text" v-model="studentData.city" />,
                <input type="text" v-model="studentData.state" />,
                <input type="text" v-model="studentData.zipCode" />
              </td>
            </tr>
            
          </tbody>
        </table>

        <button type="submit" class="gold">Save Changes</button>
      </form>
    </div>
  </div>
</template>

<script>
import StudentService from '../services/StudentService';

export default {
  data() {
    return {
      studentData: null,
    };
  },
  methods: {
    async getStudentData() {
      try {
        const response = await StudentService.getStudentById(localStorage.getItem('id'));
        this.studentData = response.data;
      } catch (error) {
        console.error('Error fetching student data:', error);
        // Handle the error, e.g., show a user-friendly message
      }
    },
    async saveChanges() {
      try {
        await StudentService.updateStudent(localStorage.getItem('id'), this.studentData);
        // Optionally, you can display a success message or redirect the user
      } catch (error) {
        console.error('Error saving changes:', error);
        // Handle the error, e.g., show a user-friendly message
      }
    },
  },
  mounted() {
    this.getStudentData();
  },
};
</script>

<style scoped>
h2 {
  margin: 20px;
}

.table {
  width: 100%;
  margin-bottom: 1rem;
  color: #212529;
  border-collapse: collapse;
}

.table th,
.table td {
  padding: 0.75rem;
  vertical-align: top;
  border-top: 1px solid #dee2e6;
}

.table thead th {
  vertical-align: bottom;
  border-bottom: 2px solid #dee2e6;
  background-color: #f8f9fa;
}
.gold {
    background-color: gold;
    color: #000000;
    border: none;
  }
</style>
