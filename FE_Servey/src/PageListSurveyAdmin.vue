<template>
    <div class="wrapper">
        <PageHearder/>
        <SurveysAdmin/>
        <PageFooter/>
    </div>
</template>

<script>
import PageHearder from './components/layout/PageHearder.vue'
import PageFooter from './components/layout/PageFooter.vue'
import SurveysAdmin from './components/admin/SurveysAdmin.vue'
export default {
    name: 'ListSurvey',
    components: {SurveysAdmin,PageHearder,PageFooter}
}
</script>

<style>

</style>