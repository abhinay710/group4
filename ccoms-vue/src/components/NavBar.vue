<template>
  <div>
    <nav class="navbar navbar-expand-lg navbar-light bg-gold">
      <div class="container">
        <router-link to="/">
          <img src="../assets/cmu.png" alt="Logo" class="navbar-brand-logo">
        </router-link>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav"
          aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
          <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse justify-content-between" id="navbarNav">
          <div>
            <ul class="navbar-nav ml-auto">
              <!-- Menu item for all users -->
              <li class="nav-item">
                <router-link to="/menu-items" v-if="$store.state.userName" class="nav-link text-dark">Menu</router-link>
              </li>
              <!-- Orders option in navbar -->
              <li class="nav-item" v-if="$store.state.userName && $store.state.designation === 'manager'">
                <router-link to="/orders" class="nav-link text-dark">Orders</router-link>
              </li>
              <!-- Orders dropdown with additional reports based on designation -->
              <li class="nav-item dropdown" v-if="$store.state.userName && $store.state.designation === 'manager'">
                <button class="nav-link dropdown-toggle text-dark" href="#" id="navbarDropdown" type="button"
                  data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                  Reports
                </button>
                <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                  <router-link to="/student-list" class="dropdown-item">Generate Student Report</router-link>
                  <router-link to="/employee-list" class="dropdown-item">Generate Employee Report</router-link>
                  <router-link to="/dining-hall-list" class="dropdown-item">Generate Dining Hall Report</router-link>
                </div>
              </li>
              <!-- Cart for students -->
              <li class="nav-item" v-if="$store.state.designation === 'student'">
                <router-link to="/cart" class="nav-link text-dark">Cart</router-link>
              </li>
            </ul>
          </div>
          <div>
            <button class="btn btn-primary"  v-if="$store.state.userName" v-on:click="logout()">Log Out</button>
            <button class="btn btn-primary" v-if="$store.state.designation === 'student'">
              <router-link to="/cart"  class="nav-link">Cart</router-link>
            </button>
          </div>
        </div>
      </div>
    </nav>
  </div>
</template>

<script>
export default {
  methods: {
    logout() {
      this.$store.commit('logout');
      this.$router.push('/login');
    },
  }
};
</script>
<style scoped>

/* Adjust the max-height and max-width as needed to fit your design */
.navbar-brand-logo {
  max-height: 40px;
  max-width: 100%;
}
</style>
