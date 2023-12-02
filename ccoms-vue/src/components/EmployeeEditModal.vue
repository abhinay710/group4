<template>
  <div class="modal fade" ref="employeeEditModal" tabindex="-1" role="dialog" aria-labelledby="employeeEditModalLabel"
    aria-hidden="true">
    <div class="modal-dialog" role="document">
      <div class="modal-content">
        <div class="modal-header">
          <h5 v-if="editedEmployee.id === undefined" class="modal-title" id="employeeEditModalLabel">Add Employee</h5>
          <h5 v-if="editedEmployee.id !== undefined" class="modal-title" id="employeeEditModalLabel">Edit Employee</h5>

          <button type="button" class="close" data-dismiss="modal" aria-label="Close" @click="closeModal">
            <span aria-hidden="true">&times;</span>
          </button>
        </div>
        <div class="modal-body">
          <form @submit.prevent="saveChanges">
            <!-- Employee ID -->
            <div class="form-group" v-if="editedEmployee.id !== undefined">
              <label for="editEmployeeId">Employee ID:</label>
              <input type="text" v-model="editedEmployee.id" class="form-control" id="editEmployeeId" disabled="true">
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
              <label for="editDiningHall">Dining Hall:</label>
              <select v-model="editedEmployee.diningHall" class="form-control" id="editDiningHall">
                <option v-for="hall in diningHalls" :key="hall.id" :value="hall">{{ hall.diningHallName }}</option>
              </select>
            </div>

            <!-- Dining Station -->
            <div class="form-group">
              <label for="editDiningStation">Dining Station:</label>
              <input type="text" v-model="editedEmployee.diningStation" class="form-control" id="editDiningStation">
            </div>

            <!-- Role -->
            <div class="form-group">
              <label for="editRole">Role:</label>
              <select v-model="editedEmployee.role" class="form-control" id="editRole">
                <option>employee</option>
                <option>manager</option>
              </select>
            </div>

            <!-- Email ID -->
            <div class="form-group">
              <label for="editEmailID">Email ID:</label>
              <input type="text" v-model="editedEmployee.emailID" class="form-control" id="editEmailID">
            </div>
            <div class="form-group">
              <label for="editPassword">password:</label>
              <input type="text" v-model="editedEmployee.password" class="form-control" id="editPassword">
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
  props: ['diningHalls'],
  methods: {
    openModal(employee) {
      if (employee) {
        this.editedEmployee = { ...employee };
      }
      $(this.$refs.employeeEditModal).modal('show');
    },
    closeModal() {
      this.editedEmployee = {};
      $(this.$refs.employeeEditModal).modal('hide');
    },
    async saveChanges() {
      try {
        if (!this.editedEmployee.id) {
          this.editedEmployee.createdBy = localStorage.getItem('name');
          this.editedEmployee.createdOn = new Date();
        }
        this.editedEmployee.updatedBy = localStorage.getItem('name');

        await EmployeeService.saveEmployee(this.editedEmployee);

        this.closeModal();
      } catch (error) {
        console.error('Error updating employee:', error);
      }
    },
  },
};
</script>

<style scoped></style>