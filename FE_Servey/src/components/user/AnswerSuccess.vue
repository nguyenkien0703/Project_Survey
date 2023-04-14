<template>
    <div>
        <div class="d-flex justify-content-center tk">
            <h3>
                Cảm ơn đã thực hiện khảo sát !!
            </h3>
        </div>
        <div class="d-flex justify-content-center ">
            <router-link to="/surveys-user" class="d-block text-center mt-3">
                Quay về trang danh sách
            </router-link>
        </div>
    </div>
</template>

<script>
export default {
    name: 'ConTentThanks'
}
</script>

<style>
.tk {
    margin-top: 300px;
}
</style>