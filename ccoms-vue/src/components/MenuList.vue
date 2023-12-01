<template>
  <div>
    <div class="position-relative page-item">
      <h2><strong>MENU ITEMS</strong></h2>
      <div class="position-absolute top-0 end-0 mt-1">
        <button class="btn btn-primary" @click="openEditMenuItemModal(undefined)">Add Menu Item</button>
      </div>
    </div>
    <table class="table">
      <thead>
        <tr>
          <td>id</td>
          <td>Item Name</td>
          <td>Price</td>
          <td>Dining Station</td>
          <td>portion</td>
          <td>Dining Hall</td>
          <td>Nutritional Info</td>

          <td>Action</td>

        </tr>
      </thead>
      <tbody>
        <tr v-for="(menuItem, index) in displayedMenuItems" :key="index">
          <td>{{ menuItem.id }}</td>
          <td>{{ menuItem.itemName }}</td>
          <td>{{ menuItem.price }}</td>
          <td>{{ menuItem.diningStation }}</td>
          <td>{{ menuItem.portion }}</td>
          <td>{{ menuItem.diningHall.diningHallName }}</td>
          <td><button class="btn btn-primary" @click="openModal(menuItem)">
              Nutritional Info
            </button></td>



          <td>
            <!-- Edit Menu Item Button -->
            <button class="btn btn-primary" @click="openEditMenuItemModal(menuItem)">Edit</button>
          </td>
        </tr>
      </tbody>
    </table>
    <div v-if="nutritionalInfoDetails" class="modal fade" id="nutritionalInfoModal" tabindex="-1" role="dialog"
      aria-labelledby="nutritionalInfoModalLabel" aria-hidden="true">
      <div class="modal-dialog modal-dialog-centered" role="document" style="z-index: 1050;">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title" id="nutritionalInfoModalLabel">Nutritional Info</h5>
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
      <menu-edit-modal ref="menuEditModal" :menu="menuToEdit" :diningHalls="diningHalls" />
    </div>


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


    <!-- MenuItemEditModal Component -->
    <menu-item-edit-modal ref="menuItemEditModal" :menuItem="menuItemToEdit" />
    <div v-if="nutritionalInfoDetails" class="modal fade" id="nutritionalInfoModal" tabindex="-1" role="dialog"
      aria-labelledby="nutritionalInfoModalLabel" aria-hidden="true">
      <div class="modal-dialog modal-dialog-centered" role="document" style="z-index: 1050;">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title" id="nutritionalInfoModalLabel">Nutritional Info</h5>
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
    <menu-item-edit-modal ref="menuEditModal" :menu="menuItemToEdit" :diningHalls="diningHalls" />

  </div>
</template>
  
<script>
import MenuService from '../services/MenuService';
import DiningHallService from '../services/DiningHallService';
import MenuEditModal from './MenuEditModal.vue';

/* eslint-disable */
export default {
  components: {
    'menu-item-edit-modal': MenuEditModal,
  },
  data() {
    return {
      menuItems: [],
      diningHalls: [],
      pageSize: 8,
      currentPage: 1,
      menuItemToEdit: null,
      nutritionalInfoDetails: null,
    };
  },
  computed: {
    totalPages() {
      return Math.ceil(this.menuItems.length / this.pageSize);
    },
    displayedMenuItems() {
      const start = (this.currentPage - 1) * this.pageSize;
      const end = start + this.pageSize;
      return this.menuItems.slice(start, end);
    },
  },
  methods: {
    async getMenuItems(forceRefresh) {
      try {
        const response = await MenuService.getMenuItems({ forceRefresh });
        this.menuItems = response.data;
      } catch (error) {
        console.error('Error fetching menu items:', error);
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
    openAddMenuItemModal() {
      this.$refs.menuItemAddModal.openModal();
    },
    openEditMenuItemModal(menuItem) {
      this.menuItemToEdit = menuItem;
      this.$refs.menuItemEditModal.openModal(menuItem);
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
  },
  mounted() {
    this.getMenuItems();
    this.getDiningHalls();
  },
};
</script>
  
<style scoped>
/* Add any custom styles for the menu item list here */
</style>
  