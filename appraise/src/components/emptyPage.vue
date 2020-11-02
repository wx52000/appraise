<template>

</template>

<script>
export default {
  name: "emptyPage",
  mounted() {
    this.beforeRouteEnter()
  },
  methods:{
    beforeRouteEnter (to, from, next) {
      next(vm => {
        vm.$router.replace(from.path)
      })
    }
  }
}
</script>

<style scoped>

</style>
