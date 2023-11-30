<template>
    <div class="modal fade" ref="diningHallEditModal" tabindex="-1" role="dialog" aria-labelledby="diningHallEditModalLabel"
      aria-hidden="true">
      <div class="modal-dialog" role="document">
        <div class="modal-content">
          <div class="modal-header">
            <h5 v-if="editedDiningHall.id === undefined" class="modal-title" id="diningHallEditModalLabel">Add Dining Hall</h5>
            <h5 v-if="editedDiningHall.id !== undefined" class="modal-title" id="diningHallEditModalLabel">Edit Dining Hall</h5>
  
            <button type="button" class="close" data-dismiss="modal" aria-label="Close" @click="closeModal">
              <span aria-hidden="true">&times;</span>
            </button>
          </div>
          <div class="modal-body">
            <form @submit.prevent="saveChanges">
              <!-- Dining Hall ID -->
              <div class="form-group" v-if="editedDiningHall.id !== undefined">
                <label for="editDiningHallId">Dining Hall ID:</label>
                <input type="text" v-model="editedDiningHall.id" class="form-control" id="editDiningHallId" disabled="true">
              </div>
  
              <!-- Dining Hall Name -->
              <div class="form-group">
                <label for="editDiningHallName">Dining Hall Name:</label>
                <input type="text" v-model="editedDiningHall.diningHallName" class="form-control" id="editDiningHallName" required>
              </div>
  
              <!-- Timings -->
              <div class="form-group">
                <label for="editTimings">Timings:</label>
                <input type="text" v-model="editedDiningHall.timings" class="form-control" id="editTimings" required>
              </div>
  
              <!-- Apt No -->
              <div class="form-group">
                <label for="editAptNo">Apartment Number:</label>
                <input type="text" v-model="editedDiningHall.aptNo" class="form-control" id="editAptNo">
              </div>
  
              <!-- Street -->
              <div class="form-group">
                <label for="editStreet">Street:</label>
                <input type="text" v-model="editedDiningHall.street" class="form-control" id="editStreet">
              </div>
  
              <!-- City -->
              <div class="form-group">
                <label for="editCity">City:</label>
                <input type="text" v-model="editedDiningHall.city" class="form-control" id="editCity">
              </div>
  
              <!-- State -->
              <div class="form-group">
                <label for="editState">State:</label>
                <input type="text" v-model="editedDiningHall.state" class="form-control" id="editState">
              </div>
  
              <!-- Zip Code -->
              <div class="form-group">
                <label for="editZipCode">Zip Code:</label>
                <input type="text" v-model="editedDiningHall.zipCode" class="form-control" id="editZipCode">
              </div>
  
              <button type="submit" class="btn btn-primary">Save Changes</button>
            </form>
          </div>
        </div>
      </div>
    </div>
  </template>
  
  <script>
  import DiningHallService from '../services/DiningHallService';
  import DininghallAddModal from './DininghallAddModal.vue';
  /* eslint-disable */
  export default {
    components: {
    'dining-hall-add-modal': DininghallAddModal,
  },
    data() {
      return {
        editedDiningHall: {},
      };
    },
    props: ['diningHalls'],
    methods: {
      openModal(diningHall) {
        if (diningHall) {
          this.editedDiningHall = { ...diningHall };
        }
        $(this.$refs.diningHallEditModal).modal('show');
      },
      closeModal() {
        this.editedDiningHall = {};
        $(this.$refs.diningHallEditModal).modal('hide');
      },
      async saveChanges() {
        try {
          await DiningHallService.saveDiningHall(this.editedDiningHall);
  
          this.closeModal();
          // Optionally, you can refresh the dining hall list
          this.$emit('refreshDiningHalls');
        } catch (error) {
          console.error('Error updating dining hall:', error);
          // Handle the error, e.g., show a user-friendly message
        }
      },
    },
  };
  /* eslint-enable */
  </script>
  
  <style scoped>
  /* Add any custom styles for the modal here */
  </style>
  