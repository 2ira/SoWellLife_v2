<template>
  <div id="app">
    <Navbar />
    <router-view />
  </div>
</template>

<script>
import Navbar from './components/AppNavbar.vue';

export default {
  name: 'App',
  components: {
    Navbar
  }
};
</script>

<style>
#app {
  @apply bg-gray-50 min-h-screen;
  font-family: 'PingFang SC', 'Helvetica Neue', Arial, sans-serif;
}
</style>