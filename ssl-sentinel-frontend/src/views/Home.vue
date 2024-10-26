<template>
  <div class="home">
    <h1>This is an home page</h1>
  </div>
</template>

<script>

export default {
  name: 'about',
  data() {
  },
  computed: {
  },
  watch: {
  },
  mounted() {
  },
  created() {
    console.log('home.......')
  },
  methods: {
  }
}
</script>

<style>
@media (min-width: 1024px) {
  .about {
    min-height: 100vh;
    display: flex;
    align-items: center;
  }
}
</style>
