<template>
    <div class="wrapper">
        <PageHearder />
        <AnswerSurvey />
    </div>
</template>

<script>
import PageHearder from './components/layout/PageHearder.vue'
import AnswerSurvey from './components/user/AnswerSurvey.vue'
export default {
    name: 'PageSurveySetup',
    components: {
        PageHearder, AnswerSurvey
    }
}
</script>

<style>
.wrapper {
    min-height: 100vh;
    /* đặt chiều cao tối thiểu bằng chiều cao của viewport */
    display: flex;
    /* sử dụng flexbox */
    flex-direction: column;
    /* xếp các phần tử theo chiều dọc */
}
</style>