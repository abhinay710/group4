<template>
    <div class="modal fade" ref="studentEditModal" tabindex="-1" role="dialog" aria-labelledby="studentEditModalLabel"
        aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="studentEditModalLabel">Edit Student</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close" @click="closeModal">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <form @submit.prevent="saveChanges">
                        <!-- Student ID -->
                        <div class="form-group">
                            <label for="editStudentId">Student ID:</label>
                            <input type="text" v-model="editedStudent.id" class="form-control" id="editStudentId" readonly>
                        </div>

                        <!-- First Name -->
                        <div class="form-group">
                            <label for="editFirstName">First Name:</label>
                            <input type="text" v-model="editedStudent.firstName" class="form-control" id="editFirstName">
                        </div>

                        <!-- Last Name -->
                        <div class="form-group">
                            <label for="editLastName">Last Name:</label>
                            <input type="text" v-model="editedStudent.lastName" class="form-control" id="editLastName">
                        </div>

                        <!-- Global ID -->
                        <div class="form-group">
                            <label for="editGlobalId">Global ID:</label>
                            <input type="text" v-model="editedStudent.globalID" class="form-control" id="editGlobalId">
                        </div>

                        <!-- Password -->
                        <div class="form-group">
                            <label for="editPassword">Password:</label>
                            <input type="password" v-model="editedStudent.password" class="form-control" id="editPassword">
                        </div>

                        <!-- Phone Number -->
                        <div class="form-group">
                            <label for="editPhoneNum">Phone Number:</label>
                            <input type="text" v-model="editedStudent.phoneNum" class="form-control" id="editPhoneNum">
                        </div>

                        <!-- Flex Amount Balance -->
                        <div class="form-group">
                            <label for="editFlexAmountBalance">Flex Amount Balance:</label>
                            <input type="text" v-model="editedStudent.flexAmountBalance" class="form-control"
                                id="editFlexAmountBalance">
                        </div>

                        <!-- Apt No -->
                        <div class="form-group">
                            <label for="editAptNo">Apt No:</label>
                            <input type="text" v-model="editedStudent.aptNo" class="form-control" id="editAptNo">
                        </div>

                        <!-- Street -->
                        <div class="form-group">
                            <label for="editStreet">Street:</label>
                            <input type="text" v-model="editedStudent.street" class="form-control" id="editStreet">
                        </div>

                        <!-- City -->
                        <div class="form-group">
                            <label for="editCity">City:</label>
                            <input type="text" v-model="editedStudent.city" class="form-control" id="editCity">
                        </div>

                        <!-- State -->
                        <div class="form-group">
                            <label for="editState">State:</label>
                            <input type="text" v-model="editedStudent.state" class="form-control" id="editState">
                        </div>

                        <!-- Zip Code -->
                        <div class="form-group">
                            <label for="editZipCode">Zip Code:</label>
                            <input type="text" v-model="editedStudent.zipCode" class="form-control" id="editZipCode">
                        </div>

                        <button type="submit" class="btn btn-primary">Save Changes</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</template>
  
<script>
import StudentService from '../services/StudentService';

export default {
    data() {
        return {
            editedStudent: {},
        };
    },
    methods: {
        openModal(student) {
            this.editedStudent = { ...student };
            $(this.$refs.studentEditModal).modal('show');
        },
        closeModal() {
            this.editedStudent = {};
            $(this.$refs.studentEditModal).modal('hide');
        },
        async saveChanges() {
            try {
                await StudentService.saveStudent(this.editedStudent);

                this.closeModal();
            } catch (error) {
                console.error('Error updating student:', error);
            }
        },
    },
};
</script>
  
<style scoped></style>
  