<template>
    <div>
      <div class="position-relative page-item">
        <h2><strong>MENU ITEMS</strong></h2>
        <div class="position-absolute top-0 end-0 mt-1">
          <button class="btn btn-primary" @click="openAddMenuItemModal">Add Menu Item</button>
        </div>
      </div>
      <table class="table">
        <thead>
          <tr>
  <td>{{ menuItem.id }}</td>
  <td>{{ menuItem.itemName }}</td>
  <td>{{ menuItem.price }}</td>
  <td>{{ menuItem.diningStation }}</td>
  <td>{{ menuItem.portion }}</td>
  <td>{{ menuItem.calories }}</td>
  <td>{{ menuItem.ingredients }}</td>
  <td>{{ menuItem.isVegan }}</td>
  <td>{{ menuItem.containsGluten }}</td>
  <td>{{ menuItem.containsDairy }}</td>
  <td>{{ menuItem.containsNuts }}</td>
  <td>{{ menuItem.protein }}</td>
  <td>{{ menuItem.totalCarbohydrates }}</td>
  <td>{{ menuItem.cholesterol }}</td>
  <td>{{ menuItem.totalFat }}</td>
  <td>{{ menuItem.sodium }}</td>
  <td>{{ menuItem.potassium }}</td>
  <td>{{ menuItem.iron }}</td>
  <td>{{ menuItem.calcium }}</td>
  <td>{{ menuItem.diningHall.diningHallName }}</td>
  <td>{{ menuItem.createdBy }}</td>
  <td>{{ menuItem.createdOn }}</td>
  <td>{{ menuItem.updatedBy }}</td>
          </tr>
        </thead>
        <tbody>
          <tr v-for="(menuItem, index) in displayedMenuItems" :key="index">
            <td>{{ menuItem.id }}</td>
            <td>{{ menuItem.itemName }}</td>
            <td>{{ menuItem.price }}</td>
            <td>{{ menuItem.diningStation }}</td>
            <td>{{ menuItem.portion }}</td>
            <!-- Add other columns for each property -->
            <td>{{ menuItem.createdBy }}</td>
            <td>{{ menuItem.createdOn }}</td>
            <td>{{ menuItem.updatedBy }}</td>
            <td>
              <!-- Edit Menu Item Button -->
              <button class="btn btn-primary" @click="openEditMenuItemModal(menuItem)">Edit</button>
            </td>
          </tr>
        </tbody>
      </table>
  
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
  
      <!-- MenuItemAddModal Component -->
      <menu-item-add-modal ref="menuItemAddModal" />
  
      <!-- MenuItemEditModal Component -->
      <menu-item-edit-modal ref="menuItemEditModal" :menuItem="menuItemToEdit" />
    </div>
  </template>
  
  <script>
  import MenuService from '../services/MenuService';
  import MenuAddModal from './MenuAddModal.vue';
  import MenuEditModal from './MenuEditModal.vue';
  /* eslint-disable */
  export default {
    components: {
      'menu-item-add-modal': MenuAddModal,
      'menu-item-edit-modal': MenuEditModal,
    },
    data() {
      return {
        menuItems: [],
        pageSize: 5,
        currentPage: 1,
        menuItemToEdit: null,
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
    },
  };
  </script>
  
  <style scoped>
  /* Add any custom styles for the menu item list here */
  </style>
  