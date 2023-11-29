<template>
  <div class="modal fade" ref="employeeEditModal" tabindex="-1" role="dialog" aria-labelledby="employeeEditModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="employeeEditModalLabel">Edit Employee</h5>
          <button type="button" class="close" data-dismiss="modal" aria-label="Close" @click="closeModal">
            <span aria-hidden="true">&times;</span>
          </button>
        </div>
        <div class="modal-body">
          <form @submit.prevent="saveChanges">
            <!-- Employee ID -->
            <div class="form-group">
              <label for="editEmployeeId">Employee ID:</label>
              <input type="text" v-model="editedEmployee.id" class="form-control" id="editEmployeeId" readonly>
            </div>

            <!-- First Name -->
            <div class="form-group">
              <label for="editFirstName">First Name:</label>
              <input type="text" v-model="editedEmployee.firstName" class="form-control" id="editFirstName">
            </div>

            <!-- Last Name -->
            <div class="form-group">
              <label for="editLastName">Last Name:</label>
              <input type="text" v-model="editedEmployee.lastName" class="form-control" id="editLastName">
            </div>

            <!-- Dining Hall ID -->
            <div class="form-group">
              <label for="editDiningHallId">Dining Hall ID:</label>
              <input type="text" v-model="editedEmployee.diningHallID" class="form-control" id="editDiningHallId">
            </div>

            <!-- Dining Station -->
            <div class="form-group">
              <label for="editDiningStation">Dining Station:</label>
              <input type="text" v-model="editedEmployee.diningStation" class="form-control" id="editDiningStation">
            </div>

            <!-- Role -->
            <div class="form-group">
              <label for="editRole">Role:</label>
              <input type="text" v-model="editedEmployee.role" class="form-control" id="editRole">
            </div>

            <!-- Email ID -->
            <div class="form-group">
              <label for="editEmailID">Email ID:</label>
              <input type="text" v-model="editedEmployee.emailID" class="form-control" id="editEmailID">
            </div>

            <!-- Phone Number -->
            <div class="form-group">
              <label for="editPhoneNum">Phone Number:</label>
              <input type="text" v-model="editedEmployee.phoneNum" class="form-control" id="editPhoneNum">
            </div>

            <!-- Apt No -->
            <div class="form-group">
              <label for="editAptNo">Apt No:</label>
              <input type="text" v-model="editedEmployee.aptNo" class="form-control" id="editAptNo">
            </div>

            <!-- Street -->
            <div class="form-group">
              <label for="editStreet">Street:</label>
              <input type="text" v-model="editedEmployee.street" class="form-control" id="editStreet">
            </div>

            <!-- City -->
            <div class="form-group">
              <label for="editCity">City:</label>
              <input type="text" v-model="editedEmployee.city" class="form-control" id="editCity">
            </div>

            <!-- State -->
            <div class="form-group">
              <label for="editState">State:</label>
              <input type="text" v-model="editedEmployee.state" class="form-control" id="editState">
            </div>

            <!-- Zip Code -->
            <div class="form-group">
              <label for="editZipCode">Zip Code:</label>
              <input type="text" v-model="editedEmployee.zipCode" class="form-control" id="editZipCode">
            </div>

            <button type="submit" class="btn btn-primary">Save Changes</button>
          </form>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import EmployeeService from '../services/EmployeeService';
/* eslint-disable */
export default {
  data() {
    return {
      editedEmployee: {},
    };
  },
  methods: {
    openModal(employee) {
      this.editedEmployee = { ...employee };
      $(this.$refs.employeeEditModal).modal('show');
    },
    closeModal() {
      this.editedEmployee = {};
      $(this.$refs.employeeEditModal).modal('hide');
    },
    async saveChanges() {
    try {
      // Call the API to update the employee
      await EmployeeService.addEmployee(this.editedEmployee);

      // If the update is successful, close the modal
      this.closeModal();
    } catch (error) {
      // Handle any errors that might occur during the update
      console.error('Error updating employee:', error);
      // Optionally, you can provide feedback to the user about the error
    }
  },
  },
};
</script>

<style scoped>
/* Add your scoped styles here */
</style>
