<template>
    <div class="wrapper">
        <PageHearder />
        <FormUpdateEmployee />
        <PageFooter />
    </div>
</template>

<script>
import PageHearder from './components/layout/PageHearder.vue'
import PageFooter from './components/layout/PageFooter.vue'
import FormUpdateEmployee from './components/admin/FormEditEmployee.vue'
export default {
    name: 'PageUpdateEmployee',
    components: {
        PageHearder, PageFooter, FormUpdateEmployee
    }
}
</script>

<style></style>