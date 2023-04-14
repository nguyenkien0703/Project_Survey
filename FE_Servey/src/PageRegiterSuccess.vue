<template>
    <div class="wrapper">
        <PageHearder />
        <div class="container">
            <div class="d-flex justify-content-center tk">
                <h3>
                    Đăng kí thành công !!
                </h3>
            </div>
            <div class="d-flex justify-content-center ">
                <router-link to="/" class="d-block text-center mt-3">
                    Quay về trang danh sách
                </router-link>
            </div>
        </div>
        <PageFooter />
    </div>
</template>

<script>
import PageHearder from './components/layout/PageHearder.vue'
import PageFooter from './components/layout/PageFooter.vue'
export default {
    name: 'PageRegiterSucces',
    components: {
        PageHearder, PageFooter
    }
}
</script>

<style>
.bg-cl {
    padding: 30px;
    background-color: #E3C2B9;
    border-radius: 20px;
}
</style>