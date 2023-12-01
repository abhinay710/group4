<template>
  <div class="modal fade" ref="menuEditModal" tabindex="-1" role="dialog" aria-labelledby="menuEditModalLabel"
    aria-hidden="true">
    <div class="modal-dialog" role="document">
      <div class="modal-content">
        <div class="modal-header">
          <h5 v-if="editedMenu.id === undefined" class="modal-title" id="menuEditModalLabel">Add Menu Item</h5>
          <h5 v-if="editedMenu.id !== undefined" class="modal-title" id="menuEditModalLabel">Edit Menu Item</h5>

          <button type="button" class="close" data-dismiss="modal" aria-label="Close" @click="closeModal">
            <span aria-hidden="true">&times;</span>
          </button>
        </div>
        <div class="modal-body">
          <form @submit.prevent="saveChanges">
            <!-- Menu Item ID -->
            <div class="form-group" v-if="editedMenu.id !== undefined">
              <label for="editMenuItemId">Menu Item ID:</label>
              <input type="text" v-model="editedMenu.id" class="form-control" id="editMenuItemId" disabled="true">
            </div>

            <!-- Item Name -->
            <div class="form-group">
              <label for="editItemName">Item Name:</label>
              <input type="text" v-model="editedMenu.itemName" class="form-control" id="editItemName">
            </div>

            <!-- Price -->
            <div class="form-group">
              <label for="editPrice">Price:</label>
              <input type="number" v-model="editedMenu.price" class="form-control" id="editPrice">
            </div>

            <!-- Dining Station -->
            <div class="form-group">
              <label for="editDiningStation">Dining Station:</label>
              <input type="text" v-model="editedMenu.diningStation" class="form-control" id="editDiningStation">
            </div>

            <!-- Portion -->
            <div class="form-group">
              <label for="editPortion">Portion:</label>
              <input type="text" v-model="editedMenu.portion" class="form-control" id="editPortion">
            </div>

            <!-- Calories -->
            <div class="form-group">
              <label for="editCalories">Calories:</label>
              <input type="number" v-model="editedMenu.calories" class="form-control" id="editCalories">
            </div>
            <div class="form-group">
              <label for="editIngredients">Ingredients:</label>
              <input type="text" v-model="editedMenu.ingredients" class="form-control" id="editIngredients">
            </div>

            <!-- Is Vegan -->
            <div class="form-group">
              <label for="editIsVegan">Is Vegan:</label>
              <input type="text" v-model="editedMenu.isVegan" class="form-control" id="editIsVegan">
            </div>

            <!-- Contains Gluten -->
            <div class="form-group">
              <label for="editContainsGluten">Contains Gluten:</label>
              <input type="text" v-model="editedMenu.containsGluten" class="form-control" id="editContainsGluten">
            </div>

            <!-- Contains Dairy -->
            <div class="form-group">
              <label for="editContainsDairy">Contains Dairy:</label>
              <input type="text" v-model="editedMenu.containsDairy" class="form-control" id="editContainsDairy">
            </div>

            <!-- Contains Nuts -->
            <div class="form-group">
              <label for="editContainsNuts">Contains Nuts:</label>
              <input type="text" v-model="editedMenu.containsNuts" class="form-control" id="editContainsNuts">
            </div>

            <!-- Protein -->
            <div class="form-group">
              <label for="editProtein">Protein:</label>
              <input type="number" v-model="editedMenu.protein" class="form-control" id="editProtein">
            </div>

            <!-- Total Carbohydrates -->
            <div class="form-group">
              <label for="editTotalCarbohydrates">Total Carbohydrates:</label>
              <input type="number" v-model="editedMenu.totalCarbohydrates" class="form-control"
                id="editTotalCarbohydrates">
            </div>

            <!-- Cholesterol -->
            <div class="form-group">
              <label for="editCholesterol">Cholesterol:</label>
              <input type="number" v-model="editedMenu.cholesterol" class="form-control" id="editCholesterol">
            </div>

            <!-- Total Fat -->
            <div class="form-group">
              <label for="editTotalFat">Total Fat:</label>
              <input type="number" v-model="editedMenu.totalFat" class="form-control" id="editTotalFat">
            </div>

            <!-- Sodium -->
            <div class="form-group">
              <label for="editSodium">Sodium:</label>
              <input type="number" v-model="editedMenu.sodium" class="form-control" id="editSodium">
            </div>

            <!-- Potassium -->
            <div class="form-group">
              <label for="editPotassium">Potassium:</label>
              <input type="number" v-model="editedMenu.potassium" class="form-control" id="editPotassium">
            </div>

            <!-- Iron -->
            <div class="form-group">
              <label for="editIron">Iron:</label>
              <input type="number" v-model="editedMenu.iron" class="form-control" id="editIron">
            </div>

            <!-- Calcium -->
            <div class="form-group">
              <label for="editCalcium">Calcium:</label>
              <input type="number" v-model="editedMenu.calcium" class="form-control" id="editCalcium">
            </div>
            <div class="form-group">
              <label for="editDiningHall">Dining Hall:</label>
              <select v-model="editedMenu.diningHall" class="form-control" id="editDiningHall">
                <option v-for="hall in diningHalls" :key="hall.id" :value="hall">{{ hall.diningHallName }}</option>
              </select>
            </div>


            <button type="submit" class="btn btn-primary">Save Changes</button>
          </form>
        </div>
      </div>
    </div>
  </div>
</template>
  
<script>
import MenuService from '../services/MenuService';
/* eslint-disable */
export default {
  data() {
    return {
      editedMenu: {},
    };
  },
  props: ['diningHalls'],
  methods: {
    openModal(menuItem) {
      if (menuItem) {
        this.editedMenu = { ...menuItem };
      }
      $(this.$refs.menuEditModal).modal('show');
    },
    closeModal() {
      this.editedMenu = {};
      $(this.$refs.menuEditModal).modal('hide');
    },
    async saveChanges() {
      try {
        await MenuService.saveMenu(this.editedMenu);

        this.closeModal();
      } catch (error) {
        console.error('Error updating menu item:', error);
      }
    },
  },
};
</script>
  
<style scoped></style>
  