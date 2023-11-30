<template>
    <div v-if="showModal" class="modal">
      <div class="modal-content">
        <span class="close" @click="closeModal">&times;</span>
        <h2>Add New Menu Item</h2>
  
        <!-- Form to add a new menu item -->
        <form @submit.prevent="saveNewMenuItem">
            <label for="itemName">Item Name:</label>
    <input type="text" v-model="editedMenuItem.itemName" required />

    <label for="price">Price:</label>
    <input type="number" v-model="editedMenuItem.price" required />

    <label for="diningStation">Dining Station:</label>
    <input type="text" v-model="editedMenuItem.diningStation" />

    <!-- Add input fields for other properties of the menu item -->
    <label for="portion">Portion:</label>
    <input type="text" v-model="editedMenuItem.portion" />

    <label for="calories">Calories:</label>
    <input type="number" v-model="editedMenuItem.calories" />

    <!-- Additional properties -->
    <!-- Continue adding input fields for other properties -->

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
  
          <button type="submit">Add Menu Item</button>
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
    },
    data() {
      return {
        newMenuItem: {
          itemName: '',
          price: null,
          diningStation: '',
          portion: '',
          calories: null,
          // Initialize other properties with appropriate default values
          // Add properties for the additional fields
          ingredients: '',
          isVegan: false,
          containsGluten: false,
          // Add more properties as needed
        },
      };
    },
    methods: {
      closeModal() {
        this.$emit('close');
      },
      saveNewMenuItem() {
        // Validate the form if needed
  
        // Emit an event to save the new menu item
        this.$emit('save', this.newMenuItem);
  
        // Close the modal
        this.closeModal();
      },
    },
  };
  </script>
  
  <style scoped>
  /* Add your styles as needed */
  </style>
  