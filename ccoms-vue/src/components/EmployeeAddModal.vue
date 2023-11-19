<template>
    <div>
      <!-- Button to trigger modal -->
      <button @click="openModal" class="btn btn-primary">Add Employee</button>
  
      <!-- Modal -->
      <div class="modal" tabindex="-1" role="dialog" ref="employeeModal">
        <div class="modal-dialog" role="document">
          <div class="modal-content">
            <div class="modal-header">
              <h5 class="modal-title">Add Employee</h5>
              <button type="button" class="close" data-dismiss="modal" aria-label="Close" @click="closeModal">
                <span aria-hidden="true">&times;</span>
              </button>
            </div>
            <div class="modal-body">
              <!-- Form for adding employee details -->
              <form @submit.prevent="addEmployee">
                <div class="form-group">
                  <label for="firstName">First Name:</label>
                  <input type="text" v-model="employee.firstName" class="form-control" required>
                </div>
  
                <div class="form-group">
                  <label for="lastName">Last Name:</label>
                  <input type="text" v-model="employee.lastName" class="form-control" required>
                </div>
  
                <div class="form-group">
                  <label for="diningHallId">Dining Hall ID:</label>
                  <input type="number" v-model="employee.diningHallId" class="form-control" required>
                </div>
  
                <div class="form-group">
                  <label for="diningStation">Dining Station:</label>
                  <input type="text" v-model="employee.diningStation" class="form-control" required>
                </div>
  
                <div class="form-group">
                  <label for="role">Role:</label>
                  <input type="text" v-model="employee.role" class="form-control" required>
                </div>
  
                <div class="form-group">
                  <label for="emailId">Email ID:</label>
                  <input type="email" v-model="employee.emailId" class="form-control" required>
                </div>
  
                <div class="form-group">
                  <label for="password">Password:</label>
                  <input type="password" v-model="employee.password" class="form-control" required>
                </div>
  
                <div class="form-group">
                  <label for="phoneNum">Phone Number:</label>
                  <input type="tel" v-model="employee.phoneNum" class="form-control" required>
                </div>
  
                <div class="form-group">
                  <label for="aptNo">Apartment Number:</label>
                  <input type="text" v-model="employee.aptNo" class="form-control">
                </div>
  
                <div class="form-group">
                  <label for="street">Street:</label>
                  <input type="text" v-model="employee.street" class="form-control">
                </div>
  
                <div class="form-group">
                  <label for="city">City:</label>
                  <input type="text" v-model="employee.city" class="form-control">
                </div>
  
                <div class="form-group">
                  <label for="state">State:</label>
                  <input type="text" v-model="employee.state" class="form-control">
                </div>
  
                <div class="form-group">
                  <label for="zipCode">Zip Code:</label>
                  <input type="text" v-model="employee.zipCode" class="form-control">
                </div>
  
                <button type="submit" class="btn btn-primary">Add Employee</button>
              </form>
            </div>
          </div>
        </div>
      </div>
    </div>
  </template>
  
  <script>
  export default {
    data() {
      return {
        employee: {
          firstName: '',
          lastName: '',
          diningHallId: null,
          diningStation: '',
          role: '',
          emailId: '',
          password: '',
          phoneNum: '',
          aptNo: '',
          street: '',
          city: '',
          state: '',
          zipCode: '',
        },
      };
    },
    methods: {
      openModal() {
        // Show the modal using jQuery or other methods
        (this.$refs.employeeModal).modal('show');
      },
      closeModal() {
        // Close the modal using jQuery or other methods
        (this.$refs.employeeModal).modal('hide');
      },
      addEmployee() {
        // Call the EmployeeService to add the employee
        // Adjust the method and endpoint based on your service
        this.$store.dispatch('addEmployee', this.employee)
          .then(() => {
            // Clear the form and close the modal
            this.employee = {
              firstName: '',
              lastName: '',
              diningHallId: null,
              diningStation: '',
              role: '',
              emailId: '',
              password: '',
              phoneNum: '',
              aptNo: '',
              street: '',
              city: '',
              state: '',
              zipCode: '',
            };
            this.closeModal();
          })
          .catch((error) => {
            console.error('Error adding employee:', error);
            // Handle the error, e.g., show a user-friendly message
          });
      },
    },
  };
  </script>
  
  <style scoped>
  /* Add any custom styles for the modal here */
  </style>
  