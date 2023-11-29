<template>
  <div>
    <div class="position-relative page-item">
      <h2><strong>EMPLOYEES</strong></h2>
      <div class="position-absolute top-0 end-0 mt-1"><button class="btn btn-primary"
          @click="openEditEmployeeModal(undefined, diningHalls)">Add Employee</button></div>
    </div>
    <table class="table">
      <thead>
        <tr>
          <th>Employee ID</th>
          <th>First Name</th>
          <th>Last Name</th>
          <th>Dining Hall</th>
          <th>Dining Station</th>
          <th>Role</th>
          <th>Email ID</th>
          <th>Phone Number</th>
          <th>Address</th>
          <th>Created By</th>
          <th>Created On</th>
          <th>Updated By</th>
          <th>Action</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="(employee, index) in displayedEmployees" :key="index">
          <td>{{ employee.id }}</td>
          <td>{{ employee.firstName }}</td>
          <td>{{ employee.lastName }}</td>
          <td>{{ employee.diningHall.diningHallName }}</td>
          <td>{{ employee.diningStation }}</td>
          <td>{{ employee.role }}</td>
          <td>{{ employee.emailID }}</td>
          <td>{{ employee.phoneNum }}</td>
          <td>{{ getAddress(employee) }}</td>
          <td>{{ employee.createdBy }}</td>
          <td>{{ employee.createdOn }}</td>
          <td>{{ employee.updatedBy }}</td>
          <td>
            <!-- Edit Employee Button -->
            <button class="btn btn-primary" @click="openEditEmployeeModal(employee)">Edit</button>
          </td>
        </tr>
      </tbody>
    </table>

    <!-- Pagination -->
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

    <!-- EmployeeAddModal Component -->
    <employee-add-modal ref="employeeAddModal" />

    <!-- EmployeeEditModal Component -->
    <employee-edit-modal ref="employeeEditModal" :employee="employeeToEdit" :diningHalls="diningHalls" />
  </div>
</template>
  
<script>
import EmployeeService from '../services/EmployeeService';
import DiningHallService from '../services/DiningHallService';
import EmployeeAddModal from './EmployeeAddModal.vue';
import EmployeeEditModal from './EmployeeEditModal.vue';

export default {
  components: {
    'employee-add-modal': EmployeeAddModal,
    'employee-edit-modal': EmployeeEditModal,
  },
  data() {
    return {
      employees: [],
      diningHalls: [],
      pageSize: 5,
      currentPage: 1,
      employeeToEdit: null,
    };
  },
  computed: {
    totalPages() {
      return Math.ceil(this.employees.length / this.pageSize);
    },
    displayedEmployees() {
      const start = (this.currentPage - 1) * this.pageSize;
      const end = start + this.pageSize;
      return this.employees.slice(start, end);
    },
  },
  methods: {
    async getEmployees(forceRefresh) {
      try {
        const response = await EmployeeService.getEmployees({ forceRefresh });
        this.employees = response.data;
      } catch (error) {
        console.error('Error fetching employees:', error);
      }
    },

    async getDiningHalls(forceRefresh) {
      try {
        const response = await DiningHallService.getDiningHalls({ forceRefresh });
        this.diningHalls = response.data;
      } catch (error) {
        console.error('Error fetching dinigHalls:', error);
      }
    },

    openAddEmployeeModal() {
      this.$refs.employeeAddModal.openModal();
    },
    openEditEmployeeModal(employee) {
      this.employeeToEdit = employee;
      this.$refs.employeeEditModal.openModal(employee);
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
    getAddress(employee) {
      return `${employee.aptNo}, ${employee.street}, ${employee.city}, ${employee.state}, ${employee.zipCode}`;
    },
  },
  mounted() {
    this.getEmployees();
    this.getDiningHalls();
  },
};
</script>
 
  
<style scoped>
.pagination {
  display: flex;
  list-style: none;
  padding: 0;
  justify-content: center;
  margin-top: 20px;
  /* Adjust as needed */
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