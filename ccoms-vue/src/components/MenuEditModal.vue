<template>
    <div v-if="showModal" class="modal">
      <div class="modal-content">
        <span class="close" @click="closeModal">&times;</span>
        <h2>Edit Menu Item</h2>
  
    <form @submit.prevent="saveChanges">
    <label for="itemName">Item Name:</label>
    <input type="text" v-model="editedMenuItem.itemName" required />

    <label for="price">Price:</label>
    <input type="number" v-model="editedMenuItem.price" required />

    <label for="diningStation">Dining Station:</label>
    <input type="text" v-model="editedMenuItem.diningStation" />

    <label for="portion">Portion:</label>
    <input type="text" v-model="editedMenuItem.portion" />

    <label for="calories">Calories:</label>
    <input type="number" v-model="editedMenuItem.calories" />

   
    <label for="ingredients">Ingredients:</label>
    <input type="text" v-model="editedMenuItem.ingredients" />

    <label for="isVegan">Is Vegan:</label>
    <input type="checkbox" v-model="editedMenuItem.isVegan" />

    <label for="containsGluten">Contains Gluten:</label>
    <input type="checkbox" v-model="editedMenuItem.containsGluten" />
    
    <label for="containsDairy">Contains Dairy:</label>
    <input type="checkbox" v-model="editedMenuItem.containsDairy" />

    <label for="containsNuts">Contains Nuts:</label>
    <input type="checkbox" v-model="editedMenuItem.containsNuts" />

    <label for="protein">Protein:</label>
    <input type="number" v-model="editedMenuItem.protein" />

    <label for="totalCarbohydrates">Total Carbohydrates:</label>
    <input type="number" v-model="editedMenuItem.totalCarbohydrates" />

    <label for="cholesterol">Cholesterol:</label>
    <input type="number" v-model="editedMenuItem.cholesterol" />

    <label for="totalFat">Total Fat:</label>
    <input type="number" v-model="editedMenuItem.totalFat" />

    <label for="sodium">Sodium:</label>
    <input type="number" v-model="editedMenuItem.sodium" />

    <label for="potassium">Potassium:</label>
    <input type="number" v-model="editedMenuItem.potassium" />

    <label for="iron">Iron:</label>
    <input type="number" v-model="editedMenuItem.iron" />

    <label for="calcium">Calcium:</label>
    <input type="number" v-model="editedMenuItem.calcium" />

    <button type="submit">Save Changes</button>
  </form>
      </div>
    </div>
  </template>
  
  <script>
  /* eslint-disable */
  export default {
    props: {
      showModal: {
        type: Boolean,
        required: true,
      },
      editedMenuItem: {
        type: Object,
        required: true,
      },
    },
    methods: {
      closeModal() {
        this.$emit('close');
      },
      saveChanges() {
        // Validate the form if needed
  
        // Emit an event to save the changes
        this.$emit('save', this.editedMenuItem);
  
        // Close the modal
        this.closeModal();
      },
    },
  };
  </script>
  
  <style scoped>
  /* Add your styles as needed */
  </style>
  