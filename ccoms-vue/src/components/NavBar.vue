<template>
  <div>
    <nav class="navbar navbar-expand-lg navbar-light bg-gold">
      <div class="container">
        <router-link to="/">
          <!-- Replace 'path-to-your-logo.png' with the actual path to your logo image -->
          <img src="../assets/cmu.png" alt="Logo" class="navbar-brand-logo">
        </router-link>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav"
          aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
          <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse justify-content-between" id="navbarNav">
          <div>
            <ul class="navbar-nav ml-auto">
              <li class="nav-item" >
                <router-link to="/menu-items" v-if="$store.state.userName" class="nav-link text-dark">Menu</router-link>
              </li>
              <li class="nav-item">
                <router-link to="/employees" v-if="$store.state.designation === 'manager'" class="nav-link text-dark">Employees</router-link>
              </li>
              <li class="nav-item">
                <router-link to="/students" v-if="$store.state.designation === 'manager'" class="nav-link text-dark">Students</router-link>
              </li>
              <li class="nav-item">
                <router-link to="/dining-halls" v-if="$store.state.designation === 'manager'" class="nav-link text-dark">Dining Halls</router-link>
              </li>
              <li class="nav-item">
                <router-link to="/orders" v-if="$store.state.userName && $store.state.designation !== 'student'" class="nav-link text-dark">Orders</router-link>
              </li>
            </ul>
          </div>
          <div>
            <button class="btn btn-primary"  v-if="$store.state.userName" v-on:click="logout()">Log Out</button>
            <button class="btn btn-primary" v-if="$store.state.designation === 'student'">
              <router-link to="/cart"  class="nav-link text-dark">Cart</router-link>
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
.bg-gold {
  background-color: gold !important;
}

/* Adjust the max-height and max-width as needed to fit your design */
.navbar-brand-logo {
  max-height: 40px;
  max-width: 100%;
}
</style>
