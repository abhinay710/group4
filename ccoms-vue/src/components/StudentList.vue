<template>
  <div>
    <h2><strong>STUDENTS</strong></h2>

    <table class="table">
      <thead>
        <tr>
          <th>Student ID</th>
          <th>First Name</th>
          <th>Last Name</th>
          <th>Global ID</th>
          <th>Phone Number</th>
          <th>Flex Amount Balance</th>
          <th>Address</th>
          <th>Created By</th>
          <th>Created On</th>
          <th>Updated By</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="(student, index) in displayedStudents" :key="index">
          <td>{{ student.id }}</td>
          <td>{{ student.firstName }}</td>
          <td>{{ student.lastName }}</td>
          <td>{{ student.globalID }}</td>
          <td>{{ student.phoneNum }}</td>
          <td>{{ student.flexAmountBalance }}</td>
          <!-- Combine address details with commas -->
          <td>{{ `${student.aptNo}, ${student.street}, ${student.city}, ${student.state}, ${student.zipCode}` }}</td>
          <td>{{ student.createdBy }}</td>
          <td>{{ student.createdOn }}</td>
          <td>{{ student.updatedBy }}</td>
        </tr>
      </tbody>
    </table>

    <nav>
      <ul class="pagination">
        <li class="page-item" :class="{ disabled: currentPage === 1 }">
          <button @click="prevPage" class="page-link">Previous</button>
        </li>
        <li class="page-item" v-for="page in totalPages" :key="page" :class="{ active: currentPage === page }">
          <button @click="goToPage(page)" class="page-link">{{ page }}</button>
        </li>
        <li class="page-item" :class="{ disabled: currentPage === totalPages }">
          <button @click="nextPage" class="page-link">Next</button>
        </li>
      </ul>
    </nav>
  </div>
</template>

<script>
import StudentService from '../services/StudentService';

export default {
  data() {
    return {
      students: [],
      pageSize: 5,
      currentPage: 1,
    };
  },
  computed: {
    totalPages() {
      return Math.ceil(this.students.length / this.pageSize);
    },
    displayedStudents() {
      const start = (this.currentPage - 1) * this.pageSize;
      const end = start + this.pageSize;
      return this.students.slice(start, end);
    },
  },
  methods: {
    async getStudent(forceRefresh) {
      try {
        const response = await StudentService.getStudent({ forceRefresh });
        this.students = response.data;
      } catch (error) {
        console.error('Error fetching students:', error);
      }
    },
    prevPage() {
      if (this.currentPage > 1) {
        this.currentPage--;
      }
    },
    nextPage() {
      if (this.currentPage < this.totalPages) {
        this.currentPage++;
      }
    },
    goToPage(page) {
      this.currentPage = page;
    },
  },
  mounted() {
    this.getStudent();
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

.pagination {
  display: flex;
  list-style: none;
  padding: 0;
  justify-content: center;
}

.page-item {
  margin-right: 5px;
}

.page-item button {
  background-color: gold !important;
  color: black;
}

.page-link {
  display: block;
  padding: 0.5rem 0.75rem;
  color: #007bff;
  background-color: #fff;
  border: 1px solid #dee2e6;
  cursor: pointer;
}

.page-link:hover {
  z-index: 2;
  color: #0056b3;
  background-color: #e9ecef;
  border-color: #dee2e6;
}

.page-link:focus {
  z-index: 2;
  outline: 0;
  box-shadow: 0 0 0 0.2rem rgba(0, 123, 255, 0.25);
}
</style>
