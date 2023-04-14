<template>
    <div class="wrapper">
        <PageHearderUser />
        <SurveysUser />
        <PageFooter />
    </div>
</template>

<script>
import PageHearderUser from './components/layout/PageHearderUser.vue'
import PageFooter from './components/layout/PageFooter.vue'
import SurveysUser from './components/user/SurveysUser.vue'
export default {
    name: 'ListSurvey',
    components: { SurveysUser, PageHearderUser, PageFooter }
}
</script>

<style></style>