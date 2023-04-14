<template>
    <div class="wrapper">
        <PageHearderUser />
        <AnswerSuccess />
        <PageFooter />
    </div>
</template>

<script>
import PageHearderUser from './components/layout/PageHearderUser.vue'
import PageFooter from './components/layout/PageFooter.vue'
import AnswerSuccess from './components/user/AnswerSuccess.vue'
export default {
    name: 'PageTY',
    components: {
        PageHearderUser, PageFooter,AnswerSuccess
    }
}
</script>

<style></style>