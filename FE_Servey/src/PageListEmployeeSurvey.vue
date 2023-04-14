<template>
    <div class="wrapper">
        <PageHearder />
        <ListEmployee />
        <PageFooter />
    </div>
</template>

<script>
import PageHearder from './components/layout/PageHearder.vue'
import PageFooter from './components/layout/PageFooter.vue'
import ListEmployee from './components/admin/ListEmployee.vue'
export default {
    name: 'PageListEmployee',
    components: {
        PageHearder, PageFooter, ListEmployee
    }
}
</script>

<style></style>