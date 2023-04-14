<template>
    <div class="wrapper">
        <PageHearderUser />
        <AnswerSurvey class="mt-5" />
    </div>
</template>

<script>
import PageHearderUser from './components/layout/PageHearderUser.vue'
import AnswerSurvey from './components/user/AnswerSurvey.vue'
export default {
    name: 'ListSurvey',
    components: { AnswerSurvey, PageHearderUser }
}
</script>

<style></style>