<template>
    <div>
      <!-- Button to trigger modal -->
      <button @click="openModal" class="btn btn-primary">Add Dining Hall</button>
  
      <!-- Modal -->
      <div class="modal" tabindex="-1" role="dialog" ref="diningHallModal">
        <div class="modal-dialog" role="document">
          <div class="modal-content">
            <div class="modal-header">
              <h5 class="modal-title">Add Dining Hall</h5>
              <button type="button" class="close" data-dismiss="modal" aria-label="Close" @click="closeModal">
                <span aria-hidden="true">&times;</span>
              </button>
            </div>
            <div class="modal-body">
              <!-- Form for adding dining hall details -->
              <form @submit.prevent="addDiningHall">
                <div class="form-group">
                  <label for="diningHallName">Dining Hall Name:</label>
                  <input type="text" v-model="diningHall.diningHallName" class="form-control" required>
                </div>
  
                <div class="form-group">
                  <label for="timings">Timings:</label>
                  <input type="text" v-model="diningHall.timings" class="form-control" required>
                </div>
  
                <div class="form-group">
                  <label for="aptNo">Apartment Number:</label>
                  <input type="text" v-model="diningHall.aptNo" class="form-control">
                </div>
  
                <div class="form-group">
                  <label for="street">Street:</label>
                  <input type="text" v-model="diningHall.street" class="form-control">
                </div>
  
                <div class="form-group">
                  <label for="city">City:</label>
                  <input type="text" v-model="diningHall.city" class="form-control">
                </div>
  
                <div class="form-group">
                  <label for="state">State:</label>
                  <input type="text" v-model="diningHall.state" class="form-control">
                </div>
  
                <div class="form-group">
                  <label for="zipCode">Zip Code:</label>
                  <input type="text" v-model="diningHall.zipCode" class="form-control">
                </div>
  
                <button type="submit" class="btn btn-primary">Add Dining Hall</button>
              </form>
            </div>
          </div>
        </div>
      </div>
    </div>
  </template>
  
  <script>
  import DiningHallService from '../services/DiningHallService';
  /* eslint-disable */
  export default {
    data() {
      return {
        diningHall: {
          diningHallName: '',
          timings: '',
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
        $(this.$refs.diningHallModal).modal('show');
      },
      closeModal() {
        // Close the modal using jQuery or other methods
        $(this.$refs.diningHallModal).modal('hide');
      },
      addDiningHall() {
        // Call the DiningHallService to add the dining hall
        DiningHallService.saveDiningHall(this.diningHall)
          .then(() => {
            // Clear the form and close the modal
            this.diningHall = {
              diningHallName: '',
              timings: '',
              aptNo: '',
              street: '',
              city: '',
              state: '',
              zipCode: '',
            };
            this.closeModal();
            // Optionally, you can refresh the dining hall list
            this.$emit('refreshDiningHalls');
          })
          .catch((error) => {
            console.error('Error adding dining hall:', error);
            // Handle the error, e.g., show a user-friendly message
          });
      },
    },
  };
  </script>
  
  <style scoped>
  /* Add any custom styles for the modal here */
  </style>
  