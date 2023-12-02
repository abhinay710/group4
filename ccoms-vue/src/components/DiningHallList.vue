<template>
  <div>
    <div class="position-relative page-item">
      <h2><strong>DINING HALLS</strong></h2>
      <div class="position-absolute top-0 end-0 mt-1">
        <button class="btn btn-primary" @click="openAddDiningHallModal">Add Dining Hall</button>
      </div>
    </div>
    <table class="table">
      <thead>
        <tr>
          <th>Dining Hall ID</th>
          <th>Name</th>
          <th>Timings</th>
          <th>Address</th>
          <th>Created By</th>
          <th>Created On</th>
          <th>Updated By</th>
          <th>Action</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="(diningHall, index) in displayedDiningHalls" :key="index">
          <td>{{ diningHall.id }}</td>
          <td>{{ diningHall.diningHallName }}</td>
          <td>{{ diningHall.timings }}</td>
          <td>{{ getAddress(diningHall) }}</td>
          <td>{{ diningHall.createdBy }}</td>
          <td>{{ formattedDate(diningHall.createdOn) }}</td>
          <td>{{ diningHall.updatedBy }}</td>
          <td>
            <!-- Edit Dining Hall Button -->
            <button class="btn btn-primary" @click="openEditDiningHallModal(diningHall)">Edit</button>
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

    <!-- DiningHallAddModal Component -->
    <dining-hall-add-modal ref="diningHallAddModal" />

    <!-- DiningHallEditModal Component -->
    <dining-hall-edit-modal ref="diningHallEditModal" :diningHall="diningHallToEdit" />
  </div>
</template>
  
<script>
import DiningHallService from '../services/DiningHallService';
import DininghallAddModal from './DininghallAddModal.vue';
import DiningHallEditModal from './DiningHallEditModal.vue';

export default {
  components: {
    'dining-hall-add-modal': DininghallAddModal,
    'dining-hall-edit-modal': DiningHallEditModal,
  },
  data() {
    return {
      diningHalls: [],
      pageSize: 5,
      currentPage: 1,
      diningHallToEdit: null,
    };
  },
  computed: {
    totalPages() {
      return Math.ceil(this.diningHalls.length / this.pageSize);
    },
    displayedDiningHalls() {
      const start = (this.currentPage - 1) * this.pageSize;
      const end = start + this.pageSize;
      return this.diningHalls.slice(start, end);
    },
  },
  methods: {
    async getDiningHalls(forceRefresh) {
      try {
        const response = await DiningHallService.getDiningHalls({ forceRefresh });
        this.diningHalls = response.data;
      } catch (error) {
        console.error('Error fetching dining halls:', error);
      }
    },
    formattedDate(date) {
      const inputDateString = date;
      const dateObject = new Date(inputDateString);

      const day = dateObject.getDate().toString().padStart(2, '0');
      const month = (dateObject.getMonth() + 1).toString().padStart(2, '0');
      const year = dateObject.getFullYear();
      // const hours = dateObject.getHours().toString().padStart(2, '0');
      // const minutes = dateObject.getMinutes().toString().padStart(2, '0');
      // ${hours}:${minutes}
      return `${day}/${month}/${year}`;
    },
    openAddDiningHallModal() {
      this.$refs.diningHallAddModal.openModal();
    },
    openEditDiningHallModal(diningHall) {
      this.diningHallToEdit = diningHall;
      this.$refs.diningHallEditModal.openModal(diningHall);
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
    getAddress(diningHall) {
      return `${diningHall.aptNo}, ${diningHall.street}, ${diningHall.city}, ${diningHall.state}, ${diningHall.zipCode}`;
    },
  },
  mounted() {
    this.getDiningHalls();
  },
};
</script>
  
<style scoped>
/* Add any custom styles for the dining hall list here */
</style>
  