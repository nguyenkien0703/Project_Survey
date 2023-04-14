<template>
    <div class="wrapper">
        <PageHearder/>
        <FormCreateEmployee/>
        <PageFooter/>
    </div>
</template>

<script>
import PageHearder from './components/layout/PageHearder.vue'
import PageFooter from './components/layout/PageFooter.vue'
import FormCreateEmployee from './components/admin/FormCreateEmployee.vue'
export default {
    name:'PageListEmployeeSurvey',
    components: {
        PageHearder,PageFooter,FormCreateEmployee
    }
}
</script>

<style>

</style>