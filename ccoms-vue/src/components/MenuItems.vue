<template>
  <div class="container">
    <h2 class="mt-5 mb-4 text-center">Menu</h2>
    <div class="mb-4">
      <label for="diningHallDropdown">Select Dining Hall:</label>
      <select v-model="selectedDiningHall" @change="filterMenuItemsByDiningHall" class="form-control"
        id="diningHallDropdown">
        <option v-for="hall in diningHalls" :key="hall.id" :value="hall.id">{{ hall.diningHallName }}</option>
      </select>
    </div>



    <div v-for="(diningStation, index) in uniqueDiningStations" :key="index" class="mb-4">
      <h4 class="bg-dark text-white p-2">{{ diningStation }}</h4>

      <div class="row">
        <div v-for="menuItem in filteredMenuItemsByStation(diningStation)" :key="menuItem.id" class="col-md-4">
          <div class="card mb-4">
            <div class="card-body">
              <h5 class="card-title">{{ menuItem.itemName }}</h5>
              <p class="card-text">{{ menuItem.diningStation }}</p>
              <p class="card-text"><strong>$ {{ menuItem.price }}</strong></p>
              <button class="btn btn-primary btn-block" @click="addToCart(menuItem)">
                Add to Cart
              </button>
              <button class="btn btn-info btn-block mt-2" @click="openModal(menuItem)">
                Nutritional Info
              </button>
            </div>
          </div>
        </div>
      </div>
    </div>

    <!-- Nutritional Info Modal -->
    <div v-if="nutritionalInfoDetails" class="modal fade" id="nutritionalInfoModal" tabindex="-1" role="dialog"
      aria-labelledby="nutritionalInfoModalLabel" aria-hidden="true">
      <div class="modal-dialog" role="document">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title" id="nutritionalInfoModalLabel">Nutritional Information</h5>
            <button type="button" class="close" data-dismiss="modal" aria-label="Close" @click="closeModal">
              <span aria-hidden="true">&times;</span>
            </button>
          </div>
          <div class="modal-body">
            <!-- Display nutritional information details here -->
            <p><strong>Item Name:</strong> {{ nutritionalInfoDetails.itemName }}</p>
            <p><strong>Portion:</strong> {{ nutritionalInfoDetails.portion }}</p>
            <p><strong>Calories:</strong> {{ nutritionalInfoDetails.calories }}</p>
            <p><strong>Ingredients:</strong> {{ nutritionalInfoDetails.ingredients }}</p>
            <p><strong>Is Vegan:</strong> {{ nutritionalInfoDetails.isVegan }}</p>
            <p><strong>Contains Gluten:</strong> {{ nutritionalInfoDetails.containsGluten }}</p>
            <p><strong>Contains Dairy:</strong> {{ nutritionalInfoDetails.containsDairy }}</p>
            <p><strong>Contains Nuts:</strong> {{ nutritionalInfoDetails.containsNuts }}</p>
            <p><strong>Protein:</strong> {{ nutritionalInfoDetails.protein }}</p>
            <p><strong>Total Carbohydrates:</strong> {{ nutritionalInfoDetails.totalCarbohydrates }}</p>
            <p><strong>Cholesterol:</strong> {{ nutritionalInfoDetails.cholesterol }}</p>
            <p><strong>Total Fat:</strong> {{ nutritionalInfoDetails.totalFat }}</p>
            <p><strong>Sodium:</strong> {{ nutritionalInfoDetails.sodium }}</p>
            <p><strong>Potassium:</strong> {{ nutritionalInfoDetails.potassium }}</p>
            <p><strong>Iron:</strong> {{ nutritionalInfoDetails.iron }}</p>
            <p><strong>Calcium:</strong> {{ nutritionalInfoDetails.calcium }}</p>

          </div>

          <div class="modal-footer">
            <button type="button" class="btn btn-secondary" data-dismiss="modal" @click="closeModal">Close</button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import MenuService from '../services/MenuService';
import DiningHallService from '@/services/DiningHallService';

/* eslint-disable */
export default {
  name: 'MenuItems',
  data() {
    return {
      menuItems: [],
      filteredMenuItems: [],
      diningHalls: [],
      nutritionalInfoDetails: null,
      selectedDiningHall: 'all'
    };
  },
  computed: {
    uniqueDiningStations() {
      // Get unique dining stations
      return [...new Set(this.menuItems.map((item) => item.diningStation))];
    },
  },
  methods: {
    getMenuItems() {
      MenuService.getMenuItems().then((response) => {
        this.menuItems = response.data;
        this.filteredMenuItems = response.data;
      });
    },
    async getDiningHalls(forceRefresh) {
      try {
        const response = await DiningHallService.getDiningHalls({ forceRefresh });
        this.diningHalls = response.data;
      } catch (error) {
        console.error('Error fetching dinigHalls:', error);
      }
    },
    filteredMenuItemsByStation(diningStation) {
      // Filter menu items based on the selected dining station
      return this.filteredMenuItems.filter((item) => item.diningStation === diningStation);
    },
    filterMenuItemsByDiningHall() {
      // Filter menu items based on the selected dining station
      
      this.filteredMenuItems = this.menuItems.filter((item) => item.diningHall.id === this.selectedDiningHall);
      localStorage.setItem('diningHallId', this.selectedDiningHall);
    
    },
    addToCart(item) {
      let cartItems = JSON.parse(localStorage.getItem('cartItems')) || [];
      const existingItem = cartItems.find((cartItem) => cartItem.item.id === item.id);

      if (existingItem) {
        existingItem.quantity++;
      } else {
        cartItems.push({ item, quantity: 1 });
      }

      localStorage.setItem('cartItems', JSON.stringify(cartItems));
    },
    openModal(menuItem) {
      // Set up the nutritional information details based on menuItem
      this.nutritionalInfoDetails = {
        itemName: menuItem.itemName || 'N/A',
        portion: menuItem.portion || 'N/A',
        calories: menuItem.calories || 'N/A',
        ingredients: menuItem.ingredients || 'N/A',
        isVegan: menuItem.isVegan || 'N/A',
        containsGluten: menuItem.containsGluten || 'N/A',
        containsDairy: menuItem.containsDairy || 'N/A',
        containsNuts: menuItem.containsNuts || 'N/A',
        protein: menuItem.protein || 'N/A',
        totalCarbohydrates: menuItem.totalCarbohydrates || 'N/A',
        cholesterol: menuItem.cholesterol || 'N/A',
        totalFat: menuItem.totalFat || 'N/A',
        sodium: menuItem.sodium || 'N/A',
        potassium: menuItem.potassium || 'N/A',
        iron: menuItem.iron || 'N/A',
        calcium: menuItem.calcium || 'N/A',
        // Add more details as needed
      };

      // Open the modal using jQuery
      $('#nutritionalInfoModal').modal('show');
    },
    closeModal() {
      // Reset the nutritional information details and close the modal
      this.nutritionalInfoDetails = null;
      $('#nutritionalInfoModal').modal('hide');
    },
  },
  created() {
    this.getMenuItems();
    this.getDiningHalls();
  },
};
/* eslint-enable */
</script>
<style>
.card {
  box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2);
  transition: 0.3s;
}

.card:hover {
  box-shadow: 0 8px 16px 0 rgba(0, 0, 0, 0.2);
}

.card-title {
  font-size: 1.25rem;
  font-weight: 600;
}

.card-text {
  font-size: 0.875rem;
}

.card-footer {
  background-color: #f7f7f7;
}

.btn {
  background-color: maroon;
  color: #fff;
  border: none;
}

.btn:hover {
  background-color: rgb(152, 1, 1);
}
</style>
